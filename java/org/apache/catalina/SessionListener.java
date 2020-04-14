


package org.apache.catalina;

import java.util.EventListener;


/**
 * Interface defining a listener for significant Session generated events.
 *
 * @author Craig R. McClanahan
 */
public interface SessionListener extends EventListener {


    /**
     * Acknowledge the occurrence of the specified event.
     *
     * @param event SessionEvent that has occurred
     */
    public void sessionEvent(SessionEvent event);


}
