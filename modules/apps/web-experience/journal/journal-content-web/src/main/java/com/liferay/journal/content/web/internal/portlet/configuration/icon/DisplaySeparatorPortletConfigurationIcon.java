package com.liferay.journal.content.web.internal.portlet.configuration.icon;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.constants.JournalContentPortletKeys;
import com.liferay.journal.content.web.internal.display.context.JournalContentDisplayContext;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.permission.JournalArticlePermission;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.ResourceBundle;

import javax.portlet.PortletRequest;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + JournalContentPortletKeys.JOURNAL_CONTENT,
		"path=-"
	},
	service = PortletConfigurationIcon.class
)
public class DisplaySeparatorPortletConfigurationIcon
	extends BaseJournalArticlePortletConfigurationIcon {

	@Override
	public String getJspPath() {
		return "/configuration/icon/web_content_display_separator.jsp";
	}

	public String getMessage(PortletRequest portletRequest) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", getLocale(portletRequest), getClass());

		return LanguageUtil.get(resourceBundle, "web-content");
	}

	public double getWeight() {
		return 0.8;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		JournalContentDisplayContext journalContentDisplayContext =
			getJournalContentDisplayContext(
				portletRequest, null, _ddmStructureClassNameId);

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();
		JournalArticle article = journalContentDisplayContext.getArticle();

		if(journalContentDisplayContext.isShowEditArticleIcon() ||
		   journalContentDisplayContext.isShowEditTemplateIcon() ||
		   JournalArticlePermission.contains(
		   		permissionChecker, article, ActionKeys.PERMISSIONS) ){
			return true;
		}

		return false;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.journal.content.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Reference(unbind = "-")
	protected void setPortal(Portal portal) {
		_ddmStructureClassNameId = portal.getClassNameId(DDMStructure.class);
	}

	private long _ddmStructureClassNameId;

}