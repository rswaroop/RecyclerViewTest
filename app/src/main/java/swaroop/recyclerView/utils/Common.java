package swaroop.recyclerView.utils;

import android.os.Handler;
import android.os.Looper;

public class Common {
    public static void require(boolean requirement) {
        if (!requirement)
            throw new IllegalArgumentException();
    }

    public static void require(boolean requirement, String message) {
        if (!requirement) {
            throw (message != null) ?
                    new IllegalArgumentException(message) :
                    new IllegalArgumentException();
        }
    }

    public static void runOnUiThread(Runnable runnable) {
        require(runnable != null, "runnable == null");
        Handler h = new Handler(Looper.getMainLooper());
        if (!h.post(runnable))
            throw new IllegalStateException("Failed to post runnable");
    }
}
