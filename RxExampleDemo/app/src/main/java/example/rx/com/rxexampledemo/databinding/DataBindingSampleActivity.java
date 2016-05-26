package example.rx.com.rxexampledemo.databinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import example.rx.com.rxexampledemo.model.User;
import example.rx.com.rxexampledemo.R;

/**
 * Created by Administrator on 2016/5/24 0024.
 */

public class DataBindingSampleActivity extends Activity {

    private example.rx.com.rxexampledemo.databinding.SampleDatabinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_sample);
        user = new User("wangjian","27");
        binding.setUser(user);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setAge("20");
                binding.setUser(user);
            }
        });
    }
}
