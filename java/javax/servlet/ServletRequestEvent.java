
package javax.servlet;

/**
 * Events of this kind indicate lifecycle events for a ServletRequest. The
 * source of the event is the ServletContext of this web application.
 *
 * @see ServletRequestListener
 * @since Servlet 2.4
 */
public class ServletRequestEvent extends java.util.EventObject {
    private static final long serialVersionUID = 1L;

    private final transient ServletRequest request;

    /**
     * Construct a ServletRequestEvent for the given ServletContext and
     * ServletRequest.
     *
     * @param sc
     *            the ServletContext of the web application.
     * @param request
     *            the ServletRequest that is sending the event.
     */
    public ServletRequestEvent(ServletContext sc, ServletRequest request) {
        super(sc);
        this.request = request;
    }

    /**
     * Get the associated ServletRequest.
     * @return the ServletRequest that is changing.
     */
    public ServletRequest getServletRequest() {
        return this.request;
    }

    /**
     * Get the associated ServletContext.
     * @return the ServletContext that is changing.
     */
    public ServletContext getServletContext() {
        return (ServletContext) super.getSource();
    }
}
