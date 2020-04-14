
package org.apache.el.util;

import javax.el.MethodNotFoundException;

import org.junit.Test;

public class TestReflectionUtil {

    private static final Tester BASE = new Tester();

    /*
     * Expect failure as it is not possible to identify which method named
     * "testA()" is intended.
     */
    @Test(expected=MethodNotFoundException.class)
    public void testBug54370a() {
        ReflectionUtil.getMethod(null, BASE, "testA",
                new Class[] {null, String.class},
                new Object[] {null, ""});
    }

    /*
     * Expect failure as it is not possible to identify which method named
     * "testB()" is intended. Note: In EL null can always be coerced to a valid
     * value for a primitive.
     */
    @Test(expected=MethodNotFoundException.class)
    public void testBug54370b() {
        ReflectionUtil.getMethod(null, BASE, "testB",
                new Class[] {null, String.class},
                new Object[] {null, ""});
    }

    @Test
    public void testBug54370c() {
        ReflectionUtil.getMethod(null, BASE, "testC",
                new Class[] {null},
                new Object[] {null});
    }

    @Test
    public void testBug54370d() {
        ReflectionUtil.getMethod(null, BASE, "testD",
                new Class[] {null},
                new Object[] {null});
    }
}
