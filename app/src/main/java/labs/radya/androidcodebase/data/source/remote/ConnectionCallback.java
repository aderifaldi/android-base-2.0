package labs.radya.androidcodebase.data.source.remote;

import android.arch.lifecycle.LiveData;

/**
 * Created by aderifaldi on 08/08/2016.
 */
public interface ConnectionCallback<T>{

    void onFinishRequest(LiveData<T> r);

}
