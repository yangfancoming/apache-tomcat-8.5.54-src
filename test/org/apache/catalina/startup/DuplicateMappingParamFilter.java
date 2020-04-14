
package org.apache.catalina.startup;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Test Mock with wrong Annotation!
 *
 * @author Peter Rossbach
 *
 */
@WebFilter(value = "/param", filterName="paramDFilter",
        urlPatterns = { "/param1" , "/param2" })
public class DuplicateMappingParamFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // NO-OP
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // destroy
    }
}

