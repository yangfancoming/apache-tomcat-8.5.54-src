
package org.apache.catalina.ant.jmx;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.apache.tools.ant.BuildException;

/**
 * unregister an MBean at <em>JMX</em> JSR 160 MBeans Server.
 * <ul>
 * <li>unregister Mbeans</li>
 * </ul>
 * <p>
 * Examples:
 * <br>
 * unregister an existing Mbean at jmx.server connection
 * </p>
 * <pre>
 *   &lt;jmx:unregister
 *           ref="jmx.server"
 *           name="Catalina:type=MBeanFactory" /&gt;
 * </pre>
 * <p>
 * <b>WARNING</b>Not all Tomcat MBeans can successfully unregister remotely. The mbean
 * unregistration don't remove valves, realm, .. from parent class.
 * Please, use the MBeanFactory operation to remove valves and realms.
 * </p>
 * <p>
 * First call to a remote MBeanserver save the JMXConnection a reference <em>jmx.server</em>
 * </p>
 * These tasks require Ant 1.6 or later interface.
 *
 * @author Peter Rossbach
 * @since 5.5.12
 */
public class JMXAccessorUnregisterTask extends JMXAccessorTask {

    // ------------------------------------------------------ protected Methods

    @Override
    public String jmxExecute(MBeanServerConnection jmxServerConnection)
        throws Exception {

        if (getName() == null) {
            throw new BuildException("Must specify a 'name'");
        }
        return  jmxUuregister(jmxServerConnection, getName());
     }


    /**
     * Unregister MBean.
     *
     * @param jmxServerConnection Connection to the JMX server
     * @param name The MBean name
     * @return null (no error message to report other than exception)
     * @throws Exception An error occurred
     */
    protected String jmxUuregister(MBeanServerConnection jmxServerConnection,String name) throws Exception {
        String error = null;
        if(isEcho()) {
            handleOutput("Unregister MBean " + name  );
        }
        jmxServerConnection.unregisterMBean(
                new ObjectName(name));
        return error;
    }

}
