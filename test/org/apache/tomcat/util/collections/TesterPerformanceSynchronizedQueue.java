
package org.apache.tomcat.util.collections;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

public class TesterPerformanceSynchronizedQueue {

    private static final int THREAD_COUNT = 4;
    private static final int ITERATIONS = 1000000;

    private static final SynchronizedQueue<Object> S_QUEUE =
            new SynchronizedQueue<>();

    private static final Queue<Object> QUEUE = new ConcurrentLinkedQueue<>();

    @Test
    public void testSynchronizedQueue() throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new StackThread();
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].join();
        }

        long end = System.currentTimeMillis();

        System.out.println("SynchronizedQueue: " + (end - start) + "ms");
    }

    public static class StackThread extends Thread {

        @Override
        public void run() {
            for(int i = 0; i < ITERATIONS; i++) {
                Object obj = S_QUEUE.poll();
                if (obj == null) {
                    obj = new Object();
                }
                S_QUEUE.offer(obj);
            }
            super.run();
        }
    }

    @Test
    public void testConcurrentQueue() throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new QueueThread();
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].join();
        }

        long end = System.currentTimeMillis();

        System.out.println("ConcurrentLinkedQueue: " + (end - start) + "ms");
    }

    public static class QueueThread extends Thread {

        @Override
        public void run() {
            for(int i = 0; i < ITERATIONS; i++) {
                Object obj = QUEUE.poll();
                if (obj == null) {
                    obj = new Object();
                }
                QUEUE.offer(obj);
            }
            super.run();
        }
    }
}
