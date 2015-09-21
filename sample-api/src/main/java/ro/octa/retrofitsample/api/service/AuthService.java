package ro.octa.retrofitsample.api.service;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import ro.octa.retrofitsample.api.ApiConstants;
import ro.octa.retrofitsample.commons.model.User;

/**
 * @author Octa on 9/21/2015.
 */
public interface AuthService {

    String ENDPOINT_PREFIX = ApiConstants.API_VERSION + "/auth";

    @POST(ENDPOINT_PREFIX + "/login")
    void login(@Body User request, Callback<User> callback);

}
