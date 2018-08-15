package labs.radya.androidcodebase.view.sample.list;

import android.databinding.BaseObservable;

import labs.radya.androidcodebase.view.sample.Contact;

public class ContactListDataBinding extends BaseObservable {

    private Contact data;

    public ContactListDataBinding(Contact data) {
        this.data = data;
    }

    public String getContact_name() {
        return data.getContact_name();
    }

    public String getContact_number() {
        return data.getContact_number();
    }

}
