package example.rx.com.rxexampledemo.databinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayMap;
import android.os.Bundle;
import android.view.View;

import example.rx.com.rxexampledemo.R;
import example.rx.com.rxexampledemo.model.User;
import example.rx.com.rxexampledemo.model.UserField;

/**
 * Created by Administrator on 2016/5/26 0026.
 */

public class UpdateUserActivity extends Activity {

    private example.rx.com.rxexampledemo.databinding.ActivityUpdateuserBinding binding;
    private User user;
    private UserField field = new UserField();
    private ObservableArrayMap<String,Object> collection = new ObservableArrayMap<String,Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_updateuser);

        user = new User("Wangjian","26");
        field.age.set("33");
        field.name.set("LiYong");

        collection.put("name","XueShuang");
        collection.put("age","24");

        binding.setUser(user);
        binding.setUserField(field);
        binding.setCollection(collection);
    }

    //如果(某个)字段发生变化.
    //1,通过User继承BaseObservable实现
    //2,通过ObservableField方式实现
    //3,通过Observable Collections的方式 如:ObservableArrayMap
    //4,当然可以通过binding.setUser(user) [相当于所有的View重新设置一遍]
    public void updateNameByPOJP(View view) {

        if ("Wangjian".equals(user.getName())) {
            user.setName("Chiclaim");
            user.setAge("110");
        } else {
            user.setName("Johnny");
            user.setAge("119");
        }
        //当然可以通过binding.setUser(user)
        binding.setUser(user);
    }

    public void updateNameByField(View view) {
        if ("LiYong".equals(field.name.get())) {
            field.name.set("Chiclaim");
            field.age.set("110");
        } else {
            field.name.set("Johnny");
            field.age.set("119");
        }
    }

    public void updateNameByCollection(View view) {
        if ("XueShuang".equals(collection.get("realName"))) {
            collection.put("name", "Chiclaim");
            collection.put("age", "110");
        } else {
            collection.put("name", "Johnny");
            collection.put("age", "119");
        }
    }
}
