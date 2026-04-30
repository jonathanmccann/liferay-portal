---
allowed-tools: [Agent, Bash, Read, Write, TaskCreate, TaskUpdate, TaskList, mcp__liferay__call-http-endpoint, mcp__liferay__get-openapi, mcp__liferay__get-openapis]
argument-hint: "[<ResourceImpl.java path> | <module name> | empty to ask]"
description: Run thorough exploratory black-box testing against a Liferay headless ResourceImpl to surface bugs, validation gaps, permission asymmetries, and behavioral inconsistencies. Produces an HTML findings report. Use when the user asks to exploratory-test, probe, or audit a headless API resource.
name: explore-headless
---

# Exploratory Testing for Liferay Headless ResourceImpls

Surface real bugs in a `*ResourceImpl.java` by hitting the live API with payloads the integration tests don't cover. The endpoint may be called by direct API clients, scripted integrations, internal Liferay tooling, or UI consumers — frame each finding around who is affected and under what conditions.

## Inputs

`$ARGUMENTS` is one of:

- A path to a `*ResourceImpl.java` file. Resolve openapi yaml and integration test from sibling/`*-test` paths.
- A headless module name (e.g. `headless-admin-site`). List `*ResourceImpl.java` under that module and ask the user to pick one.
- Empty. Ask the user which resource to test.

If you cannot resolve all three of impl / openapi / integration test, ask the user before proceeding — they are all needed for high-quality recon.

## Phase 1 — Pre-flight

Before any probing:

1. Confirm the Liferay MCP is reachable: call `mcp__liferay__get-openapis`. If the call fails, stop and tell the user:
   > The Liferay MCP server is not reachable. Configure it before continuing — see Claude Code docs on MCP setup. The server is expected at `http://localhost:8080/o/mcp` (or wherever the bundle is running) with basic-auth credentials for an admin user.
2. Pick the test artifact prefix. Default to `EXPLORE-<resource-short-name>-` (e.g. `EXPLORE-site-`, `EXPLORE-user-`, `EXPLORE-vocab-`). The user can override with a custom prefix (handy for parallel runs against the same bundle, or to tie to a ticket if they want), but never *require* a ticket. Every test artifact's external reference code starts with this prefix.

## Phase 2 — Recon (parallel sub-agents)

Launch three `Explore` sub-agents in parallel — they fit well into a single message and keep the main context lean. Each is read-only.

1. **Map the API surface** — read the `*ResourceImpl.java` in full. Report every public/override method, its HTTP verb + path, parameters, validation, permission checks, mutation scope, feature flags, threadlocal swaps, and which methods route through `_xxxLocalService` (bypasses permission AOP) vs `_xxxService` (perm-checked).
2. **Extract the OpenAPI contract** — pull the schema for the resource's DTO and every operation (paths, params, request/response bodies, enums, readOnly/writeOnly flags, declared error codes). Note loose types (`object` with arbitrary `additionalProperties`), `deprecated` markers, and required-field claims.
3. **Inventory the integration test** — every `@Test` method, what edge cases it covers, what helper methods exist, and the explicit gaps relative to the categories in Phase 4. Don't repeat coverage; identify it so probes go where tests don't.

Wait for all three reports before planning probes.

## Phase 3 — Plan probes

Use `TaskCreate` to draft a task list seeded from the categories below. Skip categories that don't apply to this resource (e.g. parent/cycle if there's no parent ERC field).

## Phase 4 — Execute probes

Work category by category. For each, mark the task `in_progress`, run a focused batch of probes (parallel where independent), capture findings inline, then mark complete. Test artifacts use ERC prefix `<chosen-prefix><short-tag>` (e.g. `EXPLORE-site-baseline`, `EXPLORE-site-self-parent`, `EXPLORE-site-emoji`).

### Categories

**Happy-path CRUD** — POST/GET/PUT/DELETE roundtrip a baseline resource. Confirm shape, defaults, and that documented OpenAPI defaults match observed.

**Round-trip integrity (GET → POST/PUT → GET → diff)** — GET an existing resource; strip read-only fields; POST or PUT-to-fresh-ERC to clone it; GET the clone; diff against the original. The diff should be empty (or only fields the server is expected to derive). Catches fields that don't round-trip cleanly — e.g., locale-fanned-out i18n maps where the destination has different installed locales, fields whose serialized form changes between versions, or server-side defaults that overwrite supplied values.

