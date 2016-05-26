package example.rx.com.rxexampledemo.model;

import android.databinding.ObservableField;

/**
 * Created by Administrator on 2016/5/26 0026.
 */

public class UserField {

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> age = new ObservableField<>();
}
