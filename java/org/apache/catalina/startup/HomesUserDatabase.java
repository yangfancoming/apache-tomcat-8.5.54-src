


package org.apache.catalina.startup;


import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Concrete implementation of the <code>UserDatabase</code> interface
 * considers all directories in a directory whose pathname is specified
 * to our constructor to be "home" directories for those users.
 *
 * @author Craig R. McClanahan
 */
public final class HomesUserDatabase
    implements UserDatabase {


    // --------------------------------------------------------- Constructors


    /**
     * Initialize a new instance of this user database component.
     */
    public HomesUserDatabase() {

        super();

    }


    // --------------------------------------------------- Instance Variables


    /**
     * The set of home directories for all defined users, keyed by username.
     */
    private final Hashtable<String,String> homes = new Hashtable<>();


    /**
     * The UserConfig listener with which we are associated.
     */
    private UserConfig userConfig = null;


    // ----------------------------------------------------------- Properties


    /**
     * Return the UserConfig listener with which we are associated.
     */
    @Override
    public UserConfig getUserConfig() {
        return this.userConfig;
    }


    /**
     * Set the UserConfig listener with which we are associated.
     *
     * @param userConfig The new UserConfig listener
     */
    @Override
    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
        init();
    }


    // ------------------------------------------------------- Public Methods


    /**
     * Return an absolute pathname to the home directory for the specified user.
     *
     * @param user User for which a home directory should be retrieved
     */
    @Override
    public String getHome(String user) {
        return homes.get(user);
    }


    /**
     * Return an enumeration of the usernames defined on this server.
     */
    @Override
    public Enumeration<String> getUsers() {
        return homes.keys();
    }


    // ------------------------------------------------------ Private Methods


    /**
     * Initialize our set of users and home directories.
     */
    private void init() {

        String homeBase = userConfig.getHomeBase();
        File homeBaseDir = new File(homeBase);
        if (!homeBaseDir.exists() || !homeBaseDir.isDirectory())
            return;
        String homeBaseFiles[] = homeBaseDir.list();
        if (homeBaseFiles == null) {
            return;
        }

        for (int i = 0; i < homeBaseFiles.length; i++) {
            File homeDir = new File(homeBaseDir, homeBaseFiles[i]);
            if (!homeDir.isDirectory() || !homeDir.canRead())
                continue;
            homes.put(homeBaseFiles[i], homeDir.toString());
        }
    }
}
