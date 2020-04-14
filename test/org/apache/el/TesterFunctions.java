

package org.apache.el;

import java.lang.reflect.Method;

import javax.el.FunctionMapper;

public class TesterFunctions {
    public static String trim(String input) {
        return input.trim();
    }

    public static String concat(String... inputs) {
        if (inputs == null || inputs.length == 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (String input : inputs) {
            result.append(input);
        }
        return result.toString();
    }

    public static String concat2(String prefix, String... inputs) {
        StringBuilder result = new StringBuilder(prefix);
        for (String input : inputs) {
            result.append(input);
        }
        return result.toString();
    }

    public static String[] toArray(String a, String b) {
        return new String[] { a, b };
    }


    public static class Inner$Class {

        public static final String RETVAL = "Return from bug49555";
        public static String bug49555() {
            return RETVAL;
        }
    }


    public static class FMapper extends FunctionMapper {

        @Override
        public Method resolveFunction(String prefix, String localName) {
            if ("trim".equals(localName)) {
                Method m;
                try {
                    m = TesterFunctions.class.getMethod("trim", String.class);
                    return m;
                } catch (SecurityException e) {
                    // Ignore
                } catch (NoSuchMethodException e) {
                    // Ignore
                }
            } else if ("concat".equals(localName)) {
                Method m;
                try {
                    m = TesterFunctions.class.getMethod("concat", String[].class);
                    return m;
                } catch (SecurityException e) {
                    // Ignore
                } catch (NoSuchMethodException e) {
                    // Ignore
                }
            } else if ("concat2".equals(localName)) {
                Method m;
                try {
                    m = TesterFunctions.class.getMethod("concat2", String.class, String[].class);
                    return m;
                } catch (SecurityException e) {
                    // Ignore
                } catch (NoSuchMethodException e) {
                    // Ignore
                }
            } else if ("toArray".equals(localName)) {
                Method m;
                try {
                    m = TesterFunctions.class.getMethod("toArray", String.class, String.class);
                    return m;
                } catch (SecurityException e) {
                    // Ignore
                } catch (NoSuchMethodException e) {
                    // Ignore
                }
            }
            return null;
        }
    }
}
