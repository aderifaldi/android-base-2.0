package labs.radya.androidcodebase.util;

import android.annotation.SuppressLint;

import labs.radya.androidcodebase.Constant;


public class StringUtils {

    @SuppressLint("DefaultLocale")
    private static String formatMoney(String prefix, long money, char separator, String end) {
        return prefix + String.format("%,d", Long.valueOf(money)).replace(',', separator) + end;
    }

    public static String getRpCurency(long money) {
        return formatMoney(Constant.RP, money, '.', "");
    }

}
