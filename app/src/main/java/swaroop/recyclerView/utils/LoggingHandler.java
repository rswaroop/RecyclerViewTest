package swaroop.recyclerView.utils;

import android.util.Log;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import swaroop.recyclerView.BuildConfig;

public class LoggingHandler extends Handler {
    private static final String TAG_PREFIX = BuildConfig.APPLICATION_ID;

    @Override
    public void close() {
    }

    @Override
    public void flush() {
    }

    @Override
    public void publish(LogRecord record) {
        int level = getAndroidLevel(record.getLevel());
        String tag = fullTag(record.getLoggerName());
        try {
            String message = getFormatter().format(record);
            Log.println(level, tag, message);
        } catch (RuntimeException e) {
            Log.e(getClass().getSimpleName(), "Error logging message.", e);
        }
    }

    private static int getAndroidLevel(Level level) {
        int value = level.intValue();
        if (value >= 1000) { // SEVERE
            return Log.ERROR;
        } else if (value >= 900) { // WARNING
            return Log.WARN;
        } else if (value >= 800) { // INFO
            return Log.INFO;
        } else if (value >= 500) { // FINE
            return Log.DEBUG;
        } else {
            return Log.VERBOSE;
        }
    }

    private String fullTag(String tag) {
        if (tag != null && tag.startsWith(TAG_PREFIX)) {
            return TAG_PREFIX + tag.substring(TAG_PREFIX.length());
        }

        return tag;
    }
}
