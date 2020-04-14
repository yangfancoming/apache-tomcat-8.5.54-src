
package org.apache.el.parser;

import javax.el.ELContext;
import javax.el.ELManager;
import javax.el.ELProcessor;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.junit.Assert;
import org.junit.Test;

public class TestAstAssign {

    @Test
    public void testGetValue01() {
        ELProcessor processor = new ELProcessor();
        processor.defineBean("bean01", new TesterBeanB());

        Object result = processor.getValue(
                "bean01.text = 'hello'", String.class);

        Assert.assertEquals("hello", result);
    }


    @Test
    public void testGetValue02() {
        ELProcessor processor = new ELProcessor();
        processor.defineBean("bean01", new TesterBeanB());

        Object result = processor.getValue(
                "bean01.text = 'hello'; bean01.text", String.class);

        Assert.assertEquals("hello", result);
    }



    @Test
    public void testGetType01() {
        ELProcessor processor = new ELProcessor();
        ELContext context = processor.getELManager().getELContext();
        ExpressionFactory factory = ELManager.getExpressionFactory();

        processor.defineBean("bean01", new TesterBeanB());
        ValueExpression ve = factory.createValueExpression(
                context, "${bean01.text = 'hello'}", String.class);

        Assert.assertEquals(String.class, ve.getType(context));
        Assert.assertEquals("hello", ve.getValue(context));
    }


    @Test
    public void testGetType02() {
        ELProcessor processor = new ELProcessor();
        ELContext context = processor.getELManager().getELContext();
        ExpressionFactory factory = ELManager.getExpressionFactory();

        processor.defineBean("bean01", new TesterBeanB());
        ValueExpression ve = factory.createValueExpression(
                context, "${bean01.text = 'hello'; bean01.text}", String.class);

        Assert.assertEquals(String.class, ve.getType(context));
        Assert.assertEquals("hello", ve.getValue(context));
    }
}
