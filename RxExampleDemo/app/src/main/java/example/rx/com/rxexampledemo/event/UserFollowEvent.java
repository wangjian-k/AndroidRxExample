package example.rx.com.rxexampledemo.event;

import android.view.View;

/**
 * Created by chiclaim on 2016/02/21
 */
public interface UserFollowEvent {
    void follow(View view);
    void unFollow(View view);
}
