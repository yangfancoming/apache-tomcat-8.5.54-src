
package org.apache.tomcat.util.digester;

import java.security.Permission;

import org.apache.tomcat.util.IntrospectionUtils;
import org.apache.tomcat.util.security.PermissionCheck;

/**
 * A {@link org.apache.tomcat.util.IntrospectionUtils.SecurePropertySource}
 * that uses environment variables to resolve expressions.
 *
 * <p><strong>Usage example:</strong></p>
 *
 * Configure the certificate with environment variables.
 *
 * <pre>
 *   {@code
 *     <SSLHostConfig>
 *           <Certificate certificateKeyFile="${CERTIFICATE_KEY_FILE}"
 *                        certificateFile="${CERTIFICATE_FILE}"
 *                        certificateChainFile="${CERTIFICATE_CHAIN_FILE}"
 *                        type="RSA" />
 *     </SSLHostConfig> }
 * </pre>
 *
 * How to configure:
 * <pre>
 * {@code
 *   echo "org.apache.tomcat.util.digester.PROPERTY_SOURCE=org.apache.tomcat.util.digester.EnvironmentPropertySource" >> conf/catalina.properties}
 * </pre>
 * or add this to {@code CATALINA_OPTS}
 *
 * <pre>
 * {@code
 *   -Dorg.apache.tomcat.util.digester.PROPERTY_SOURCE=org.apache.tomcat.util.digester.EnvironmentPropertySource}
 * </pre>
 *
 * <b>NOTE</b>: When configured the PropertySource for resolving expressions
 *              from system properties is still active.
 *
 * @see Digester
 *
 * @see <a href="https://tomcat.apache.org/tomcat-9.0-doc/config/systemprops.html#Property_replacements">Tomcat Configuration Reference System Properties</a>
 */
public class EnvironmentPropertySource implements IntrospectionUtils.SecurePropertySource {

    @Override
    public String getProperty(String key) {
        return null;
    }

    @Override
    public String getProperty(String key, ClassLoader classLoader) {
        if (classLoader instanceof PermissionCheck) {
            Permission p = new RuntimePermission("getenv." + key, null);
            if (!((PermissionCheck) classLoader).check(p)) {
                return null;
            }
        }
        return System.getenv(key);
    }
}
