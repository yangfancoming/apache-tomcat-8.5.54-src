
package org.apache.el.util;

import java.io.InputStream;

public class Tester {

    @SuppressWarnings("unused")
    public void testA(InputStream param1, String param2) {
        // NO-OP
    }

    @SuppressWarnings("unused")
    public void testA(Long param1, String param2) {
        // NO-OP
    }

    @SuppressWarnings("unused")
    public void testB(InputStream param1, String param2) {
        // NO-OP
    }

    @SuppressWarnings("unused")
    public void testB(long param1, String param2) {
        // NO-OP
    }

    @SuppressWarnings("unused")
    public void testC(long param1) {
        // NO-OP
    }

    @SuppressWarnings("unused")
    public void testD(String param1) {
        // NO-OP
    }
}
