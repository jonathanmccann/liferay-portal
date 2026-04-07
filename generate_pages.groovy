import com.liferay.portal.kernel.model.Group
import com.liferay.portal.kernel.model.LayoutConstants
import com.liferay.portal.kernel.security.permission.PermissionChecker
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal
import com.liferay.portal.kernel.service.GroupLocalServiceUtil
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil
import com.liferay.portal.kernel.service.ServiceContext
import com.liferay.portal.kernel.service.UserLocalServiceUtil
import com.liferay.portal.kernel.util.PortalUtil

// Replace with the desired group ID (e.g. site ID) where pages should be generated
long groupId = 20127

try {
    // 1. Fetch the group and company ID
    Group group = GroupLocalServiceUtil.getGroup(groupId)
    long companyId = group.getCompanyId()

    // 2. Setup user and permission context
    def user = UserLocalServiceUtil.getDefaultUser(companyId)
    
    PermissionChecker checker = PermissionCheckerFactoryUtil.create(user)
    PermissionThreadLocal.setPermissionChecker(checker)

    // 3. Initialize ServiceContext
    ServiceContext serviceContext = new ServiceContext()
    serviceContext.setScopeGroupId(groupId)
    serviceContext.setCompanyId(companyId)
    serviceContext.setUserId(user.getUserId())

    int numPages = 50000
    println("Starting generation of ${numPages} pages for Group ID: ${groupId}...")

    // 4. Generate pages
    for (int i = 40001; i <= numPages; i++) {
        String name = "Test Page " + i
        String friendlyURL = "/test-page-" + i
        
        try {
            LayoutLocalServiceUtil.addLayout(
                null, // externalReferenceCode
                user.getUserId(),
                groupId,
                false, // privateLayout (false = public pages)
                LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, // parentLayoutId (0)
                name, // name
                null, // title
                null, // description
                LayoutConstants.TYPE_PORTLET, // type ("portlet")
                false, // hidden
                friendlyURL, // friendlyURL
                serviceContext
            )
            
            if (i % 500 == 0) {
                println("Successfully created " + i + " pages...")
            }
        } catch (Exception e) {
            println("Error creating page " + i + " (" + friendlyURL + "): " + e.getMessage())
        }
    }

    println("Finished generating ${numPages} pages.")

} catch (Exception e) {
    println("Failed to initialize script context: " + e.getMessage())
    e.printStackTrace()
}
