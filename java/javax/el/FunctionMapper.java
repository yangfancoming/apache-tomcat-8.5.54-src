
package javax.el;

import java.lang.reflect.Method;

public abstract class FunctionMapper {

    public abstract Method resolveFunction(String prefix, String localName);

    /**
     * Map a method to a function name.
     *
     * @param prefix    Function prefix
     * @param localName Function name
     * @param method    Method
     *
     * @since EL 3.0
     */
    public void mapFunction(String prefix, String localName, Method method) {
        // NO-OP
    }
}
