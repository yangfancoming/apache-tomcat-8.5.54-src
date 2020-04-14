

package org.apache.el;

public class TesterBeanB {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String sayHello() {
        return "Hello from " + name;
    }

    public String sayHello(String to) {
        return "Hello " + to + " from " + name;
    }

    public String echo(String...strings) {
        if (strings == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(strings[i]);
        }
        return sb.toString();
    }
}
