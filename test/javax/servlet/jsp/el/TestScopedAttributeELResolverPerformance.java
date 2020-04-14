
package javax.servlet.jsp.el;

import javax.el.ELContext;
import javax.el.ELManager;
import javax.el.ELResolver;
import javax.el.StandardELContext;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.TesterPageContext;

import org.junit.Test;

public class TestScopedAttributeELResolverPerformance {

    /*
     * With the caching of NotFound responses this test takes ~20ms. Without the
     * caching it takes ~6s.
     */
    @Test
    public void testGetValuePerformance() throws Exception {

        ELContext context = new StandardELContext(ELManager.getExpressionFactory());

        context.putContext(JspContext.class, new TesterPageContext());

        ELResolver resolver = new ScopedAttributeELResolver();

        for (int i = 0; i < 100000; i++) {
            resolver.getValue(context, null, "unknown");
        }
    }
}
