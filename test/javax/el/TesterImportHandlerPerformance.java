
package javax.el;

import org.junit.Test;

public class TesterImportHandlerPerformance {

    /*
     * This test is looking at the cost of looking up a class when the standard
     * JSP package imports are present:
     * - java.lang
     * - javax.servlet
     * - javax.servlet.http
     * - javax.servlet.jsp
     *
     * Before optimisation, this test took ~4.6s on markt's desktop
     * After optimisation, this test took ~0.05s on markt's desktop
     */
    @Test
    public void testBug62453() throws Exception {
        long totalTime = 0;
        for (int i = 0; i < 100000; i++) {
            ImportHandler ih = new ImportHandler();
            ih.importPackage("javax.servlet");
            ih.importPackage("javax.servlet.http");
            ih.importPackage("javax.servlet.jsp");
            long start = System.nanoTime();
            ih.resolveClass("unknown");
            long end = System.nanoTime();
            totalTime += (end -start);
        }
        System.out.println("Time taken: " + totalTime + "ns");
    }
}
