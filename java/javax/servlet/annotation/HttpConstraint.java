
package javax.servlet.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;

/**
 * This annotation represents the security constraints that are applied to all
 * requests with HTTP protocol method types that are not otherwise represented
 * by a corresponding {@link javax.servlet.annotation.HttpMethodConstraint} in a
 * {@link javax.servlet.annotation.ServletSecurity} annotation.
 *
 * @since Servlet 3.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpConstraint {

    /**
     * The EmptyRoleSemantic determines the behaviour when the rolesAllowed list
     * is empty.
     *
     * @return empty role semantic
     */
    EmptyRoleSemantic value() default EmptyRoleSemantic.PERMIT;

    /**
     * Determines whether SSL/TLS is required to process the current request.
     *
     * @return transport guarantee
     */
    TransportGuarantee transportGuarantee() default TransportGuarantee.NONE;

    /**
     * The authorized roles' names. The container may discard duplicate role
     * names during processing of the annotation. N.B. The String "*" does not
     * have a special meaning if it occurs as a role name.
     *
     * @return array of names. The array may be of zero length, in which case
     *         the EmptyRoleSemantic applies; the returned value determines
     *         whether access is to be permitted or denied regardless of the
     *         identity and authentication state in either case, PERMIT or DENY.<br>
     *         Otherwise, when the array contains one or more role names access
     *         is permitted if the user a member of at least one of the named
     *         roles. The EmptyRoleSemantic is not applied in this case.
     *
     */
    String[] rolesAllowed() default {};

}
