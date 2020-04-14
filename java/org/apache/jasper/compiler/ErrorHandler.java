

package org.apache.jasper.compiler;

import org.apache.jasper.JasperException;

/**
 * Interface for handling JSP parse and javac compilation errors.
 *
 * An implementation of this interface may be registered with the
 * ErrorDispatcher by setting the XXX initialization parameter in the JSP
 * page compiler and execution servlet in Catalina's web.xml file to the
 * implementation's fully qualified class name.
 *
 * @author Jan Luehe
 * @author Kin-man Chung
 */
public interface ErrorHandler {

    /**
     * Processes the given JSP parse error.
     *
     * @param fname Name of the JSP file in which the parse error occurred
     * @param line Parse error line number
     * @param column Parse error column number
     * @param msg Parse error message
     * @param exception Parse exception
     * @throws JasperException An error occurred
     */
    public void jspError(String fname, int line, int column, String msg,
            Exception exception) throws JasperException;

    /**
     * Processes the given JSP parse error.
     *
     * @param msg Parse error message
     * @param exception Parse exception
     * @throws JasperException An error occurred
     */
    public void jspError(String msg, Exception exception)
            throws JasperException;

    /**
     * Processes the given javac compilation errors.
     *
     * @param details Array of JavacErrorDetail instances corresponding to the
     * compilation errors
     * @throws JasperException An error occurred
     */
    public void javacError(JavacErrorDetail[] details)
            throws JasperException;

    /**
     * Processes the given javac error report and exception.
     *
     * @param errorReport Compilation error report
     * @param exception Compilation exception
     * @throws JasperException An error occurred
     */
    public void javacError(String errorReport, Exception exception)
            throws JasperException;
}
