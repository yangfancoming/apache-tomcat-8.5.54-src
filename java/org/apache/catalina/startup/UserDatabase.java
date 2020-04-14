


package org.apache.catalina.startup;


import java.util.Enumeration;


/**
 * Abstraction of the set of users defined by the operating system on the
 * current server platform.
 *
 * @author Craig R. McClanahan
 */
public interface UserDatabase {


    // ----------------------------------------------------------- Properties


    /**
     * @return the UserConfig listener with which we are associated.
     */
    public UserConfig getUserConfig();


    /**
     * Set the UserConfig listener with which we are associated.
     *
     * @param userConfig The new UserConfig listener
     */
    public void setUserConfig(UserConfig userConfig);


    // ------------------------------------------------------- Public Methods


    /**
     * @return an absolute pathname to the home directory for the specified user.
     *
     * @param user User for which a home directory should be retrieved
     */
    public String getHome(String user);


    /**
     * @return an enumeration of the usernames defined on this server.
     */
    public Enumeration<String> getUsers();


}
