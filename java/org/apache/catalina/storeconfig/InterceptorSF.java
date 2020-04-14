

package org.apache.catalina.storeconfig;

import java.io.PrintWriter;

import org.apache.catalina.tribes.ChannelInterceptor;
import org.apache.catalina.tribes.group.interceptors.StaticMembershipInterceptor;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 * Generate Interceptor Element
 */
public class InterceptorSF extends StoreFactoryBase {

    private static Log log = LogFactory.getLog(InterceptorSF.class);

    @Override
    public void store(PrintWriter aWriter, int indent, Object aElement)
            throws Exception {
        if (aElement instanceof StaticMembershipInterceptor) {
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
     * Store the specified Interceptor child.
     *
     * @param aWriter
     *            PrintWriter to which we are storing
     * @param indent
     *            Number of spaces to indent this element
     * @param aInterceptor
     *            Channel whose properties are being stored
     *
     * @exception Exception
     *                if an exception occurs while storing
     */
    @Override
    public void storeChildren(PrintWriter aWriter, int indent, Object aInterceptor,
            StoreDescription parentDesc) throws Exception {
        if (aInterceptor instanceof StaticMembershipInterceptor) {
            ChannelInterceptor interceptor = (ChannelInterceptor) aInterceptor;
            // Store nested <Member> elements
            storeElementArray(aWriter, indent + 2, interceptor.getMembers());
       }
    }
}