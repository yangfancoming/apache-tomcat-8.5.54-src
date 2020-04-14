

package org.apache.catalina.storeconfig;

import java.io.PrintWriter;

import org.apache.catalina.Loader;
import org.apache.catalina.loader.WebappLoader;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 * Store Loader Element.
 */
public class LoaderSF extends StoreFactoryBase {

    private static Log log = LogFactory.getLog(LoaderSF.class);

    /**
     * Store the only the Loader elements, when not default
     *
     * @see NamingResourcesSF#storeChildren(PrintWriter, int, Object, StoreDescription)
     */
    @Override
    public void store(PrintWriter aWriter, int indent, Object aElement)
            throws Exception {
        StoreDescription elementDesc = getRegistry().findDescription(
                aElement.getClass());
        if (elementDesc != null) {
            Loader loader = (Loader) aElement;
            if (!isDefaultLoader(loader)) {
                if (log.isDebugEnabled())
                    log.debug("store " + elementDesc.getTag() + "( " + aElement
                            + " )");
                getStoreAppender().printIndent(aWriter, indent + 2);
                getStoreAppender().printTag(aWriter, indent + 2, loader,
                        elementDesc);
            }
        } else {
            if (log.isWarnEnabled()) {
                log
                        .warn("Descriptor for element"
                                + aElement.getClass()
                                + " not configured or element class not StandardManager!");
            }
        }
    }

    /**
     * Is this an instance of the default <code>Loader</code> configuration,
     * with all-default properties?
     *
     * @param loader
     *            Loader to be tested
     * @return <code>true</code> if this is an instance of the default loader
     */
    protected boolean isDefaultLoader(Loader loader) {

        if (!(loader instanceof WebappLoader)) {
            return false;
        }
        WebappLoader wloader = (WebappLoader) loader;
        if ((wloader.getDelegate() != false)
                || !wloader.getLoaderClass().equals(
                        "org.apache.catalina.loader.WebappClassLoader")) {
            return false;
        }
        return true;
    }
}
