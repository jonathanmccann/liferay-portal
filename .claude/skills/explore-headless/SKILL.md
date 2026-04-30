---
allowed-tools: [Agent, Bash, Read, Write, TaskCreate, TaskUpdate, TaskList, mcp__liferay__call-http-endpoint, mcp__liferay__get-openapi, mcp__liferay__get-openapis]
argument-hint: "[<ResourceImpl.java path> | <module name> | empty to ask]"
description: Run thorough exploratory black-box testing against a Liferay headless ResourceImpl to surface bugs, validation gaps, permission asymmetries, and behavioral inconsistencies. Produces an HTML findings report. Use when the user asks to exploratory-test, probe, or audit a headless API resource.
name: explore-headless
---

# Exploratory Testing for Liferay Headless ResourceImpls

Surface real bugs in a `*ResourceImpl.java` by hitting the live API with payloads the integration tests do not cover. Frame each finding around who is affected and under what conditions.

## Token and Time Budget

Three rules to stay within budget:

1. Recon agents return short, structured reports — never exhaustive dumps. Each Explore prompt below caps its output explicitly.

1. Probes run in parallel batches. A single message dispatches 8-15 `mcp__liferay__call-http-endpoint` calls at once. Do not narrate between calls within a batch.

1. The findings report is one `Write` call at the end. Do not echo findings inline as you go — the durable report is the output.

If a turn looks like it will exceed the output limit, split it. Symptoms: dispatching three or more `Agent` calls in one message, or batching probes alongside writing the HTML report.

## Inputs

`$ARGUMENTS` is one of:

- A path to a `*ResourceImpl.java` file. Resolve OpenAPI YAML and integration test from sibling/`*-test` paths.
- A headless module name (e.g. `headless-admin-site`). List `*ResourceImpl.java` under that module and ask the user to pick one.
- Empty. Ask the user which resource to test.

If you cannot resolve all three of impl, OpenAPI, and integration test, ask the user before proceeding.

## Phase 1 — Pre-Flight

1. Confirm the Liferay MCP is reachable: call `mcp__liferay__get-openapis`. If the call fails, stop and tell the user:

	> The Liferay MCP server is not reachable. Configure it before continuing — see the Claude Code docs on MCP setup. The server is expected at `http://localhost:8080/o/mcp` (or wherever the bundle is running) with basic-auth credentials for an admin user.

1. Pick the test artifact prefix. Default to `EXPLORE-<resource-short-name>-`. Every test artifact's external reference code starts with this prefix.

## Phase 2 — Recon (One Message, Three Parallel Agents)

Dispatch three `Explore` agents in a single message. Each prompt must cap output as shown — recon is a checklist, not a report.

1. **API surface** — Read the impl in full. Bullet list under 400 words: each method's HTTP verb plus path, service routing (`_xxxLocalService` versus `_xxxService`), explicit permission checks, ServiceContext or threadlocal tweaks, feature flags. Flag system-resource guards and i18n, status, and parent fields. List what is there. Do not describe what the code does.

1. **OpenAPI contract** — Pull the schema for the resource's DTO and operations. Bullet list under 400 words: each operation's verb, path, request/response, declared error codes. Flag readOnly, writeOnly, and deprecated fields, loose `additionalProperties` types, undefined enums, and declared defaults. Note which fields are i18n maps, status enums, or parent pointers.

1. **Integration test inventory** — List every `@Test` in the hand-written and generated test files (one bullet per test, name plus one-line summary). Then a Gaps section: for each category in the Phase 3 checklist, state covered, partial, or missing.

End each prompt with: "Report under 400 words. Bullet lists, not prose."

Wait for all three. Read the reports — do not re-quote them. Move to Phase 3.

## Phase 3 — Probe (Batched)

For each category below that applies, dispatch all probes in one parallel batch. Do not write a chat update per probe. Write a chat update only when a finding warrants flagging mid-run, or when a category is complete.

Test artifact ERCs use `<prefix><short-tag>` (e.g. `EXPLORE-site-baseline`, `EXPLORE-site-self-parent`). Track every created ERC for cleanup, including auto-generated UUIDs from null/empty ERC probes.

### Categories Checklist

For each, run the listed probes if applicable:

**Happy-Path CRUD plus Round-Trip** — POST → GET → PUT → GET → DELETE on a baseline. Diff fields. Flag silent server overwrites.

**ERC Edge Cases** — empty, null, and missing (auto-gen UUID?); 3000-char; `/`; `\n`; `..`; leading and trailing space; RTL; CJK. ERC immutability across PUT.

**readOnly Tampering** — POST or PUT with `id`, `dateCreated`, `dateModified`, and `creator.id` set. Server should ignore — silent acceptance is a bug class.

