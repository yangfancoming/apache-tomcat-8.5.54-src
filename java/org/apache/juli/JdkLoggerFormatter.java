
package org.apache.juli;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * A more compact formatter.
 *
 * Equivalent log4j config:
 *   <pre>
 *  log4j.rootCategory=WARN, A1
 *  log4j.appender.A1=org.apache.log4j.ConsoleAppender
 *  log4j.appender.A1.layout=org.apache.log4j.PatternLayout
 *  log4j.appender.A1.Target=System.err
 *  log4j.appender.A1.layout.ConversionPattern=%r %-15.15c{2} %-1.1p %m %n
 *  </pre>
 *
 * Example:
 *  1130122891846 Http11BaseProtocol I Initializing Coyote HTTP/1.1 on http-8800
 *
 *
 * @author Costin Manolache
 */
public class JdkLoggerFormatter extends Formatter {

    // values from JDK Level
    public static final int LOG_LEVEL_TRACE  = 400;
    public static final int LOG_LEVEL_DEBUG  = 500;
    public static final int LOG_LEVEL_INFO   = 800;
    public static final int LOG_LEVEL_WARN   = 900;
    public static final int LOG_LEVEL_ERROR  = 1000;
    public static final int LOG_LEVEL_FATAL  = 1000;

    @Override
    public String format(LogRecord record) {
        Throwable t=record.getThrown();
        int level=record.getLevel().intValue();
        String name=record.getLoggerName();
        long time=record.getMillis();
        String message=formatMessage(record);


        if( name.indexOf('.') >= 0 )
            name = name.substring(name.lastIndexOf('.') + 1);

        // Use a string buffer for better performance
        StringBuilder buf = new StringBuilder();

        buf.append(time);

        // pad to 8 to make it more readable
        for( int i=0; i<8-buf.length(); i++ ) { buf.append(" "); }

        //      Append a readable representation of the log level.
        switch(level) {
         case LOG_LEVEL_TRACE: buf.append(" T "); break;
         case LOG_LEVEL_DEBUG: buf.append(" D "); break;
         case LOG_LEVEL_INFO:  buf.append(" I ");  break;
         case LOG_LEVEL_WARN:  buf.append(" W ");  break;
         case LOG_LEVEL_ERROR: buf.append(" E "); break;
         //case : buf.append(" F "); break;
         default: buf.append("   ");
         }


        // Append the name of the log instance if so configured
        buf.append(name);
        buf.append(" ");

        // pad to 20 chars
        for( int i=0; i<8-buf.length(); i++ ) { buf.append(" "); }

        // Append the message
        buf.append(message);

        // Append stack trace if not null
        if(t != null) {
            buf.append(System.lineSeparator());

            java.io.StringWriter sw= new java.io.StringWriter(1024);
            java.io.PrintWriter pw= new java.io.PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            buf.append(sw.toString());
        }

        buf.append(System.lineSeparator());
        // Print to the appropriate destination
        return buf.toString();
    }
}
