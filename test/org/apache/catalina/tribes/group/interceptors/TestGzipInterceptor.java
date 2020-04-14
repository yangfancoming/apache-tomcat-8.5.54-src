
package org.apache.catalina.tribes.group.interceptors;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class TestGzipInterceptor {

    @Test
    public void testSmallerThanBufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE / 2);
    }

    @Test
    public void testJustSmallerThanBufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE -1);
    }

    @Test
    public void testExactBufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE);
    }

    @Test
    public void testJustLargerThanBufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE + 1);
    }

    @Test
    public void testFactor2BufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE * 2);
    }

    @Test
    public void testFactor4BufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE * 4);
    }

    @Test
    public void testMuchLargerThanBufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE * 10 + 1000);
    }

    private void doCompressDecompress(int size) throws Exception {
        byte[] data = new byte[size];
        Arrays.fill(data, (byte)1);
        byte[] compress = GzipInterceptor.compress(data);
        byte[] result = GzipInterceptor.decompress(compress);
        Assert.assertTrue(Arrays.equals(data, result));
    }
}
