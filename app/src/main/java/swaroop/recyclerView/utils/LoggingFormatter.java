package swaroop.recyclerView.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LoggingFormatter extends Formatter {
    private static final String ENTRY_MESSAGE = "entry";
    private static final String ENTRY_PREFIX_1 = "entry with (";
    private static final String ENTRY_PREFIX_2 = " " + ENTRY_PREFIX_1;
    private static final String EXIT_MESSAGE = "exit";
    private static final String EXIT_PREFIX = "exit with (";

    @Override
    public String format(LogRecord logRecord) {
        Throwable t = logRecord.getThrown();
        String message = logRecord.getMessage();
        if (t != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println(message);
            try {
                t.printStackTrace(pw);
            } finally {
                pw.close();
            }

            return sw.toString();
        }

        if (isEntryExitRecord(logRecord)) {
            String sourceMethod = logRecord.getSourceMethodName();
            return sourceMethod + " - " + logRecord.getMessage();
        }

        return message;
    }

    private static boolean isEntryExitRecord(LogRecord logRecord) {
        if (logRecord.getLevel().intValue() >= 500) {
            return false;
        }

        String sourceMethod = logRecord.getSourceMethodName();
        if (sourceMethod == null) {
            return false;
        }

        String message = logRecord.getMessage();
        if (message == null) {
            return false;
        }

        if (message.equals(ENTRY_MESSAGE)
                || message.startsWith(ENTRY_PREFIX_1)
                || message.startsWith(ENTRY_PREFIX_2)) {
            return true;
        }

        if (message.equals(EXIT_MESSAGE) || message.startsWith(EXIT_PREFIX)) {
            return true;
        }

        return false;
    }
}
