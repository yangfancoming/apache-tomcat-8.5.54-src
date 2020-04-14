
package org.apache.el.util;

import java.lang.reflect.AccessibleObject;

/*
 * This is a cut down version of org.apache.tomcat.util.JreCompat that provides
 * only the methods required by the EL implementation.
 *
 * This class is duplicated in javax.el
 * When making changes keep the two in sync.
 */
public class JreCompat {

    private static final JreCompat instance;

    static {
        if (Jre9Compat.isSupported()) {
            instance = new Jre9Compat();
        } else {
            instance = new JreCompat();
        }
    }


    public static JreCompat getInstance() {
        return instance;
    }


    /**
     * Is the accessibleObject accessible (as a result of appropriate module
     * exports) on the provided instance?
     *
     * @param base  The specific instance to be tested.
     * @param accessibleObject  The method/field/constructor to be tested.
     *
     * @return {code true} if the AccessibleObject can be accessed otherwise
     *         {code false}
     */
    public boolean canAcccess(Object base, AccessibleObject accessibleObject) {
        // Java 8 doesn't support modules so default to true
        return true;
    }
}
