
package javax.servlet.jsp.tagext;

/**
 * Variable information for a tag in a Tag Library; This class is instantiated
 * from the Tag Library Descriptor file (TLD) and is available only at
 * translation time. This object should be immutable. This information is only
 * available in JSP 1.2 format TLDs or above.
 */
public class TagVariableInfo {

    /**
     * Constructor for TagVariableInfo.
     *
     * @param nameGiven
     *            value of &lt;name-given&gt;
     * @param nameFromAttribute
     *            value of &lt;name-from-attribute&gt;
     * @param className
     *            value of &lt;variable-class&gt;
     * @param declare
     *            value of &lt;declare&gt;
     * @param scope
     *            value of &lt;scope&gt;
     */
    public TagVariableInfo(String nameGiven, String nameFromAttribute,
            String className, boolean declare, int scope) {
        this.nameGiven = nameGiven;
        this.nameFromAttribute = nameFromAttribute;
        this.className = className;
        this.declare = declare;
        this.scope = scope;
    }

    /**
     * The body of the &lt;name-given&gt; element.
     *
     * @return The variable name as a constant
     */
    public String getNameGiven() {
        return nameGiven;
    }

    /**
     * The body of the &lt;name-from-attribute&gt; element. This is the name of
     * an attribute whose (translation-time) value will give the name of the
     * variable. One of &lt;name-given&gt; or &lt;name-from-attribute&gt; is
     * required.
     *
     * @return The attribute whose value defines the variable name
     */
    public String getNameFromAttribute() {
        return nameFromAttribute;
    }

    /**
     * The body of the &lt;variable-class&gt; element.
     *
     * @return The name of the class of the variable or 'java.lang.String' if
     *         not defined in the TLD.
     */
    public String getClassName() {
        return className;
    }

    /**
     * The body of the &lt;declare&gt; element.
     *
     * @return Whether the variable is to be declared or not. If not defined in
     *         the TLD, 'true' will be returned.
     */
    public boolean getDeclare() {
        return declare;
    }

    /**
     * The body of the &lt;scope&gt; element.
     *
     * @return The scope to give the variable. NESTED scope will be returned if
     *         not defined in the TLD.
     */
    public int getScope() {
        return scope;
    }

    /*
     * private fields
     */
    private final String nameGiven; // <name-given>
    private final String nameFromAttribute; // <name-from-attribute>
    private final String className; // <class>
    private final boolean declare; // <declare>
    private final int scope; // <scope>
}
