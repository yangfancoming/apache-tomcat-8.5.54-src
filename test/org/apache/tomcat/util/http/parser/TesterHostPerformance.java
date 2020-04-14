
package org.apache.tomcat.util.http.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import org.apache.tomcat.util.buf.MessageBytes;

@RunWith(Parameterized.class)
public class TesterHostPerformance {

    @Parameters
    public static Collection<Object[]> inputs() {
        List<Object[]> result = new ArrayList<>();
        result.add(new Object[] { "localhost" });
        result.add(new Object[] { "tomcat.apache.org" });
        result.add(new Object[] { "tomcat.apache.org." });
        result.add(new Object[] { "127.0.0.1" });
        result.add(new Object[] { "255.255.255.255" });
        result.add(new Object[] { "[::1]" });
        result.add(new Object[] { "[0123:4567:89AB:CDEF:0123:4567:89AB:CDEF]" });
        return result;
    }

    @Parameter(0)
    public String hostname;

    private static final int ITERATIONS = 100000000;

    @Test
    public void testParseHost() throws Exception {
        long start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            Host.parse(hostname);
        }
        long time = System.nanoTime() - start;

        System.out.println("St " + hostname + ": " + ITERATIONS + " iterations in " + time + "ns");
        System.out.println("St " + hostname + ": " + ITERATIONS * 1000000000.0/time + " iterations per second");

        MessageBytes mb = MessageBytes.newInstance();
        mb.setString(hostname);
        mb.toBytes();

        start = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            Host.parse(mb);
        }
        time = System.nanoTime() - start;

        System.out.println("MB " + hostname + ": " + ITERATIONS + " iterations in " + time + "ns");
        System.out.println("MB " + hostname + ": " + ITERATIONS * 1000000000.0/time + " iterations per second");
    }
}
