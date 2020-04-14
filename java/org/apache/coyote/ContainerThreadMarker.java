
package org.apache.coyote;

/**
 * Used to mark threads that have been allocated by the container to process
 * data from an incoming connection. Application created threads are not
 * container threads and neither are threads taken from the container thread
 * pool to execute AsyncContext.start(Runnable).
 */
public class ContainerThreadMarker {

    public static boolean isContainerThread() {
        return org.apache.tomcat.util.net.ContainerThreadMarker.isContainerThread();
    }

    public static void set() {
        org.apache.tomcat.util.net.ContainerThreadMarker.set();
    }

    public static void clear() {
        org.apache.tomcat.util.net.ContainerThreadMarker.clear();
    }
}
