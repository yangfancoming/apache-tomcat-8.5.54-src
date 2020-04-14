
package org.apache.tomcat.websocket.pojo;

import java.util.Collections;
import java.util.List;

import javax.websocket.Decoder;
import javax.websocket.DeploymentException;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;


/**
 * Wrapper class for instances of POJOs annotated with
 * {@link javax.websocket.ClientEndpoint} so they appear as standard
 * {@link javax.websocket.Endpoint} instances.
 */
public class PojoEndpointClient extends PojoEndpointBase {

    public PojoEndpointClient(Object pojo,
            List<Class<? extends Decoder>> decoders) throws DeploymentException {
        setPojo(pojo);
        setMethodMapping(
                new PojoMethodMapping(pojo.getClass(), decoders, null));
        setPathParameters(Collections.<String,String>emptyMap());
    }

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        doOnOpen(session, config);
    }
}
