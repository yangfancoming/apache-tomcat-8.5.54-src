
package org.apache.catalina.servlets;


public class TestDefaultServletEncodingWithoutBom extends DefaultServletEncodingBaseTest {

    @Override
    protected boolean getUseBom() {
        return false;
    }
}
