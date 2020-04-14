
package org.apache.catalina.security;

import java.security.BasicPermission;

/**
 * Grant this permission to a docBase to permit the web application to use any
 * <code>META-INF/context.xml</code> that might be present with in the
 * application when <code>deployXML</code> has been disabled at the Host level.
 * The name of the permission should be the base name for the web application.
 */
public class DeployXmlPermission extends BasicPermission {

    private static final long serialVersionUID = 1L;

    public DeployXmlPermission(String name) {
        super(name);
    }

    public DeployXmlPermission(String name, String actions) {
        super(name, actions);
    }
}
