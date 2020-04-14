

package org.apache.catalina.storeconfig;

import java.io.PrintWriter;

import org.apache.catalina.Manager;
import org.apache.catalina.SessionIdGenerator;
import org.apache.catalina.session.StandardManager;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 * Store server.xml Manager element
 */
public class ManagerSF extends StoreFactoryBase {

    private static Log log = LogFactory.getLog(ManagerSF.class);

    /**
     * Store the only the Manager elements
     *
     * @see NamingResourcesSF#storeChildren(PrintWriter, int, Object, StoreDescription)
     */
    @Override
    public void store(PrintWriter aWriter, int indent, Object aElement)
            throws Exception {
        StoreDescription elementDesc = getRegistry().findDescription(
                aElement.getClass());
        if (elementDesc != null) {
            if (aElement instanceof StandardManager) {
                StandardManager manager = (StandardManager) aElement;
                if (!isDefaultManager(manager)) {
                    if (log.isDebugEnabled())
                        log.debug(sm.getString("factory.storeTag", elementDesc
                                .getTag(), aElement));
                    super.store(aWriter, indent, aElement);
                }
            } else {
                super.store(aWriter, indent, aElement);
            }
        } else {
            if (log.isWarnEnabled())
                log.warn(sm.getString("factory.storeNoDescriptor", aElement
                        .getClass()));
        }
    }

    /**
     * Is this an instance of the default <code>Manager</code> configuration,
     * with all-default properties?
     *
     * @param smanager
     *            Manager to be tested
     * @return <code>true</code> if this is an instance of the default manager
     */
    protected boolean isDefaultManager(StandardManager smanager) {

        if (!"SESSIONS.ser".equals(smanager.getPathname())
                || (smanager.getMaxActiveSessions() != -1)) {
            return false;
        }
        return true;

    }

    @Override
    public void storeChildren(PrintWriter aWriter, int indent, Object aManager,
            StoreDescription parentDesc) throws Exception {
        if (aManager instanceof Manager) {
            Manager manager = (Manager) aManager;
            // Store nested <SessionIdGenerator> element;
            SessionIdGenerator sessionIdGenerator = manager.getSessionIdGenerator();
            if (sessionIdGenerator != null) {
                storeElement(aWriter, indent, sessionIdGenerator);
            }
        }
    }

}
