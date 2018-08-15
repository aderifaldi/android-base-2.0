package labs.radya.androidcodebase.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import labs.radya.androidcodebase.R;
import labs.radya.androidcodebase.view.master.base.BaseFragment;

/**
 * Created by aderifaldi on 2018-02-06.
 */

public class NullFragment extends BaseFragment {

    Unbinder unbinder;

    public NullFragment() {
        // Requires empty public constructor
    }

    public static NullFragment newInstance() {
        return new NullFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.null_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
