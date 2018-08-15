package labs.radya.androidcodebase.view.sample.detail;


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
import labs.radya.androidcodebase.databinding.ContactDetailFragmentBinding;
import labs.radya.androidcodebase.view.master.base.BaseFragment;
import labs.radya.androidcodebase.view.sample.Contact;

/**
 * Created by aderifaldi on 2018-02-06.
 */

public class ContactDetailFragment extends BaseFragment {

    Unbinder unbinder;

    private Contact contact;
    private Bundle bundle;

    private ContactDetailFragmentBinding fragmentBinding;
    private ContactDetailDataBinding dataBinding;

    public ContactDetailFragment() {
        // Requires empty public constructor
    }

    public static ContactDetailFragment newInstance() {
        return new ContactDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.contact_detail_fragment, container, false);
        unbinder = ButterKnife.bind(this, fragmentBinding.getRoot());

        initData();

        return fragmentBinding.getRoot();
    }

    private void initData() {
        bundle = getArguments();
        if (bundle != null){
            contact = (Contact) bundle.getSerializable(Constant.SERIALIZABLE_NAME);
            dataBinding = new ContactDetailDataBinding(contact);
            bindingData();
        }
    }

    private void bindingData() {
        fragmentBinding.setContactDetail(dataBinding);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
