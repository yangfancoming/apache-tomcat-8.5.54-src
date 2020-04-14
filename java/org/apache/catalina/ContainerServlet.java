
package org.apache.catalina;

/**
 * A <b>ContainerServlet</b> is a servlet that has access to Catalina
 * internal functionality, and is loaded from the Catalina class loader
 * instead of the web application class loader.  The property setter
 * methods must be called by the container whenever a new instance of
 * this servlet is put into service.
 *
 * @author Craig R. McClanahan
 */
public interface ContainerServlet {

    /**
     * Obtain the Wrapper with which this Servlet is associated.
     *
     * @return The Wrapper with which this Servlet is associated.
     */
    public Wrapper getWrapper();


    /**
     * Set the Wrapper with which this Servlet is associated.
     *
     * @param wrapper The new associated Wrapper
     */
    public void setWrapper(Wrapper wrapper);
}
