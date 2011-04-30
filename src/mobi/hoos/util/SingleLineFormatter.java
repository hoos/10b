package mobi.hoos.util;

import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SingleLineFormatter extends Formatter {
    private static SimpleDateFormat FRMT_DATE;
        
    public SingleLineFormatter() {
        // check if the date format was specified
        // by default this is used for quick and dirty runs, don't care about the year date
        String dateFormat = System.getProperty("java.util.logging.dateFormat", "HH:mm:ss"); 
        FRMT_DATE = new SimpleDateFormat(dateFormat);
    }
                    
    public String format(LogRecord record) {
        // use the buffer for optimal string construction
        StringBuffer sb = new StringBuffer();
        // level
       sb.append(record.getLevel().toString().toLowerCase());
       sb.append(": ");
       // format time
       sb.append(FRMT_DATE.format(record.getMillis())).append(" ");
       // thread
       sb.append("[").append(Thread.currentThread().getName()).append("] ");
       // package/class name, logging name
       String name = record.getLoggerName();
           if (name.startsWith("com.letor.")) // truncate the logging name, reduce the clutter
               name = name.substring("com.letor.".length());

               sb.append(name);
               sb.append("   ");
               sb.append(record.getMessage());
               // if there was an exception thrown, log it as well
               if (record.getThrown() != null) {
                   sb.append("\n").append(printThrown(record.getThrown()));
               }

               sb.append("\n");

               return sb.toString();
           }
        
    private String printThrown(Throwable thrown) {
        StringBuffer sb = new StringBuffer();
        sb.append("").append(thrown.getClass().getName());
        sb.append(" - ").append(thrown.getMessage());
        sb.append("\n");
        for (StackTraceElement trace : thrown.getStackTrace())
           sb.append("\tat ").append(trace).append("\n");
           
        Throwable cause = thrown.getCause();
        if (cause != null)
            sb.append("\n").append(printThrown(cause));
           
            return sb.toString();
     }
}
