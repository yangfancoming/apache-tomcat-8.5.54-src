
package org.apache.catalina.ssi;


/**
 * Exception used to tell SSIProcessor that it should stop processing SSI
 * commands. This is used to mimic the Apache behavior in #set with invalid
 * attributes.
 *
 * @author Paul Speed
 * @author Dan Sandberg
 */
public class SSIStopProcessingException extends Exception {

    private static final long serialVersionUID = 1L;
    // No specific functionality for this class
}