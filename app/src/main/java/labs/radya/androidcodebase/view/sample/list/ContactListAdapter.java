package labs.radya.androidcodebase.view.sample.list;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import labs.radya.androidcodebase.R;
import labs.radya.androidcodebase.BR;
import labs.radya.androidcodebase.view.GenericViewHolder;
import labs.radya.androidcodebase.view.sample.Contact;

/**
 * Created by aderifaldi on 2018-01-04.
 */

public class ContactListAdapter extends RecyclerView.Adapter<GenericViewHolder> {
    private List<Contact> listItem;
    private AdapterView.OnItemClickListener onItemClickListener;
    private ViewDataBinding binding;

    public ContactListAdapter() {
        listItem = new ArrayList<>();
    }

    public List<Contact> getData() {
        return listItem;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.contact_list_item, parent, false);
        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        Contact itemData = listItem.get(position);
        ContactListDataBinding dataBinding = new ContactListDataBinding(itemData);
        holder.bindModel(BR.contactListItem, dataBinding, position, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }
}
