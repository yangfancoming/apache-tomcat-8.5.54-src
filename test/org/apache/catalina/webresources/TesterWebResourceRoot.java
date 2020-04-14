
package org.apache.catalina.webresources;

import java.net.URL;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.LifecycleState;
import org.apache.tomcat.unittest.TesterContext;

/**
 * Minimal implementation for use in unit tests that supports main and pre
 * resources.
 */
public class TesterWebResourceRoot extends StandardRoot {

    public TesterWebResourceRoot() {
        super();
        setCachingAllowed(false);
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        // NO-OP
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return null;
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        // NO-OP
    }

    @Override
    public void initInternal() throws LifecycleException {
        // NO-OP
    }

    @Override
    public void startInternal() throws LifecycleException {
        setState(LifecycleState.STARTING);
    }

    @Override
    public void stopInternal() throws LifecycleException {
        setState(LifecycleState.STOPPING);
    }

    @Override
    public void destroyInternal() throws LifecycleException {
        // NO-OP
    }

    @Override
    public LifecycleState getState() {
        return LifecycleState.STARTED;
    }

    @Override
    public String getStateName() {
        return null;
    }

    @Override
    public Context getContext() {
        return new TesterContext();
    }

    @Override
    public void setContext(Context context) {
        // NO-OP
    }

    @Override
    public void createWebResourceSet(ResourceSetType type, String webAppPath,
            URL url, String internalPath) {
        // NO-OP
    }

    @Override
    public void createWebResourceSet(ResourceSetType type, String webAppPath,
            String base, String archivePath, String internalPath) {
        // NO-OP
    }

    @Override
    public void setAllowLinking(boolean allowLinking) {
        // NO-OP
    }

    @Override
    public boolean getAllowLinking() {
        return false;
    }

    @Override
    public void setCachingAllowed(boolean cachingAllowed) {
        // NO-OP
    }

    @Override
    public boolean isCachingAllowed() {
        return false;
    }

    @Override
    public void setCacheTtl(long ttl) {
        // NO-OP
    }

    @Override
    public long getCacheTtl() {
        return 0;
    }

    @Override
    public void setCacheMaxSize(long cacheMaxSize) {
        // NO-OP
    }

    @Override
    public long getCacheMaxSize() {
        return 0;
    }

    @Override
    public void setCacheObjectMaxSize(int cacheObjectMaxSize) {
        // NO-OP
    }

    @Override
    public int getCacheObjectMaxSize() {
        return 0;
    }

    @Override
    public void backgroundProcess() {
        // NO-OP
    }
}
