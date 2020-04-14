
package org.apache.catalina.webresources.war;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler {

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new WarURLConnection(u);
    }

    @Override
    protected void setURL(URL u, String protocol, String host, int port, String authority, String userInfo, String path,
            String query, String ref) {
        if (path.startsWith("file:") && !path.startsWith("file:/")) {
            // Work around a problem with the URLs in the security policy file.
            // On Windows, the use of ${catalina.[home|base]} in the policy file
            // results in codebase URLs of the form file:C:/... when they should
            // be file:/C:/...
            // For file: and jar: URLs, the JRE compensates for this. It does not
            // compensate for this for war:file:... URLs. Therefore, we do that
            // here
            path = "file:/" + path.substring(5);
        }
        super.setURL(u, protocol, host, port, authority, userInfo, path, query, ref);
    }
}
