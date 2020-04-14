
package org.apache.tomcat.websocket;

import java.util.HashSet;
import java.util.Set;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.ExceptionUtils;
import org.apache.tomcat.util.res.StringManager;

/**
 * Provides a background processing mechanism that triggers roughly once a
 * second. The class maintains a thread that only runs when there is at least
 * one instance of {@link BackgroundProcess} registered.
 */
public class BackgroundProcessManager {

    private final Log log =
            LogFactory.getLog(BackgroundProcessManager.class);
    private static final StringManager sm =
            StringManager.getManager(BackgroundProcessManager.class);
    private static final BackgroundProcessManager instance;


    static {
        instance = new BackgroundProcessManager();
    }


    public static BackgroundProcessManager getInstance() {
        return instance;
    }

    private final Set<BackgroundProcess> processes = new HashSet<>();
    private final Object processesLock = new Object();
    private WsBackgroundThread wsBackgroundThread = null;

    private BackgroundProcessManager() {
        // Hide default constructor
    }


    public void register(BackgroundProcess process) {
        synchronized (processesLock) {
            if (processes.size() == 0) {
                wsBackgroundThread = new WsBackgroundThread(this);
                wsBackgroundThread.setContextClassLoader(
                        this.getClass().getClassLoader());
                wsBackgroundThread.setDaemon(true);
                wsBackgroundThread.start();
            }
            processes.add(process);
        }
    }


    public void unregister(BackgroundProcess process) {
        synchronized (processesLock) {
            processes.remove(process);
            if (wsBackgroundThread != null && processes.size() == 0) {
                wsBackgroundThread.halt();
                wsBackgroundThread = null;
            }
        }
    }


    private void process() {
        Set<BackgroundProcess> currentProcesses = new HashSet<>();
        synchronized (processesLock) {
            currentProcesses.addAll(processes);
        }
        for (BackgroundProcess process : currentProcesses) {
            try {
                process.backgroundProcess();
            } catch (Throwable t) {
                ExceptionUtils.handleThrowable(t);
                log.error(sm.getString(
                        "backgroundProcessManager.processFailed"), t);
            }
        }
    }


    /*
     * For unit testing.
     */
    int getProcessCount() {
        synchronized (processesLock) {
            return processes.size();
        }
    }


    void shutdown() {
        synchronized (processesLock) {
            processes.clear();
            if (wsBackgroundThread != null) {
                wsBackgroundThread.halt();
                wsBackgroundThread = null;
            }
        }
    }


    private static class WsBackgroundThread extends Thread {

        private final BackgroundProcessManager manager;
        private volatile boolean running = true;

        public WsBackgroundThread(BackgroundProcessManager manager) {
            setName("WebSocket background processing");
            this.manager = manager;
        }

        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Ignore
                }
                manager.process();
            }
        }

        public void halt() {
            setName("WebSocket background processing - stopping");
            running = false;
        }
    }
}
