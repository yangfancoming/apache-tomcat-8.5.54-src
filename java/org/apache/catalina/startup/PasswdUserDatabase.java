
package org.apache.catalina.startup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.naming.StringManager;

/**
 * Concrete implementation of the <code>UserDatabase</code> interface
 * that processes the <code>/etc/passwd</code> file on a Unix system.
 *
 * @author Craig R. McClanahan
 */
public final class PasswdUserDatabase implements UserDatabase {

    private static final Log log = LogFactory.getLog(PasswdUserDatabase.class);
    private static final StringManager sm = StringManager.getManager(PasswdUserDatabase.class);

    /**
     * The pathname of the Unix password file.
     */
    private static final String PASSWORD_FILE = "/etc/passwd";


    /**
     * The set of home directories for all defined users, keyed by username.
     */
    private final Hashtable<String,String> homes = new Hashtable<>();


    /**
     * The UserConfig listener with which we are associated.
     */
    private UserConfig userConfig = null;


    /**
     * Return the UserConfig listener with which we are associated.
     */
    @Override
    public UserConfig getUserConfig() {
        return userConfig;
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


    /**
     * Initialize our set of users and home directories.
     */
    private void init() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            String line = reader.readLine();
            while (line != null) {
                String tokens[] = line.split(":");
                // Need non-zero 1st and 6th tokens
                if (tokens.length > 5 && tokens[0].length() > 0 && tokens[5].length() > 0) {
                    // Add this user and corresponding directory
                    homes.put(tokens[0], tokens[5]);
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            log.warn(sm.getString("passwdUserDatabase.readFail"), e);
        }
    }
}
