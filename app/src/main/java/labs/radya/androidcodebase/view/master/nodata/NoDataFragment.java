package labs.radya.androidcodebase.view.master.nodata;


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
import labs.radya.androidcodebase.databinding.NoDataFragmentBinding;
import labs.radya.androidcodebase.view.master.base.BaseFragment;

/**
 * Created by aderifaldi on 2018-02-06.
 */

public class NoDataFragment extends BaseFragment {

    Unbinder unbinder;

    private Bundle bundle;
    private String message;
    private int icon;

    private NoDataFragmentBinding fragmentBinding;
    private NoDataDataBinding dataBinding;

    public NoDataFragment() {
        // Requires empty public constructor
    }

    public static NoDataFragment newInstance() {
        return new NoDataFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.no_data_fragment, container, false);
        unbinder = ButterKnife.bind(this, fragmentBinding.getRoot());

        initData();

        return fragmentBinding.getRoot();
    }

    private void initData() {
        bundle = getArguments();
        if (bundle != null){
            icon = bundle.getInt(Constant.Bundle.ICON);
            message = bundle.getString(Constant.Bundle.MESSAGE);

            dataBinding = new NoDataDataBinding(icon, message);
        }
        bindingData();
    }

    private void bindingData() {
        fragmentBinding.setNoData(dataBinding);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
