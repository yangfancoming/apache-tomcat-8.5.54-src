
package org.apache.jasper.compiler;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import org.apache.jasper.compiler.Node.PageDirective;

public class TestNode {

    /*
     * https://bz.apache.org/bugzilla/show_bug.cgi?id=57099
     */
    @Test(expected=IllegalArgumentException.class)
    public void testPageDirectiveImport01() {
        doTestPageDirectiveImport("java.io.*;\r\n\timport java.net.*");
    }

    @Test
    public void testPageDirectiveImport02() {
        doTestPageDirectiveImport("a,b,c");
    }

    @Test
    public void testPageDirectiveImport03() {
        doTestPageDirectiveImport(" a , b , c ");
    }

    @Test
    public void testPageDirectiveImport04() {
        doTestPageDirectiveImport(" a\n , \r\nb , \nc\r ");
    }

    @Test
    public void testPageDirectiveImport05() {
        doTestPageDirectiveImport("java.util.List,java.util.ArrayList,java.util.Set");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPageDirectiveImport06() {
        doTestPageDirectiveImport("java.util.List;import java.util.ArrayList; import java.util.Set");
    }

    @Test
    public void testPageDirectiveImport07() {
        doTestPageDirectiveImport("java .\nutil.List,java.util.ArrayList,java.util.Set");
    }

    private void doTestPageDirectiveImport(String importDirective) {
        PageDirective pd = new PageDirective(null, null, null);
        pd.addImport(importDirective);
        List<String> imports = pd.getImports();

        Assert.assertEquals(3, imports.size());
    }
}
