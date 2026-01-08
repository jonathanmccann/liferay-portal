/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.site.map.web.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletRenderResponse;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.site.navigation.site.map.web.internal.configuration.SiteNavigationSiteMapPortletInstanceConfiguration;
import com.liferay.site.navigation.site.map.web.internal.constants.SiteNavigationSitemapPortletKeys;
import com.liferay.site.navigation.site.map.web.internal.display.context.SiteNavigationSiteMapDisplayContext;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Cheryl Tang
 */
@RunWith(Arquillian.class)
public class SiteNavigationSiteMapTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testIsMultiColumnLayoutRootIncluded() throws Exception {
		Layout parentLayout = LayoutTestUtil.addTypeContentLayout(_group);

		Layout childLayout = LayoutTestUtil.addTypeContentLayout(
			_group, parentLayout.getPlid());

		String portletId = LayoutTestUtil.addPortletToLayout(
			parentLayout,
			SiteNavigationSitemapPortletKeys.SITE_NAVIGATION_SITEMAP,
			HashMapBuilder.put(
				"displayStyle",
				new String[] {"ddmTemplate_SITE-MAP-MULTI-COLUMN-LAYOUT-FTL"}
			).put(
				"displayStyleGroupKey", new String[] {_group.getGroupKey()}
			).put(
				"includeRootInTree", new String[] {"true"}
			).put(
				"rootLayoutUuid", new String[] {parentLayout.getUuid()}
			).build()); // preferenceMap doesn't save to SiteNavigationSiteMapPortletInstanceConfiguration

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put(
			"displayStyle", "ddmTemplate_SITE-MAP-MULTI-COLUMN-LAYOUT-FTL");
		properties.put("displayStyleGroupKey", _group.getGroupKey());
		properties.put("includeRootInTree", "true");
		properties.put("rootLayoutUuid", parentLayout.getUuid());

		_configurationProvider.savePortletInstanceConfiguration(
			SiteNavigationSiteMapPortletInstanceConfiguration.class, portletId,
			properties);

		HttpServletRequest httpServletRequest = _getHttpServletRequest(
			parentLayout);

		SiteNavigationSiteMapDisplayContext
			siteNavigationSiteMapDisplayContext =
				new SiteNavigationSiteMapDisplayContext(
					httpServletRequest, new MockLiferayPortletRenderResponse());

		List<Layout> rootLayouts =
			siteNavigationSiteMapDisplayContext.getRootLayouts();

		Assert.assertTrue(rootLayouts.contains(parentLayout));
		Assert.assertTrue(rootLayouts.contains(childLayout));
	}

	private HttpServletRequest _getHttpServletRequest(Layout layout)
		throws Exception {

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setAttribute(
			JavaConstants.JAKARTA_PORTLET_RESPONSE,
			new MockLiferayPortletRenderResponse());

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(
			_companyLocalService.fetchCompany(TestPropsValues.getCompanyId()));
		themeDisplay.setLayout(layout);

		LayoutSet layoutSet = _group.getPublicLayoutSet();

		themeDisplay.setLookAndFeel(
			layoutSet.getTheme(), layoutSet.getColorScheme());

		themeDisplay.setRealUser(TestPropsValues.getUser());
		themeDisplay.setRequest(mockHttpServletRequest);
		themeDisplay.setResponse(new MockHttpServletResponse());
		themeDisplay.setScopeGroupId(_group.getGroupId());
		themeDisplay.setUser(TestPropsValues.getUser());

		mockHttpServletRequest.setAttribute(
			WebKeys.THEME_DISPLAY, themeDisplay);

		return mockHttpServletRequest;
	}

	@Inject
	private static ConfigurationProvider _configurationProvider;

	@Inject
	private CompanyLocalService _companyLocalService;

	@DeleteAfterTestRun
	private Group _group;

}