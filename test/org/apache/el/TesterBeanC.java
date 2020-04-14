

package org.apache.el;

public class TesterBeanC {
    public String sayHello(TesterBeanA a, TesterBeanB b) {
        return "AB: Hello " + a.getName() + " from " + b.getName();
    }
    public String sayHello(TesterBeanAA a, TesterBeanB b) {
        return "AAB: Hello " + a.getName() + " from " + b.getName();
    }
    public String sayHello(TesterBeanA a, TesterBeanBB b) {
        return "ABB: Hello " + a.getName() + " from " + b.getName();
    }
    public String sayHello(TesterBeanA a, TesterBeanBB... b) {
        StringBuilder result =
            new StringBuilder("ABB[]: Hello " + a.getName() + " from ");
        for (int i = 0; i < b.length; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(b[i].getName());
        }
        return result.toString();
    }
}
