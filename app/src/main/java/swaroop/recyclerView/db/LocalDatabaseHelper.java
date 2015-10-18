package swaroop.recyclerView.db;

import android.content.Context;

import com.turbomanage.storm.DatabaseHelper;
import com.turbomanage.storm.api.Database;
import com.turbomanage.storm.api.DatabaseFactory;

@Database(name = "local", version = 1)
public class LocalDatabaseHelper extends DatabaseHelper {

    public LocalDatabaseHelper(Context ctx, DatabaseFactory dbFactory) {
        super(ctx, dbFactory);
    }

    @Override
    public UpgradeStrategy getUpgradeStrategy() {
        return UpgradeStrategy.BACKUP_RESTORE;
    }
}
