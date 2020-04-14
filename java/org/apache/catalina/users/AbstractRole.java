


package org.apache.catalina.users;


import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;


/**
 * <p>Convenience base class for {@link Role} implementations.</p>
 *
 * @author Craig R. McClanahan
 * @since 4.1
 */
public abstract class AbstractRole implements Role {


    // ----------------------------------------------------- Instance Variables


    /**
     * The description of this Role.
     */
    protected String description = null;


    /**
     * The role name of this Role.
     */
    protected String rolename = null;


    // ------------------------------------------------------------- Properties


    /**
     * Return the description of this role.
     */
    @Override
    public String getDescription() {
        return this.description;
    }


    /**
     * Set the description of this role.
     *
     * @param description The new description
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Return the role name of this role, which must be unique
     * within the scope of a {@link UserDatabase}.
     */
    @Override
    public String getRolename() {
        return this.rolename;
    }


    /**
     * Set the role name of this role, which must be unique
     * within the scope of a {@link UserDatabase}.
     *
     * @param rolename The new role name
     */
    @Override
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }


    /**
     * Return the {@link UserDatabase} within which this Role is defined.
     */
    @Override
    public abstract UserDatabase getUserDatabase();


    // --------------------------------------------------------- Public Methods


    // ------------------------------------------------------ Principal Methods


    /**
     * Make the principal name the same as the role name.
     */
    @Override
    public String getName() {
        return getRolename();
    }


}
