

package org.apache.catalina.manager.util;

import java.util.Comparator;

import org.apache.catalina.Session;

/**
 * Comparator which permits to compare on a session's content.
 *
 * @param <T> The type of the session content to be compared
 *
 * @author C&eacute;drik LIME
 */
public abstract class BaseSessionComparator<T> implements Comparator<Session> {

    /**
     *
     */
    public BaseSessionComparator() {
        super();
    }

    public abstract Comparable<T> getComparableObject(Session session);

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public final int compare(Session s1, Session s2) {
        Comparable<T> c1 = getComparableObject(s1);
        Comparable<T> c2 = getComparableObject(s2);
        return c1==null ? (c2==null ? 0 : -1) : (c2==null ? 1 : c1.compareTo((T)c2));
    }
}
