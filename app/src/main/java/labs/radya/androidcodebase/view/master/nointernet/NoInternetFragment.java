package labs.radya.androidcodebase.view.master.nointernet;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import labs.radya.androidcodebase.Constant;
import labs.radya.androidcodebase.R;
import labs.radya.androidcodebase.data.source.remote.BaseModel;
import labs.radya.androidcodebase.databinding.NoInternetFragmentBinding;
import labs.radya.androidcodebase.view.master.base.BaseFragment;

/**
 * Created by aderifaldi on 2018-02-06.
 */

public class NoInternetFragment extends BaseFragment {

    Unbinder unbinder;

    private Bundle bundle;
    private BaseModel response;

    private NoInternetFragmentBinding fragmentBinding;
    private NoInternetDataBinding dataBinding;

    public NoInternetFragment() {
        // Requires empty public constructor
    }

    public static NoInternetFragment newInstance() {
        return new NoInternetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.no_internet_fragment, container, false);
        unbinder = ButterKnife.bind(this, fragmentBinding.getRoot());

        initData();

        return fragmentBinding.getRoot();
    }

    private void initData() {
        bundle = getArguments();
        if (bundle != null){
            response = (BaseModel) bundle.getSerializable(Constant.SERIALIZABLE_NAME);
            dataBinding = new NoInternetDataBinding(response);
        }
        bindingData();
    }

    private void bindingData() {
        fragmentBinding.setNoInternet(dataBinding);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
