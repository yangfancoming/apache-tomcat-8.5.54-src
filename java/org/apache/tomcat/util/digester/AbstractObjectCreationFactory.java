
package org.apache.tomcat.util.digester;


import org.xml.sax.Attributes;


/**
 * <p>Abstract base class for <code>ObjectCreationFactory</code>
 * implementations.</p>
 */
public abstract class AbstractObjectCreationFactory
        implements ObjectCreationFactory {


    // ----------------------------------------------------- Instance Variables


    /**
     * The associated <code>Digester</code> instance that was set up by
     * {@link FactoryCreateRule} upon initialization.
     */
    private Digester digester = null;


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Factory method called by {@link FactoryCreateRule} to supply an
     * object based on the element's attributes.
     *
     * @param attributes the element's attributes
     *
     * @throws Exception any exception thrown will be propagated upwards
     */
    @Override
    public abstract Object createObject(Attributes attributes) throws Exception;


    /**
     * <p>Returns the {@link Digester} that was set by the
     * {@link FactoryCreateRule} upon initialization.
     */
    @Override
    public Digester getDigester() {
        return this.digester;
    }


    /**
     * <p>Set the {@link Digester} to allow the implementation to do logging,
     * classloading based on the digester's classloader, etc.
     *
     * @param digester parent Digester object
     */
    @Override
    public void setDigester(Digester digester) {
        this.digester = digester;
    }


}
