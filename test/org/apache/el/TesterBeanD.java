
package org.apache.el;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to test the calling of overloaded methods.
 */
public class TesterBeanD {

    private List<Object> things = new ArrayList<>();

    public void addThing(String thing) {
        things.add(thing);
    }

    public void addThing(Class<?> clazz) {
        things.add(clazz);
    }

    public List<Object> getThings() {
        return things;
    }
}
