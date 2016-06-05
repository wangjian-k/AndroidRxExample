package example.rx.com.rxexampledemo.retrofit;

import android.text.TextUtils;

import java.io.IOException;

import example.rx.com.rxexampledemo.MyApplication;
import example.rx.com.rxexampledemo.http.ZoomEyeApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/5/28 0028.
 */

public class ZoomeyeService {

    private static final String BASEURL = "http://api.zoomeye.org/";
    public static final String AUTH_JWT = "JWT ";

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASEURL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

    public static ZoomEyeApi getZoomEyeApi() {
        final String token = MyApplication.getZoomeyeToken();
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder newRequestBuilder = request.newBuilder();
                    if(TextUtils.isEmpty(token)) {
                        newRequestBuilder.addHeader("Authorization", AUTH_JWT + token);
                    }

                    newRequestBuilder.addHeader("Accept", "application/json")
                            .addHeader("Content-Type", "application/json; charset=UTF-8");
                    Request newRequest = newRequestBuilder.build();
                    return chain.proceed(newRequest);
                }
            }).build();
            builder.client(client);
        return builder.build().create(ZoomEyeApi.class);
    }
}
