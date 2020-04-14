
package org.apache.jasper.security;

import org.apache.jasper.Constants;

/**
 * Util class for Security related operations.
 */

public final class SecurityUtil{

    private static final boolean packageDefinitionEnabled =
         System.getProperty("package.definition") == null ? false : true;

    /**
     * Return the <code>SecurityManager</code> only if Security is enabled AND
     * package protection mechanism is enabled.
     * @return <code>true</code> if package protection is enabled
     */
    public static boolean isPackageProtectionEnabled(){
        if (packageDefinitionEnabled && Constants.IS_SECURITY_ENABLED){
            return true;
        }
        return false;
    }


    /**
     * Filter the specified message string for characters that are sensitive
     * in HTML.  This avoids potential attacks caused by including JavaScript
     * codes in the request URL that is often reported in error messages.
     *
     * @param message The message string to be filtered
     * @return the HTML filtered message
     *
     * @deprecated This method will be removed in Tomcat 9
     */
    @Deprecated
    public static String filter(String message) {

        if (message == null)
            return null;

        char content[] = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuilder result = new StringBuilder(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
            case '<':
                result.append("&lt;");
                break;
            case '>':
                result.append("&gt;");
                break;
            case '&':
                result.append("&amp;");
                break;
            case '"':
                result.append("&quot;");
                break;
            default:
                result.append(content[i]);
            }
        }
        return result.toString();

    }
}
