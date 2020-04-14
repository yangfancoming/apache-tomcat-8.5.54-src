
package org.apache.tomcat.util.collections;

import org.junit.Assert;
import org.junit.Test;

public class TestSynchronizedStack {

    @Test
    public void testPopEmpty() {
        SynchronizedStack<Object> stack = new SynchronizedStack<>();
        Assert.assertNull(stack.pop());
    }

    @Test
    public void testPushPopOrder() {
        SynchronizedStack<Object> stack = new SynchronizedStack<>();

        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        Object o4 = new Object();

        stack.push(o1);
        stack.push(o2);
        stack.push(o3);
        stack.push(o4);

        Assert.assertSame(stack.pop(), o4);
        Assert.assertSame(stack.pop(), o3);
        Assert.assertSame(stack.pop(), o2);
        Assert.assertSame(stack.pop(), o1);

        Assert.assertNull(stack.pop());
    }

    @Test
    public void testExpandPushPopOrder() {
        SynchronizedStack<Object> stack = new SynchronizedStack<>();

        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        Object o4 = new Object();

        for (int i = 0; i < 300; i++) {
            stack.push(o1);
            stack.push(o2);
            stack.push(o3);
            stack.push(o4);
        }

        for (int i = 0; i < 300; i++) {
            Assert.assertSame(stack.pop(), o4);
            Assert.assertSame(stack.pop(), o3);
            Assert.assertSame(stack.pop(), o2);
            Assert.assertSame(stack.pop(), o1);
        }

        Assert.assertNull(stack.pop());
    }

    @Test
    public void testLimit() {
        SynchronizedStack<Object> stack = new SynchronizedStack<>(2,2);

        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        Object o4 = new Object();

        stack.push(o1);
        stack.push(o2);
        stack.push(o3);
        stack.push(o4);

        Assert.assertSame(stack.pop(), o2);
        Assert.assertSame(stack.pop(), o1);

        Assert.assertNull(stack.pop());
    }


    @Test
    public void testLimitExpand() {
        SynchronizedStack<Object> stack = new SynchronizedStack<>(1,3);

        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        Object o4 = new Object();

        stack.push(o1);
        stack.push(o2);
        stack.push(o3);
        stack.push(o4);

        Assert.assertSame(stack.pop(), o3);
        Assert.assertSame(stack.pop(), o2);
        Assert.assertSame(stack.pop(), o1);

        Assert.assertNull(stack.pop());
    }
}
