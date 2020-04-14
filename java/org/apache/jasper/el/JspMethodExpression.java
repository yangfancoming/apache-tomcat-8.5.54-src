
package org.apache.jasper.el;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.MethodExpression;
import javax.el.MethodInfo;
import javax.el.MethodNotFoundException;
import javax.el.PropertyNotFoundException;

public final class JspMethodExpression extends MethodExpression implements
        Externalizable {

    private String mark;

    private MethodExpression target;

    public JspMethodExpression() {
        super();
    }

    public JspMethodExpression(String mark, MethodExpression target) {
        this.target = target;
        this.mark = mark;
    }

    @Override
    public MethodInfo getMethodInfo(ELContext context)
            throws NullPointerException, PropertyNotFoundException,
            MethodNotFoundException, ELException {
        context.notifyBeforeEvaluation(getExpressionString());
        try {
            MethodInfo result = this.target.getMethodInfo(context);
            context.notifyAfterEvaluation(getExpressionString());
            return result;
        } catch (MethodNotFoundException e) {
            if (e instanceof JspMethodNotFoundException) throw e;
            throw new JspMethodNotFoundException(this.mark, e);
        } catch (PropertyNotFoundException e) {
            if (e instanceof JspPropertyNotFoundException) throw e;
            throw new JspPropertyNotFoundException(this.mark, e);
        } catch (ELException e) {
            if (e instanceof JspELException) throw e;
            throw new JspELException(this.mark, e);
        }
    }

    @Override
    public Object invoke(ELContext context, Object[] params)
            throws NullPointerException, PropertyNotFoundException,
            MethodNotFoundException, ELException {
        context.notifyBeforeEvaluation(getExpressionString());
        try {
            Object result = this.target.invoke(context, params);
            context.notifyAfterEvaluation(getExpressionString());
            return result;
        } catch (MethodNotFoundException e) {
            if (e instanceof JspMethodNotFoundException) throw e;
            throw new JspMethodNotFoundException(this.mark, e);
        } catch (PropertyNotFoundException e) {
            if (e instanceof JspPropertyNotFoundException) throw e;
            throw new JspPropertyNotFoundException(this.mark, e);
        } catch (ELException e) {
            if (e instanceof JspELException) throw e;
            throw new JspELException(this.mark, e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return this.target.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.target.hashCode();
    }

    @Override
    public String getExpressionString() {
        return this.target.getExpressionString();
    }

    @Override
    public boolean isLiteralText() {
        return this.target.isLiteralText();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(this.mark);
        out.writeObject(this.target);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        this.mark = in.readUTF();
        this.target = (MethodExpression) in.readObject();
    }

}
