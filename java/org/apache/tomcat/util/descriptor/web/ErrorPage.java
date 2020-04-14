
package org.apache.tomcat.util.descriptor.web;

import java.io.Serializable;

import org.apache.tomcat.util.buf.UDecoder;

/**
 * Representation of an error page element for a web application,
 * as represented in a <code>&lt;error-page&gt;</code> element in the
 * deployment descriptor.
 *
 * @author Craig R. McClanahan
 */
public class ErrorPage extends XmlEncodingBase implements Serializable {

    private static final long serialVersionUID = 2L;


    // ----------------------------------------------------- Instance Variables

    /**
     * The error (status) code for which this error page is active. Note that
     * status code 0 is used for the default error page.
     */
    private int errorCode = 0;


    /**
     * The exception type for which this error page is active.
     */
    private String exceptionType = null;


    /**
     * The context-relative location to handle this error or exception.
     */
    private String location = null;


    // ------------------------------------------------------------- Properties


    /**
     * @return the error code.
     */
    public int getErrorCode() {
        return this.errorCode;
    }


    /**
     * Set the error code.
     *
     * @param errorCode The new error code
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Set the error code (hack for default XmlMapper data type).
     *
     * @param errorCode The new error code
     */
    public void setErrorCode(String errorCode) {

        try {
            this.errorCode = Integer.parseInt(errorCode);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(nfe);
        }
    }


    /**
     * @return the exception type.
     */
    public String getExceptionType() {
        return this.exceptionType;
    }


    /**
     * Set the exception type.
     *
     * @param exceptionType The new exception type
     */
    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }


    /**
     * @return the location.
     */
    public String getLocation() {
        return this.location;
    }


    /**
     * Set the location.
     *
     * @param location The new location
     */
    public void setLocation(String location) {

        //        if ((location == null) || !location.startsWith("/"))
        //            throw new IllegalArgumentException
        //                ("Error Page Location must start with a '/'");
        this.location = UDecoder.URLDecode(location, getCharset());

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Render a String representation of this object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ErrorPage[");
        if (exceptionType == null) {
            sb.append("errorCode=");
            sb.append(errorCode);
        } else {
            sb.append("exceptionType=");
            sb.append(exceptionType);
        }
        sb.append(", location=");
        sb.append(location);
        sb.append("]");
        return sb.toString();
    }

    public String getName() {
        if (exceptionType == null) {
            return Integer.toString(errorCode);
        } else {
            return exceptionType;
        }
    }

}
