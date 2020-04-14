
package org.apache.jasper.el;

import javax.el.ELContext;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.VariableResolver;

@Deprecated
public final class VariableResolverImpl implements VariableResolver {

    private final ELContext ctx;

    public VariableResolverImpl(ELContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Object resolveVariable(String pName) throws ELException {
        return this.ctx.getELResolver().getValue(this.ctx, null, pName);
    }

}
