package example.rx.com.rxexampledemo.http;

import java.util.List;

import example.rx.com.rxexampledemo.model.AuthToken;
import example.rx.com.rxexampledemo.model.Contributor;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by youku on 2016/5/27.
 */

public interface GithubApi {

    @GET("/contributor/list")
    Observable<List<Contributor>> contributors();

    @GET("/token")
    AuthToken refreshToken();

}
