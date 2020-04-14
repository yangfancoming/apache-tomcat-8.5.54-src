
package org.apache.naming;

import javax.naming.StringRefAddr;

/**
 * Represents a reference handler for a web service.
 *
 * @author Fabien Carrion
 */
public class HandlerRef extends AbstractRef {

    private static final long serialVersionUID = 1L;


    /**
     * Default factory for this reference.
     */
    public static final String DEFAULT_FACTORY =
            org.apache.naming.factory.Constants.DEFAULT_HANDLER_FACTORY;


    /**
     * HandlerName address type.
     */
    public static final String HANDLER_NAME  = "handlername";


    /**
     * Handler Classname address type.
     */
    public static final String HANDLER_CLASS  = "handlerclass";


    /**
     * Handler Classname address type.
     */
    public static final String HANDLER_LOCALPART  = "handlerlocalpart";


    /**
     * Handler Classname address type.
     */
    public static final String HANDLER_NAMESPACE  = "handlernamespace";


    /**
     * Handler Classname address type.
     */
    public static final String HANDLER_PARAMNAME  = "handlerparamname";


    /**
     * Handler Classname address type.
     */
    public static final String HANDLER_PARAMVALUE  = "handlerparamvalue";


    /**
     * Handler SoapRole address type.
     */
    public static final String HANDLER_SOAPROLE  = "handlersoaprole";


    /**
     * Handler PortName address type.
     */
    public static final String HANDLER_PORTNAME  = "handlerportname";


    public HandlerRef(String refname, String handlerClass) {
        this(refname, handlerClass, null, null);
    }


    public HandlerRef(String refname, String handlerClass,
                    String factory, String factoryLocation) {
        super(refname, factory, factoryLocation);
        StringRefAddr refAddr = null;
        if (refname != null) {
            refAddr = new StringRefAddr(HANDLER_NAME, refname);
            add(refAddr);
        }
        if (handlerClass != null) {
            refAddr = new StringRefAddr(HANDLER_CLASS, handlerClass);
            add(refAddr);
        }
    }


    @Override
    protected String getDefaultFactoryClassName() {
        return DEFAULT_FACTORY;
    }
}
