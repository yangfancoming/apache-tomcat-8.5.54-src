
package org.apache.jasper.compiler;


import java.util.HashMap;

import org.apache.jasper.JasperException;

/**
 * Repository of {page, request, session, application}-scoped beans
 *
 * @author Mandar Raje
 * @author Remy Maucherat
 */
public class BeanRepository {

    private final HashMap<String, String> beanTypes;
    private final ClassLoader loader;
    private final ErrorDispatcher errDispatcher;

    /**
     * Constructor.
     * @param loader The class loader
     * @param err The error dispatcher that will be used to report errors
     */
    public BeanRepository(ClassLoader loader, ErrorDispatcher err) {
        this.loader = loader;
        this.errDispatcher = err;
        beanTypes = new HashMap<>();
    }

    public void addBean(Node.UseBean n, String s, String type, String scope)
        throws JasperException {

        if (!(scope == null || scope.equals("page") || scope.equals("request")
                || scope.equals("session") || scope.equals("application"))) {
            errDispatcher.jspError(n, "jsp.error.usebean.badScope");
        }

        beanTypes.put(s, type);
    }

    public Class<?> getBeanType(String bean)
        throws JasperException {
        Class<?> clazz = null;
        try {
            clazz = loader.loadClass(beanTypes.get(bean));
        } catch (ClassNotFoundException ex) {
            throw new JasperException (ex);
        }
        return clazz;
    }

    public boolean checkVariable(String bean) {
        return beanTypes.containsKey(bean);
    }

}


