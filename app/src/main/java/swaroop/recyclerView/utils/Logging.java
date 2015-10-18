package swaroop.recyclerView.utils;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {
    public static void init(boolean debug) {
        Logger root = Logger.getLogger("");

        for (Handler h : root.getHandlers()) {
            root.removeHandler(h);
        }

        Handler h = new LoggingHandler();
        h.setFormatter(new LoggingFormatter());
        root.addHandler(h);

        if (debug) {
            root.setLevel(Level.ALL);
        } else {
            root.setLevel(Level.OFF);
        }
    }
}
