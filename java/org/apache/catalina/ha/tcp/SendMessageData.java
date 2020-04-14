

package org.apache.catalina.ha.tcp;

import org.apache.catalina.tribes.Member;

/**
 * @author Peter Rossbach
 */
public class SendMessageData {

    private Object message ;
    private Member destination ;
    private Exception exception ;


    /**
     * @param message The message to send
     * @param destination Member destination
     * @param exception Associated error
     */
    public SendMessageData(Object message, Member destination,
            Exception exception) {
        super();
        this.message = message;
        this.destination = destination;
        this.exception = exception;
    }

    /**
     * @return the destination.
     */
    public Member getDestination() {
        return destination;
    }
    /**
     * @return the exception.
     */
    public Exception getException() {
        return exception;
    }
    /**
     * @return the message.
     */
    public Object getMessage() {
        return message;
    }
}
