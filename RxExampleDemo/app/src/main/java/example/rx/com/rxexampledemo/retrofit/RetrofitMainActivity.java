package example.rx.com.rxexampledemo.retrofit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import example.rx.com.rxexampledemo.R;
import example.rx.com.rxexampledemo.zoomeye.ZoomEyeLogin;

/**
 * Created by Administrator on 2016/5/28 0028.
 */

public class RetrofitMainActivity extends Activity {

    @OnClick(R.id.githubButton)
    void onClickGithubButton() {
        startActivity(new Intent(this,GithubActivity.class));
    }

    @OnClick(R.id.zoomeyeButton)
    void onClickZoomEyeButton() {
        startActivity(new Intent(this, ZoomEyeLogin.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_main);
        ButterKnife.bind(this);
    }
}
