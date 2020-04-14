
package javax.servlet;

import java.util.ResourceBundle;

/**
 * @since Servlet 3.0
 * TODO SERVLET3 - Add comments
 */
public class HttpMethodConstraintElement extends HttpConstraintElement {

    // Can't inherit from HttpConstraintElement as API does not allow it
    private static final String LSTRING_FILE = "javax.servlet.LocalStrings";
    private static final ResourceBundle lStrings =
        ResourceBundle.getBundle(LSTRING_FILE);

    private final String methodName;

    public HttpMethodConstraintElement(String methodName) {
        if (methodName == null || methodName.length() == 0) {
            throw new IllegalArgumentException(lStrings.getString(
                    "httpMethodConstraintElement.invalidMethod"));
        }
        this.methodName = methodName;
    }

    public HttpMethodConstraintElement(String methodName,
            HttpConstraintElement constraint) {
        super(constraint.getEmptyRoleSemantic(),
                constraint.getTransportGuarantee(),
                constraint.getRolesAllowed());
        if (methodName == null || methodName.length() == 0) {
            throw new IllegalArgumentException(lStrings.getString(
                    "httpMethodConstraintElement.invalidMethod"));
        }
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }
}