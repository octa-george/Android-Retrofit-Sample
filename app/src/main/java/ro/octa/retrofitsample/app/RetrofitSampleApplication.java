package ro.octa.retrofitsample.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.concurrent.ExecutorService;

import retrofit.RestAdapter;
import ro.octa.retrofitsample.api.RetrofitSampleClient;
import ro.octa.retrofitsample.api.endpoint.ApiRequestInterceptor;
import ro.octa.retrofitsample.api.service.AuthService;
import ro.octa.retrofitsample.api.service.SampleService;
import ro.octa.retrofitsample.api.service.UserService;
import ro.octa.retrofitsample.app.commons.AppStatistics;

/**
 * @author Octa on 9/21/2015.
 */
public class RetrofitSampleApplication extends Application {

    private static RetrofitSampleApplication sInstance;
    private RetrofitSampleClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = RetrofitSampleApplication.this;
        initVersionStatics();

        client = new RetrofitSampleClient.Builder().useTestServer().setLoggingLevel(RestAdapter.LogLevel.FULL)
                .appVersion(AppStatistics.getAppVersion()).build();
//        client = new RetrofitSampleClient.Builder().setLoggingLevel(RestAdapter.LogLevel.FULL).build();
//        client = RetrofitSampleClient.start();
    }

    public static synchronized RetrofitSampleApplication getInstance() {
        return sInstance;
    }

    public boolean isProduction() {
        return client.isProduction();
    }

    public ApiRequestInterceptor getApiRequestInterceptor() {
        return client.getRequestInterceptor();
    }

    public RestAdapter getHostAdapter(String url) {
        return client.getHostAdapter(url);
    }

    public RestAdapter getAdapter(ExecutorService executorService) {
        return client.getAdapter(executorService);
    }

    public void setEndpoint(String endpoint) {
        client.setEndpoint(endpoint);
    }

    public UserService getUserService() {
        return client.getUserService();
    }

    public AuthService getAuthService() {
        return client.getAuthService();
    }

    public SampleService getSampleService() {
        return client.getSampleService();
    }

    /**
     * Initialize the app version
     */
    private void initVersionStatics() {
        String appVersion = "";
        int appCode = 1;
        try {
            if (getPackageManager() != null) {
                PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                appVersion = pInfo.versionName;
                appCode = pInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            appVersion = "0";
        }
        AppStatistics.init(appVersion, appCode);
    }

}
