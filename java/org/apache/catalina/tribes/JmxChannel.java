
package org.apache.catalina.tribes;

import javax.management.MBeanRegistration;


public interface JmxChannel extends MBeanRegistration {

    /**
     * If set to true, this channel is registered with jmx.
     * @return true if this channel will be registered with jmx.
     */
    public boolean isJmxEnabled();

    /**
     * If set to true, this channel is registered with jmx.
     * @param jmxEnabled set to true if this channel should be registered with jmx.
     */
    public void setJmxEnabled(boolean jmxEnabled);

    /**
     * Return the jmx domain which this channel is registered.
     * @return jmxDomain
     */
    public String getJmxDomain();

    /**
     * Set the jmx domain which this channel should be registered.
     * @param jmxDomain The jmx domain which this channel should be registered.
     */
    public void setJmxDomain(String jmxDomain);

    /**
     * Return the jmx prefix which will be used with channel ObjectName.
     * @return jmxPrefix
     */
    public String getJmxPrefix();

    /**
     * Set the jmx prefix which will be used with channel ObjectName.
     * @param jmxPrefix The jmx prefix which will be used with channel ObjectName.
     */
    public void setJmxPrefix(String jmxPrefix);

}
