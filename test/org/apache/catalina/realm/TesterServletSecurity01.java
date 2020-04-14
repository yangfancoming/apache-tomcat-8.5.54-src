
package org.apache.catalina.realm;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;

import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

@ServletSecurity(value=@HttpConstraint,
        httpMethodConstraints={
                @HttpMethodConstraint(value="POST",
                        rolesAllowed=TestRealmBase.ROLE1),
                @HttpMethodConstraint(value="PUT",
                        rolesAllowed=SecurityConstraint.ROLE_ALL_ROLES),
                @HttpMethodConstraint(value="TRACE",
                        rolesAllowed=SecurityConstraint.ROLE_ALL_AUTHENTICATED_USERS)})
public class TesterServletSecurity01 {
    // Class is NO-OP. It is only used to 'host' the annotation.
}
