
package org.apache.coyote.ajp;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.res.StringManager;

/**
 * @deprecated This class will be removed in Tomcat 9.
 */
@Deprecated
public class AjpProtocol extends AjpNioProtocol {

    private static final Log log = LogFactory.getLog(AjpProtocol.class);
    private static final StringManager sm = StringManager.getManager(AjpProtocol.class);


    public AjpProtocol() {
        super();
        log.warn(sm.getString("ajpprotocol.noBio"));
    }
}