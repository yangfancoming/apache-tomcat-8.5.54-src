
package org.apache.catalina.servlets;


public class TestDefaultServletEncodingWithBom extends DefaultServletEncodingBaseTest {

    @Override
    protected boolean getUseBom() {
        return true;
    }
}
