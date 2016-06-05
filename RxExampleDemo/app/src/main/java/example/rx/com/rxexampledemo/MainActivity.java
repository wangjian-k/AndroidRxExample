package example.rx.com.rxexampledemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.rx.com.rxexampledemo.butterknife.ButterKnifeSampleActivity;
import example.rx.com.rxexampledemo.databinding.DataBindingActivity;
import example.rx.com.rxexampledemo.retrofit.RetrofitMainActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.butterknife_button)
    public Button button1;
    @BindView(R.id.databinding_button)
    public Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.butterknife_button)
    public void click() {
//        Toast.makeText(this,"button1 is onclick!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ButterKnifeSampleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.databinding_button)
    void button2Click() {
//        Toast.makeText(this, "button2 is onclicked!!!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, DataBindingActivity.class));
    }

    @OnClick(R.id.retrofit_button)
    void retrofitButtonClick() {
        startActivity(new Intent(this, RetrofitMainActivity.class));
    }
}
