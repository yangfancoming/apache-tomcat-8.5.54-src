
package javax.servlet.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation used to declare an initialization parameter on a
 * {@link javax.servlet.Servlet} or {@link javax.servlet.Filter}, within a
 * {@link javax.servlet.annotation.WebFilter} or
 * {@link javax.servlet.annotation.WebServlet} annotation.<br>
 * <br>
 *
 * E.g.
 * <code>&amp;#064;WebServlet(name="TestServlet", urlPatterns={"/test"},initParams={&amp;#064;WebInitParam(name="test", value="true")})
 * public class TestServlet extends HttpServlet { ... </code><br>
 *
 * @since Servlet 3.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebInitParam {

    /**
     * @return name of the initialization parameter
     */
    String name();

    /**
     * @return value of the initialization parameter
     */
    String value();

    /**
     * @return description of the initialization parameter
     */
    String description() default "";
}
