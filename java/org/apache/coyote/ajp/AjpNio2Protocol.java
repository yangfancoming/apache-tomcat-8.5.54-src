
package org.apache.coyote.ajp;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.net.Nio2Channel;
import org.apache.tomcat.util.net.Nio2Endpoint;


/**
 * This the NIO2 based protocol handler implementation for AJP.
 */
public class AjpNio2Protocol extends AbstractAjpProtocol<Nio2Channel> {

    private static final Log log = LogFactory.getLog(AjpNio2Protocol.class);

    @Override
    protected Log getLog() { return log; }


    // ------------------------------------------------------------ Constructor

    public AjpNio2Protocol() {
        super(new Nio2Endpoint());
    }


    // ----------------------------------------------------- JMX related methods

    @Override
    protected String getNamePrefix() {
        return "ajp-nio2";
    }
}
