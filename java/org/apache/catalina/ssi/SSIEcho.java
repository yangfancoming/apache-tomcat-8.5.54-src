
package org.apache.catalina.ssi;


import java.io.PrintWriter;
/**
 * Return the result associated with the supplied Server Variable.
 *
 * @author Bip Thelin
 * @author Paul Speed
 * @author Dan Sandberg
 * @author David Becker
 */
public class SSIEcho implements SSICommand {
    protected static final String DEFAULT_ENCODING = SSIMediator.ENCODING_ENTITY;
    protected static final String MISSING_VARIABLE_VALUE = "(none)";


    /**
     * @see SSICommand
     */
    @Override
    public long process(SSIMediator ssiMediator, String commandName,
            String[] paramNames, String[] paramValues, PrintWriter writer) {
        String encoding = DEFAULT_ENCODING;
        String originalValue = null;
        String errorMessage = ssiMediator.getConfigErrMsg();
        for (int i = 0; i < paramNames.length; i++) {
            String paramName = paramNames[i];
            String paramValue = paramValues[i];
            if (paramName.equalsIgnoreCase("var")) {
                originalValue = paramValue;
            } else if (paramName.equalsIgnoreCase("encoding")) {
                if (isValidEncoding(paramValue)) {
                    encoding = paramValue;
                } else {
                    ssiMediator.log("#echo--Invalid encoding: " + paramValue);
                    writer.write(ssiMediator.encode(errorMessage, SSIMediator.ENCODING_ENTITY));
                }
            } else {
                ssiMediator.log("#echo--Invalid attribute: " + paramName);
                writer.write(ssiMediator.encode(errorMessage, SSIMediator.ENCODING_ENTITY));
            }
        }
        String variableValue = ssiMediator.getVariableValue(originalValue, encoding);
        if (variableValue == null) {
            variableValue = MISSING_VARIABLE_VALUE;
        }
        writer.write(variableValue);
        return System.currentTimeMillis();
    }


    protected boolean isValidEncoding(String encoding) {
        return encoding.equalsIgnoreCase(SSIMediator.ENCODING_URL)
                || encoding.equalsIgnoreCase(SSIMediator.ENCODING_ENTITY)
                || encoding.equalsIgnoreCase(SSIMediator.ENCODING_NONE);
    }
}