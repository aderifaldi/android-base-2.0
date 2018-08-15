package labs.radya.androidcodebase.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import labs.radya.androidcodebase.R;
import labs.radya.androidcodebase.view.master.base.BaseActivity;


public class NullActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
    }

}
