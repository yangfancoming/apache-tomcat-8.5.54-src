
package org.apache.catalina.util;

/**
 * General purpose request parsing and encoding utility methods.
 *
 * @author Craig R. McClanahan
 * @author Tim Tye
 */
public final class RequestUtil {

    /**
     * Filter the specified message string for characters that are sensitive
     * in HTML.  This avoids potential attacks caused by including JavaScript
     * codes in the request URL that is often reported in error messages.
     *
     * @param message The message string to be filtered
     *
     * @return the filtered message
     *
     * @deprecated This method will be removed in Tomcat 9
     */
    @Deprecated
    public static String filter(String message) {

        if (message == null) {
            return null;
        }

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
