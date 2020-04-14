
package javax.el;

import org.junit.Assert;
import org.junit.Test;

public class TestELProcessor {

    @Test
    public void testDefineBean01() {
        ELProcessor elp = new ELProcessor();
        elp.defineBean("bean01", new TesterBean("name01"));
        Assert.assertEquals("name01", elp.eval("bean01.name"));
    }


    @Test(expected=ELException.class)
    public void testEval01() {
        ELProcessor elp = new ELProcessor();
        elp.eval("${1+1}");
    }


    @Test(expected=ELException.class)
    public void testEval02() {
        ELProcessor elp = new ELProcessor();
        elp.eval("#{1+1}");
    }


    @Test
    public void testEval03() {
        ELProcessor elp = new ELProcessor();
        // Note \ is escaped as \\ in Java source code
        String result = (String) elp.eval("'\\\\'");
        Assert.assertEquals("\\", result);
    }


    @Test
    public void testDefineFunctionMethod01() throws Exception {
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("fn", "toBoolean",
                Boolean.class.getMethod("valueOf", String.class));
        Assert.assertEquals(Boolean.valueOf(true),
                elp.eval("fn:toBoolean(true)"));
    }


    @Test
    public void testDefineFunctionName01() throws Exception {
        ELProcessor elp = new ELProcessor();
        // java.lang should be automatically imported so no need for full class
        // name
        elp.defineFunction("fn", "toBoolean", "Boolean", "valueOf");
        Assert.assertEquals(Boolean.valueOf(true),
                elp.eval("fn:toBoolean(true)"));
    }


    @Test
    public void testDefineFunctionName02() throws Exception {
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("fn", "test", "java.lang.Integer", "Integer valueOf(int)");
        Assert.assertEquals(Integer.valueOf(1), elp.eval("fn:test(1)"));
    }


    @Test
    public void testDefineFunctionName03() throws Exception {
        TesterFunctions.resetCallList();
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("fn", "test", "javax.el.TesterFunctions", "void doIt()");
        elp.eval("fn:test()");
        Assert.assertEquals("A", TesterFunctions.getCallList());
    }


    @Test
    public void testDefineFunctionName04() throws Exception {
        TesterFunctions.resetCallList();
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("fn", "test", "javax.el.TesterFunctions", "void doIt(int)");
        elp.eval("fn:test(5)");
        Assert.assertEquals("B", TesterFunctions.getCallList());
    }


    @Test
    public void testDefineFunctionName05() throws Exception {
        TesterFunctions.resetCallList();
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("fn", "test", "javax.el.TesterFunctions", "void doIt(Integer)");
        elp.eval("fn:test(null)");
        Assert.assertEquals("C", TesterFunctions.getCallList());
    }


    @Test
    public void testDefineFunctionName06() throws Exception {
        TesterFunctions.resetCallList();
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("", "", "javax.el.TesterFunctions", "void doIt(int)");
        elp.eval("doIt(5)");
        Assert.assertEquals("B", TesterFunctions.getCallList());
    }


    @Test
    public void testDefineFunctionName07() throws Exception {
        TesterFunctions.resetCallList();
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("fn", "", "javax.el.TesterFunctions", "void doIt(int)");
        elp.eval("fn:doIt(5)");
        Assert.assertEquals("B", TesterFunctions.getCallList());
    }


    @Test
    public void testDefineFunctionName08() throws Exception {
        TesterFunctions.resetCallList();
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("fn", "", "javax.el.TesterFunctions", "void doIt(int[])");
        elp.eval("fn:doIt([5].stream().toArray())");
        Assert.assertEquals("D", TesterFunctions.getCallList());
    }


    @Test
    public void testDefineFunctionName09() throws Exception {
        TesterFunctions.resetCallList();
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("fn", "", "javax.el.TesterFunctions", "void doIt(int[][])");
        elp.eval("fn:doIt([[5].stream().toArray()].stream().toArray())");
        Assert.assertEquals("E", TesterFunctions.getCallList());
    }


    @Test
    public void testDefineFunctionName10() throws Exception {
        TesterFunctions.resetCallList();
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("fn", "test1", "java.lang.Integer", "Integer valueOf(int)");
        elp.defineFunction("fn", "test2", "javax.el.TesterFunctions", "void doIt(Integer[])");
        elp.eval("fn:test2([fn:test1(1), fn:test1(2)].stream().toArray())");
        Assert.assertEquals("F", TesterFunctions.getCallList());
    }


    @Test
    public void testDefineFunctionName11() throws Exception {
        TesterFunctions.resetCallList();
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("fn", "test1", "java.lang.Integer", "Integer valueOf(int)");
        elp.defineFunction("fn", "test2", "javax.el.TesterFunctions", "void doIt(Integer[][])");
        elp.eval("fn:test2([[fn:test1(1), fn:test1(2)].stream().toArray()].stream().toArray())");
        Assert.assertEquals("G", TesterFunctions.getCallList());
    }


    @Test
    public void testDefineFunctionName12() throws Exception {
        TesterFunctions.resetCallList();
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("fn", "test", "javax.el.TesterFunctions", "void doIt(long...)");
        elp.eval("fn:test(1,2)");
        Assert.assertEquals("H", TesterFunctions.getCallList());
    }


    @Test
    public void testDefineFunctionName13() throws Exception {
        TesterFunctions.resetCallList();
        ELProcessor elp = new ELProcessor();
        elp.defineFunction("fn", "test", "javax.el.TesterFunctions", "void doIt(Object...)");
        elp.eval("fn:test(null, null)");
        Assert.assertEquals("I", TesterFunctions.getCallList());
    }


    @Test
    public void testPrimitiveArray01() {
        ELProcessor elp = new ELProcessor();
        TesterBean bean01= new TesterBean("bean01");
        elp.defineBean("bean01", bean01);
        elp.defineBean("bean02", new TesterBean("bean02"));

        Object result = elp.eval("bean02.setValueC(bean01.valueB);bean02.valueC");

        Integer[] resultArray = (Integer[]) result;
        Assert.assertEquals(bean01.getValueB().length, resultArray.length);
        for (int i = 0; i < resultArray.length; i++) {
            Assert.assertEquals(bean01.getValueB()[i], resultArray[i].intValue());
        }
    }
}
