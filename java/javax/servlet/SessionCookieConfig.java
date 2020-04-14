
package javax.servlet;

/**
 * Configures the session cookies used by the web application associated with
 * the ServletContext from which this SessionCookieConfig was obtained.
 *
 * @since Servlet 3.0
 */
public interface SessionCookieConfig {

    /**
     * Sets the session cookie name.
     *
     * @param name The name of the session cookie
     *
     * @throws IllegalStateException if the associated ServletContext has
     *         already been initialised
     */
    public void setName(String name);

    public String getName();

    /**
     * Sets the domain for the session cookie
     *
     * @param domain The session cookie domain
     *
     * @throws IllegalStateException if the associated ServletContext has
     *         already been initialised
     */
    public void setDomain(String domain);

    public String getDomain();

    /**
     * Sets the path of the session cookie.
     *
     * @param path The session cookie path
     *
     * @throws IllegalStateException if the associated ServletContext has
     *         already been initialised
     */
    public void setPath(String path);

    public String getPath();

    /**
     * Sets the comment for the session cookie
     *
     * @param comment The session cookie comment
     *
     * @throws IllegalStateException if the associated ServletContext has
     *         already been initialised
     */
    public void setComment(String comment);

    public String getComment();

    /**
     * Sets the httpOnly flag for the session cookie.
     *
     * @param httpOnly The httpOnly setting to use for session cookies
     *
     * @throws IllegalStateException if the associated ServletContext has
     *         already been initialised
     */
    public void setHttpOnly(boolean httpOnly);

    public boolean isHttpOnly();

    /**
     * Sets the secure flag for the session cookie.
     *
     * @param secure The secure setting to use for session cookies
     *
     * @throws IllegalStateException if the associated ServletContext has
     *         already been initialised
     */
    public void setSecure(boolean secure);

    public boolean isSecure();

    /**
     * Sets the maximum age.
     *
     * @param MaxAge the maximum age to set
     * @throws IllegalStateException if the associated ServletContext has
     *         already been initialised
     */
    public void setMaxAge(int MaxAge);

    public int getMaxAge();

}
