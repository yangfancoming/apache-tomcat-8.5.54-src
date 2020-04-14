
package org.apache.catalina.ssi;


import java.io.PrintWriter;
import java.util.Collection;

/**
 * Implements the Server-side #printenv command
 *
 * @author Dan Sandberg
 * @author David Becker
 */
public class SSIPrintenv implements SSICommand {
    /**
     * @see SSICommand
     */
    @Override
    public long process(SSIMediator ssiMediator, String commandName,
            String[] paramNames, String[] paramValues, PrintWriter writer) {
        long lastModified = 0;
        //any arguments should produce an error
        if (paramNames.length > 0) {
            String errorMessage = ssiMediator.getConfigErrMsg();
            writer.write(errorMessage);
        } else {
            Collection<String> variableNames = ssiMediator.getVariableNames();
            for (String variableName : variableNames) {
                String variableValue = ssiMediator.getVariableValue(variableName, SSIMediator.ENCODING_ENTITY);
                //This shouldn't happen, since all the variable names must
                // have values
                if (variableValue == null) {
                    variableValue = "(none)";
                }
                writer.write(variableName);
                writer.write('=');
                writer.write(variableValue);
                writer.write('\n');
                lastModified = System.currentTimeMillis();
            }
        }
        return lastModified;
    }
}
