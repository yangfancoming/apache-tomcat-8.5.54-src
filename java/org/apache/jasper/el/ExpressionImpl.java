
package org.apache.jasper.el;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.Expression;
import javax.servlet.jsp.el.VariableResolver;

@Deprecated
public final class ExpressionImpl extends Expression {

    private final ValueExpression ve;
    private final ExpressionFactory factory;


    public ExpressionImpl(ValueExpression ve, ExpressionFactory factory) {
        this.ve = ve;
        this.factory = factory;
    }

    @Override
    public Object evaluate(VariableResolver vResolver) throws ELException {
        ELContext ctx =
                new ELContextImpl(new ELResolverImpl(vResolver, factory));
        return ve.getValue(ctx);
    }
}
