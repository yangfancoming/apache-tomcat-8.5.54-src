
package org.apache.tomcat.util.buf;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;

public class TestCharsetCachePerformance {

    @Test
    public void testNoCsCache() throws Exception {
        doTest(new NoCsCache());
    }


    @Test
    public void testFullCsCache() throws Exception {
        doTest(new FullCsCache());
    }


    @Test
    public void testLazyCsCache() throws Exception {
        doTest(new LazyCsCache());
    }


    private void doTest(CsCache cache) throws Exception {
        int threadCount = 10;
        int iterations = 10000000;
        String[] lookupNames = new String[] {
                "ISO-8859-1", "ISO-8859-2", "ISO-8859-3", "ISO-8859-4", "ISO-8859-5" };

        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new TestCsCacheThread(iterations, cache, lookupNames);
        }

        long startTime = System.nanoTime();

        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        long endTime = System.nanoTime();

        System.out.println(cache.getClass().getName() + ": " + (endTime - startTime) + "ns");
    }


    private static interface CsCache {
        Charset getCharset(String charsetName);
    }


    private static class NoCsCache implements CsCache {

        @Override
        public Charset getCharset(String charsetName) {
            return Charset.forName(charsetName);
        }
    }


    private static class FullCsCache implements CsCache {

        private static final Map<String,Charset> cache = new HashMap<>();

        static {
            for (Charset charset: Charset.availableCharsets().values()) {
                cache.put(charset.name().toLowerCase(Locale.ENGLISH), charset);
                for (String alias : charset.aliases()) {
                    cache.put(alias.toLowerCase(Locale.ENGLISH), charset);
                }
            }
        }


        @Override
        public Charset getCharset(String charsetName) {
            return cache.get(charsetName.toLowerCase(Locale.ENGLISH));
        }
    }


    private static class LazyCsCache implements CsCache {

        private CharsetCache cache = new CharsetCache();

        @Override
        public Charset getCharset(String charsetName) {
            return cache.getCharset(charsetName);
        }
    }


    private static class TestCsCacheThread extends Thread {

        private final int iterations;
        private final CsCache cache;
        private final String[] lookupNames;
        private final int lookupNamesCount;

        public TestCsCacheThread(int iterations, CsCache cache, String[] lookupNames) {
            this.iterations = iterations;
            this.cache = cache;
            this.lookupNames = lookupNames;
            this.lookupNamesCount = lookupNames.length;
        }

        @Override
        public void run() {
            for (int i = 0; i < iterations; i++) {
                cache.getCharset(lookupNames[i % lookupNamesCount]);
            }
        }
    }
}
