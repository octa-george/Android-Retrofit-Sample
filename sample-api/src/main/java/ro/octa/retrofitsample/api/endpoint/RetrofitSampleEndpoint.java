package ro.octa.retrofitsample.api.endpoint;

import retrofit.Endpoint;

/**
 * @author Octa
 */
public class RetrofitSampleEndpoint implements Endpoint {

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getName() {
        return "retrofitSample";
    }
}
