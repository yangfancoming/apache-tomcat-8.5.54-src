
package org.apache.catalina.mbeans;

import java.util.ArrayList;
import java.util.Iterator;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.tomcat.util.modeler.BaseModelMBean;
import org.apache.tomcat.util.modeler.ManagedBean;
import org.apache.tomcat.util.modeler.Registry;

/**
 * <p>A <strong>ModelMBean</strong> implementation for the
 * <code>org.apache.catalina.User</code> component.</p>
 *
 * @author Craig R. McClanahan
 */
public class UserMBean extends BaseModelMBean {


    // ----------------------------------------------------- Instance Variables

    /**
     * The configuration information registry for our managed beans.
     */
    protected final Registry registry = MBeanUtils.createRegistry();


    /**
     * The <code>ManagedBean</code> information describing this MBean.
     */
    protected final ManagedBean managed = registry.findManagedBean("User");


    // ------------------------------------------------------------- Attributes


    /**
     * @return the MBean Names of all groups this user is a member of.
     */
    public String[] getGroups() {

        User user = (User) this.resource;
        ArrayList<String> results = new ArrayList<>();
        Iterator<Group> groups = user.getGroups();
        while (groups.hasNext()) {
            Group group = null;
            try {
                group = groups.next();
                ObjectName oname =
                    MBeanUtils.createObjectName(managed.getDomain(), group);
                results.add(oname.toString());
            } catch (MalformedObjectNameException e) {
                IllegalArgumentException iae = new IllegalArgumentException
                    ("Cannot create object name for group " + group);
                iae.initCause(e);
                throw iae;
            }
        }
        return results.toArray(new String[results.size()]);
    }


    /**
     * @return the MBean Names of all roles assigned to this user.
     */
    public String[] getRoles() {

        User user = (User) this.resource;
        ArrayList<String> results = new ArrayList<>();
        Iterator<Role> roles = user.getRoles();
        while (roles.hasNext()) {
            Role role = null;
            try {
                role = roles.next();
                ObjectName oname =
                    MBeanUtils.createObjectName(managed.getDomain(), role);
                results.add(oname.toString());
            } catch (MalformedObjectNameException e) {
                IllegalArgumentException iae = new IllegalArgumentException
                    ("Cannot create object name for role " + role);
                iae.initCause(e);
                throw iae;
            }
        }
        return results.toArray(new String[results.size()]);
    }


    // ------------------------------------------------------------- Operations

    /**
     * Add a new {@link Group} to those this user belongs to.
     *
     * @param groupname Group name of the new group
     */
    public void addGroup(String groupname) {

        User user = (User) this.resource;
        if (user == null) {
            return;
        }
        Group group = user.getUserDatabase().findGroup(groupname);
        if (group == null) {
            throw new IllegalArgumentException("Invalid group name '" + groupname + "'");
        }
        user.addGroup(group);
    }


    /**
     * Add a new {@link Role} to those this user belongs to.
     *
     * @param rolename Role name of the new role
     */
    public void addRole(String rolename) {

        User user = (User) this.resource;
        if (user == null) {
            return;
        }
        Role role = user.getUserDatabase().findRole(rolename);
        if (role == null) {
            throw new IllegalArgumentException("Invalid role name '" + rolename + "'");
        }
        user.addRole(role);
    }


    /**
     * Remove a {@link Group} from those this user belongs to.
     *
     * @param groupname Group name of the old group
     */
    public void removeGroup(String groupname) {

        User user = (User) this.resource;
        if (user == null) {
            return;
        }
        Group group = user.getUserDatabase().findGroup(groupname);
        if (group == null) {
            throw new IllegalArgumentException("Invalid group name '" + groupname + "'");
        }
        user.removeGroup(group);
    }


    /**
     * Remove a {@link Role} from those this user belongs to.
     *
     * @param rolename Role name of the old role
     */
    public void removeRole(String rolename) {

        User user = (User) this.resource;
        if (user == null) {
            return;
        }
        Role role = user.getUserDatabase().findRole(rolename);
        if (role == null) {
            throw new IllegalArgumentException("Invalid role name '" + rolename + "'");
        }
        user.removeRole(role);
    }
}
