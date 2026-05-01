/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.internal.struts.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.test.util.GroupConfigurationTemporarySwapper;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.VirtualHostLocalService;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.PropsValues;
import com.liferay.portal.kernel.util.TreeMapBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import jakarta.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Jonathan McCann
 */
@RunWith(Arquillian.class)
public class LLMManifestStrutsActionTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		LayoutSet layoutSet = _group.getPublicLayoutSet();

		_virtualHostname =
			"llm-manifest-test-" + RandomTestUtil.randomString(8);

		_virtualHostLocalService.updateVirtualHosts(
			_group.getCompanyId(), layoutSet.getLayoutSetId(),
			TreeMapBuilder.put(
				_virtualHostname, StringPool.BLANK
			).build());
	}

	@Test
	public void testExecuteWithBlankMarkdown() throws Exception {
		try (GroupConfigurationTemporarySwapper
				groupConfigurationTemporarySwapper =
					new GroupConfigurationTemporarySwapper(
						_group.getGroupId(), _PID,
						HashMapDictionaryBuilder.<String, Object>put(
							"enabled", true
						).put(
							"markdown", ""
						).build())) {

			MockHttpServletResponse mockHttpServletResponse = _executeWithHost(
				_virtualHostname);

			Assert.assertEquals(
				HttpServletResponse.SC_NOT_FOUND,
				mockHttpServletResponse.getStatus());
		}
	}

	@Test
	public void testExecuteWithCompanyVirtualHost() throws Exception {
		Assume.assumeTrue(
			Validator.isNotNull(PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME));

		Company company = _companyLocalService.getCompany(
			TestPropsValues.getCompanyId());

		Group defaultGroup = _groupLocalService.getGroup(
			company.getCompanyId(),
			PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME);

		String markdown = "# Default Site\n";

		try (GroupConfigurationTemporarySwapper
				groupConfigurationTemporarySwapper =
					new GroupConfigurationTemporarySwapper(
						defaultGroup.getGroupId(), _PID,
						HashMapDictionaryBuilder.<String, Object>put(
							"enabled", true
						).put(
							"markdown", markdown
						).build())) {

			MockHttpServletResponse mockHttpServletResponse = _executeWithHost(
				company.getVirtualHostname());

			Assert.assertEquals(
				HttpServletResponse.SC_OK, mockHttpServletResponse.getStatus());
			Assert.assertEquals(
				markdown, mockHttpServletResponse.getContentAsString());
		}
	}

	@Test
	public void testExecuteWithDisabledConfiguration() throws Exception {
		MockHttpServletResponse mockHttpServletResponse = _executeWithHost(
			_virtualHostname);

		Assert.assertEquals(
			HttpServletResponse.SC_NOT_FOUND,
			mockHttpServletResponse.getStatus());
	}

	@Test
	public void testExecuteWithMarkdown() throws Exception {
		String markdown = "# Test\n\n> Summary\n\n## Docs\n\n- [A](/a)\n";

		try (GroupConfigurationTemporarySwapper
				groupConfigurationTemporarySwapper =
					new GroupConfigurationTemporarySwapper(
						_group.getGroupId(), _PID,
						HashMapDictionaryBuilder.<String, Object>put(
							"enabled", true
						).put(
							"markdown", markdown
						).build())) {

			MockHttpServletResponse mockHttpServletResponse = _executeWithHost(
				_virtualHostname);

			Assert.assertEquals(
				HttpServletResponse.SC_OK, mockHttpServletResponse.getStatus());
			Assert.assertEquals(
				ContentTypes.TEXT_PLAIN_UTF8,
				mockHttpServletResponse.getContentType());
			Assert.assertEquals(
				markdown, mockHttpServletResponse.getContentAsString());
		}
	}

	@Test
	public void testExecuteWithUnknownHost() throws Exception {
		try (GroupConfigurationTemporarySwapper
				groupConfigurationTemporarySwapper =
					new GroupConfigurationTemporarySwapper(
						_group.getGroupId(), _PID,
						HashMapDictionaryBuilder.<String, Object>put(
							"enabled", true
						).put(
							"markdown", "# Test"
						).build())) {

			MockHttpServletResponse mockHttpServletResponse = _executeWithHost(
				RandomTestUtil.randomString());

			Assert.assertEquals(
				HttpServletResponse.SC_NOT_FOUND,
				mockHttpServletResponse.getStatus());
		}
	}

	private MockHttpServletResponse _executeWithHost(String host)
		throws Exception {

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.addHeader("Host", host);

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		_llmManifestStrutsAction.execute(
			mockHttpServletRequest, mockHttpServletResponse);

		return mockHttpServletResponse;
	}

	private static final String _PID =
		"com.liferay.site.internal.configuration.LLMManifestGroupConfiguration";

	@Inject
	private CompanyLocalService _companyLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private GroupLocalService _groupLocalService;

	@Inject(
		filter = "component.name=com.liferay.site.internal.struts.LLMManifestStrutsAction"
	)
	private StrutsAction _llmManifestStrutsAction;

	@Inject
	private VirtualHostLocalService _virtualHostLocalService;

	private String _virtualHostname;

}