**PUT-by-ERC Semantics** — PUT to a nonexistent ERC (upsert or 404?), PUT-after-DELETE re-PUT, PUT with a body field omitted (preserved or reset?).

**PATCH Semantics** (if exists) — confirm partial update, not aliased to PUT replace. Same permission-seam check as PUT.

**Hierarchy** (if applicable) — self-parent, two-cycle (A→B, B→A), delete-with-children, parent ERC pointing at a different resource type that shares the same backing model.

**System-Resource Protection** — find portal-required or instance-default instances. Try every mutating verb. Documented status, not 500.

**Cross-Type Leakage** — `getByErc` returning a different resource type that shares the same backing model. LIST consistency with GET-by-ERC.

**Permissions (Non-Admin User)** — create a User-only test user via `headless-admin-user`. Hit each verb on each path. Look for `_xxxLocalService.update` paths bypassing permission AOP.

**Actions Map Fidelity** — as non-admin, GET a resource. The map should omit operations that 403 on call.

**Status Code Leakage** — every 5xx that should be 4xx. Tail `<BUNDLES>/logs/liferay.<YYYY-MM-DD>.log` after each 500 to catch unmapped exceptions.

**Sort, Filter, Search Fuzzing** (if LIST accepts these) — malformed filter, unknown filter field, sort by unknown field, filter on permission-protected field.

**Pagination Count Versus Items** — non-admin LIST: does `totalCount` match `items.length`? Asymmetric filtering is a real bug pattern.

**Locale and i18n** (if `*_i18n` map fields) — bogus locale (`xx-YY`), missing default-locale entry, `defaultLanguageId` off-list (often 500 with `LocaleException`).

**Enum and Numeric** — wrong-case enum, unknown enum, negative or out-of-range numeric. Silent normalization is a bug.

**Workflow and Status** (if a status enum or `*Draft`, `*Publish`, `*Subscribe` endpoint exists) — direct PUT of `status` (state-machine bypass), republish (idempotent or 409?), illegal transitions.

**customFields** (if the DTO has `customFields`) — unknown field name, wrong value type, XSS- or SQL-shaped strings.

**Free-Text and Unexpected Inputs** — markup or XSS in `name` and `description`, reserved values in URL or path-shaped fields, syntactically valid but semantically broken external syntax (script fragments, regex, URLs).

### Known Quirks (Do Not Re-File)

- 4-byte UTF-8 (emoji) in ERC: returns 500 from a MySQL `utf8mb3` versus `utf8mb4` collation mismatch. Do not probe.
- `/` in ERC: customers double-encode as `%252F` (LPD-28316). Document, do not flag.
- `\n` in ERC: GET-by-ERC fails after URL normalization. Document.

## Phase 4 — Cleanup (Mandatory)

Even an aborted run must clean up. In one batch:

1. LIST with `pageSize=200` and filter for the ERC prefix. DELETE each match by ERC.

1. For ERCs that cannot round-trip through the URL (slash, newline), delete by integer ID via the `/{id}` endpoint when available.

1. Delete the non-admin test user and any ancillary resources (parent resources, scoping containers).

1. One LIST to verify clean state. Only system or pre-existing resources should remain.

If interrupted before this phase, run it before exiting. Never delete artifacts that do not carry the prefix.

## Phase 5 — Report (One Write Call)

Write findings to `~/explore-<resource-short-name>-findings.html` (or `~/explore-<resource-short-name>-findings-<YYYYMMDD>.html` if a previous report exists). Use `references/report-template.html`. Each finding has:

- Severity badge (Critical, High, Medium, Low — colors in the template)
- One-line summary
- Reproducer (curl-ready)
- Expected versus actual
- Root cause (file:line where investigated)
- Client impact — who is affected (UI consumer, scripted client, admin tool, end user) and under what conditions; note any known mitigations
- Suggested fix

End the report with:

- A Coverage Observations section listing categories the existing integration test misses
- A Suggested Fix Priority ordered list

After writing the file, summarize the top 3-5 findings inline (one line each) and link to the file. Do not paste finding bodies into chat.

## Severity Guide

- **Critical** — permission bypass on write paths; stored XSS combined with permission bypass; data corruption; loss of data with no recovery path
- **High** — cross-resource type leakage; 5xx leaks for documented domain rules; asymmetric authz across verbs on the same resource; bypass of system-resource protections
- **Medium** — counting or pagination mismatches; silent data drops on locale or character-set boundaries; documented and code defaults disagree; PUT-after-DELETE behavior surprises
- **Low** — error message clarity nits; OpenAPI `readOnly` or description doc bugs; enum case-sensitivity surprises

When the user dismisses a finding, respect it and move on. Note the disposition in the report.