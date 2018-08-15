package labs.radya.androidcodebase.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.io.Serializable;

import labs.radya.androidcodebase.Constant;
import labs.radya.androidcodebase.R;

public class IntentUtils {

    public static void runSplashDelay(final Activity from, final Class<?> to) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intentTo(from, to, true);
            }
        }, Constant.SPLASH_DELAY);
    }

    public static void runSplashDelay(final Activity from, final Class<?> to, final Bundle bundle) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intentTo(from, to, true, bundle);
            }
        }, Constant.SPLASH_DELAY);
    }

    public static void intentTo(final Activity from, final Class<?> to, final boolean isFinish) {

        Intent intent = new Intent(from, to);
        from.startActivity(intent);
        from.overridePendingTransition(R.anim.left_in, R.anim.left_out);
        if (isFinish) {
            from.finish();
        }
    }

    public static void intentTo(final Activity from, final Class<?> to, final boolean isFinish, Bundle data) {
        if (data != null){
            Intent intent = new Intent(from, to);
            intent.putExtras(data);

            from.startActivity(intent);
            from.overridePendingTransition(R.anim.left_in, R.anim.left_out);
            if (isFinish) {
                from.finish();
            }
        }
    }

    public static void backTo(final Activity from, final Class<?> to, final boolean isFinish) {

        Intent intent = new Intent(from, to);
        from.startActivity(intent);
        from.overridePendingTransition(R.anim.left_back_in, R.anim.left_back_out);
        if (isFinish) {
            from.finish();
        }
    }

    public static void backTo(final Activity from, final Class<?> to, final boolean isFinish, Bundle data) {
        if (data != null){
            Intent intent = new Intent(from, to);
            intent.putExtras(data);

            from.startActivity(intent);
            from.overridePendingTransition(R.anim.left_in, R.anim.left_out);
            if (isFinish) {
                from.finish();
            }
        }
    }

    public static void backAnimation(Activity activity){
        activity.overridePendingTransition(R.anim.left_back_in, R.anim.left_back_out);
    }

}
