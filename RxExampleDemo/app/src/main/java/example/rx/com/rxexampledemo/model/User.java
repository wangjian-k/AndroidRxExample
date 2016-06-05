package example.rx.com.rxexampledemo.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import example.rx.com.rxexampledemo.BR;

/**
 * Created by Administrator on 2016/5/24 0024.
 */

public class User extends BaseObservable {

    private String name;
    private String age;
    private String email;

    /**
     * 注意: 在BR里对应的常量为follow
     */
    private boolean isFollow;
    public User() {
    }

    public User(String username,String userage) {
        name = username;
        age = userage;
    }

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public String getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public boolean isFollow() {
        return isFollow;
    }

    public void setIsFollow(boolean isFollow) {
        this.isFollow = isFollow;
        notifyPropertyChanged(BR.follow);
    }

    @Bindable
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public String getEmail() {
        return email;
    }
}
