package com.mfzn.deepuses.adapter.khgl;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.CustomDotIndexProvider;
import com.mfzn.deepuses.adapter.CustomLoadingUIProvider;
import com.mfzn.deepuses.adapter.GlideSimpleLoader;
import com.mfzn.deepuses.adapter.xiangmu.ShouliPhotoAdapter;
import com.mfzn.deepuses.model.khgl.FollowProModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.view.MyRecyclerView;
import com.wevey.selector.dialog.bean.DetailsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sun on 2018/6/12.
 */

public class FollowProAdapter extends RecyclerView.Adapter {

    private Context context;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private List<FollowProModel> datas;
    private OnSeeItemClickListener mOnSeeItemClickListener = null;

    private ImageWatcherHelper iwHelper;

    public FollowProAdapter(Context mContext, List<FollowProModel> datas) {
        this.context = mContext;
        this.datas = datas;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);

        iwHelper = ImageWatcherHelper.with((Activity) context, new GlideSimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
                .setIndexProvider(new CustomDotIndexProvider()) // 自定义index
                .setLoadingUIProvider(new CustomLoadingUIProvider()); // 骰子loading
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_follow_pro, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MoreViewHolder viewHolder = (MoreViewHolder) holder;

        FollowProModel bean = datas.get(position);

        String time = DateUtils.stampToDateTime(String.valueOf(bean.getAddTime()));
        viewHolder.tvFollTime.setText(time);
        viewHolder.tvFollType.setText(bean.getFollowStatus());
        viewHolder.tvFollName.setText("【" + bean.getSalesPersonName() + "】   " + bean.getCommunicationType());
        viewHolder.tvFollNote.setText(bean.getContent());

        List<String> imageUrls = bean.getImageUrls();
        if (imageUrls != null && !TextUtils.isEmpty(imageUrls.get(0))) {
            viewHolder.flRecycleview.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.flRecycleview.setLayoutManager(layoutManager);
            FollowImageAdapter recycleAdapter = new FollowImageAdapter(context, imageUrls);
            viewHolder.flRecycleview.setAdapter(recycleAdapter);

            List<Uri> dataList = new ArrayList<>();
            for (int i = 0 ; i < imageUrls.size() ; i++){
                dataList.add(Uri.parse("https://cdn.mfzn.com.cn/" + imageUrls.get(i)));
            }

            recycleAdapter.setOnSeeItemClickListener(new FollowImageAdapter.OnSeeItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    iwHelper.show(dataList, position);
                }
            });
        } else {
            viewHolder.flRecycleview.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_foll_time)
        TextView tvFollTime;
        @BindView(R.id.tv_foll_type)
        TextView tvFollType;
        @BindView(R.id.tv_foll_name)
        TextView tvFollName;
        @BindView(R.id.tv_foll_note)
        TextView tvFollNote;
        @BindView(R.id.fl_recycleview)
        MyRecyclerView flRecycleview;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnSeeItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnSeeItemClickListener(OnSeeItemClickListener listener) {
        this.mOnSeeItemClickListener = listener;
    }
}
