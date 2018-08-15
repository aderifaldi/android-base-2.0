package labs.radya.androidcodebase.view.sample.detail;

import android.databinding.BaseObservable;

import labs.radya.androidcodebase.view.sample.Contact;

public class ContactDetailDataBinding extends BaseObservable {

    private Contact data;

    public ContactDetailDataBinding(Contact data) {
        this.data = data;
    }

    public String getContact_id() {
        return data.getContact_id();
    }

    public String getContact_name() {
        return data.getContact_name();
    }

    public String getContact_number() {
        return data.getContact_number();
    }

    public String getPosition() {
        return data.getPosition();
    }

    public String getEmail() {
        return data.getEmail();
    }
}
