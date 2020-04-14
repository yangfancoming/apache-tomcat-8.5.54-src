
package org.apache.juli;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class TestOneLineFormatterMillisPerformance {

    @Parameterized.Parameters(name = "{index}: format[{0}]")
    public static Collection<Object[]> parameters() {

        List<Object[]> parameterSets = new ArrayList<>();

        parameterSets.add(new String[] { "dd-MMM-yyyy HH:mm:ss.SSS" });
        parameterSets.add(new String[] { "dd-MMM-yyyy HH:mm:ss.SS" });
        parameterSets.add(new String[] { "dd-MMM-yyyy HH:mm:ss.S" });
        parameterSets.add(new String[] { "dd-MMM-yyyy HH:mm:ss" });
        parameterSets.add(new String[] { "dd-MMM-yyyy HH:mm:ss XXX" });
        parameterSets.add(new String[] { "dd-MMM-yyyy HH:mm:ss.SSSXXX" });
        parameterSets.add(new String[] { "dd-MMM-yyyy HH:mm:ss.SSXXX" });
        parameterSets.add(new String[] { "dd-MMM-yyyy HH:mm:ss.SXXX" });
        parameterSets.add(new String[] { "SSS dd-MMM-yyyy HH:mm:ss" });
        parameterSets.add(new String[] { "SS dd-MMM-yyyy HH:mm:ss" });
        parameterSets.add(new String[] { "S dd-MMM-yyyy HH:mm:ss" });

        return parameterSets;
    }


    @Parameter(0)
    public String timestampFormat;

    @Test
    public void testMillisHandling() {
        OneLineFormatter olf = new OneLineFormatter();
        olf.setTimeFormat(timestampFormat);

        long timeStamp = System.currentTimeMillis();
        StringBuilder buf = new StringBuilder(64);

        long start = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            buf.setLength(0);
            olf.addTimestamp(buf, timeStamp);
        }
        System.out.println("Format: [" + timestampFormat + "], Output: [" + buf + "], Duration: [" + (System.nanoTime() - start) + "] ns");
    }
}
