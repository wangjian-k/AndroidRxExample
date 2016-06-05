package example.rx.com.rxexampledemo;

import android.app.Application;

/**
 * Created by Administrator on 2016/5/28 0028.
 */

public class MyApplication extends Application {

    private static String zoomeyeToken;

    public static void setZoomeyeToken(String token) {
        zoomeyeToken = token;
    }

    public static String getZoomeyeToken() {
        return zoomeyeToken;
    }
}
