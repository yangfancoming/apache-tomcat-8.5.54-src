
package org.apache.naming.resources;

public class TesterObject {

    private String foo;

    @Override
    public String toString() {
        return "This is a test object (" + super.toString() + ").";
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public String getFoo() {
        return this.foo;
    }
}
