
/* Generated By:JJTree: Do not edit this line. AstOr.java */

package org.apache.el.parser;

import javax.el.ELException;

import org.apache.el.lang.EvaluationContext;


/**
 * @author Jacob Hookom [jacob@hookom.net]
 */
public final class AstOr extends BooleanNode {
    public AstOr(int id) {
        super(id);
    }

    @Override
    public Object getValue(EvaluationContext ctx)
            throws ELException {
        Object obj = this.children[0].getValue(ctx);
        Boolean b = coerceToBoolean(ctx, obj, true);
        if (b.booleanValue()) {
            return b;
        }
        obj = this.children[1].getValue(ctx);
        b = coerceToBoolean(ctx, obj, true);
        return b;
    }
}
