
package org.apache.catalina.ssi;


import java.io.PrintWriter;
/**
 * The interface that all SSI commands ( SSIEcho, SSIInclude, ...) must
 * implement.
 *
 * @author Bip Thelin
 * @author Dan Sandberg
 * @author David Becker
 */
public interface SSICommand {
    /**
     * Write the output of the command to the writer.
     *
     * @param ssiMediator
     *            the ssi mediator
     * @param commandName
     *            the name of the actual command ( ie. echo )
     * @param paramNames
     *            The parameter names
     * @param paramValues
     *            The parameter values
     * @param writer
     *            the writer to output to
     * @return the most current modified date resulting from any SSI commands
     * @throws SSIStopProcessingException
     *             if SSI processing should be aborted
     */
    public long process(SSIMediator ssiMediator, String commandName,
            String[] paramNames, String[] paramValues, PrintWriter writer)
            throws SSIStopProcessingException;
}