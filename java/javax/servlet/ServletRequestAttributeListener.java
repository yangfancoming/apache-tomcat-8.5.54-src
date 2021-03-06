
package javax.servlet;

import java.util.EventListener;

/**
 * A ServletRequestAttributeListener can be implemented by the
 * developer interested in being notified of request attribute
 * changes. Notifications will be generated while the request
 * is within the scope of the web application in which the listener
 * is registered. A request is defined as coming into scope when
 * it is about to enter the first servlet or filter in each web
 * application, as going out of scope when it exits the last servlet
 * or the first filter in the chain.
 *
 * @since Servlet 2.4
 */
public interface ServletRequestAttributeListener extends EventListener {
    /**
     * Notification that a new attribute was added to the
     * servlet request. Called after the attribute is added.
     * @param srae Information about the new request attribute
     */
    public void attributeAdded(ServletRequestAttributeEvent srae);

    /**
     * Notification that an existing attribute has been removed from the
     * servlet request. Called after the attribute is removed.
     * @param srae Information about the removed request attribute
     */
    public void attributeRemoved(ServletRequestAttributeEvent srae);

    /**
     * Notification that an attribute was replaced on the
     * servlet request. Called after the attribute is replaced.
     * @param srae Information about the replaced request attribute
     */
    public void attributeReplaced(ServletRequestAttributeEvent srae);
}