**ERC edge cases** (high yield)
- empty / null ERC — does the server auto-generate a UUID, or 400?
- very long ERC (3000+ chars) — does it 400 or leak `Data truncation`?
- emoji / 4-byte UTF-8 — does it 400 or leak MySQL collation mismatch?
- `/` in ERC — POST often accepts; GET-by-ERC fails because Tomcat blocks `%2F`. Existing decision: customers double-encode as `%252F` (LPD-28316). Note as documented behavior.
- `\n` in ERC — POST often accepts; GET-by-ERC fails after URL normalization
- `..`, spaces (test both unencoded and `%20`), control chars, RTL, CJK
- ERC immutability across PUT — does sending a different ERC in the body 400?

**`readOnly` field tampering** — the OpenAPI marks several fields `readOnly: true` (typically `id`, `dateCreated`, `dateModified`, `creator`). Probe each:
- POST/PUT a body with `id: 999999` — server should ignore and assign its own; verify the response shows the server-assigned ID, not 999999
- POST/PUT with `dateCreated`/`dateModified` set to a past or future timestamp — server should overwrite
- POST/PUT with `creator.id: <other-user-id>` — ownership-spoofing risk if accepted
- Silent acceptance of any of these is a bug class even when the field isn't otherwise sensitive

**Parent / hierarchy** (if applicable)
- self-parent (A.parent = A) — POST often silently drops, PUT often 500s; flag the asymmetry
- two-cycle (A.parent = B, B.parent = A) — should 4xx, often 500
- delete a parent that has children — should 409, often 500
- parent ERC pointing at a resource of a different type that shares the same backing model — type-leakage check

**System / built-in resource protection** — identify any portal-required, instance-default, or otherwise non-deletable instances of this resource (the impl usually has explicit checks; the OpenAPI may declare a 405 path). Try every mutating verb against them. Verify each is rejected with the documented status, not 500.

**Cross-type leakage** — does the `getByErc` endpoint return resources of a different type that share the same backing model? Does the LIST endpoint filter consistently with the GET-by-ERC endpoint? Many Liferay backing tables (e.g. `Group`) are reused across multiple headless resources, and a leak shows up as one resource type returning records that should belong to another.

**PUT-by-ERC semantics**
- PUT to a non-existent ERC — does it create (upsert) or 404? Document.
- PUT-after-DELETE re-PUT — fresh resource or 4xx?
- PUT with body field omitted — preserved or reset to default? PUT-by-ERC is typically full-replacement in REST, which is defensible, but flag asymmetric handling (some fields preserve, others clobber) since that is surprising regardless of caller.

**PATCH semantics** (if a PATCH endpoint exists)
- Confirm PATCH is actually partial-update, not silently aliased to PUT-replace
- Apply the same permission seam check as PUT: does PATCH route through `_xxxLocalService.update` (bypassing permission AOP) or `_xxxService.update`?
- Send a PATCH that omits all but one field — the response should reflect only that field changed; the rest preserved

**Permissions** — create a non-admin user via `headless-admin-user` with only the default `User` role. Hit each verb on each path. The seam to find: methods that route through `_xxxLocalService.update` skip permission AOP. Compare verb-by-verb to confirm authz is enforced symmetrically.

**`actions` map fidelity** (if responses include an `actions` map) — many headless responses include a HATEOAS `actions` map describing operations available to the caller. As a non-admin user, GET a resource and verify the map omits operations that user can't actually perform. If the map advertises `delete` or `update` but the actual call returns 403, that is a misleading API contract — clients building UIs from `actions` will show buttons that don't work.

**Status code leakage** — every 500 that should have been a 4xx. Backend exceptions to look for in `<bundles>/logs/liferay.<date>.log` after a 500: `*Exception` subclasses with no registered `ExceptionMapper`. The fix is usually a new mapper class in `<module>/src/main/java/com/liferay/.../jaxrs/exception/mapper/` modeled on existing `Duplicate*ExceptionMapper`.

**Sort / filter / search / aggregation parameter fuzzing** (if the LIST endpoint accepts these query params)
- Malformed filter expression (`?filter=foo eq`, `?filter=&&`)
- Filter on an unknown field name
- Filter on a permission-protected field — a result set of zero may leak that the value didn't match, while a 400 leaks that the field exists at all
- Sort by an unknown field
- Aggregation on a field that doesn't make sense to aggregate
- These are common 5xx-leak vectors and rarely covered by integration tests

**Pagination count vs items** — as a non-admin user, GET the LIST endpoint. Does `totalCount` match the visibility-filtered `items.length`? Asymmetric filtering (items filtered, count not) is a real bug pattern.

**Locale / i18n** (if the DTO has any `*_i18n` map fields)
- `*_i18n` map with bogus or uninstalled locale codes (`xx-YY`, `klingon`, numeric strings) — silently dropped or rejected?
- `*_i18n` map with no entry for the default portal locale — what's the error message? Does it correctly identify the missing field, or surface an internal-sounding message?
- `defaultLanguageId` (or analogous default-locale field) set to a locale not in the resource's `locales` list — should 400, often 500 with a `LocaleException`

