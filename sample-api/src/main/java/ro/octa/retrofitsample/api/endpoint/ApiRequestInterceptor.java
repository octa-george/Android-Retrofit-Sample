package ro.octa.retrofitsample.api.endpoint;

import android.text.TextUtils;

import java.util.Date;

import retrofit.RequestInterceptor;

/**
 * Headers can be added to a request using this class
 *
 * @author Octa on 9/21/2015.
 */
public class ApiRequestInterceptor implements RequestInterceptor {

    private String authToken;
    private String appVersion;
    private String appLanguage = "en-US";

    public ApiRequestInterceptor(String appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    public void intercept(RequestFacade requestFacade) {
        // set the User-Agent to each request
        requestFacade.addHeader("User-Agent", System.getProperty("http.agent") + " Retrofit_Sample_" + appVersion);

        if (!TextUtils.isEmpty(authToken)) {
            requestFacade.addHeader("X-User-Hash", authToken);
        }

        String timestamp = String.valueOf(new Date().getTime());
        requestFacade.addHeader("timestamp", timestamp);

        requestFacade.addHeader("Accept", "application/json");
        requestFacade.addHeader("Accept-Language", appLanguage);
    }

    public void setUserHash(String authToken) {
        this.authToken = authToken;
    }

    public void setAppLanguage(String appLanguage) {
        this.appLanguage = appLanguage;
    }
}
