
package javax.el;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/*
 * This is a cut down version of org.apache.tomcat.util.Jre9Compat that provides
 * only the methods required by the EL implementation.
 *
 * This class is duplicated in org.apache.el.util
 * When making changes keep the two in sync.
 */
class Jre9Compat extends JreCompat {

    private static final Method canAccessMethod;
    private static final Method getModuleMethod;
    private static final Method isExportedMethod;

    static {
        Method m1 = null;
        Method m2 = null;
        Method m3 = null;

        try {
            m1 = AccessibleObject.class.getMethod("canAccess", Object.class);
            m2 = Class.class.getMethod("getModule");
            Class<?> moduleClass = Class.forName("java.lang.Module");
            m3 = moduleClass.getMethod("isExported", String.class);
        } catch (NoSuchMethodException e) {
            // Expected for Java 8
        } catch (ClassNotFoundException e) {
            // Can't log this so...
            throw new RuntimeException(e);
        }

        canAccessMethod = m1;
        getModuleMethod = m2;
        isExportedMethod = m3;
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


    @Override
    public boolean isExported(Class<?> type) {
        try {
            String packageName = type.getPackage().getName();
            Object module = getModuleMethod.invoke(type);
            return ((Boolean) isExportedMethod.invoke(module, packageName)).booleanValue();
        } catch (ReflectiveOperationException e) {
            return false;
        }
    }
}
