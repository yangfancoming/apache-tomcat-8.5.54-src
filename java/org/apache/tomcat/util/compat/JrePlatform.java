
package org.apache.tomcat.util.compat;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class JrePlatform {

    private static final String OS_NAME_PROPERTY = "os.name";
    private static final String OS_NAME_WINDOWS_PREFIX = "Windows";

    static {
        /*
         * There are a few places where a) the behaviour of the Java API depends
         * on the underlying platform and b) those behavioural differences have
         * an impact on Tomcat.
         *
         * Tomcat therefore needs to be able to determine the platform it is
         * running on to account for those differences.
         *
         * In an ideal world this code would not exist.
         */

        // This check is derived from the check in Apache Commons Lang
        String osName;
        if (System.getSecurityManager() == null) {
            osName = System.getProperty(OS_NAME_PROPERTY);
        } else {
            osName = AccessController.doPrivileged(
                    new PrivilegedAction<String>() {

                    @Override
                    public String run() {
                        return System.getProperty(OS_NAME_PROPERTY);
                    }
                });
        }

        IS_WINDOWS = osName.startsWith(OS_NAME_WINDOWS_PREFIX);
    }


    public static final boolean IS_WINDOWS;
}
