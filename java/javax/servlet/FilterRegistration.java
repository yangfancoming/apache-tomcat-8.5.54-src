
package javax.servlet;

import java.util.Collection;
import java.util.EnumSet;

/**
 * @since Servlet 3.0
 * TODO SERVLET3 - Add comments
 */
public interface FilterRegistration extends Registration {

    /**
     * Add a mapping for this filter to one or more named Servlets.
     *
     * @param dispatcherTypes The dispatch types to which this filter should
     *                        apply
     * @param isMatchAfter    Should this filter be applied after any mappings
     *                        defined in the deployment descriptor
     *                        (<code>true</code>) or before?
     * @param servletNames    Requests mapped to these servlets will be
     *                        processed by this filter
     * @throws IllegalArgumentException if the list of servlet names is empty
     *                                  or null
     * @throws IllegalStateException if the associated ServletContext has
     *                               already been initialised
     */
    public void addMappingForServletNames(
            EnumSet<DispatcherType> dispatcherTypes,
            boolean isMatchAfter, String... servletNames);
    /**
     *
     * @return TODO
     */
    public Collection<String> getServletNameMappings();

    /**
     * Add a mapping for this filter to one or more URL patterns.
     *
     * @param dispatcherTypes The dispatch types to which this filter should
     *                        apply
     * @param isMatchAfter    Should this filter be applied after any mappings
     *                        defined in the deployment descriptor
     *                        (<code>true</code>) or before?
     * @param urlPatterns     The URL patterns to which this filter should be
     *                        applied
     * @throws IllegalArgumentException if the list of URL patterns is empty or
     *                                  null
     * @throws IllegalStateException if the associated ServletContext has
     *                               already been initialised
     */
    public void addMappingForUrlPatterns(
            EnumSet<DispatcherType> dispatcherTypes,
            boolean isMatchAfter, String... urlPatterns);

    /**
     *
     * @return TODO
     */
    public Collection<String> getUrlPatternMappings();

    public static interface Dynamic
    extends FilterRegistration, Registration.Dynamic {
        // No additional methods
    }
}
