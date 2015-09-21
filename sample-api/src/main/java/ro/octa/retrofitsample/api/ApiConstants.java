package ro.octa.retrofitsample.api;

/**
 * @author Octa
 */
public final class ApiConstants {

    private ApiConstants() {
    }

    public static final String PROD_URL = "https://api.sample.com/rest";
    public static final String TEST_URL = "http://qa.sample.com/rest";

    public static final String API_VERSION = "/v1";

    protected static boolean isProduction = true;

    public static String getSampleUrl() {
        return !isProduction ? TEST_URL : PROD_URL;
    }

}
