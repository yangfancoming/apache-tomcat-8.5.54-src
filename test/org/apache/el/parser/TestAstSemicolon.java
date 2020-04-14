
package org.apache.el.parser;

import javax.el.ELContext;
import javax.el.ELManager;
import javax.el.ELProcessor;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.junit.Assert;
import org.junit.Test;

public class TestAstSemicolon {

    @Test
    public void testGetValue01() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.getValue("1;2", String.class);
        Assert.assertEquals("2", result);
    }


    @Test
    public void testGetValue02() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.getValue("1;2", Integer.class);
        Assert.assertEquals(Integer.valueOf(2), result);
    }


    @Test
    public void testGetValue03() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.getValue("1;2 + 3", Integer.class);
        Assert.assertEquals(Integer.valueOf(5), result);
    }


    @Test
    public void testGetType() {
        ELProcessor processor = new ELProcessor();
        ELContext context = processor.getELManager().getELContext();
        ExpressionFactory factory = ELManager.getExpressionFactory();

        ValueExpression ve = factory.createValueExpression(
                context, "${1+1;2+2}", Integer.class);

        Assert.assertEquals(Number.class, ve.getType(context));
        Assert.assertEquals(Integer.valueOf(4), ve.getValue(context));
    }
}
