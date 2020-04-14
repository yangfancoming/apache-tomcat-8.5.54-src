
package javax.el;

/**
 * @since EL 3.0
 */
public class ELClass {

    private final Class<?> clazz;

    public ELClass(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getKlass() {
        return clazz;
    }
}
