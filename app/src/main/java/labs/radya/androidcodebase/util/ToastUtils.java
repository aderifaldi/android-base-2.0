package labs.radya.androidcodebase.util;

import android.widget.Toast;

import labs.radya.androidcodebase.MyApplication;

/**
 * Created by aderifaldi on 2018-02-06.
 */
public class ToastUtils {

    public static void showToast(String text) {
        Toast.makeText(MyApplication.getInstance(), text, Toast.LENGTH_SHORT).show();
    }

}
