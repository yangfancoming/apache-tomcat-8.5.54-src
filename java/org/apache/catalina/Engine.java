
package org.apache.catalina;

/**
 * An <b>Engine</b> is a Container that represents the entire Catalina servlet
 * engine.  It is useful in the following types of scenarios:
 * <ul>
 * <li>You wish to use Interceptors that see every single request processed
 *     by the entire engine.
 * <li>You wish to run Catalina in with a standalone HTTP connector, but still
 *     want support for multiple virtual hosts.
 * </ul>
 * In general, you would not use an Engine when deploying Catalina connected
 * to a web server (such as Apache), because the Connector will have
 * utilized the web server's facilities to determine which Context (or
 * perhaps even which Wrapper) should be utilized to process this request.
 * <p>
 * The child containers attached to an Engine are generally implementations
 * of Host (representing a virtual host) or Context (representing individual
 * an individual servlet context), depending upon the Engine implementation.
 * <p>
 * If used, an Engine is always the top level Container in a Catalina
 * hierarchy. Therefore, the implementation's <code>setParent()</code> method
 * should throw <code>IllegalArgumentException</code>.
 *
 * @author Craig R. McClanahan
 */
public interface Engine extends Container {

    /**
     * @return the default host name for this Engine.
     */
    public String getDefaultHost();


    /**
     * Set the default hostname for this Engine.
     *
     * @param defaultHost The new default host
     */
    public void setDefaultHost(String defaultHost);


    /**
     * @return the JvmRouteId for this engine.
     */
    public String getJvmRoute();


    /**
     * Set the JvmRouteId for this engine.
     *
     * @param jvmRouteId the (new) JVM Route ID. Each Engine within a cluster
     *        must have a unique JVM Route ID.
     */
    public void setJvmRoute(String jvmRouteId);


    /**
     * @return the <code>Service</code> with which we are associated (if any).
     */
    public Service getService();


    /**
     * Set the <code>Service</code> with which we are associated (if any).
     *
     * @param service The service that owns this Engine
     */
    public void setService(Service service);
}
