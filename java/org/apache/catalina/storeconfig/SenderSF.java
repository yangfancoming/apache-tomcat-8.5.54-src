

package org.apache.catalina.storeconfig;

import java.io.PrintWriter;

import org.apache.catalina.tribes.transport.MultiPointSender;
import org.apache.catalina.tribes.transport.ReplicationTransmitter;

/**
 * Generate Sender Element
 */
public class SenderSF extends StoreFactoryBase {

    /**
     * Store the specified Sender child.
     *
     * @param aWriter
     *            PrintWriter to which we are storing
     * @param indent
     *            Number of spaces to indent this element
     * @param aSender
     *            Channel whose properties are being stored
     *
     * @exception Exception
     *                if an exception occurs while storing
     */
    @Override
    public void storeChildren(PrintWriter aWriter, int indent, Object aSender,
            StoreDescription parentDesc) throws Exception {
        if (aSender instanceof ReplicationTransmitter) {
            ReplicationTransmitter transmitter = (ReplicationTransmitter) aSender;
            // Store nested <Transport> element
            MultiPointSender transport = transmitter.getTransport();
            if (transport != null) {
                storeElement(aWriter, indent, transport);
            }
       }
    }
}