


package org.apache.catalina.ant;


import org.apache.tools.ant.BuildException;


/**
 * Ant task that implements the <code>/sessions</code> command
 * supported by the Tomcat manager application.
 *
 * @author Vivek Chopra
 */
public class SessionsTask extends AbstractCatalinaCommandTask {


    protected String idle = null;

    public String getIdle() {
        return this.idle;
    }

    public void setIdle(String idle) {
        this.idle = idle;
    }

    @Override
    public StringBuilder createQueryString(String command) {
        StringBuilder buffer = super.createQueryString(command);
        if (path != null && idle != null) {
            buffer.append("&idle=");
            buffer.append(this.idle);
        }
        return buffer;
    }

    /**
     * Execute the requested operation.
     *
     * @exception BuildException if an error occurs
     */
    @Override
    public void execute() throws BuildException {

        super.execute();
        execute(createQueryString("/sessions").toString());

    }

}
