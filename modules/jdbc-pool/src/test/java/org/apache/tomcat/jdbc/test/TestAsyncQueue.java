

package org.apache.tomcat.jdbc.test;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.tomcat.jdbc.pool.FairBlockingQueue;

public class TestAsyncQueue {

    protected FairBlockingQueue<Object> queue = null;

    @Before
    public void setUp() throws Exception {
        this.queue = new FairBlockingQueue<>();
    }

    @After
    public void tearDown() throws Exception {
        this.queue = null;
    }


    @Test
    public void testAsyncPoll1() throws Exception {
        Object item = new Object();
        queue.offer(item);
        Future<Object> future = queue.pollAsync();
        Assert.assertEquals(future.get(),item);
    }


    @Test
    public void testAsyncPoll2() throws Exception {
        Object item = new Object();
        OfferThread thread = new OfferThread(item,5000);
        thread.start();
        Future<Object> future = queue.pollAsync();
        try {
            future.get(2000, TimeUnit.MILLISECONDS);
            Assert.assertFalse("Request should have timed out",true);
        }catch (TimeoutException x) {
            Assert.assertTrue("Request timed out properly",true);
        }catch (Exception x) {
            Assert.assertTrue("Request threw an error",false);
            x.printStackTrace();
        }
        Assert.assertEquals(future.get(),item);
    }

    protected class OfferThread extends Thread {
        Object item = null;
        long delay = 5000;
        volatile boolean offered = false;
        public OfferThread(Object i, long d) {
            this.item = i;
            this.delay = d;
            this.setDaemon(false);
            this.setName(TestAsyncQueue.class.getName()+"-OfferThread");
        }
        @Override
        public void run() {
            try {
                sleep(delay);
            } catch (Exception ignore){
                // Ignore
            }
            offered = true;
            TestAsyncQueue.this.queue.offer(item);
        }
    }
}
