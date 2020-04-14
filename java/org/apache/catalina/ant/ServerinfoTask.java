
package org.apache.catalina.ant;


import org.apache.tools.ant.BuildException;


/**
 * Ant task that implements the <code>/serverinfo</code> command
 * supported by the Tomcat manager application.
 *
 * @author Vivek Chopra
 */
public class ServerinfoTask extends AbstractCatalinaTask {

    // Public Methods

    /**
     * Execute the requested operation.
     *
     * @exception BuildException if an error occurs
     */
    @Override
    public void execute() throws BuildException {

        super.execute();
        execute("/serverinfo");

    }
}
