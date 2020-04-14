
package javax.servlet;

/**
 * This is the event class for notifications about changes to the attributes of
 * the servlet context of a web application.
 *
 * @see ServletContextAttributeListener
 * @since v 2.3
 */
public class ServletContextAttributeEvent extends ServletContextEvent {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final Object value;

    /**
     * Construct a ServletContextAttributeEvent from the given context for the
     * given attribute name and attribute value.
     *
     * @param source The ServletContext associated with this attribute event
     * @param name   The name of the servlet context attribute
     * @param value  The value of the servlet context attribute
     */
    public ServletContextAttributeEvent(ServletContext source, String name,
            Object value) {
        super(source);
        this.name = name;
        this.value = value;
    }

    /**
     * Return the name of the attribute that changed on the ServletContext.
     *
     * @return The name of the attribute that changed
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the value of the attribute that has been added, removed, or
     * replaced.
     *
     * @return If the attribute was added, this is the value of the attribute.
     *         If the attribute was removed, this is the value of the removed
     *         attribute. If the attribute was replaced, this is the old value
     *         of the attribute.
     */
    public Object getValue() {
        return this.value;
    }
}
