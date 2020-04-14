
package org.apache.tomcat.unittest;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class TesterCounter {

    private static final Log log = LogFactory.getLog(TesterCounter.class);

    static {
        log.info("TestCounter loaded by " + TesterCounter.class.getClassLoader() +
                " in thread " + Thread.currentThread().getName());
    }

    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}