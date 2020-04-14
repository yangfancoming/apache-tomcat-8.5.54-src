

package org.apache.catalina.mapper;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.Wrapper;
import org.apache.catalina.servlet4preview.http.MappingMatch;
import org.apache.tomcat.util.buf.MessageBytes;

/**
 * Mapping data.
 *
 * @author Remy Maucherat
 */
public class MappingData {

    public Host host = null;
    public Context context = null;
    public int contextSlashCount = 0;
    public Context[] contexts = null;
    public Wrapper wrapper = null;
    public boolean jspWildCard = false;

    /**
     * @deprecated Unused. This will be removed in Tomcat 10.
     */
    @Deprecated
    public final MessageBytes contextPath = MessageBytes.newInstance();
    public final MessageBytes requestPath = MessageBytes.newInstance();
    public final MessageBytes wrapperPath = MessageBytes.newInstance();
    public final MessageBytes pathInfo = MessageBytes.newInstance();

    public final MessageBytes redirectPath = MessageBytes.newInstance();

    // Fields used by ApplicationMapping to implement javax.servlet.http.HttpServletMapping
    public MappingMatch matchType = null;

    public void recycle() {
        host = null;
        context = null;
        contextSlashCount = 0;
        contexts = null;
        wrapper = null;
        jspWildCard = false;
        contextPath.recycle();
        requestPath.recycle();
        wrapperPath.recycle();
        pathInfo.recycle();
        redirectPath.recycle();
        matchType = null;
    }
}
