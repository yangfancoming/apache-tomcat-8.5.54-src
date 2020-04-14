
package javax.servlet;

/**
 * This is the event class for notifications about changes to the servlet
 * context of a web application.
 *
 * @see ServletContextListener
 * @since v 2.3
 */
public class ServletContextEvent extends java.util.EventObject {

    private static final long serialVersionUID = 1L;

    /**
     * Construct a ServletContextEvent from the given context.
     *
     * @param source
     *            - the ServletContext that is sending the event.
     */
    public ServletContextEvent(ServletContext source) {
        super(source);
    }

    /**
     * Return the ServletContext that changed.
     *
     * @return the ServletContext that sent the event.
     */
    public ServletContext getServletContext() {
        return (ServletContext) super.getSource();
    }
}
