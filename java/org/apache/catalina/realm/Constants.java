
package org.apache.catalina.realm;

/**
 * Manifest constants for this Java package.
 *
 * @author Craig R. McClanahan
 *
 * @deprecated Unused. Will be removed in Tomcat 9
 */
@Deprecated
public final class Constants {

    public static final String Package = "org.apache.catalina.realm";

    // Authentication methods for login configuration
    public static final String FORM_METHOD = "FORM";

    // Form based authentication constants
    public static final String FORM_ACTION = "/j_security_check";

    // User data constraints for transport guarantee
    public static final String NONE_TRANSPORT = "NONE";
    public static final String INTEGRAL_TRANSPORT = "INTEGRAL";
    public static final String CONFIDENTIAL_TRANSPORT = "CONFIDENTIAL";

}
