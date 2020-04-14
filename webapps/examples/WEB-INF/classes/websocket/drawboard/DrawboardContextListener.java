
package websocket.drawboard;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public final class DrawboardContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // NO-OP
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Shutdown our room.
        Room room = DrawboardEndpoint.getRoom(false);
        if (room != null) {
            room.shutdown();
        }
    }
}
