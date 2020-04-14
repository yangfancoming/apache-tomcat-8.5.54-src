

package org.apache.tomcat.jni;

/** PasswordCallback Interface
 *
 * @author Mladen Turk
 */
public interface PasswordCallback {

    /**
     * Called when the password is required
     * @param prompt Password prompt
     * @return Valid password or null
     */
    public String callback(String prompt);
}
