

package org.apache.juli;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Outputs the just the log message with no additional elements. Stack traces
 * are not logged. Log messages are separated by
 * <code>System.lineSeparator()</code>. This is intended for use
 * by access logs and the like that need complete control over the output
 * format.
 */
public class VerbatimFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        // Timestamp
        StringBuilder sb = new StringBuilder(record.getMessage());

        // New line for next record
        sb.append(System.lineSeparator());

        return sb.toString();
    }

}
