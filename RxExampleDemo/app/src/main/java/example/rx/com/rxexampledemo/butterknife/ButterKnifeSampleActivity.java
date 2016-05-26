package example.rx.com.rxexampledemo.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnLongClick;
import example.rx.com.rxexampledemo.R;

import static example.rx.com.rxexampledemo.R.id.footer;
import static example.rx.com.rxexampledemo.R.id.hello;

/**
 * Created by Administrator on 2016/5/24 0024.
 */

public class ButterKnifeSampleActivity extends Activity {

    private static final ButterKnife.Action<View> ALPHA_FADE = new ButterKnife.Action<View>() {

        @Override
        public void apply(@NonNull View view, int index) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
            alphaAnimation.setFillBefore(true);
            alphaAnimation.setDuration(500);
            alphaAnimation.setStartOffset(100 * index);
            view.startAnimation(alphaAnimation);
        }
    };

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subTitle;
    @BindView(hello)
    Button helloButton;
    @BindView(R.id.list_of_things)
    ListView listView;
    @BindView(footer)
    TextView footerTextView;

    @BindViews({R.id.title,R.id.subtitle, footer})
    List<View> headerViews;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        ButterKnife.bind(this);

        title.setText("Butter Knife");
        subTitle.setText("Field and method binding for Android views.");
        footerTextView.setText("by Jake Wharton");
        helloButton.setText("Say Hello");

        simpleAdapter = new SimpleAdapter(this);
        listView.setAdapter(simpleAdapter);
    }

    @OnClick(hello)
    void sayHello() {
        Toast.makeText(this,"Hello,views!",Toast.LENGTH_SHORT).show();
        ButterKnife.apply(headerViews,ALPHA_FADE);
    }

    @OnLongClick(hello)
    boolean sayGetOffMe() {
        Toast.makeText(this,"get off me!!!",Toast.LENGTH_SHORT).show();
        return true;
    }

    @OnItemClick(R.id.list_of_things)
    void onItemClick(int position) {
        Toast.makeText(this,"click list position : " + position,Toast.LENGTH_SHORT).show();
    }

}
