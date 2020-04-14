
package org.apache.tomcat.unittest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class TesterLeakingServlet1 extends HttpServlet {

    private static final Log log = LogFactory.getLog(TesterLeakingServlet1.class);

    private static final long serialVersionUID = 1L;

    private static ThreadLocal<TesterCounter> myThreadLocal = new ThreadLocal<>();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        TesterCounter counter = myThreadLocal.get();
        if (counter == null) {
            log.info("Adding thread local to thread " + Thread.currentThread().getName());
            counter = new TesterCounter();
            myThreadLocal.set(counter);
        }

        counter.increment();
        response.getWriter().println(
                "The current thread served this servlet "
                        + counter.getCount() + " times");
    }
}