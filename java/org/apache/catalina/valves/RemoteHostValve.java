
package org.apache.catalina.valves;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 * Concrete implementation of <code>RequestFilterValve</code> that filters
 * based on the remote client's host name optionally combined with the
 * server connector port number.
 *
 * @author Craig R. McClanahan
 */
public final class RemoteHostValve extends RequestFilterValve {

    private static final Log log = LogFactory.getLog(RemoteHostValve.class);


    // --------------------------------------------------------- Public Methods

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        String property;
        if (getAddConnectorPort()) {
            property = request.getRequest().getRemoteHost() + ";" + request.getConnector().getPort();
        } else {
            property = request.getRequest().getRemoteHost();
        }
        process(property, request, response);
    }


    @Override
    protected Log getLog() {
        return log;
    }
}
