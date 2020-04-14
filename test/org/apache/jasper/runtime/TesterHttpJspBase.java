
package org.apache.jasper.runtime;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.HttpJspPage;

public abstract class TesterHttpJspBase extends GenericServlet implements HttpJspPage {

    private static final long serialVersionUID = 1L;


    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        _jspService((HttpServletRequest) req, (HttpServletResponse) res);
    }


    @Override
    public abstract void _jspService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        jspInit();
    }


    @Override
    public void jspInit() {
        // NO-OP by default
    }


    @Override
    public void destroy() {
        super.destroy();
        jspDestroy();
    }


    @Override
    public void jspDestroy() {
        // NO-OP by default
    }
}
