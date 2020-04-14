


package org.apache.catalina.ant;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.tools.ant.BuildException;


/**
 * Ant task that implements the <code>/resources</code> command, supported by
 * the Tomcat manager application.
 *
 * @author Craig R. McClanahan
 * @since 4.1
 */
public class ResourcesTask extends AbstractCatalinaTask {


    // ------------------------------------------------------------- Properties


    /**
     * The fully qualified class name of the resource type being requested
     * (if any).
     */
    protected String type = null;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Execute the requested operation.
     *
     * @exception BuildException if an error occurs
     */
    @Override
    public void execute() throws BuildException {

        super.execute();
        if (type != null) {
            try {
                execute("/resources?type=" +
                        URLEncoder.encode(type, getCharset()));
            } catch (UnsupportedEncodingException e) {
                throw new BuildException
                    ("Invalid 'charset' attribute: " + getCharset());
            }
        } else {
            execute("/resources");
        }

    }


}
