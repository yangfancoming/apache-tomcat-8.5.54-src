

package org.apache.tomcat.jni;

/** ProcErrorCallback Interface
 *
 * @author Mladen Turk
 */
public interface ProcErrorCallback {

    /**
     * Called in the child process if APR encounters an error
     * in the child prior to running the specified program.
     * @param pool Pool associated with the apr_proc_t.  If your child
     *             error function needs user data, associate it with this
     *             pool.
     * @param err APR error code describing the error
     * @param description Text description of type of processing which failed
     */
    public void callback(long pool, int err, String description);
}
