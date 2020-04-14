
package org.apache.catalina.ant;

import org.apache.tools.ant.BuildException;

/**
 * Ant task that implements the <code>/findleaks</code> command, supported by
 * the Tomcat manager application.
 */
public class FindLeaksTask extends AbstractCatalinaTask {

    private boolean statusLine = true;

    /**
     * Sets the statusLine parameter that controls if the response includes a
     * status line or not.
     *
     * @param statusLine <code>true</code> if the status line should be included
     */
    public void setStatusLine(boolean statusLine) {
        this.statusLine = statusLine;
    }

    /**
     * Returns the statusLine parameter that controls if the response includes a
     * status line or not.
     *
     * @return <code>true</code> if the status line should be included,
     *         otherwise <code>false</code>
     */
    public boolean getStatusLine() {
        return statusLine;
    }


    /**
     * Execute the requested operation.
     *
     * @exception BuildException if an error occurs
     */
    @Override
    public void execute() throws BuildException {
        super.execute();
        execute("/findleaks?statusLine=" + Boolean.toString(statusLine));
    }
}
