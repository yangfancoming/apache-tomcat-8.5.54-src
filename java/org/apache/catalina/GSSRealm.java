
package org.apache.catalina;

import java.security.Principal;

import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSName;

/**
 * A <b>GSSRealm</b> is a specialized realm for GSS-based principals.
 *
 * @deprecated This will be removed in Tomcat 9 and integrated into {@link Realm}.
 */
@Deprecated
public interface GSSRealm extends Realm {


    // --------------------------------------------------------- Public Methods

    /**
     * Try to authenticate using a {@link GSSName}
     *
     * @param gssName The {@link GSSName} of the principal to look up
     * @param gssCredential The {@link GSSCredential} of the principal, may be
     *                      {@code null}
     * @return the associated principal, or {@code null} if there is none
     */
    public Principal authenticate(GSSName gssName, GSSCredential gssCredential);

}
