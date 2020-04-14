
package javax.el;

/**
 * Base implementation that provides a minimal default implementation that is
 * intended to be extended by application developers.
 *
 * @since EL 3.0
 */
public abstract class BeanNameResolver {

    /**
     * Can this resolver resolve the given bean name?
     *
     * @param beanName The bean name to resolve
     *
     * @return This default implementation always returns <code>false</code>
     */
    public boolean isNameResolved(String beanName) {
        return false;
    }


    /**
     * Returns the named bean.
     *
     * @param beanName The bean name to return
     *
     * @return This default implementation always returns <code>null</code>
     */
    public Object getBean(String beanName) {
        return null;
    }


    /**
     * Sets a value of a bean of the given name. If the named bean does not
     * exist and {@link #canCreateBean} returns <code>true</code> then a bean
     * is created with the given value.
     *
     * @param beanName The name of the bean to be set/create
     * @param value    The value of the bean to set/create
     *
     * @throws PropertyNotWritableException if the bean is read only
     */
    public void setBeanValue(String beanName, Object value)
            throws PropertyNotWritableException {
        throw new PropertyNotWritableException();
    }


    /**
     * Is the named bean read-only?
     *
     * @param beanName The name of the bean of interest
     *
     * @return <code>true</code> if the bean is read only, otherwise
     *         <code>false</code>
     */
    public boolean isReadOnly(String beanName) {
        return true;
    }


    /**
     * Is it permitted to create a bean of the given name?
     *
     * @param beanName The name of the bean of interest
     *
     * @return <code>true</code> if the bean may be created, otherwise
     *         <code>false</code>
     */
    public boolean canCreateBean(String beanName) {
        return false;
    }
}
