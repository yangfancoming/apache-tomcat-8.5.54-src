
package org.apache.tomcat.unittest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;


public class TesterLeakingServlet2 extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(TesterLeakingServlet2.class);


    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        List<TesterCounter> counterList = TesterThreadScopedHolder.getFromHolder();
        TesterCounter counter;
        if (counterList == null) {
            log.info("Adding thread local to thread " + Thread.currentThread().getName());
            counter = new TesterCounter();
            TesterThreadScopedHolder.saveInHolder(Arrays.asList(counter));
        } else {
            counter = counterList.get(0);
        }

        counter.increment();
        response.getWriter().println(
                "The current thread served this servlet "
                        + counter.getCount() + " times");
    }
}