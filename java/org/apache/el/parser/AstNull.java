
/* Generated By:JJTree: Do not edit this line. AstNull.java */

package org.apache.el.parser;

import javax.el.ELException;

import org.apache.el.lang.EvaluationContext;


/**
 * @author Jacob Hookom [jacob@hookom.net]
 */
public final class AstNull extends SimpleNode {
    public AstNull(int id) {
        super(id);
    }

    @Override
    public Class<?> getType(EvaluationContext ctx)
            throws ELException {
        return null;
    }

    @Override
    public Object getValue(EvaluationContext ctx)
            throws ELException {
        return null;
    }
}
