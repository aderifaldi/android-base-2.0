package labs.radya.androidcodebase.view.sample.list;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import labs.radya.androidcodebase.Constant;
import labs.radya.androidcodebase.R;
import labs.radya.androidcodebase.data.source.remote.ApiResponse;
import labs.radya.androidcodebase.data.source.remote.BaseModel;
import labs.radya.androidcodebase.helper.EndlessRecyclerViewScrollListener;
import labs.radya.androidcodebase.util.IntentUtils;
import labs.radya.androidcodebase.util.NetworkUtils;
import labs.radya.androidcodebase.view.master.base.BaseFragment;
import labs.radya.androidcodebase.view.sample.Contact;
import labs.radya.androidcodebase.view.sample.ContactList;
import labs.radya.androidcodebase.view.sample.ContactViewModel;
import labs.radya.androidcodebase.view.sample.detail.ContactDetailActivity;

/**
 * Created by aderifaldi on 2018-02-06.
 */

public class ContactListFragment extends BaseFragment {

    Unbinder unbinder;

    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.loadmoreLoading)
    ProgressBar loadmoreLoading;

    private Bundle bundle;
    private ContactList contactList;

    private ContactListAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private EndlessRecyclerViewScrollListener scrollListener;

    private ContactViewModel viewModel;

    public ContactListFragment() {
        // Requires empty public constructor
    }

    public static ContactListFragment newInstance() {
        return new ContactListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViewModel();
        initList();
        initData();
        return view;
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
    }

    private void getMoreContact(int offset) {
        showLoadMoreLoading(loadmoreLoading);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Constant.Api.Params.KEYWORD, "");

        viewModel.getContactList(NetworkUtils.getConnectionManager(), jsonObject, Constant.Api.LIMIT, offset);
        viewModel.getContactListResponse().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(@Nullable ApiResponse apiResponse) {
                dismissLoadMoreLoading(loadmoreLoading);

                if (apiResponse.getData() != null) {
                    BaseModel baseResponse = (BaseModel) apiResponse.getData();
                    if (!baseResponse.isError()) {
                        ContactList response = (ContactList) apiResponse.getData();
                        storeDataToList(response);
                    }
                }
            }
        });
    }

    private void initList() {
        adapter = new ContactListAdapter();
        linearLayoutManager = new LinearLayoutManager(getActivity());

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int offset, int totalItemsCount, RecyclerView view) {
                getMoreContact(offset);
            }
        };

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = adapter.getData().get(i);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.SERIALIZABLE_NAME, contact);

                IntentUtils.intentTo(getActivity(), ContactDetailActivity.class, false, bundle);
            }
        });

        list.setAdapter(adapter);
        list.setLayoutManager(linearLayoutManager);

        list.addOnScrollListener(scrollListener);
    }

    private void initData() {
        bundle = getArguments();
        if (bundle != null) {
            contactList = (ContactList) bundle.getSerializable(Constant.SERIALIZABLE_NAME);
            storeDataToList(contactList);
        }
    }

    private void storeDataToList(ContactList response) {
        if (response.getData().size() != 0){
            for (int i = 0; i < response.getData().size(); i++) {
                adapter.getData().add(response.getData().get(i));
                adapter.notifyItemInserted(adapter.getData().size() - 1);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
