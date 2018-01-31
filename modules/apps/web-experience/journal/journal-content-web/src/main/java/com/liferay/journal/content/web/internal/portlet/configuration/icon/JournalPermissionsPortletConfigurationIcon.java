package com.liferay.journal.content.web.internal.portlet.configuration.icon;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.constants.JournalContentPortletKeys;
import com.liferay.journal.content.web.internal.display.context.JournalContentDisplayContext;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.permission.JournalArticlePermission;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.Portal;

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
public class JournalPermissionsPortletConfigurationIcon
	extends BaseJournalArticlePortletConfigurationIcon {

	@Override
	public String getJspPath() {
		return "/configuration/icon/edit_permissions.jsp";
	}

	@Override
	public double getWeight() {
		return 0.1;
	}

	@Override
	public boolean isShow(PortletRequest portletRequest) {
		JournalContentDisplayContext journalContentDisplayContext =
			getJournalContentDisplayContext(
				portletRequest, null, _ddmStructureClassNameId);

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();
		JournalArticle article = journalContentDisplayContext.getArticle();

		if (JournalArticlePermission.contains(
				permissionChecker, article, ActionKeys.PERMISSIONS)) {

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