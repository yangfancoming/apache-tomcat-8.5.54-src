
package org.apache.el.stream;

import javax.el.ELException;
import javax.el.LambdaExpression;

import org.apache.el.util.MessageFactory;

public class Optional {

    private final Object obj;

    static final Optional EMPTY = new Optional(null);

    Optional(Object obj) {
        this.obj = obj;
    }


    public Object get() throws ELException {
        if (obj == null) {
            throw new ELException(MessageFactory.get("stream.optional.empty"));
        } else {
            return obj;
        }
    }


    public void ifPresent(LambdaExpression le) {
        if (obj != null) {
            le.invoke(obj);
        }
    }


    public Object orElse(Object other) {
        if (obj == null) {
            return other;
        } else {
            return obj;
        }
    }


    public Object orElseGet(Object le) {
        if (obj == null) {
            // EL 3.0 specification says parameter is LambdaExpression but it
            // may already have been evaluated. If that is the case, the
            // original parameter will have been checked to ensure it was a
            // LambdaExpression before it was evaluated.

            if (le instanceof LambdaExpression) {
                return ((LambdaExpression) le).invoke((Object[]) null);
            } else {
                return le;
            }
        } else {
            return obj;
        }
    }
}
