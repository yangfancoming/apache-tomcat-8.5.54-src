


package org.apache.tomcat.util.descriptor.web;

import java.io.Serializable;


/**
 * Representation of a context initialization parameter that is configured
 * in the server configuration file, rather than the application deployment
 * descriptor.  This is convenient for establishing default values (which
 * may be configured to allow application overrides or not) without having
 * to modify the application deployment descriptor itself.
 *
 * @author Craig R. McClanahan
 */
public class ApplicationParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    // ------------------------------------------------------------- Properties


    /**
     * The description of this environment entry.
     */
    private String description = null;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * The name of this application parameter.
     */
    private String name = null;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Does this application parameter allow overrides by the application
     * deployment descriptor?
     */
    private boolean override = true;

    public boolean getOverride() {
        return this.override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }


    /**
     * The value of this application parameter.
     */
    private String value = null;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // --------------------------------------------------------- Public Methods


    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("ApplicationParameter[");
        sb.append("name=");
        sb.append(name);
        if (description != null) {
            sb.append(", description=");
            sb.append(description);
        }
        sb.append(", value=");
        sb.append(value);
        sb.append(", override=");
        sb.append(override);
        sb.append("]");
        return sb.toString();

    }


}
