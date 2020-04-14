

package org.apache.catalina.storeconfig;

import java.io.PrintWriter;

import org.apache.catalina.CredentialHandler;
import org.apache.catalina.Realm;
import org.apache.catalina.realm.CombinedRealm;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 * Store server.xml Element Realm
 */
public class RealmSF extends StoreFactoryBase {

    private static Log log = LogFactory.getLog(RealmSF.class);

    @Override
    public void store(PrintWriter aWriter, int indent, Object aElement)
            throws Exception {
        if (aElement instanceof CombinedRealm) {
            StoreDescription elementDesc = getRegistry().findDescription(
                    aElement.getClass());

            if (elementDesc != null) {
                if (log.isDebugEnabled())
                    log.debug(sm.getString("factory.storeTag",
                            elementDesc.getTag(), aElement));
                getStoreAppender().printIndent(aWriter, indent + 2);
                getStoreAppender().printOpenTag(aWriter, indent + 2, aElement,
                            elementDesc);
                storeChildren(aWriter, indent + 2, aElement, elementDesc);
                getStoreAppender().printIndent(aWriter, indent + 2);
                getStoreAppender().printCloseTag(aWriter, elementDesc);
            } else {
                if (log.isWarnEnabled())
                    log.warn(sm.getString("factory.storeNoDescriptor",
                            aElement.getClass()));
            }
        } else {
            super.store(aWriter, indent, aElement);
        }
    }

    /**
     * Store the specified Realm properties and child (Realm)
     *
     * @param aWriter
     *            PrintWriter to which we are storing
     * @param indent
     *            Number of spaces to indent this element
     * @param aRealm
     *            Realm whose properties are being stored
     *
     * @exception Exception
     *                if an exception occurs while storing
     */
    @Override
    public void storeChildren(PrintWriter aWriter, int indent, Object aRealm,
            StoreDescription parentDesc) throws Exception {
        if (aRealm instanceof CombinedRealm) {
            CombinedRealm combinedRealm = (CombinedRealm) aRealm;

            // Store nested <Realm> element
            Realm[] realms = combinedRealm.getNestedRealms();
            storeElementArray(aWriter, indent, realms);
        }
        // Store nested <CredentialHandler> element
        CredentialHandler credentialHandler = ((Realm) aRealm).getCredentialHandler();
        if (credentialHandler != null) {
            storeElement(aWriter, indent, credentialHandler);
        }
    }

}