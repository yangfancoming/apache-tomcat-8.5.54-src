

package org.apache.el;

public class TesterBeanAA extends TesterBeanA {

    @Override
    public String echo1(CharSequence cs) {
        return "AA1" + cs.toString();
    }

    @Override
    public String echo2(String s) {
        return "AA2" + s;
    }
}
