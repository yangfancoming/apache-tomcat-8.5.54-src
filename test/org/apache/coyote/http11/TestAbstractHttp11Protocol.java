
package org.apache.coyote.http11;

import org.junit.Test;

public class TestAbstractHttp11Protocol {

    @Test
    public void testGetSslProtocol() {
        Http11Nio2Protocol protocol = new Http11Nio2Protocol();
        protocol.getSSLProtocol();
    }
}
