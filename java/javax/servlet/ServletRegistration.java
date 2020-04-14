
package javax.servlet;

import java.util.Collection;
import java.util.Set;

/**
 * TODO SERVLET3 - Add comments
 * @since Servlet 3.0
 */
public interface ServletRegistration extends Registration {

    /**
     * TODO
     * @param urlPatterns The URL patterns that this Servlet should be mapped to
     * @return TODO
     * @throws IllegalArgumentException if urlPattern is null or empty
     * @throws IllegalStateException if the associated ServletContext has
     *                                  already been initialised
     */
    public Set<String> addMapping(String... urlPatterns);

    public Collection<String> getMappings();

    public String getRunAsRole();

    public static interface Dynamic
    extends ServletRegistration, Registration.Dynamic {
        public void setLoadOnStartup(int loadOnStartup);
        public void setMultipartConfig(MultipartConfigElement multipartConfig);
        public void setRunAsRole(String roleName);
        public Set<String> setServletSecurity(ServletSecurityElement constraint);
    }
}
