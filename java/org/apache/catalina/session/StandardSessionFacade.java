
package org.apache.catalina.session;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * Facade for the StandardSession object.
 *
 * @author Remy Maucherat
 */
public class StandardSessionFacade implements HttpSession {

    // ----------------------------------------------------------- Constructors

    /**
     * Construct a new session facade.
     *
     * @param session The session instance to wrap
     */
    public StandardSessionFacade(HttpSession session) {
        this.session = session;
    }


    // ----------------------------------------------------- Instance Variables

    /**
     * Wrapped session object.
     */
    private final HttpSession session;


    // ---------------------------------------------------- HttpSession Methods

    @Override
    public long getCreationTime() {
        return session.getCreationTime();
    }


    @Override
    public String getId() {
        return session.getId();
    }


    @Override
    public long getLastAccessedTime() {
        return session.getLastAccessedTime();
    }


    @Override
    public ServletContext getServletContext() {
        // FIXME : Facade this object ?
        return session.getServletContext();
    }


    @Override
    public void setMaxInactiveInterval(int interval) {
        session.setMaxInactiveInterval(interval);
    }


    @Override
    public int getMaxInactiveInterval() {
        return session.getMaxInactiveInterval();
    }


    /**
     * @deprecated As of Version 2.1, this method is deprecated and has no
     *             replacement.
     */
    @Override
    @Deprecated
    public javax.servlet.http.HttpSessionContext getSessionContext() {
        return session.getSessionContext();
    }


    @Override
    public Object getAttribute(String name) {
        return session.getAttribute(name);
    }


    /**
     * @deprecated As of Version 2.2, this method is replaced by
     *             {@link #getAttribute}.
     */
    @Override
    @Deprecated
    public Object getValue(String name) {
        return session.getAttribute(name);
    }


    @Override
    public Enumeration<String> getAttributeNames() {
        return session.getAttributeNames();
    }


    /**
     * @deprecated As of Version 2.2, this method is replaced by
     *             {@link #getAttributeNames}
     */
    @Override
    @Deprecated
    public String[] getValueNames() {
        return session.getValueNames();
    }


    @Override
    public void setAttribute(String name, Object value) {
        session.setAttribute(name, value);
    }


    /**
     * @deprecated As of Version 2.2, this method is replaced by
     *             {@link #setAttribute}
     */
    @Override
    @Deprecated
    public void putValue(String name, Object value) {
        session.setAttribute(name, value);
    }


    @Override
    public void removeAttribute(String name) {
        session.removeAttribute(name);
    }


    /**
     * @deprecated As of Version 2.2, this method is replaced by
     *             {@link #removeAttribute}
     */
    @Override
    @Deprecated
    public void removeValue(String name) {
        session.removeAttribute(name);
    }


    @Override
    public void invalidate() {
        session.invalidate();
    }


    @Override
    public boolean isNew() {
        return session.isNew();
    }
}
