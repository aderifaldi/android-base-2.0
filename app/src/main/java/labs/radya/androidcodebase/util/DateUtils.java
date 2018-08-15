package labs.radya.androidcodebase.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import labs.radya.androidcodebase.Constant;

/**
 * Created by aderifaldi on 2018-02-10.
 */
public class DateUtils {

    public static void showDatePicker(Activity activity, final TextView textView){
        int mYear, mMonth, mDay;

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(activity,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DateFormat.SHORT);

                        textView.setText(dateFormat.format(calendar.getTime()));

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.show();
    }

    public static String getCurrentTime() {
        try {
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();
            return convertDateToString(currentDate, Constant.DateFormat.SERVER);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertStringDate(String date, String format) {

        SimpleDateFormat sdf;
        SimpleDateFormat dateFormat;
        Date convertedDate;

        sdf = new SimpleDateFormat(format);
        dateFormat = new SimpleDateFormat(Constant.DateFormat.SERVER);

        try {
            convertedDate = dateFormat.parse(date);
            return sdf.format(convertedDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }

    }

    @SuppressLint("SimpleDateFormat")
    public static String convertDateToString(Date date, String format) {
        try {
            SimpleDateFormat sdf;
            sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static long convertDateToLong(String date) {

        DateFormat formatter;
        formatter = new SimpleDateFormat(Constant.DateFormat.SERVER);

        try {
            Date convertedDate = formatter.parse(date);
            return convertedDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
