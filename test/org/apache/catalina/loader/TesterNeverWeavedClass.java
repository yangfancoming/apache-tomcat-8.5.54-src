
package org.apache.catalina.loader;

public class TesterNeverWeavedClass {

    public String doMethod() {
        return "This will never be weaved.";
    }
}
