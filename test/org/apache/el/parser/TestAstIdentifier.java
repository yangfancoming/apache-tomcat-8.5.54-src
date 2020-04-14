
package org.apache.el.parser;

import javax.el.ELProcessor;

import org.junit.Assert;
import org.junit.Test;

public class TestAstIdentifier {

    @Test
    public void testImport01() {
        ELProcessor processor = new ELProcessor();
        Object result =
                processor.getValue("Integer.MAX_VALUE",
                        Integer.class);
        Assert.assertEquals(Integer.valueOf(Integer.MAX_VALUE), result);
    }


    @Test
    public void testImport02() {
        ELProcessor processor = new ELProcessor();
        processor.getELManager().getELContext().getImportHandler().importStatic(
                "java.lang.Integer.MAX_VALUE");
        Object result =
                processor.getValue("MAX_VALUE",
                        Integer.class);
        Assert.assertEquals(Integer.valueOf(Integer.MAX_VALUE), result);
    }
}
