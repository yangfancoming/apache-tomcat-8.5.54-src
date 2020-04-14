
package org.apache.el.parser;

import javax.el.ELProcessor;

import org.junit.Assert;
import org.junit.Test;

public class TestAstChoice {

    @Test
    public void test01() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.eval("null?1:2");
        Assert.assertEquals(Long.valueOf(2), result);
    }
}
