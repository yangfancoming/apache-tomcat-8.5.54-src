
package org.apache.tomcat.util.net;

/**
 * Used to mark threads that have been allocated by the container to process
 * data from an incoming connection. Application created threads are not
 * container threads and neither are threads taken from the container thread
 * pool to execute AsyncContext.start(Runnable).
 */
public class ContainerThreadMarker {

    private static final ThreadLocal<Boolean> marker = new ThreadLocal<>();

    public static boolean isContainerThread() {
        Boolean flag = marker.get();
        if (flag == null) {
            return false;
        } else {
            return flag.booleanValue();
        }
    }

    public static void set() {
        marker.set(Boolean.TRUE);
    }

    public static void clear() {
        marker.set(Boolean.FALSE);
    }
}
