

package org.apache.tomcat;

public interface PeriodicEventListener {
    /**
     * Execute a periodic task, such as reloading, etc.
     */
    public void periodicEvent();
}
