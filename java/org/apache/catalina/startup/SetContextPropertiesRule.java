


package org.apache.catalina.startup;

import org.apache.tomcat.util.IntrospectionUtils;
import org.apache.tomcat.util.digester.Rule;
import org.xml.sax.Attributes;

/**
 * Rule that uses the introspection utils to set properties of a context
 * (everything except "path").
 *
 * @author Remy Maucherat
 */
public class SetContextPropertiesRule extends Rule {


    // ----------------------------------------------------------- Constructors


    // ----------------------------------------------------- Instance Variables


    // --------------------------------------------------------- Public Methods


    /**
     * Handle the beginning of an XML element.
     *
     * @param attributes The attributes of this element
     *
     * @exception Exception if a processing error occurs
     */
    @Override
    public void begin(String namespace, String nameX, Attributes attributes)
        throws Exception {

        for (int i = 0; i < attributes.getLength(); i++) {
            String name = attributes.getLocalName(i);
            if ("".equals(name)) {
                name = attributes.getQName(i);
            }
            if ("path".equals(name) || "docBase".equals(name)) {
                continue;
            }
            String value = attributes.getValue(i);
            if (!digester.isFakeAttribute(digester.peek(), name)
                    && !IntrospectionUtils.setProperty(digester.peek(), name, value)
                    && digester.getRulesValidation()) {
                digester.getLogger().warn("[SetContextPropertiesRule]{" + digester.getMatch() +
                        "} Setting property '" + name + "' to '" +
                        value + "' did not find a matching property.");
            }
        }

    }


}
