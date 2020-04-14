
package org.apache.naming;


/**
 * Represents a binding in a NamingContext.
 *
 * @author Remy Maucherat
 */
public class NamingEntry {

    public static final int ENTRY = 0;
    public static final int LINK_REF = 1;
    public static final int REFERENCE = 2;
    public static final int CONTEXT = 10;


    public NamingEntry(String name, Object value, int type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }


    /**
     * The type instance variable is used to avoid using RTTI when doing
     * lookups.
     */
    public int type;
    public final String name;
    public Object value;


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NamingEntry) {
            return name.equals(((NamingEntry) obj).name);
        } else {
            return false;
        }
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
