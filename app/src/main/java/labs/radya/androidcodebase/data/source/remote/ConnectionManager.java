package labs.radya.androidcodebase.data.source.remote;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;

import labs.radya.androidcodebase.Constant;
import labs.radya.androidcodebase.MyApplication;
import labs.radya.androidcodebase.data.source.cache.CacheManager;
import labs.radya.androidcodebase.util.ToastUtils;
import labs.radya.androidcodebase.view.sample.ContactList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectionManager<T> {
    private final String TAG = "CONNECTION_MANAGER";

    private MutableLiveData<ApiResponse> liveData;
    private ApiResponse<T> apiResponse;

    private JsonObject jsonCache;
    private String alertMessage;
    private boolean useCache;
    private boolean isError;
    private int responseCode;
    private int cacheType;
    private int alertCode;

    private CacheManager cacheManager;

    private BaseModel baseModel;

    public ConnectionManager() {
    }

    public void callApi(final Call<T> call, final ConnectionCallback callback) {

        liveData = new MutableLiveData<>();
        apiResponse = new ApiResponse<>();

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {

                responseCode = response.code();

                if (responseCode == 500) {
                    alertMessage = "Internal server error";
                    showToast(alertMessage);
                    apiResponse.setData(null);
                } else if (responseCode == 404) {
                    alertMessage = "Service not found";
                    showToast(alertMessage);
                    apiResponse.setData(null);
                } else {
                    baseModel = (BaseModel) response.body();

                    alertMessage = baseModel.getAlert().getMessage();
                    alertCode = baseModel.getAlert().getCode();
                    isError = baseModel.isError();

                    if (response.isSuccessful()) {
                        try {
                            if (!isError) {
                                if (alertCode == Constant.Api.Code.SUCCESS) {
                                    apiResponse.setData(response.body());
                                    if (useCache) {
                                        storeCache(response);
                                    }
                                } else {
                                    showToast(alertMessage);
                                }
                            } else {
                                if (alertCode == Constant.Api.Code.INVALID_TOKEN) {
                                    showToast(alertMessage);
                                } else {
                                    showToast(alertMessage);
                                }
                                apiResponse.setData(null);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();

                            showToast(e.getMessage());
                            apiResponse.setData(null);
                        }

                    } else {

                        if (response.errorBody() != null) {
                            try {
                                if (!"".equals(response.errorBody().string())) {
                                    alertMessage = response.errorBody().string();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        showToast(alertMessage);
                        apiResponse.setData(null);
                    }
                }

                liveData.setValue(apiResponse);
                callback.onFinishRequest(liveData);

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

                if (t instanceof IOException) {
                    BaseModel noInternet = getNoInternetResponse();
                    apiResponse.setData((T) noInternet);
                } else {
                    alertMessage = t.getMessage();
                    showToast(alertMessage);

                    apiResponse.setData(null);
                }

                if (useCache) {
                    checkCache();
                }

                liveData.setValue(apiResponse);
                callback.onFinishRequest(liveData);

            }
        });
    }

    private BaseModel getNoInternetResponse() {
        BaseModel.Alerts alerts = new BaseModel.Alerts(Constant.Api.Code.NO_INTERNET, Constant.Data.NO_INTERNET);
        BaseModel baseModel = new BaseModel(true, alerts);
        return baseModel;
    }

    private void showToast(String alertMessage) {
        ToastUtils.showToast(alertMessage);
    }

    private void storeCache(Response<T> response) {
        cacheManager = new CacheManager();
        cacheManager.storeCache(MyApplication.getInstance().getLocalDB(), cacheType, new Gson().toJson(response.body()));
    }

    private void checkCache() {
        cacheManager = new CacheManager();
        jsonCache = cacheManager.loadCache(MyApplication.getInstance().getLocalDB(), cacheType);
        if (cacheType == Constant.Cache.CONTACT) {
            ContactList cache = new GsonBuilder().create().fromJson(jsonCache, ContactList.class);
            apiResponse.setData((T) cache);
        } else {
            apiResponse.setData(null);
        }
    }

    public void needCache(int type) {
        useCache = true;
        cacheType = type;
    }

}
