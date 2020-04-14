

package javax.el;

/**
 * @author Jacob Hookom [jacob/hookom.net]
 *
 */
public interface ELContextListener extends java.util.EventListener {

    public void contextCreated(ELContextEvent event);

}
