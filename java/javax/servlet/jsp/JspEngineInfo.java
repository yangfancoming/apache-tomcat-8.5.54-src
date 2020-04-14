
package javax.servlet.jsp;

/**
 * The JspEngineInfo is an abstract class that provides information on the
 * current JSP engine.
 */

public abstract class JspEngineInfo {

    /**
     * Sole constructor. (For invocation by subclass constructors,
     * typically implicit.)
     */
    public JspEngineInfo() {
        // NOOP by default
    }

    /**
     * Return the version number of the JSP specification that is supported by
     * this JSP engine.
     * <p>
     * Specification version numbers that consists of positive decimal integers
     * separated by periods ".", for example, "2.0" or "1.2.3.4.5.6.7".
     * This allows an extensible number to be used to
     * represent major, minor, micro, etc versions.
     * The version number must begin with a number.
     * </p>
     *
     * @return the specification version, null is returned if it is not known
     */

    public abstract String getSpecificationVersion();
}
