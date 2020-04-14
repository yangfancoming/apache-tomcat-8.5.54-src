


package org.apache.catalina.util;


import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.tomcat.util.digester.Digester;


/**
 * Helper class used to do property replacement on system properties.
 * @deprecated No longer used. Will be removed in Tomcat 9.
 */
@Deprecated
public class SystemPropertyReplacerListener
    implements LifecycleListener {


    // ---------------------------------------------- LifecycleListener Methods


    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        if (Lifecycle.BEFORE_INIT_EVENT.equals(event.getType())) {
            Digester.replaceSystemProperties();
        }
    }


}
