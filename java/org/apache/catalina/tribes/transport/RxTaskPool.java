

package org.apache.catalina.tribes.transport;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A very simple thread pool class.  The pool size is set at
 * construction time and remains fixed.  Threads are cycled
 * through a FIFO idle queue.
 * @version 1.0
 */
public class RxTaskPool {

    final List<AbstractRxTask> idle = new LinkedList<>();
    final List<AbstractRxTask> used = new LinkedList<>();

    final Object mutex = new Object();
    boolean running = true;

    private int maxTasks;
    private int minTasks;

    private final TaskCreator creator;


    public RxTaskPool (int maxTasks, int minTasks, TaskCreator creator) throws Exception {
        // fill up the pool with worker threads
        this.maxTasks = maxTasks;
        this.minTasks = minTasks;
        this.creator = creator;
    }

    protected void configureTask(AbstractRxTask task) {
        synchronized (task) {
            task.setTaskPool(this);
//            task.setName(task.getClass().getName() + "[" + inc() + "]");
//            task.setDaemon(true);
//            task.setPriority(Thread.MAX_PRIORITY);
//            task.start();
        }
    }

    /**
     * Find an idle worker thread, if any.  Could return null.
     * @return a worker
     */
    public AbstractRxTask getRxTask()
    {
        AbstractRxTask worker = null;
        synchronized (mutex) {
            while ( worker == null && running ) {
                if (idle.size() > 0) {
                    try {
                        worker = idle.remove(0);
                    } catch (java.util.NoSuchElementException x) {
                        //this means that there are no available workers
                        worker = null;
                    }
                } else if ( used.size() < this.maxTasks && creator != null) {
                    worker = creator.createRxTask();
                    configureTask(worker);
                } else {
                    try {
                        mutex.wait();
                    } catch (java.lang.InterruptedException x) {
                        Thread.currentThread().interrupt();
                    }
                }
            }//while
            if ( worker != null ) used.add(worker);
        }
        return worker;
    }

    public int available() {
        return idle.size();
    }

    /**
     * Called by the worker thread to return itself to the
     * idle pool.
     * @param worker The worker
     */
    public void returnWorker (AbstractRxTask worker) {
        if ( running ) {
            synchronized (mutex) {
                used.remove(worker);
                //if ( idle.size() < minThreads && !idle.contains(worker)) idle.add(worker);
                if ( idle.size() < maxTasks && !idle.contains(worker)) idle.add(worker); //let max be the upper limit
                else {
                    worker.setDoRun(false);
                    synchronized (worker){worker.notifyAll();}
                }
                mutex.notifyAll();
            }
        } else {
            worker.setDoRun(false);
            synchronized (worker){worker.notifyAll();}
        }
    }

    public int getMaxThreads() {
        return maxTasks;
    }

    public int getMinThreads() {
        return minTasks;
    }

    public void stop() {
        running = false;
        synchronized (mutex) {
            Iterator<AbstractRxTask> i = idle.iterator();
            while ( i.hasNext() ) {
                AbstractRxTask worker = i.next();
                returnWorker(worker);
                i.remove();
            }
        }
    }

    public void setMaxTasks(int maxThreads) {
        this.maxTasks = maxThreads;
    }

    public void setMinTasks(int minThreads) {
        this.minTasks = minThreads;
    }

    public TaskCreator getTaskCreator() {
        return this.creator;
    }

    public static interface TaskCreator  {
        public AbstractRxTask createRxTask();
    }
}
