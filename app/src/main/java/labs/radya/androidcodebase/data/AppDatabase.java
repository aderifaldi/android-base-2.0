package labs.radya.androidcodebase.data;

/**
 * Created by RadyaLabs PC on 15/12/2017.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import labs.radya.androidcodebase.Constant;
import labs.radya.androidcodebase.data.source.cache.CacheDao;


@Database(entities = {Cache.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract CacheDao cacheDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Constant.DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}