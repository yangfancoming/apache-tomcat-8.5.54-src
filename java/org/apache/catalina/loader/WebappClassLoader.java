
package org.apache.catalina.loader;

import org.apache.catalina.LifecycleException;

public class WebappClassLoader extends WebappClassLoaderBase {

    public WebappClassLoader() {
        super();
    }


    public WebappClassLoader(ClassLoader parent) {
        super(parent);
    }


    /**
     * Returns a copy of this class loader without any class file
     * transformers. This is a tool often used by Java Persistence API
     * providers to inspect entity classes in the absence of any
     * instrumentation, something that can't be guaranteed within the
     * context of a {@link java.lang.instrument.ClassFileTransformer}'s
     * {@link java.lang.instrument.ClassFileTransformer#transform(ClassLoader,
     * String, Class, java.security.ProtectionDomain, byte[]) transform} method.
     * <p>
     * The returned class loader's resource cache will have been cleared
     * so that classes already instrumented will not be retained or
     * returned.
     *
     * @return the transformer-free copy of this class loader.
     */
    @Override
    public WebappClassLoader copyWithoutTransformers() {

        WebappClassLoader result = new WebappClassLoader(getParent());

        super.copyStateWithoutTransformers(result);

        try {
            result.start();
        } catch (LifecycleException e) {
            throw new IllegalStateException(e);
        }

        return result;
    }


    /**
     * This class loader is not parallel capable so lock on the class loader
     * rather than a per-class lock.
     */
    @Override
    protected Object getClassLoadingLock(String className) {
        return this;
    }
}
