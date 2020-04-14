
package javax.websocket.server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.websocket.Decoder;
import javax.websocket.Encoder;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ServerEndpoint {

    /**
     * URI or URI-template that the annotated class should be mapped to.
     * @return The URI or URI-template that the annotated class should be mapped
     *         to.
     */
    String value();

    String[] subprotocols() default {};

    Class<? extends Decoder>[] decoders() default {};

    Class<? extends Encoder>[] encoders() default {};

    public Class<? extends ServerEndpointConfig.Configurator> configurator()
            default ServerEndpointConfig.Configurator.class;
}
