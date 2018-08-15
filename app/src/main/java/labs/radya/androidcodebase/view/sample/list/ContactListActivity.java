package labs.radya.androidcodebase.view.sample.list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import labs.radya.androidcodebase.Constant;
import labs.radya.androidcodebase.R;
import labs.radya.androidcodebase.data.source.preference.PrefManager;
import labs.radya.androidcodebase.data.source.remote.ApiResponse;
import labs.radya.androidcodebase.data.source.remote.BaseModel;
import labs.radya.androidcodebase.util.ActivityUtils;
import labs.radya.androidcodebase.util.NetworkUtils;
import labs.radya.androidcodebase.view.master.base.BaseActivity;
import labs.radya.androidcodebase.view.sample.ContactList;
import labs.radya.androidcodebase.view.sample.ContactViewModel;

public class ContactListActivity extends BaseActivity {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        ButterKnife.bind(this);
        initSwipeRefresh();
        initToken();
        initViewModel();
        getContactList();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getContactList();
            }
        });
    }

    private void initToken() {
        PrefManager.saveString(Constant.Preference.User.AUTH_TOKEN, Constant.Data.TOKEN);
    }

    private void getContactList() {
        showLoading(swipeRefreshLayout);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Constant.Api.Params.KEYWORD, "");

        viewModel.getContactList(NetworkUtils.getConnectionManager(), jsonObject, Constant.Api.LIMIT, 0);
        viewModel.getContactListResponse().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(@Nullable ApiResponse apiResponse) {

            }
        });

        viewModel.getContactList(NetworkUtils.getConnectionManager(), jsonObject, Constant.Api.LIMIT, 0);
        viewModel.getContactListResponse().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(@Nullable ApiResponse apiResponse) {
                dismissLoading(swipeRefreshLayout);
                if (apiResponse.getData() != null) {

                    BaseModel baseResponse = (BaseModel) apiResponse.getData();

                    if (!baseResponse.isError()) {
                        ContactList response = (ContactList) apiResponse.getData();

                        if (response.getData().size() != 0) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(Constant.SERIALIZABLE_NAME, response);

                            Fragment fragment = ContactListFragment.newInstance();
                            fragment.setArguments(bundle);

                            ActivityUtils.replaceFragment(getSupportFragmentManager(), fragment,
                                    R.id.contentFrame, false);

                        } else {
                            showNoData(R.mipmap.ic_launcher_round, getString(R.string.alertMessageNoData));
                        }

                    } else {
                        showNoInternet(baseResponse);
                    }

                }
            }
        });
    }

}
