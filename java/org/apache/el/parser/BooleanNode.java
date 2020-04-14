
package org.apache.el.parser;

import javax.el.ELException;

import org.apache.el.lang.EvaluationContext;

/**
 * @author Jacob Hookom [jacob@hookom.net]
 */
public abstract class BooleanNode extends SimpleNode {

    public BooleanNode(int i) {
        super(i);
    }

    @Override
    public Class<?> getType(EvaluationContext ctx)
            throws ELException {
        return Boolean.class;
    }
}
