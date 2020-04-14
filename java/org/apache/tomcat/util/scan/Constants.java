

package org.apache.tomcat.util.scan;

/**
 * String constants for the scan package.
 */
public final class Constants {

    public static final String Package = "org.apache.tomcat.util.scan";

    /* System properties */
    public static final String SKIP_JARS_PROPERTY =
            "tomcat.util.scan.StandardJarScanFilter.jarsToSkip";
    public static final String SCAN_JARS_PROPERTY =
            "tomcat.util.scan.StandardJarScanFilter.jarsToScan";

    /* Commons strings */
    public static final String JAR_EXT = ".jar";
    public static final String WEB_INF_LIB = "/WEB-INF/lib/";
    public static final String WEB_INF_CLASSES = "/WEB-INF/classes";
}
