
package org.apache.catalina.tribes.membership;

import java.io.IOException;

import org.apache.catalina.tribes.util.Arrays;

public class StaticMember extends MemberImpl {
    public StaticMember() {
        super();
    }

    public StaticMember(String host, int port, long aliveTime) throws IOException {
        super(host, port, aliveTime);
    }

    public StaticMember(String host, int port, long aliveTime, byte[] payload) throws IOException {
        super(host, port, aliveTime, payload);
    }

    /**
     * @param host String, either in byte array string format, like {214,116,1,3}
     * or as a regular hostname, 127.0.0.1 or tomcat01.mydomain.com
     */
    public void setHost(String host) {
        if ( host == null ) return;
        if ( host.startsWith("{") ) setHost(Arrays.fromString(host));
        else try { setHostname(host); }catch (IOException x) { throw new RuntimeException(x);}

    }

    /**
     * @param domain String, either in byte array string format, like {214,116,1,3}
     * or as a regular string value like 'mydomain'. The latter will be converted using ISO-8859-1 encoding
     */
    public void setDomain(String domain) {
        if ( domain == null ) return;
        if ( domain.startsWith("{") ) setDomain(Arrays.fromString(domain));
        else setDomain(Arrays.convert(domain));
    }

    /**
     * @param id String, must be in byte array string format, like {214,116,1,3} and exactly 16 bytes long
     */
    public void setUniqueId(String id) {
        byte[] uuid = Arrays.fromString(id);
        if ( uuid==null || uuid.length != 16 ) throw new RuntimeException(sm.getString("staticMember.invalid.uuidLength", id));
        setUniqueId(uuid);
    }


}