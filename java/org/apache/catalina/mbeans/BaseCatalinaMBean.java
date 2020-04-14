
package org.apache.catalina.mbeans;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.RuntimeOperationsException;
import javax.management.modelmbean.InvalidTargetObjectTypeException;

import org.apache.tomcat.util.modeler.BaseModelMBean;

public abstract class BaseCatalinaMBean<T> extends BaseModelMBean {

    protected T doGetManagedResource() throws MBeanException {
        try {
            @SuppressWarnings("unchecked")
            T resource = (T) getManagedResource();
            return resource;
        } catch (InstanceNotFoundException | RuntimeOperationsException |
                InvalidTargetObjectTypeException e) {
            throw new MBeanException(e);
        }
    }


    protected static Object newInstance(String type) throws MBeanException {
        try {
            return Class.forName(type).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new MBeanException(e);
        }
    }
}
