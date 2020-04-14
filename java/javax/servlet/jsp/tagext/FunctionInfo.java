

package javax.servlet.jsp.tagext;

/**
 * Information for a function in a Tag Library.
 * This class is instantiated from the Tag Library Descriptor file (TLD)
 * and is available only at translation time.
 *
 * @since 2.0
 */
public class FunctionInfo {

    /**
     * Constructor for FunctionInfo.
     *
     * @param name The name of the function
     * @param klass The class of the function
     * @param signature The signature of the function
     */

    public FunctionInfo(String name, String klass, String signature) {
        this.name = name;
        this.functionClass = klass;
        this.functionSignature = signature;
    }

    /**
     * The name of the function.
     *
     * @return The name of the function
     */

    public String getName() {
        return name;
    }

    /**
     * The class of the function.
     *
     * @return The class of the function
     */

    public String getFunctionClass() {
        return functionClass;
    }

    /**
     * The signature of the function.
     *
     * @return The signature of the function
     */

    public String getFunctionSignature() {
        return functionSignature;
    }

    /*
     * fields
     */
    private final String name;
    private final String functionClass;
    private final String functionSignature;
}
