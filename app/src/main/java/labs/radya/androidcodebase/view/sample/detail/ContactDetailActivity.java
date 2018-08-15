package labs.radya.androidcodebase.view.sample.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import labs.radya.androidcodebase.Constant;
import labs.radya.androidcodebase.R;
import labs.radya.androidcodebase.util.ActivityUtils;
import labs.radya.androidcodebase.view.master.base.BaseActivity;
import labs.radya.androidcodebase.view.sample.Contact;


public class ContactDetailActivity extends BaseActivity {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private Contact contact;
    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        disablerefreshLayout(swipeRefreshLayout);
    }

    private void initData() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            contact = (Contact) bundle.getSerializable(Constant.SERIALIZABLE_NAME);

            bundle = new Bundle();
            bundle.putSerializable(Constant.SERIALIZABLE_NAME, contact);

            initFragment();
        }
    }

    private void initFragment() {
        Fragment fragment = ContactDetailFragment.newInstance();
        fragment.setArguments(bundle);

        ActivityUtils.replaceFragment(getSupportFragmentManager(), fragment, R.id.contentFrame, false);
    }

}
