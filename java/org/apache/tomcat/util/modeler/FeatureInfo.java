


package org.apache.tomcat.util.modeler;


import java.io.Serializable;

import javax.management.MBeanFeatureInfo;


/**
 * <p>Convenience base class for <code>AttributeInfo</code> and
 * <code>OperationInfo</code> classes that will be used to collect configuration
 * information for the <code>ModelMBean</code> beans exposed for management.</p>
 *
 * @author Craig R. McClanahan
 */
public class FeatureInfo implements Serializable {
    static final long serialVersionUID = -911529176124712296L;

    protected String description = null;
    protected String name = null;
    protected MBeanFeatureInfo info = null;

    // all have type except Constructor
    protected String type = null;


    // ------------------------------------------------------------- Properties

    /**
     * @return the human-readable description of this feature.
     */
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return the name of this feature, which must be unique among features
     *  in the same collection.
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the fully qualified Java class name of this element.
     */
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
