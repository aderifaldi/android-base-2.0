package labs.radya.androidcodebase.view.master.nointernet;

import android.databinding.BaseObservable;

import labs.radya.androidcodebase.data.source.remote.BaseModel;

public class NoInternetDataBinding extends BaseObservable {

    private BaseModel data;

    public NoInternetDataBinding(BaseModel data) {
        this.data = data;
    }

    public String getMessage(){
        return data.getAlert().getMessage();
    }

}
