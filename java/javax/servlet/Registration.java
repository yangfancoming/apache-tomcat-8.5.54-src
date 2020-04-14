
package javax.servlet;

import java.util.Map;
import java.util.Set;

/**
 * Common interface for the registration of Filters and Servlets.
 * @since Servlet 3.0
 */
public interface Registration {

    public String getName();

    public String getClassName();

    /**
     * Add an initialisation parameter if not already added.
     *
     * @param name  Name of initialisation parameter
     * @param value Value of initialisation parameter
     * @return <code>true</code> if the initialisation parameter was set,
     *         <code>false</code> if the initialisation parameter was not set
     *         because an initialisation parameter of the same name already
     *         existed
     * @throws IllegalArgumentException if name or value is <code>null</code>
     * @throws IllegalStateException if the ServletContext associated with this
     *         registration has already been initialised
     */
    public boolean setInitParameter(String name, String value);

    /**
     * Get the value of an initialisation parameter.
     *
     * @param name  The initialisation parameter whose value is required
     *
     * @return The value of the named initialisation parameter
     */
    public String getInitParameter(String name);

    /**
     * Add multiple initialisation parameters. If any of the supplied
     * initialisation parameter conflicts with an existing initialisation
     * parameter, no updates will be performed.
     *
     * @param initParameters The initialisation parameters to add
     *
     * @return The set of initialisation parameter names that conflicted with
     *         existing initialisation parameter. If there are no conflicts,
     *         this Set will be empty.
     * @throws IllegalArgumentException if any of the supplied initialisation
     *         parameters have a null name or value
     * @throws IllegalStateException if the ServletContext associated with this
     *         registration has already been initialised
     */
    public Set<String> setInitParameters(Map<String,String> initParameters);

    /**
     * Get the names and values of all the initialisation parameters.
     *
     * @return A Map of initialisation parameter names and associated values
     *         keyed by name
     */
    public Map<String, String> getInitParameters();

    public interface Dynamic extends Registration {

        /**
         * Mark this Servlet/Filter as supported asynchronous processing.
         *
         * @param isAsyncSupported  Should this Servlet/Filter support
         *                          asynchronous processing
         *
         * @throws IllegalStateException if the ServletContext associated with
         *         this registration has already been initialised
         */
        public void setAsyncSupported(boolean isAsyncSupported);
    }
}
