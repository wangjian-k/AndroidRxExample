package example.rx.com.rxexampledemo.databinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.rx.com.rxexampledemo.R;

/**
 * Created by Administrator on 2016/5/26 0026.
 */

public class DataBindingActivity extends Activity {

    @BindView(R.id.simple_binding)
    Button mSimpleBindingButton;
    @BindView(R.id.update_user_button)
    Button mUpdateUserButton;


    @OnClick(R.id.update_user_button)
    void updateUser() {
        startActivity(new Intent(this,UpdateUserActivity.class));
    }

    @OnClick(R.id.simple_binding)
    void simpleBinding() {
        Intent intent = new Intent(this,DataBindingSampleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.convert_button)
    void convertActivity() {
        startActivity(new Intent(this,ConvertActivity.class));
    }

    @OnClick(R.id.custom_setter)
    void customSetter() {
        startActivity(new Intent(this,CustomSetterActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databinding_main);
        ButterKnife.bind(this);
    }
}
