


package org.apache.tomcat.util.modeler;


import javax.management.MBeanParameterInfo;


/**
 * <p>Internal configuration information for a <code>Parameter</code>
 * descriptor.</p>
 *
 * @author Craig R. McClanahan
 */
public class ParameterInfo extends FeatureInfo {
    static final long serialVersionUID = 2222796006787664020L;
    // ----------------------------------------------------------- Constructors


    /**
     * Standard zero-arguments constructor.
     */
    public ParameterInfo() {
        super();
    }

    /**
     * Create and return a <code>MBeanParameterInfo</code> object that
     * corresponds to the parameter described by this instance.
     * @return a parameter info
     */
    public MBeanParameterInfo createParameterInfo() {

        // Return our cached information (if any)
        if (info == null) {
            info = new MBeanParameterInfo
                (getName(), getType(), getDescription());
        }
        return (MBeanParameterInfo)info;
    }
}
