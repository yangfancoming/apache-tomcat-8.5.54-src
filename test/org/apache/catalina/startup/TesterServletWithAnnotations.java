

package org.apache.catalina.startup;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TesterServletWithAnnotations extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource
    private int envEntry1;

    private int envEntry2;

    private int envEntry3;

    private int envEntry4;

    @Resource(name = "envEntry5")
    private int envEntry5;

    private int envEntry6;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().print("envEntry1: " + envEntry1);
        resp.getWriter().print(" envEntry2: " + envEntry2);
        resp.getWriter().print(" envEntry3: " + envEntry3);
        resp.getWriter().print(" envEntry4: " + envEntry4);
        resp.getWriter().print(" envEntry5: " + envEntry5);
        resp.getWriter().print(" envEntry6: " + envEntry6);
    }

    public void setEnvEntry2(int envEntry2) {
        this.envEntry2 = envEntry2;
    }

    @Resource
    public void setEnvEntry3(int envEntry3) {
        this.envEntry3 = envEntry3;
    }

    @Resource
    public void setEnvEntry4(int envEntry4) {
        this.envEntry4 = envEntry4;
    }

    @Resource(name = "envEntry6")
    public void setEnvEntry6(int envEntry6) {
        this.envEntry6 = envEntry6;
    }
}