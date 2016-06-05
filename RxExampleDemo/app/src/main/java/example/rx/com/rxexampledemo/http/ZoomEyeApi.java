package example.rx.com.rxexampledemo.http;

import example.rx.com.rxexampledemo.model.HostQueryResult;
import example.rx.com.rxexampledemo.model.LoginInfo;
import example.rx.com.rxexampledemo.model.ZoomeyeToken;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/5/28 0028.
 */

public interface ZoomEyeApi {

    @GET("/host/search")
    Observable<HostQueryResult> searchWithHost(@Query("query") String content);

    @POST("/user/login")
    Observable<ZoomeyeToken> login(@Body LoginInfo loginInfo);

}
