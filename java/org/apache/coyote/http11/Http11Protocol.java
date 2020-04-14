
package org.apache.coyote.http11;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.res.StringManager;

/**
 * @deprecated This class will be removed in Tomcat 9.
 */
@Deprecated
public class Http11Protocol extends Http11NioProtocol {

    private static final Log log = LogFactory.getLog(Http11Protocol.class);
    private static final StringManager sm = StringManager.getManager(Http11Protocol.class);


    public Http11Protocol() {
        super();
        log.warn(sm.getString("http11protocol.noBio"));
    }
}