
package org.apache.el.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/*
 * This is a cut down version of org.apache.tomcat.util.Jre9Compat that provides
 * only the methods required by the EL implementation.
 *
 * This class is duplicated in javax.el
 * When making changes keep the two in sync.
 */
class Jre9Compat extends JreCompat {

    private static final Method canAccessMethod;


    static {
        Method m1 = null;
        try {
            m1 = AccessibleObject.class.getMethod("canAccess", new Class<?>[] { Object.class });
        } catch (NoSuchMethodException e) {
            // Expected for Java 8
        }
        canAccessMethod = m1;
    }


    public static boolean isSupported() {
        return canAccessMethod != null;
    }


    @Override
    public boolean canAcccess(Object base, AccessibleObject accessibleObject) {
        try {
            return ((Boolean) canAccessMethod.invoke(accessibleObject, base)).booleanValue();
        } catch (ReflectiveOperationException | IllegalArgumentException e) {
            return false;
        }
    }
}
