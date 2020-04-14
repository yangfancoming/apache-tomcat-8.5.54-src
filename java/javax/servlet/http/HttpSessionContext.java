

package javax.servlet.http;

import java.util.Enumeration;

/**
 * Do not use.
 * @deprecated As of Java(tm) Servlet API 2.1 for security reasons, with no
 *             replacement. This interface will be removed in a future version
 *             of this API.
 * @see HttpSession
 * @see HttpSessionBindingEvent
 * @see HttpSessionBindingListener
 */
@SuppressWarnings("dep-ann")
// Spec API does not use @Deprecated
public interface HttpSessionContext {

    /**
     * Do not use.
     * @param sessionId Ignored
     * @return Always <code>null</code>
     * @deprecated As of Java Servlet API 2.1 with no replacement. This method
     *             must return null and will be removed in a future version of
     *             this API.
     */
    // Spec API does not use @Deprecated
    public HttpSession getSession(String sessionId);

    /**
     * Do not use.
     * @return Always an empty Enumeration
     * @deprecated As of Java Servlet API 2.1 with no replacement. This method
     *             must return an empty <code>Enumeration</code> and will be
     *             removed in a future version of this API.
     */
    // Spec API does not use @Deprecated
    public Enumeration<String> getIds();
}
