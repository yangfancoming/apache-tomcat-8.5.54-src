

package org.apache.catalina.storeconfig;

import java.io.PrintWriter;

import org.apache.catalina.SessionIdGenerator;
import org.apache.catalina.Store;
import org.apache.catalina.session.PersistentManager;

/**
 * store server.xml PersistentManager element with nested "Store"
 */
public class PersistentManagerSF extends StoreFactoryBase {

    /**
     * Store the specified PersistentManager properties.
     *
     * @param aWriter
     *            PrintWriter to which we are storing
     * @param indent
     *            Number of spaces to indent this element
     * @param aManager
     *            PersistentManager whose properties are being stored
     *
     * @exception Exception
     *                if an exception occurs while storing
     */
    @Override
    public void storeChildren(PrintWriter aWriter, int indent, Object aManager,
            StoreDescription parentDesc) throws Exception {
        if (aManager instanceof PersistentManager) {
            PersistentManager manager = (PersistentManager) aManager;

            // Store nested <Store> element
            Store store = manager.getStore();
            storeElement(aWriter, indent, store);

            // Store nested <SessionIdGenerator> element
            SessionIdGenerator sessionIdGenerator = manager.getSessionIdGenerator();
            if (sessionIdGenerator != null) {
                storeElement(aWriter, indent, sessionIdGenerator);
            }

        }
    }

}