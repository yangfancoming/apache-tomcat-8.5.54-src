
package async;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * Ensures the Stockticker is shut down cleanly when the context stops. This
 * also covers the case when the server shuts down.
 */
public class AsyncStockContextListener implements ServletContextListener {

    public static final String STOCK_TICKER_KEY = "StockTicker";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Stockticker stockticker = new Stockticker();
        ServletContext sc = sce.getServletContext();
        sc.setAttribute(STOCK_TICKER_KEY, stockticker);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        Stockticker stockticker = (Stockticker) sc.getAttribute(STOCK_TICKER_KEY);
        stockticker.shutdown();
    }
}
