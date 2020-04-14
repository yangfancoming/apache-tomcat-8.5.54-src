


package org.apache.catalina.ha.backend;

/**
 * Interface to send data to proxies.
 */
public interface Sender {

  /**
   * Set the configuration parameters
   * @param config The heartbeat listener configuration
   * @throws Exception An error occurred
   */
  public void init(HeartbeatListener config) throws Exception;

  /**
   * Send the message to the proxies
   * @param mess The message that will be sent
   * @return <code>0</code> if no error occurred, <code>-1</code> otherwise
   * @throws Exception An error occurred
   */
  public int send(String mess) throws Exception;
}
