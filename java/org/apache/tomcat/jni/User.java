

package org.apache.tomcat.jni;

/** User
 *
 * @author Mladen Turk
 */
public class User {

    /**
     * Get the userid (and groupid) of the calling process
     * This function is available only if APR_HAS_USER is defined.
     * @param p The pool from which to allocate working space
     * @return Returns the user id
     * @throws Error If an error occurred
     */
     public static native long uidCurrent(long p)
        throws Error;

    /**
     * Get the groupid of the calling process
     * This function is available only if APR_HAS_USER is defined.
     * @param p The pool from which to allocate working space
     * @return Returns the group id
     * @throws Error If an error occurred
     */
     public static native long gidCurrent(long p)
        throws Error;


    /**
     * Get the userid for the specified username
     * This function is available only if APR_HAS_USER is defined.
     * @param username The username to lookup
     * @param p The pool from which to allocate working space
     * @return Returns the user id
     * @throws Error If an error occurred
     */
     public static native long uid(String username, long p)
        throws Error;

    /**
     * Get the groupid for the specified username
     * This function is available only if APR_HAS_USER is defined.
     * @param username The username to lookup
     * @param p The pool from which to allocate working space
     * @return  Returns the user's group id
     * @throws Error If an error occurred
     */
     public static native long usergid(String username, long p)
        throws Error;

    /**
     * Get the groupid for a specified group name
     * This function is available only if APR_HAS_USER is defined.
     * @param groupname The group name to look up
     * @param p The pool from which to allocate working space
     * @return  Returns the user's group id
     * @throws Error If an error occurred
     */
     public static native long gid(String groupname, long p)
        throws Error;

    /**
     * Get the user name for a specified userid
     * This function is available only if APR_HAS_USER is defined.
     * @param userid The userid
     * @param p The pool from which to allocate the string
     * @return New string containing user name
     * @throws Error If an error occurred
     */
     public static native String username(long userid, long p)
        throws Error;

    /**
     * Get the group name for a specified groupid
     * This function is available only if APR_HAS_USER is defined.
     * @param groupid The groupid
     * @param p The pool from which to allocate the string
     * @return New string containing group name
     * @throws Error If an error occurred
     */
     public static native String groupname(long groupid, long p)
        throws Error;

    /**
     * Compare two user identifiers for equality.
     * This function is available only if APR_HAS_USER is defined.
     * @param left One uid to test
     * @param right Another uid to test
     * @return APR_SUCCESS if the apr_uid_t structures identify the same user,
     * APR_EMISMATCH if not, APR_BADARG if an apr_uid_t is invalid.
     */
     public static native int uidcompare(long left, long right);

    /**
     * Compare two group identifiers for equality.
     * This function is available only if APR_HAS_USER is defined.
     * @param left One gid to test
     * @param right Another gid to test
     * @return APR_SUCCESS if the apr_gid_t structures identify the same group,
     * APR_EMISMATCH if not, APR_BADARG if an apr_gid_t is invalid.
     */
     public static native int gidcompare(long left, long right);

    /**
     * Get the home directory for the named user
     * This function is available only if APR_HAS_USER is defined.
     * @param username The named user
     * @param p The pool from which to allocate the string
     * @return New string containing directory name
     * @throws Error If an error occurred
     */
     public static native String homepath(String username, long p)
        throws Error;

}
