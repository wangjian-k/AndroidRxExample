package example.rx.com.rxexampledemo.zoomeye;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.rx.com.rxexampledemo.R;
import example.rx.com.rxexampledemo.model.HostQueryResult;
import example.rx.com.rxexampledemo.retrofit.ZoomeyeService;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/5/28 0028.
 */

public class ZoomEyeSearch extends Activity {

    private static final String TAG = ZoomEyeSearch.class.getSimpleName();

    @BindView(R.id.spinner)
    Spinner typeSpinner;
    @BindView(R.id.search_content)
    AutoCompleteTextView mSearchContentView;
    @BindView(R.id.search_loading)
    View mLoadingView;
    @BindView(R.id.search_button)
    Button mSearchButton;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private SearchRecyclerAdapter recyclerAdapter;

    private ArrayList<String> searchTypeList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomeye_search);
        ButterKnife.bind(this);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayout.VERTICAL);//默认是LinearLayout.VERTICAL
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        searchTypeList.add("主机");
        searchTypeList.add("网站");

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, searchTypeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
    }

    @OnClick(R.id.search_button)
    void search() {
        // Check for a valid email address.
        if (TextUtils.isEmpty(mSearchContentView.getText().toString())) {
            mSearchContentView.setError(getString(R.string.error_field_required));
            return;
        }

        showLoading(true);

        ZoomeyeService.getZoomEyeApi().searchWithHost(mSearchContentView.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HostQueryResult>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"search completed!!!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"search with error : " + e);
                        showLoading(false);
                    }

                    @Override
                    public void onNext(HostQueryResult result) {
                        if(result != null && result.matches != null && result.matches.size() > 0) {
                            int size = result.matches.size();
                            for(int i=0;i<size;i++) {
                                HostQueryResult.result infoResult = result.matches.get(i);
                                Log.d(TAG,"ip : " + infoResult.ip);
                            }
                            mRecyclerView.removeAllViews();
                            recyclerAdapter = null;
                            recyclerAdapter = new SearchRecyclerAdapter(ZoomEyeSearch.this,result.matches);
                            mRecyclerView.setAdapter(recyclerAdapter);
                            showLoading(false);
                        }
                    }
                });
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showLoading(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoadingView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoadingView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoadingView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mLoadingView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
}
