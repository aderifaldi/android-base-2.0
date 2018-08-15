package labs.radya.androidcodebase;

import android.app.Activity;
import android.app.Application;

import labs.radya.androidcodebase.data.AppDatabase;

/**
 * Created by aderifaldi on 2018-01-18.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    public AppDatabase getLocalDB() {
        return AppDatabase.getDatabase(getInstance());
    }

}
