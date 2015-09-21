package ro.octa.retrofitsample.app.commons;

import ro.octa.retrofitsample.app.R;
import ro.octa.retrofitsample.app.RetrofitSampleApplication;

/**
 * @author Octa on 9/21/2015.
 */
public class AppStatistics {

    private static String appVersion = "";
    private static int appCode = 0;

    public static void init(String appV, int appC) {
        appVersion = appV;
        appCode = appC;
    }

    public static String getAppVersion() {
        return appVersion;
    }

    public static int getAppCode() {
        return appCode;
    }

    public static String getActorId() {
        return String.format(RetrofitSampleApplication.getInstance().getResources().getString(R.string.android_app_v), getAppVersion());
    }
}
