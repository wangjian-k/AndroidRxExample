package example.rx.com.rxexampledemo.databinding;

import android.app.Activity;
import android.databinding.BindingConversion;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import example.rx.com.rxexampledemo.R;
import example.rx.com.rxexampledemo.event.UserFollowEvent;
import example.rx.com.rxexampledemo.model.User;

/**
 * Created by Administrator on 2016/5/27 0027.
 */

public class ConvertActivity extends Activity implements UserFollowEvent {

    private example.rx.com.rxexampledemo.databinding.ActivityConverterBinding binding;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_converter);
        user = new User();
        binding.setUser(user);
        binding.setUserFollowEvent(this);
    }

    @Override
    public void follow(View view) {
        user.setIsFollow(true);
    }

    @Override
    public void unFollow(View view) {
        user.setIsFollow(false);
    }

    //Note:最新版本的 dataBinding插件 在设置background 会自动把color转成ColorDrawable
    //所以不需要以下转换方法,如果创建了该方法,系统则会调用.
    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color) {
        Log.d("BindingConversion", "convertColorToDrawable:" + color);
        return new ColorDrawable(color);
    }
}
