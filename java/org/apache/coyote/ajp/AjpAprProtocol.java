
package org.apache.coyote.ajp;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.net.AprEndpoint;


/**
 * This the APR/native based protocol handler implementation for AJP.
 */
public class AjpAprProtocol extends AbstractAjpProtocol<Long> {

    private static final Log log = LogFactory.getLog(AjpAprProtocol.class);

    @Override
    protected Log getLog() { return log; }


    @Override
    public boolean isAprRequired() {
        // Override since this protocol implementation requires the APR/native
        // library
        return true;
    }


    // ------------------------------------------------------------ Constructor

    public AjpAprProtocol() {
        super(new AprEndpoint());
    }


    // --------------------------------------------------------- Public Methods

    public int getPollTime() { return ((AprEndpoint)getEndpoint()).getPollTime(); }
    public void setPollTime(int pollTime) { ((AprEndpoint)getEndpoint()).setPollTime(pollTime); }


    // ----------------------------------------------------- JMX related methods

    @Override
    protected String getNamePrefix() {
        return "ajp-apr";
    }
}
