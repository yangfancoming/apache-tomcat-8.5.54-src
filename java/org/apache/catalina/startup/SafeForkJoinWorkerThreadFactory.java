
package org.apache.catalina.startup;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * Provides a {@link ForkJoinWorkerThreadFactory} that provides {@link
 * ForkJoinWorkerThread}s that won't trigger memory leaks due to retained
 * references to web application class loaders.
 * <p>
 * Note: This class must be available on the boot strap class path for it to be
 * visible to {@link ForkJoinPool}.
 */
public class SafeForkJoinWorkerThreadFactory implements ForkJoinWorkerThreadFactory {

    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        return new SafeForkJoinWorkerThread(pool);
    }


    private static class SafeForkJoinWorkerThread extends ForkJoinWorkerThread {

        protected SafeForkJoinWorkerThread(ForkJoinPool pool) {
            super(pool);
            setContextClassLoader(ForkJoinPool.class.getClassLoader());
        }
    }
}