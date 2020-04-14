
package org.apache.tomcat.unittest;

import java.io.IOException;

import org.apache.catalina.connector.Response;

/**
 * Minimal changes to Response to enable tests that use this class to operate
 * correctly.
 */
public class TesterResponse extends Response {

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void sendError(int status, String message) throws IOException {
        // NO-OP by default.
        /*
        System.out.println("TesterResponse.sendError(" + status + ", \"" +
                message + "\")");
         */
    }

    @Override
    public void resetBuffer(boolean resetWriterStreamFlags) {
        // NO-OP by default.
        // There is no buffer created for this test object since no test depends
        // on one being present or on this method resetting it.
    }

    @Override
    public org.apache.coyote.Response getCoyoteResponse() {
        // Lazy init
        if (super.getCoyoteResponse() == null) {
            this.coyoteResponse = new org.apache.coyote.Response();
        }
        return super.getCoyoteResponse();
    }

    @Override
    public void setSuspended(boolean suspended) {
        // NO-OP by default.
        // There is no buffer created for this test object since no test depends
        // on one being present or on this method suspending it.
    }

    @Override
    public void reset() {
        // Minimal implementation for tests that avoids using OutputBuffer
        if (super.getCoyoteResponse() != null) {
            super.getCoyoteResponse().reset();
        }
    }
}
