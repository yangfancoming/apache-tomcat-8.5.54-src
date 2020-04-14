
package javax.el;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class TestUtil {

    @Test
    public void test01() {
        ELProcessor processor = new ELProcessor();
        processor.defineBean("sb", new StringBuilder());
        Assert.assertEquals("a", processor.eval("sb.append('a'); sb.toString()"));
    }


    @Test
    public void test02() {
        ELProcessor processor = new ELProcessor();
        processor.getELManager().importClass("java.util.Date");
        Date result = (Date) processor.eval("Date(86400)");
        Assert.assertEquals(86400, result.getTime());
    }


    @Test
    public void testBug56425a() {
        ELProcessor processor = new ELProcessor();
        processor.defineBean("string", "a-b-c-d");
        Assert.assertEquals("a_b_c_d", processor.eval("string.replace(\"-\",\"_\")"));
    }

    @Test
    public void testBug56425b() {
        ELProcessor processor = new ELProcessor();
        processor.defineBean("string", "Not used. Any value is fine here");
        Assert.assertEquals("5", processor.eval("string.valueOf(5)"));
    }
}
