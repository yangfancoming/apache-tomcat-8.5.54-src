
package org.apache.coyote.ajp;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.net.NioChannel;
import org.apache.tomcat.util.net.NioEndpoint;

/**
 * This the NIO based protocol handler implementation for AJP.
 */
public class AjpNioProtocol extends AbstractAjpProtocol<NioChannel> {

    private static final Log log = LogFactory.getLog(AjpNioProtocol.class);

    @Override
    protected Log getLog() { return log; }


    // ------------------------------------------------------------ Constructor

    public AjpNioProtocol() {
        super(new NioEndpoint());
    }


    // ----------------------------------------------------- JMX related methods

    @Override
    protected String getNamePrefix() {
        return "ajp-nio";
    }
}
