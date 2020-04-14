
package org.apache.jasper.util;

import org.junit.Assert;
import org.junit.Test;


public class TestFastRemovalDequeue {

    @Test
    public void testSinglePushPop() throws Exception {
        FastRemovalDequeue<Object> q = new FastRemovalDequeue<>(2);

        Object o1 = new Object();

        q.push(o1);

        Object r = q.pop();

        Assert.assertEquals(o1, r);
        Assert.assertNull(q.first);
        Assert.assertNull(q.last);
    }


    @Test
    public void testDoublePushPop() throws Exception {
        FastRemovalDequeue<Object> q = new FastRemovalDequeue<>(2);

        Object o1 = new Object();
        Object o2 = new Object();

        q.push(o1);
        q.push(o2);

        Assert.assertEquals(o2, q.first.getContent());
        Assert.assertEquals(o1, q.last.getContent());

        Object r1 = q.pop();

        Assert.assertEquals(o1, r1);
        Assert.assertEquals(o2, q.first.getContent());
        Assert.assertEquals(o2, q.last.getContent());


        Object r2 = q.pop();
        Assert.assertEquals(o2, r2);
        Assert.assertNull(q.first);
        Assert.assertNull(q.last);
    }


    @Test
    public void testSingleUnpopPop() throws Exception {
        FastRemovalDequeue<Object> q = new FastRemovalDequeue<>(2);

        Object o1 = new Object();

        q.unpop(o1);

        Object r = q.pop();

        Assert.assertEquals(o1, r);
        Assert.assertNull(q.first);
        Assert.assertNull(q.last);
    }


    @Test
    public void testDoubleUnpopPop() throws Exception {
        FastRemovalDequeue<Object> q = new FastRemovalDequeue<>(2);

        Object o1 = new Object();
        Object o2 = new Object();

        q.unpop(o1);
        q.unpop(o2);

        Assert.assertEquals(o1, q.first.getContent());
        Assert.assertEquals(o2, q.last.getContent());

        Object r2 = q.pop();

        Assert.assertEquals(o2, r2);
        Assert.assertEquals(o1, q.first.getContent());
        Assert.assertEquals(o1, q.last.getContent());


        Object r1 = q.pop();
        Assert.assertEquals(o1, r1);
        Assert.assertNull(q.first);
        Assert.assertNull(q.last);
    }


    @Test
    public void testSinglePushUnpush() throws Exception {
        FastRemovalDequeue<Object> q = new FastRemovalDequeue<>(2);

        Object o1 = new Object();

        q.push(o1);

        Object r = q.unpush();

        Assert.assertEquals(o1, r);
        Assert.assertNull(q.first);
        Assert.assertNull(q.last);
    }


    @Test
    public void testDoublePushUnpush() throws Exception {
        FastRemovalDequeue<Object> q = new FastRemovalDequeue<>(2);

        Object o1 = new Object();
        Object o2 = new Object();

        q.push(o1);
        q.push(o2);

        Assert.assertEquals(o2, q.first.getContent());
        Assert.assertEquals(o1, q.last.getContent());

        Object r2 = q.unpush();

        Assert.assertEquals(o2, r2);
        Assert.assertEquals(o1, q.first.getContent());
        Assert.assertEquals(o1, q.last.getContent());


        Object r1 = q.unpush();
        Assert.assertEquals(o1, r1);
        Assert.assertNull(q.first);
        Assert.assertNull(q.last);
    }


    @Test
    public void testSinglePushRemove() throws Exception {
        FastRemovalDequeue<Object> q = new FastRemovalDequeue<>(2);

        Object o1 = new Object();

        FastRemovalDequeue<Object>.Entry e1 = q.push(o1);

        Assert.assertEquals(o1, e1.getContent());

        q.remove(e1);

        Assert.assertNull(q.first);
        Assert.assertNull(q.last);
    }


    @Test
    public void testDoublePushRemove() throws Exception {
        FastRemovalDequeue<Object> q = new FastRemovalDequeue<>(2);

        Object o1 = new Object();
        Object o2 = new Object();

        FastRemovalDequeue<Object>.Entry e1 = q.push(o1);
        FastRemovalDequeue<Object>.Entry e2 = q.push(o2);

        Assert.assertEquals(o1, e1.getContent());
        Assert.assertEquals(o2, e2.getContent());

        Assert.assertEquals(o2, q.first.getContent());
        Assert.assertEquals(o1, q.last.getContent());

        q.remove(e1);

        Assert.assertEquals(o2, q.first.getContent());
        Assert.assertEquals(o2, q.last.getContent());

        q.remove(e2);

        Assert.assertNull(q.first);
        Assert.assertNull(q.last);
    }
}
