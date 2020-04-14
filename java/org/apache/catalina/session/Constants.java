
package org.apache.catalina.session;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.catalina.Globals;
import org.apache.catalina.valves.CrawlerSessionManagerValve;

/**
 * Manifest constants for the <code>org.apache.catalina.session</code>
 * package.
 *
 * @author Craig R. McClanahan
 */

public class Constants {

    /**
     * Set of session attribute names used internally by Tomcat that should
     * always be removed from the session before it is persisted, replicated or
     * equivalent.
     */
    public static final Set<String> excludedAttributeNames;

    static {
        Set<String> names = new HashSet<>();
        names.add(Globals.SUBJECT_ATTR);
        names.add(CrawlerSessionManagerValve.class.getName());
        excludedAttributeNames = Collections.unmodifiableSet(names);
    }
}
