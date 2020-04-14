

package org.apache.tomcat.jni;

/** Sockaddr
 *
 * @author Mladen Turk
 */
public class Sockaddr {

   /** The pool to use... */
    public long pool;
    /** The hostname */
    public String hostname;
    /** Either a string of the port number or the service name for the port */
    public String servname;
    /** The numeric port */
    public int port;
    /** The family */
    public int family;
    /** If multiple addresses were found by apr_sockaddr_info_get(), this
     *  points to a representation of the next address. */
    public long next;

}
