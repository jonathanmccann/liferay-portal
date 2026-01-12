/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.site.map.web.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTemplate;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactory;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.constants.MVCRenderConstants;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletRenderRequest;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletRenderResponse;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portlet.test.MockLiferayPortletContext;
//import com.liferay.site.navigation.site.map.web.internal.configuration.SiteNavigationSiteMapPortletInstanceConfiguration;

import jakarta.portlet.Portlet;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import jakarta.portlet.PortletPreferences;
import jakarta.portlet.RenderRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
		Layout parentLayout = LayoutTestUtil.addTypeContentPublishedLayout(
			_group, RandomTestUtil.randomString(),
			WorkflowConstants.STATUS_APPROVED);

		Layout childLayout = LayoutTestUtil.addTypeContentLayout(
			_group, parentLayout.getPlid());

		HashMap<String, String[]> preferenceMap = HashMapBuilder.put(
			"displayStyle",
			new String[] {"ddmTemplate_SITE-MAP-MULTI-COLUMN-LAYOUT-FTL"}
		).put(
			"displayStyleGroupKey", new String[] {_group.getGroupKey()}
		).put(
			"includeRootInTree", new String[] {"true"}
		).put(
			"rootLayoutUuid", new String[] {parentLayout.getUuid()}
		).build();

		String portletId = LayoutTestUtil.addPortletToLayout(
			TestPropsValues.getUserId(), parentLayout,
			"com_liferay_site_navigation_site_map_web_portlet_" +
			"SiteNavigationSiteMapPortlet", "column-1",
			preferenceMap);

		// set with portlet preferences service

		PortletPreferences portletPreferences =
			_portletPreferencesLocalService.fetchPreferences(
				_portletPreferencesFactory.getPortletPreferencesIds(
					parentLayout.getCompanyId(), parentLayout.getGroupId(), 0,
					parentLayout.getPlid(), portletId));

		// set with configuration provider

		//		Dictionary<String, Object> properties = new Hashtable<>();

		//
		//		properties.put(
		//			"displayStyle", "ddmTemplate_SITE-MAP-MULTI-COLUMN-LAYOUT-FTL");
		//		properties.put("displayStyleGroupKey", _group.getGroupKey());
		//		properties.put("includeRootInTree", "true");
		//		properties.put("rootLayoutUuid", parentLayout.getUuid());
		//
		//		_configurationProvider.savePortletInstanceConfiguration(
		//			SiteNavigationSiteMapPortletInstanceConfiguration.class, portletId,
		//			properties);

		// make request to hit jsp

		List<Layout> rootLayouts = ReflectionTestUtil.invoke(
			_getSiteNavigationSiteMapDisplayContext(parentLayout),
			"getRootLayouts", new Class<?>[0]);

		Assert.assertTrue(rootLayouts.contains(parentLayout));
		Assert.assertTrue(rootLayouts.contains(childLayout));
	}

	private MockLiferayPortletRenderRequest _getMockLiferayPortletRenderRequest(
			Layout layout)
		throws Exception {

		MockLiferayPortletRenderRequest mockLiferayPortletRenderRequest =
			new MockLiferayPortletRenderRequest();

		String path = "/view.jsp";

		mockLiferayPortletRenderRequest.setAttribute(
			MVCRenderConstants.
				PORTLET_CONTEXT_OVERRIDE_REQUEST_ATTIBUTE_NAME_PREFIX + path,
			new MockLiferayPortletContext(path));

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(
			_companyLocalService.getCompany(TestPropsValues.getCompanyId()));
		themeDisplay.setLayout(layout);
		themeDisplay.setLocale(LocaleUtil.getSiteDefault());

		User user = UserTestUtil.getAdminUser(_group.getCompanyId());

		themeDisplay.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(user));

		themeDisplay.setScopeGroupId(_group.getGroupId());
		themeDisplay.setUser(user);

		mockLiferayPortletRenderRequest.setAttribute(
			WebKeys.THEME_DISPLAY, themeDisplay);

		mockLiferayPortletRenderRequest.setParameter("mvcPath", path);

		return mockLiferayPortletRenderRequest;
	}

	private Object _getSiteNavigationSiteMapDisplayContext(Layout layout)
		throws Exception {

		MVCPortlet mvcPortlet = (MVCPortlet)_portlet;

		MockLiferayPortletRenderRequest mockLiferayPortletRenderRequest =
			_getMockLiferayPortletRenderRequest(layout);

		mockLiferayPortletRenderRequest.setParameter(
			"groupId", String.valueOf(_group.getGroupId()));

		mvcPortlet.render(
			mockLiferayPortletRenderRequest,
			new MockLiferayPortletRenderResponse());

		return mockLiferayPortletRenderRequest.getAttribute(
			"com.liferay.site.navigation.site.map.web.internal.display." +
				"context.SiteNavigationSiteMapDisplayContext");
	}

	@Inject
	private static ConfigurationProvider _configurationProvider;

	@Inject
	private CompanyLocalService _companyLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject(
		filter = "component.name=com.liferay.site.navigation.site.map.web.internal.portlet.SiteNavigationSiteMapPortlet"
	)
	private Portlet _portlet;

	@Inject
	private PortletPreferencesFactory _portletPreferencesFactory;

	@Inject
	private PortletPreferencesLocalService _portletPreferencesLocalService;

}