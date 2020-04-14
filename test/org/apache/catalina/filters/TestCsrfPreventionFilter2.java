

package org.apache.catalina.filters;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.filters.CsrfPreventionFilter.LruCache;

public class TestCsrfPreventionFilter2 {

    /*
     * When this test fails, it tends to enter a long running loop but it will
     * eventually finish (after ~70s on a 8-core Windows box).
     */
    @Test
    public void testLruCacheConcurrency() throws Exception {
        int threadCount = 2;
        long iterationCount = 100000L;

        Assert.assertTrue(threadCount > 1);

        LruCache<String> cache = new LruCache<>(threadCount - 1);

        LruTestThread[] threads = new LruTestThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new LruTestThread(cache, iterationCount);
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        for (int i = 0; i < threadCount; i++) {
            Assert.assertTrue(threads[i].getResult());
        }

    }

    private static class LruTestThread extends Thread {
        private final LruCache<String> cache;
        private long iterationCount = 0;
        private volatile boolean result = false;

        public LruTestThread(LruCache<String> cache, long iterationCount) {
            this.cache = cache;
            this.iterationCount = iterationCount;
        }

        public boolean getResult() {
            return result;
        }

        @Override
        public void run() {
            String test = getName();
            try {
                for (long i = 0; i < iterationCount; i++) {
                    cache.add(test + i);
                    if (!cache.contains(test + i)) {
                        // Expected
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            result = true;
        }
    }
}
