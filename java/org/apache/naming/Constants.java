
package org.apache.naming;


/**
 * Static constants for this package.
 *
 * @deprecated Unused. Will be removed in Tomcat 9.
 */
@Deprecated
public final class Constants {

    public static final String Package = "org.apache.naming";

    /**
     * Has security been turned on?
     */
    public static final boolean IS_SECURITY_ENABLED =
        (System.getSecurityManager() != null);
}
