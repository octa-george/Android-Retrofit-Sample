package ro.octa.retrofitsample.api.service;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.PartMap;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import retrofit.mime.TypedFile;
import ro.octa.retrofitsample.api.ApiConstants;
import ro.octa.retrofitsample.commons.model.User;

/**
 * @author Octa
 */
public interface UserService {

    String ENDPOINT_PREFIX = ApiConstants.API_VERSION + "/users";

    @GET(ENDPOINT_PREFIX)
    void getUserDetails(@Query("userHash") String userHash, Callback<User> callback);


    @GET(ENDPOINT_PREFIX)
    void getUser(@QueryMap Map<String, String> filters);


    @DELETE(ENDPOINT_PREFIX + "/{userId}")
    void deleteUser(@Path("userId") long userId, @Header("X-User-Hash") String userHash);


    @Multipart
    @POST("/")
    void uploadImage(@PartMap LinkedHashMap<String, String> params, @Part("File") TypedFile file);

}
