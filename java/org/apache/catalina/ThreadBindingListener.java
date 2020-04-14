
package org.apache.catalina;


/**
 * Callback for establishing naming association when entering the application
 * scope. This corresponds to setting the context classloader.
 */
public interface ThreadBindingListener {

    public void bind();
    public void unbind();

}
