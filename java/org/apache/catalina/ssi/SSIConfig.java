
package org.apache.catalina.ssi;


import java.io.PrintWriter;
/**
 * Implements the Server-side #exec command
 *
 * @author Bip Thelin
 * @author Paul Speed
 * @author Dan Sandberg
 * @author David Becker
 */
public final class SSIConfig implements SSICommand {
    /**
     * @see SSICommand
     */
    @Override
    public long process(SSIMediator ssiMediator, String commandName,
            String[] paramNames, String[] paramValues, PrintWriter writer) {
        for (int i = 0; i < paramNames.length; i++) {
            String paramName = paramNames[i];
            String paramValue = paramValues[i];
            String substitutedValue = ssiMediator
                    .substituteVariables(paramValue);
            if (paramName.equalsIgnoreCase("errmsg")) {
                ssiMediator.setConfigErrMsg(substitutedValue);
            } else if (paramName.equalsIgnoreCase("sizefmt")) {
                ssiMediator.setConfigSizeFmt(substitutedValue);
            } else if (paramName.equalsIgnoreCase("timefmt")) {
                ssiMediator.setConfigTimeFmt(substitutedValue);
            } else {
                ssiMediator.log("#config--Invalid attribute: " + paramName);
                //We need to fetch this value each time, since it may change
                // during the
                // loop
                String configErrMsg = ssiMediator.getConfigErrMsg();
                writer.write(configErrMsg);
            }
        }
        // Setting config options doesn't really change the page
        return 0;
    }
}