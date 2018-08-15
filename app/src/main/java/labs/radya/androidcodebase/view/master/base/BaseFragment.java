package labs.radya.androidcodebase.view.master.base;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;

import labs.radya.androidcodebase.util.AlertUtils;

public class BaseFragment extends Fragment {

    public void disablerefreshLayout(final SwipeRefreshLayout swipeRefreshLayout){
        swipeRefreshLayout.setEnabled(false);
    }

    public void showLoading(SwipeRefreshLayout swipeRefreshLayout){
        swipeRefreshLayout.setRefreshing(true);
    }

    public void dismissLoading(SwipeRefreshLayout swipeRefreshLayout){
        swipeRefreshLayout.setRefreshing(false);
    }

    public void showLoadMoreLoading(ProgressBar progressBar){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void dismissLoadMoreLoading(ProgressBar progressBar){
        progressBar.setVisibility(View.GONE);
    }

    public void showAlert(String message, final Runnable runnable){
        AlertUtils alertUtils = new AlertUtils(getActivity());
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

}
