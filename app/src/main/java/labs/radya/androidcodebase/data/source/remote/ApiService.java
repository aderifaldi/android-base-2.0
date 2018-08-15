package labs.radya.androidcodebase.data.source.remote;

import com.google.gson.JsonObject;

import labs.radya.androidcodebase.Constant;
import labs.radya.androidcodebase.view.sample.ContactList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by aderifaldi on 08/08/2016.
 */
public interface ApiService {

    //CONTACT
    @POST(Constant.Api.EndPoint.Contact.LIST)
    @Headers("Content-Type: application/json")
    Call<ContactList> GetContactList(@Body JsonObject jsonObject,
                                     @Query(Constant.Api.Params.LIMIT) int limit,
                                     @Query(Constant.Api.Params.OFFSET) int offset);
}
