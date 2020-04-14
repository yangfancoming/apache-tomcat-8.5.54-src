


package org.apache.tomcat.util.descriptor.web;

import java.io.Serializable;


/**
 * <p>Representation of a security role reference for a web application, as
 * represented in a <code>&lt;security-role-ref&gt;</code> element
 * in the deployment descriptor.</p>
 *
 * @since Tomcat 5.5
 */
public class SecurityRoleRef implements Serializable {

    private static final long serialVersionUID = 1L;


    // ------------------------------------------------------------- Properties

    /**
     * The (required) role name.
     */
    private String name = null;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * The optional role link.
     */
    private String link = null;

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }



    // --------------------------------------------------------- Public Methods


    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SecurityRoleRef[");
        sb.append("name=");
        sb.append(name);
        if (link != null) {
            sb.append(", link=");
            sb.append(link);
        }
        sb.append("]");
        return sb.toString();
    }


}
