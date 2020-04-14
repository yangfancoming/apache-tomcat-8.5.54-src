
package org.apache.el.parser;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ELManager;
import javax.el.ELProcessor;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import org.junit.Assert;
import org.junit.Test;

public class TestAstSetData {

    private static final Set<String> simpleSet = new HashSet<>();
    private static final Set<Object> nestedSet = new HashSet<>();

    static {
        simpleSet.add("a");
        simpleSet.add("b");
        simpleSet.add("c");

        nestedSet.add(simpleSet);
        nestedSet.add(Collections.EMPTY_SET);
        nestedSet.add("d");
    }


    @Test
    public void testSimple01() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.getValue("{'a','b','c'}", Set.class);
        Assert.assertEquals(simpleSet, result);
    }


    @Test
    public void testSimple02() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.getValue("{}", Set.class);
        Assert.assertEquals(Collections.EMPTY_SET, result);
    }


    @Test
    public void testNested01() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.getValue("{{'a','b','c'},{},'d'}", Set.class);
        Assert.assertEquals(nestedSet, result);
    }


    @Test
    public void testGetType() {
        ELProcessor processor = new ELProcessor();
        ELContext context = processor.getELManager().getELContext();
        ExpressionFactory factory = ELManager.getExpressionFactory();

        ValueExpression ve = factory.createValueExpression(
                context, "${{'a','b','c'}}", Set.class);

        Assert.assertEquals(Set.class, ve.getType(context));
        Assert.assertEquals(simpleSet, ve.getValue(context));
    }
}
