
package org.apache.catalina.tribes.group.interceptors;

public interface EncryptInterceptorMBean {

    // Config
    public int getOptionFlag();
    public void setOptionFlag(int optionFlag);

    public void setEncryptionAlgorithm(String algorithm);
    public String getEncryptionAlgorithm();
    public void setEncryptionKey(byte[] key);
    public byte[] getEncryptionKey();
    public void setProviderName(String provider);
    public String getProviderName();
}
