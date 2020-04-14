
package org.apache.catalina.tribes.group.interceptors;

public interface FragmentationInterceptorMBean {

    // Attributes
    public int getMaxSize();

    public long getExpire();

    public void setMaxSize(int maxSize);

    public void setExpire(long expire);
}