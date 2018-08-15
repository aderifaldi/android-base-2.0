package labs.radya.androidcodebase.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class AppUtils {

    public static boolean ENABLE_LOG = true;

    public AppUtils() {
    }

    public void logD(String TAG, String msg) {
        if (ENABLE_LOG) {
            try {
                if (msg == null) {
                    throw new NullPointerException();
                }

                Log.d(TAG, msg);
            } catch (NullPointerException var3) {
                var3.printStackTrace();
            }
        }

    }

    public float pixelsToSp(Context context, Float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px.floatValue() / scaledDensity;
    }

    public float spToPixel(Context context, Float sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return sp.floatValue() * scaledDensity;
    }

    public int getDip(int value, Context context) {
        return (int) TypedValue.applyDimension(1, (float) value, context.getResources().getDisplayMetrics());
    }

    public int getDip(int value, DisplayMetrics display) {
        return (int) TypedValue.applyDimension(1, (float) value, display);
    }

    public int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((double) ((float) dp * displayMetrics.density) + 0.5D);
    }

    @TargetApi(13)
    public Point getScreenSize(Activity act) {
        Point size = new Point();
        WindowManager w = act.getWindowManager();
        if (Build.VERSION.SDK_INT >= 13) {
            w.getDefaultDisplay().getSize(size);
        } else {
            Display d = w.getDefaultDisplay();
            size.x = d.getWidth();
            size.y = d.getHeight();
        }

        return size;
    }

    public Point getScreenSize(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        Point size = new Point();
        size.x = metrics.widthPixels;
        size.y = metrics.heightPixels;
        return size;
    }

    @TargetApi(16)
    public void removeGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (Build.VERSION.SDK_INT < 16) {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        } else {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        }

    }

    @TargetApi(16)
    public void setBackground(View view, Drawable draw) {
        if (Build.VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(draw);
        } else {
            view.setBackground(draw);
        }

    }

    public String getStringFile(InputStream input) throws IOException {
        InputStreamReader is = new InputStreamReader(input);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(is);

        for (String read = br.readLine(); read != null; read = br.readLine()) {
            sb.append(read);
        }

        return sb.toString();
    }

    public Point getSizeAspecRatioWithInputWidth(int widthDesired, int widthBitmap, int heightBitmap) {
        int heightDesired = widthDesired * heightBitmap / widthBitmap;
        Point point = new Point(widthDesired, heightDesired);
        return point;
    }

    public Point getSizeAspecRatioWithInputHeight(int heightDesired, int widthBitmap, int heightBitmap) {
        int widthDesired = heightDesired * widthBitmap / heightBitmap;
        Point point = new Point(widthDesired, heightDesired);
        return point;
    }

    public boolean deleteFolder(File dir) {
        if (dir.exists()) {
            File[] fileList = dir.listFiles();

            for (int i = 0; i < fileList.length; ++i) {
                if (fileList[i].isDirectory()) {
                    deleteFolder(fileList[i]);
                } else {
                    fileList[i].delete();
                }
            }

            return dir.delete();
        } else {
            return false;
        }
    }

    @TargetApi(11)
    public void setAlpha(float alpha, View view) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.setAlpha(alpha);
        } else {
            int intAlpha = (int) (255.0F * alpha);
            view.setAlpha((float) intAlpha);
        }

    }

    @TargetApi(16)
    public void setBackgroundDrawable(Drawable drawable, View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }

    }

    public String bin2hex(String input) {
        byte[] data = input.getBytes();
        return String.format("%0" + (data.length * 2) + 'x', new BigInteger(1, data));
    }

    public void showLoading(ProgressBar progress) {
        progress.setVisibility(View.VISIBLE);
    }

    public void dismissLoading(ProgressBar progress) {
        progress.setVisibility(View.GONE);
    }

}
