package example.rx.com.rxexampledemo.zoomeye;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.rx.com.rxexampledemo.R;
import example.rx.com.rxexampledemo.model.HostQueryResult;

/**
 * Created by Administrator on 2016/5/28 0028.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter {

    private static final String TAG = SearchRecyclerAdapter.class.getSimpleName();
    protected Context mContext;
    protected ArrayList<HostQueryResult.result> mData;

    public SearchRecyclerAdapter(Context context,ArrayList<HostQueryResult.result> data){
        this.mData = data;
        this.mContext = context;
        Log.d(TAG,"size : " + mData.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.zoomeye_search_item_layout,parent,false);
        RecyclerView.ViewHolder holder = new SearchViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchViewHolder searchViewHolder = (SearchViewHolder)holder;
        HostQueryResult.result info = mData.get(position);
        searchViewHolder.ip.setText(info.ip);
        if(info.portinfo != null) {
            searchViewHolder.banner.setText(info.portinfo.banner);
            searchViewHolder.port.setText(info.portinfo.port + "");
            searchViewHolder.product.setText(info.portinfo.product);
        }

        if(info.geoinfo != null && info.geoinfo.country != null && info.geoinfo.country.names != null) {
            searchViewHolder.country.setText(info.geoinfo.country.names.en);
        }
        searchViewHolder.timestamp.setText(info.timestamp);

    }

    @Override
    public int getItemCount() {
        if(mData != null) {
            return mData.size();
        }
        return 0;
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ip)
        public TextView ip;
        @BindView(R.id.country)
        public TextView country;
        @BindView(R.id.port)
        public TextView port;  //地区
        @BindView(R.id.product)
        public TextView product;  //星座
        @BindView(R.id.timestamp)
        public TextView timestamp;  //职业
        @BindView(R.id.banner)
        public TextView banner;

        public SearchViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
