
package org.apache.naming;

import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.RefAddr;
import javax.naming.Reference;

public abstract class AbstractRef extends Reference {

    private static final long serialVersionUID = 1L;


    public AbstractRef(String className) {
        super(className);
    }


    public AbstractRef(String className, String factory, String factoryLocation) {
        super(className, factory, factoryLocation);
    }


    /**
     * Retrieves the class name of the factory of the object to which this
     * reference refers.
     */
    @Override
    public final String getFactoryClassName() {
        String factory = super.getFactoryClassName();
        if (factory != null) {
            return factory;
        } else {
            factory = System.getProperty(Context.OBJECT_FACTORIES);
            if (factory != null) {
                return null;
            } else {
                return getDefaultFactoryClassName();
            }
        }
    }


    protected abstract String getDefaultFactoryClassName();


    /**
     * Return a String rendering of this object.
     */
    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append("[className=");
        sb.append(getClassName());
        sb.append(",factoryClassLocation=");
        sb.append(getFactoryClassLocation());
        sb.append(",factoryClassName=");
        sb.append(getFactoryClassName());
        Enumeration<RefAddr> refAddrs = getAll();
        while (refAddrs.hasMoreElements()) {
            RefAddr refAddr = refAddrs.nextElement();
            sb.append(",{type=");
            sb.append(refAddr.getType());
            sb.append(",content=");
            sb.append(refAddr.getContent());
            sb.append("}");
        }
        sb.append("]");
        return sb.toString();
    }
}
