package labs.radya.androidcodebase.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import labs.radya.androidcodebase.Constant;
import labs.radya.androidcodebase.R;
import labs.radya.androidcodebase.data.source.preference.PrefManager;

public class LocationUtils {

    private Activity activity;

    public LocationUtils(Activity activity) {
        this.activity = activity;
    }

    public void checkGPSAccess(Runnable runnable) {

        LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //Ask the user to enable GPS
            AlertUtils alertUtils = new AlertUtils(activity);
            alertUtils.showAlert(activity.getString(R.string.alertMessageNeedGPSAccess),
                    new AlertUtils.positiveButton() {
                        @Override
                        public void onYes(DialogInterface dialogInterface) {
                            Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            activity.startActivity(i);
                            activity.finish();
                        }
                    }, activity.getString(R.string.labelOk));
        } else {
            runnable.run();
        }
    }

    public void setCurrentLocation() {

        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                mFusedLocationClient.getLastLocation()
                        .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations, this can be null.
                                if (location != null) {
                                    // Logic to handle location object
                                    saveCurrentLocation(location.getLatitude(), location.getLongitude());
                                }
                            }
                        });
            }
        } else {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations, this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                saveCurrentLocation(location.getLatitude(), location.getLongitude());
                            }
                        }
                    });
        }

    }

    public Location getCurrentLocation() {
        try {
            Location location = new Location("");

            location.setLatitude(Double.longBitsToDouble(PrefManager.getLong(Constant.Preference.User.LATITUDE)));
            location.setLongitude(Double.longBitsToDouble(PrefManager.getLong(Constant.Preference.User.LONGITUDE)));

            return location;
        } catch (Exception e) {
            return null;
        }
    }

    public Double getJarak(Double latitude, Double longitude, Double cabangLatitude, Double cabangLongitude) {
        double R = 6371.0D;
        double dLat = Math.toRadians(cabangLatitude.doubleValue() - latitude.doubleValue());
        double dLon = Math.toRadians(cabangLongitude.doubleValue() - longitude.doubleValue());
        double lat1 = Math.toRadians(latitude.doubleValue());
        double lat2 = Math.toRadians(cabangLatitude.doubleValue());
        double a = Math.sin(dLat / 2.0D) * Math.sin(dLat / 2.0D) + Math.sin(dLon / 2.0D) * Math.sin(dLon / 2.0D) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2.0D * Math.atan2(Math.sqrt(a), Math.sqrt(1.0D - a));
        double d = R * c;
        return Double.valueOf(d);
    }

    private void saveCurrentLocation(double latitude, double longitude) {
        PrefManager.saveLong(Constant.Preference.User.LATITUDE, Double.doubleToRawLongBits(latitude));
        PrefManager.saveLong(Constant.Preference.User.LONGITUDE, Double.doubleToRawLongBits(longitude));
    }

}
