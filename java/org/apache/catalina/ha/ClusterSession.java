


package org.apache.catalina.ha;

import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

public interface ClusterSession extends Session, HttpSession {
   /**
    * returns true if this session is the primary session, if that is the
    * case, the manager can expire it upon timeout.
    * @return True if this session is primary
    */
   public boolean isPrimarySession();

   /**
    * Sets whether this is the primary session or not.
    * @param primarySession Flag value
    */
   public void setPrimarySession(boolean primarySession);


}
