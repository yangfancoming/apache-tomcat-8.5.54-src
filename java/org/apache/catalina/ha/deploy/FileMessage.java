

package org.apache.catalina.ha.deploy;

import org.apache.catalina.ha.ClusterMessageBase;
import org.apache.catalina.tribes.Member;

/**
 * Contains the data for a file being transferred over TCP, this is
 * essentially a fragment of a file, read and written by the FileMessageFactory
 * @version 1.0
 */

public class FileMessage extends ClusterMessageBase {
    private static final long serialVersionUID = 2L;

    private int messageNumber;
    private byte[] data;
    private int dataLength;

    private long totalNrOfMsgs;
    private final String fileName;
    private final String contextName;

    public FileMessage(Member source,
                       String fileName,
                       String contextName) {
        this.address=source;
        this.fileName=fileName;
        this.contextName=contextName;
    }

    public int getMessageNumber() {
        return messageNumber;
    }
    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }
    public long getTotalNrOfMsgs() {
        return totalNrOfMsgs;
    }
    public void setTotalNrOfMsgs(long totalNrOfMsgs) {
        this.totalNrOfMsgs = totalNrOfMsgs;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data, int length) {
        this.data = data;
        this.dataLength = length;
    }
    public int getDataLength() {
        return dataLength;
    }

    @Override
    public String getUniqueId() {
        StringBuilder result = new StringBuilder(getFileName());
        result.append("#-#");
        result.append(getMessageNumber());
        result.append("#-#");
        result.append(System.currentTimeMillis());
        return result.toString();
    }


    public String getFileName() {
        return fileName;
    }
    public String getContextName() {
        return contextName;
    }
}
