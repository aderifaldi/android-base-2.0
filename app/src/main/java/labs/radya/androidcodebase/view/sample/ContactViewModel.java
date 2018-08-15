package labs.radya.androidcodebase.view.sample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.gson.JsonObject;

import labs.radya.androidcodebase.data.source.remote.ApiRequest;
import labs.radya.androidcodebase.data.source.remote.ApiResponse;
import labs.radya.androidcodebase.data.source.remote.ConnectionCallback;
import labs.radya.androidcodebase.data.source.remote.ConnectionManager;
import retrofit2.Call;

public class ContactViewModel extends ViewModel {

    private MediatorLiveData<ApiResponse> contactListResponse;

    public LiveData<ApiResponse> getContactListResponse() {
        return contactListResponse;
    }

    public void getContactList(ConnectionManager connectionManager, JsonObject jsonObject, int limit, int offset) {
        contactListResponse = new MediatorLiveData<>();

        Call<ContactList> call = new ApiRequest().call(true).GetContactList(jsonObject, limit, offset);
        connectionManager.callApi(call, new ConnectionCallback() {
            @Override
            public void onFinishRequest(LiveData r) {
                ApiResponse apiResponse = (ApiResponse) r.getValue();
                contactListResponse.setValue(apiResponse);
            }

        });

    }

}
