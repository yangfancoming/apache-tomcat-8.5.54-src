

package org.apache.catalina.startup;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TesterServletWithLifeCycleMethods extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String result;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().print(result);
    }

    @PostConstruct
    protected void postConstruct() {
        result = "postConstruct()";
    }

    @PreDestroy
    protected void preDestroy() {
        result = "preDestroy()";
    }

    protected void postConstruct1() {
        result = "postConstruct1()";
    }

    protected void preDestroy1() {
        result = "preDestroy1()";
    }
}
