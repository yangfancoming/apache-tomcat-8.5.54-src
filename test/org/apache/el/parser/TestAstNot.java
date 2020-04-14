
package org.apache.el.parser;

import javax.el.ELProcessor;

import org.junit.Assert;
import org.junit.Test;

public class TestAstNot {

    @Test
    public void test01() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.eval("!null");
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void test02() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.eval("!true");
        Assert.assertEquals(Boolean.FALSE, result);
    }

    @Test
    public void test03() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.eval("!false");
        Assert.assertEquals(Boolean.TRUE, result);
    }
}
