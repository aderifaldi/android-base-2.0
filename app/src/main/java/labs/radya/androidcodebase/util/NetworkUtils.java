package labs.radya.androidcodebase.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import labs.radya.androidcodebase.MyApplication;
import labs.radya.androidcodebase.data.source.remote.ConnectionManager;

public class NetworkUtils {

    public static ConnectionManager getConnectionManager(){
        return new ConnectionManager<>();
    }

    public static boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
