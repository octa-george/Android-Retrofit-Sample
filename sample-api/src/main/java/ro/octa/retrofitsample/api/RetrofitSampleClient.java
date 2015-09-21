package ro.octa.retrofitsample.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.Date;
import java.util.concurrent.ExecutorService;

import retrofit.Endpoint;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import ro.octa.retrofitsample.api.endpoint.ApiRequestInterceptor;
import ro.octa.retrofitsample.api.endpoint.RetrofitSampleEndpoint;
import ro.octa.retrofitsample.api.service.AuthService;
import ro.octa.retrofitsample.api.service.SampleService;
import ro.octa.retrofitsample.api.service.UserService;
import ro.octa.retrofitsample.commons.adapter.CustomDateTypeAdapter;

/**
 * @author Octa on 9/21/2015.
 */
public class RetrofitSampleClient {

    static RetrofitSampleClient singleton = null;
    private boolean isProduction;

    /**
     * Uses GSON for serialization and deserialization of entities
     */
    private GsonConverter converter;

    private UserService userService;
    private AuthService authService;
    private SampleService sampleService;

    private RetrofitSampleEndpoint endpoint;
    private ApiRequestInterceptor requestInterceptor;

    private RestAdapter.LogLevel logLevel;
    private String appVersion;

    public RetrofitSampleClient(String appVersion, Gson gson, boolean isProduction, RestAdapter.LogLevel loggingLevel) {

        this.appVersion = appVersion;

        ApiConstants.isProduction = isProduction;
        this.converter = new LenientGsonConverter(gson);
        this.isProduction = isProduction;
        this.logLevel = loggingLevel;

        initTheEndpoint(loggingLevel);
    }

    public void setEndpoint(String endpoint) {
        if (endpoint == null)
            initTheEndpoint(logLevel == null ? RestAdapter.LogLevel.NONE : logLevel);
        this.endpoint.setUrl(endpoint);
    }

    private void initTheEndpoint(RestAdapter.LogLevel loggingLevel) {
        endpoint = new RetrofitSampleEndpoint();
        endpoint.setUrl(ApiConstants.getSampleUrl());

        // Set up Retrofit.
        RestAdapter sampleRestAdapter = buildRestAdapter(endpoint, converter, loggingLevel);

        userService = sampleRestAdapter.create(UserService.class);
        authService = sampleRestAdapter.create(AuthService.class);
        sampleService = sampleRestAdapter.create(SampleService.class);
    }

    public boolean isProduction() {
        return isProduction;
    }

    /**
     * @param url       - server url
     * @param converter - GSON converter
     * @param logLevel  - rest log level
     * @return - new {@link retrofit.RestAdapter} instances.
     */
    private RestAdapter buildRestAdapter(Endpoint url, Converter converter, RestAdapter.LogLevel logLevel) {
        requestInterceptor = new ApiRequestInterceptor(appVersion);
        return new RestAdapter.Builder()
                .setRequestInterceptor(requestInterceptor)
                .setEndpoint(url)
                .setConverter(converter)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(logLevel)
                .build();
    }

    public RestAdapter getHostAdapter(String baseHost) {
        requestInterceptor = new ApiRequestInterceptor(appVersion);
        return new RestAdapter.Builder()
                .setRequestInterceptor(requestInterceptor)
                .setEndpoint(baseHost)
                .setConverter(new LenientGsonConverter(new GsonBuilder()
                        .registerTypeAdapter(Date.class, new CustomDateTypeAdapter())
                        .serializeNulls()
                        .create()))
                .setClient(new OkClient(new OkHttpClient()))
                .setLog(RestAdapter.Log.NONE)
                .build();
    }

    public RestAdapter getAdapter(ExecutorService executorService) {
        requestInterceptor = new ApiRequestInterceptor(appVersion);
        return new RestAdapter.Builder()
                .setRequestInterceptor(requestInterceptor)
                .setEndpoint(endpoint)
                .setConverter(converter)
                .setExecutors(executorService, executorService)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(logLevel)
                .build();
    }

    public ApiRequestInterceptor getRequestInterceptor() {
        return requestInterceptor;
    }

    public UserService getUserService() {
        return userService;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public SampleService getSampleService() {
        return sampleService;
    }

    /**
     * The global default {@link RetrofitSampleClient} instance.
     * <p/>
     * This instance is automatically initialized with defaults that are suitable to most
     * implementations. Will use production by default with no logging.
     */
    public static RetrofitSampleClient start() {
        if (singleton == null) {
            synchronized (RetrofitSampleClient.class) {
                if (singleton == null) {
                    singleton = new Builder().build();
                }
            }
        }
        return singleton;
    }

    /**
     * Fluent API for creating {@link RetrofitSampleClient} instances.
     */
    @SuppressWarnings("UnusedDeclaration") // Public API.
    public static class Builder {
        private Gson gson;

        private RestAdapter.LogLevel loggingLevel = RestAdapter.LogLevel.NONE;
        private boolean isProduction = true;
        private String appVersion = "1.0";

        /**
         * Specify the gson for marshaling and un-marshaling the objects.
         */
        public Builder converter(Gson gson) {
            if (gson == null) {
                throw new IllegalArgumentException("Gson must not be null.");
            }
            if (this.gson != null) {
                throw new IllegalStateException("Gson already set.");
            }
            this.gson = gson;
            return this;
        }

        /**
         * Specify the logging level.
         * <p/>
         * <b>WARNING:</b> Enabling this will result in excessive object allocation. This should be only
         * be used for debugging purposes. Do NOT pass {@code BuildConfig.DEBUG}.
         */
        public Builder setLoggingLevel(RestAdapter.LogLevel level) {
            this.loggingLevel = level;
            return this;
        }

        /**
         * Specify that the backend should be linked to the QA environment
         */
        public Builder useTestServer() {
            this.isProduction = false;
            return this;
        }

        /**
         * Specify the app version
         */
        public Builder appVersion(String version) {
            this.appVersion = version;
            return this;
        }

        /**
         * Create the {@link RetrofitSampleClient} instance.
         */
        public RetrofitSampleClient build() {
            if (gson == null) {
                gson = new GsonBuilder()
                        .registerTypeAdapter(Date.class, new CustomDateTypeAdapter())
                        .serializeNulls()
                        .create();
            }

            return new RetrofitSampleClient(appVersion, gson, isProduction, loggingLevel);
        }
    }

}
