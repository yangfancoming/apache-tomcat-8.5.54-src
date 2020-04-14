
package javax.servlet.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation used to declare a listener for various types of event, in a
 * given web application context.<br>
 * <br>
 *
 * The class annotated MUST implement one, (or more), of the following
 * interfaces: {@link javax.servlet.http.HttpSessionAttributeListener},
 * {@link javax.servlet.http.HttpSessionListener},
 * {@link javax.servlet.ServletContextAttributeListener},
 * {@link javax.servlet.ServletContextListener},
 * {@link javax.servlet.ServletRequestAttributeListener},
 * {@link javax.servlet.ServletRequestListener} or
 * {@link javax.servlet.http.HttpSessionIdListener}
 * <br>
 *
 * E.g. <code>@WebListener</code><br>
 * <code>public TestListener implements ServletContextListener {</code><br>
 *
 * @since Servlet 3.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebListener {

    /**
     * @return description of the listener, if present
     */
    String value() default "";
}
