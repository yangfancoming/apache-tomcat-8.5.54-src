
package org.apache.tomcat.websocket.pojo;

import javax.websocket.DeploymentException;

import org.apache.tomcat.util.res.StringManager;
import org.apache.tomcat.websocket.Util;

/**
 * Stores the parameter type and name for a parameter that needs to be passed to
 * an onXxx method of {@link javax.websocket.Endpoint}. The name is only present
 * for parameters annotated with
 * {@link javax.websocket.server.PathParam}. For the
 * {@link javax.websocket.Session} and {@link java.lang.Throwable} parameters,
 * {@link #getName()} will always return <code>null</code>.
 */
public class PojoPathParam {

    private static final StringManager sm = StringManager.getManager(PojoPathParam.class);

    private final Class<?> type;
    private final String name;


    public PojoPathParam(Class<?> type, String name)  throws DeploymentException {
        if (name != null) {
            // Annotated as @PathParam so validate type
            validateType(type);
        }
        this.type = type;
        this.name = name;
    }


    public Class<?> getType() {
        return type;
    }


    public String getName() {
        return name;
    }


    private static void validateType(Class<?> type) throws DeploymentException {
        if (String.class == type) {
            return;
        }
        if (Util.isPrimitive(type)) {
            return;
        }
        throw new DeploymentException(sm.getString("pojoPathParam.wrongType", type.getName()));
    }
}
