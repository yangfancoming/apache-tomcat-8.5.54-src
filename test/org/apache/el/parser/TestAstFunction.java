
package org.apache.el.parser;

import javax.el.ELProcessor;

import org.junit.Assert;
import org.junit.Test;

public class TestAstFunction {

    @Test
    public void testImport01() {
        ELProcessor processor = new ELProcessor();
        Object result = processor.getValue("Integer(1000)", Integer.class);
        Assert.assertEquals(Integer.valueOf(1000), result);
    }

    @Test
    public void testImport02() {
        ELProcessor processor = new ELProcessor();
        processor.getELManager().getELContext().getImportHandler()
                .importStatic("java.lang.Integer.valueOf");
        Object result = processor.getValue("valueOf(1000)", Integer.class);
        Assert.assertEquals(Integer.valueOf(1000), result);
    }
}
