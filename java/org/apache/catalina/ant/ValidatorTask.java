


package org.apache.catalina.ant;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.catalina.Globals;
import org.apache.tomcat.util.descriptor.DigesterFactory;
import org.apache.tomcat.util.digester.Digester;
import org.apache.tools.ant.BuildException;
import org.xml.sax.InputSource;


/**
 * Task for validating a web application deployment descriptor, using XML
 * schema validation.
 *
 * @author Remy Maucherat
 * @since 5.0
 */
public class ValidatorTask extends BaseRedirectorHelperTask {


    // ----------------------------------------------------- Instance Variables


    // ------------------------------------------------------------- Properties


    /**
     * The path to the webapp directory.
     */
    protected String path = null;

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    // --------------------------------------------------------- Public Methods

    /**
     * Execute the specified command.  This logic only performs the common
     * attribute validation required by all subclasses; it does not perform
     * any functional logic directly.
     *
     * @exception BuildException if a validation error occurs
     */
    @Override
    public void execute() throws BuildException {

        if (path == null) {
            throw new BuildException("Must specify 'path'");
        }

        File file = new File(path, "WEB-INF/web.xml");
        if (!file.canRead()) {
            throw new BuildException("Cannot find web.xml");
        }

        // Commons-logging likes having the context classloader set
        ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader
            (ValidatorTask.class.getClassLoader());

        // Called through trusted manager interface. If running under a
        // SecurityManager assume that untrusted applications may be deployed.
        Digester digester = DigesterFactory.newDigester(
                true, true, null, Globals.IS_SECURITY_ENABLED);
        try (InputStream stream = new BufferedInputStream(new FileInputStream(file.getCanonicalFile()))) {
            InputSource is = new InputSource(file.toURI().toURL().toExternalForm());
            is.setByteStream(stream);
            digester.parse(is);
            handleOutput("web.xml validated");
        } catch (Exception e) {
            if (isFailOnError()) {
                throw new BuildException("Validation failure", e);
            } else {
                handleErrorOutput("Validation failure: " + e);
            }
        } finally {
            Thread.currentThread().setContextClassLoader(oldCL);
            closeRedirector();
        }

    }


}
