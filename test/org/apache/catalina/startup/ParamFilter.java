
package org.apache.catalina.startup;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Test Mock to check Filter Annotations
 * @author Peter Rossbach
 */
@WebFilter(value = "/param", filterName = "paramFilter", dispatcherTypes = {
        DispatcherType.ERROR, DispatcherType.ASYNC }, initParams = { @WebInitParam(name = "message", value = "Servlet says: ") })
public class ParamFilter implements Filter {

    private FilterConfig _filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        out.print(_filterConfig.getInitParameter("message"));
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // destroy
    }
}