**Enum / numeric** — wrong-case enum values (e.g. uppercase variant of a lowercase-defined enum), unknown enum values, negative or out-of-range numeric fields. Look for silent normalization (input ignored, default substituted) and report it — clients that send invalid values deserve a 400, not a silently-corrected success.

**Workflow / status / draft / publish** (if the DTO has a `status` enum or there are `*Draft` / `*Publish` / `*Subscribe` endpoints)
- Direct PUT of `status` field in the body — does it bypass any state machine that the dedicated `/publish` or `/draft` endpoint enforces?
- Re-publish an already-published resource — idempotent or 409?
- Illegal transitions (e.g. Approved → Draft) via direct PUT of `status`
- Draft and published copies of the same resource — does GET return the right one based on caller / parameters?

**`customFields`** (if the DTO has a `customFields` array)
- POST/PUT with a `customFields` entry whose `name` doesn't match any defined custom field on the resource
- POST/PUT with malformed `customFields` value types (string where number expected, etc.)
- String injection (XSS, SQL-shaped) in custom field string values — these are user-defined and validation tends to be looser than for first-class fields

**Unexpected inputs** — values that satisfy the type but violate the spirit of the field:
- Markup/XSS payloads in any free-text field (often stored verbatim and escaped only on render — flag where third-party consumers without their own escaping might be at risk)
- Reserved or system-reserved values in URL/path/key-shaped fields
- Fields the OpenAPI describes as accepting an external syntax (script fragments, regex, URLs, CSS selectors, JSON blobs) — pass values that match the type but break the documented intent

## Phase 5 — Cleanup

Mandatory. Even findings reports lose value if the bundle is left littered.

1. Use the LIST endpoint with `pageSize=200` and filter for the ERC prefix. For each remaining test artifact, DELETE by ERC.
2. For ERCs that can't round-trip through the URL (slash, newline) — use the integer-id endpoint (most resources expose `/{id}` alongside `/by-external-reference-code/{erc}`) or any sibling deprecated `/by-id/` endpoint to delete by primary key.
3. Delete the non-admin test user and any ancillary resources created during probing (parent resources, related sub-resources, scoping containers).
4. Verify with one more LIST. Only system / pre-existing resources should remain.

## Phase 6 — Report

Write findings to `~/explore-<resource-short-name>-findings.html` (or `~/explore-<resource-short-name>-findings-<YYYYMMDD>.html` if a previous report exists at that path and the user does not want it overwritten). Use the template in `references/report-template.html` from this skill directory. Each finding has:

- Severity badge (Critical / High / Medium / Low) — use the colors in the template
- One-line summary
- Reproducer (curl-ready)
- Expected vs actual
- Root cause (file:line where investigated)
- Client impact — who is affected (UI consumer, scripted client, admin tool, end user) and under what conditions; note any known mitigations or workarounds
- Suggested fix

End the report with:
- A "Coverage observations" section listing categories the existing integration test misses
- A "Suggested fix priority" ordered list

After writing the file, summarize the top 3–5 findings inline in chat and link to the file.

## Severity guide

Use these heuristics:

- **Critical** — permission bypass on write paths; stored XSS combined with permission bypass; data corruption; loss of data with no recovery path
- **High** — cross-resource type leakage; 5xx leaks for documented domain rules; asymmetric authz across verbs on the same resource; bypass of system-resource protections
- **Medium** — counting / pagination mismatches; silent data drops on locale or character-set boundaries; doc / code defaults disagree; PUT-after-DELETE behavior surprises
- **Low** — error message clarity nits; OpenAPI `readOnly` / description doc bugs; enum case-sensitivity surprises

When the user dismisses a finding (e.g. "that's how PUT is supposed to work"), respect it and move on — don't argue. Note the disposition in the report.

## Cleanup discipline (non-negotiable)

- Every test artifact's external reference code starts with the chosen prefix from Phase 1 (default `EXPLORE-<resource-short-name>-`).
- Track created resource IDs in case ERC-based delete fails (slash, newline, etc.).
- The cleanup phase runs even if you abort early. If interrupted, run the cleanup phase before exiting.
- Never modify or delete artifacts that don't carry the prefix unless they were created by this run (e.g. a non-admin test user whose ERC also starts with the prefix). System / instance-default resources are read-only targets — probe but never mutate.

## Reporting cadence

Default to short interim findings in chat as you uncover each one — exploratory testing rewards short feedback loops, and the user often wants to redirect mid-stream. The HTML report is the durable output.
