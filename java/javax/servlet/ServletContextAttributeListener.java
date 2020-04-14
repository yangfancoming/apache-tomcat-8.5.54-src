
package javax.servlet;

import java.util.EventListener;

/**
 * Implementations of this interface receive notifications of changes to the
 * attribute list on the servlet context of a web application. To receive
 * notification events, the implementation class must be configured in the
 * deployment descriptor for the web application.
 *
 * @see ServletContextAttributeEvent
 * @since v 2.3
 */

public interface ServletContextAttributeListener extends EventListener {
    /**
     * Notification that a new attribute was added to the servlet context.
     * Called after the attribute is added.
     * @param scae Information about the new attribute
     */
    public void attributeAdded(ServletContextAttributeEvent scae);

    /**
     * Notification that an existing attribute has been removed from the servlet
     * context. Called after the attribute is removed.
     * @param scae Information about the removed attribute
     */
    public void attributeRemoved(ServletContextAttributeEvent scae);

    /**
     * Notification that an attribute on the servlet context has been replaced.
     * Called after the attribute is replaced.
     * @param scae Information about the replaced attribute
     */
    public void attributeReplaced(ServletContextAttributeEvent scae);
}
