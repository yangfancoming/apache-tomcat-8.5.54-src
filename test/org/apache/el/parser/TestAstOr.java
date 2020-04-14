
package org.apache.el.parser;

import javax.el.ELProcessor;

import org.junit.Assert;
import org.junit.Test;

public class TestAstOr {

    @Test
    public void test01() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.eval("true || true");
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void test02() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.eval("true || null");
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void test03() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.eval("null || true");
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void test04() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.eval("null || null");
        Assert.assertEquals(Boolean.FALSE, result);
    }
}
