
package org.apache.tomcat.unittest;

import java.util.List;

public class TesterThreadScopedHolder {
    private static final ThreadLocal<List<TesterCounter>> threadLocal =
            new ThreadLocal<>();

    public static void saveInHolder(List<TesterCounter> o) {
        threadLocal.set(o);
    }

    public static List<TesterCounter> getFromHolder() {
        return threadLocal.get();
    }
}