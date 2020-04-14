
package org.apache.tomcat;

public interface JarScanFilter {

    /**
     *
     * @param jarScanType   The type of JAR scan currently being performed
     * @param jarName       The name of the JAR file (without any path
     *                          information) to be checked to see if it should
     *                          be included in the results or not
     * @return <code>true</code> if the JAR should be returned in the results,
     *             <code>false</code> if it should be excluded
     */
    boolean check(JarScanType jarScanType, String jarName);
}
