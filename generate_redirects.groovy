import com.liferay.portal.kernel.log.Log
import com.liferay.portal.kernel.log.LogFactoryUtil
import com.liferay.portal.kernel.model.Group
import com.liferay.portal.kernel.module.util.SystemBundleUtil
import com.liferay.portal.kernel.service.GroupLocalServiceUtil
import com.liferay.portal.kernel.service.ServiceContext
import com.liferay.portal.kernel.service.UserLocalServiceUtil
import com.liferay.redirect.service.RedirectEntryLocalServiceUtil
import org.osgi.framework.BundleContext
import org.osgi.service.cm.Configuration
import org.osgi.service.cm.ConfigurationAdmin
import java.util.ArrayList
import java.util.Arrays

// Initialize logger for execution progress
Log _log = LogFactoryUtil.getLog("GenerateRedirects")

// Replace with the desired group ID (e.g. site ID) where redirects should be generated
long groupId = 20127

try {
    _log.info("Starting redirect generation script for Group ID: " + groupId)

    Group group = GroupLocalServiceUtil.getGroup(groupId)
    long companyId = group.getCompanyId()
    def user = UserLocalServiceUtil.getDefaultUser(companyId)

    ServiceContext serviceContext = new ServiceContext()
    serviceContext.setScopeGroupId(groupId)
    serviceContext.setCompanyId(companyId)
    serviceContext.setUserId(user.getUserId())

    int numEntries = 500
    
    // 1. Generate Redirect Aliases
    _log.info("Generating " + numEntries + " Redirect Aliases...")
    int aliasCount = 0
    
    for (int i = 1; i <= numEntries; i++) {
        // sourceURL must not start with a slash when adding through RedirectEntryLocalServiceUtil
        String sourceURL = "alias-source-" + i
        String destinationURL = "/alias-destination-" + i
        
        try {
            RedirectEntryLocalServiceUtil.addRedirectEntry(
                groupId, 
                destinationURL, 
                null, // expirationDate
                true, // permanent
                sourceURL, 
                serviceContext
            )
            aliasCount++
            
            if (aliasCount % 100 == 0) {
                _log.info("Successfully created " + aliasCount + " aliases...")
            }
        } catch (Exception e) {
            _log.error("Error adding alias " + sourceURL + ": " + e.getMessage(), e)
        }
    }
    _log.info("Finished generating Redirect Aliases.")

    // 2. Generate Redirect Patterns using OSGi ConfigurationAdmin
    _log.info("Generating " + numEntries + " Redirect Patterns...")
    int patternCount = 0
    
    BundleContext bundleContext = SystemBundleUtil.getBundleContext()
    def serviceReference = bundleContext.getServiceReference(ConfigurationAdmin.class.getName())
    
    if (serviceReference != null) {
        try {
            ConfigurationAdmin configurationAdmin = (ConfigurationAdmin) bundleContext.getService(serviceReference)
            
            String factoryPid = "com.liferay.redirect.internal.configuration.RedirectPatternConfiguration.scoped"
            String filter = "(&(service.factoryPid=" + factoryPid + ")(groupId=" + groupId + "))"
            Configuration[] configurations = configurationAdmin.listConfigurations(filter)
            
            Configuration configuration = null
            if (configurations != null && configurations.length > 0) {
                configuration = configurations[0]
            } else {
                configuration = configurationAdmin.createFactoryConfiguration(factoryPid, "?")
            }
            
            def properties = configuration.getProperties()
            if (properties == null) {
                properties = new java.util.Hashtable<String, Object>()
            }
            
            // Scoped configurations require companyId and groupId as String properties
            properties.put("companyId", String.valueOf(companyId))
            properties.put("groupId", String.valueOf(groupId))
            
            // Get existing patterns so we don't overwrite them
            String[] existingPatterns = (String[]) properties.get("patternStrings")
            List<String> patternList = new ArrayList<>()
            if (existingPatterns != null) {
                patternList.addAll(Arrays.asList(existingPatterns))
            }
            
            for (int i = 1; i <= numEntries; i++) {
                try {
                    // Pattern format expected by Liferay: <regex pattern> <destination> <user-agent>
                    String patternStr = "^/pattern-source-" + i + ".* /pattern-destination-" + i + " all"
                    patternList.add(patternStr)
                    patternCount++
                    
                    if (patternCount % 100 == 0) {
                        _log.info("Successfully prepared " + patternCount + " patterns...")
                    }
                } catch (Exception e) {
                    _log.error("Error preparing pattern " + i + ": " + e.getMessage(), e)
                }
            }
            
            properties.put("patternStrings", patternList.toArray(new String[0]))
            configuration.update(properties)
            
            _log.info("Successfully saved " + patternCount + " Redirect Patterns via ConfigurationAdmin.")
        } catch (Exception e) {
            _log.error("Error updating ConfigurationAdmin for Redirect Patterns: " + e.getMessage(), e)
        } finally {
            bundleContext.ungetService(serviceReference)
        }
    } else {
        _log.error("ConfigurationAdmin service not found. Could not generate Redirect Patterns.")
    }

    // Summary Output (buffered for the UI)
    println("--- Script Execution Summary ---")
    println("Group ID: " + groupId)
    println("Redirect Aliases Created: " + aliasCount)
    println("Redirect Patterns Added: " + patternCount)
    println("--------------------------------")

} catch (Exception e) {
    _log.error("Fatal script error: " + e.getMessage(), e)
    println("Script failed to execute properly. Check server logs for details.")
}