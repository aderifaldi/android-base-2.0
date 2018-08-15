package labs.radya.androidcodebase.view.master.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import labs.radya.androidcodebase.Constant;
import labs.radya.androidcodebase.R;
import labs.radya.androidcodebase.data.source.remote.BaseModel;
import labs.radya.androidcodebase.util.ActivityUtils;
import labs.radya.androidcodebase.util.AlertUtils;
import labs.radya.androidcodebase.util.IntentUtils;
import labs.radya.androidcodebase.view.master.nodata.NoDataFragment;
import labs.radya.androidcodebase.view.master.nointernet.NoInternetFragment;

public class BaseActivity extends AppCompatActivity {

    public void disablerefreshLayout(final SwipeRefreshLayout swipeRefreshLayout){
        swipeRefreshLayout.setEnabled(false);
    }

    public void showLoading(SwipeRefreshLayout swipeRefreshLayout){
        swipeRefreshLayout.setRefreshing(true);
    }

    public void dismissLoading(SwipeRefreshLayout swipeRefreshLayout){
        swipeRefreshLayout.setRefreshing(false);
    }

    public void showAlert(String message, final Runnable runnable){
        AlertUtils alertUtils = new AlertUtils(this);
        alertUtils.showAlert(message, new AlertUtils.positiveButton() {
            @Override
            public void onYes(DialogInterface dialogInterface) {
                if (runnable != null){
                    runnable.run();
                }
                dialogInterface.dismiss();
            }
        }, "Ok");
    }

    public void showNoInternet(BaseModel response){
        if (response != null){
            if (response.isError()) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.SERIALIZABLE_NAME, response);

                Fragment fragment = NoInternetFragment.newInstance();
                fragment.setArguments(bundle);

                ActivityUtils.replaceFragment(getSupportFragmentManager(), fragment, R.id.contentFrame, false);
            }
        }
    }

    public void showNoData(int icon, String message){
        if (!("").equals(message)){

            Bundle bundle = new Bundle();
            bundle.putInt(Constant.Bundle.ICON, icon);
            bundle.putString(Constant.Bundle.MESSAGE, message);

            Fragment fragment = NoDataFragment.newInstance();
            fragment.setArguments(bundle);

            ActivityUtils.replaceFragment(getSupportFragmentManager(), fragment, R.id.contentFrame, false);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        IntentUtils.backAnimation(this);
    }
}
