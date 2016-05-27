package example.rx.com.rxexampledemo.retrofit;

import java.io.IOException;

import example.rx.com.rxexampledemo.http.GithubApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.String.format;

/**
 * Created by youku on 2016/5/27.
 */

public class GithubApiService {

    public static final String TAG = GithubApiService.class.getSimpleName();
    public static final String BaseUrl = "https://api.github.com";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public GithubApi createGithubApi(final String token) {
        if(token != null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            Request newRequest = request.newBuilder()
                                    .addHeader("Authorization",format("token %s", token)).build();
                            return chain.proceed(newRequest);
                        }
                    }).build();
        }
        return builder.build().create(GithubApi.class);
    }
}
