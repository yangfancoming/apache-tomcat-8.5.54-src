


package org.apache.catalina.ant;


import org.apache.tools.ant.BuildException;


/**
 * Ant task that implements the <code>/sslConnectorCiphers</code> command
 * supported by the Tomcat manager application.
 *
 */
public class SslConnectorCiphersTask extends AbstractCatalinaTask {

    // Public Methods

    /**
     * Execute the requested operation.
     *
     * @exception BuildException if an error occurs
     */
    @Override
    public void execute() throws BuildException {

        super.execute();
        execute("/sslConnectorCiphers");

    }

}
