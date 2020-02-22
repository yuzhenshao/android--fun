package com.mfzn.deepuses.adapter.khgl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.home.HomeShowModel;
import com.mfzn.deepuses.model.khgl.MyShareModel;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;


/**
 * Created by sun on 2018/6/12.
 */

public class ShareCusAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<MyShareModel.DataBean.CustomerInfoBean> others;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener = null;

    public ShareCusAdapter(Context mContext, List<MyShareModel.DataBean.CustomerInfoBean> others) {
        this.context = mContext;
        this.others = others;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_share_cus, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MoreViewHolder bbnViewHolder = (MoreViewHolder) holder;

        MyShareModel.DataBean.CustomerInfoBean model = others.get(position);

        bbnViewHolder.tvCusItemName.setText(model.getCustomerName());

        int customerLevelID = model.getCustomerLevelID();
        if(customerLevelID == 4) {
            bbnViewHolder.ivCusItemLevel.setImageResource(R.mipmap.cus_level1);
        }else if(customerLevelID == 5) {
            bbnViewHolder.ivCusItemLevel.setImageResource(R.mipmap.cus_level2);
        }else if(customerLevelID == 6) {
            bbnViewHolder.ivCusItemLevel.setImageResource(R.mipmap.cus_level3);
        }else if(customerLevelID == 7) {
            bbnViewHolder.ivCusItemLevel.setImageResource(R.mipmap.cus_level4);
        }else if(customerLevelID == 8) {
            bbnViewHolder.ivCusItemLevel.setImageResource(R.mipmap.cus_level5);
        }else {
            bbnViewHolder.ivCusItemLevel.setImageResource(0);
        }

        List<MyShareModel.DataBean.CustomerInfoBean.CustomerProsBean> pros = model.getCustomerPros();
        if(pros != null && pros.size() != 0) {
            String sss = null;
            for(int i = 0; i < pros.size(); i++) {
                if(TextUtils.isEmpty(sss)) {
                    sss = pros.get(i).getPro_name();
                }else {
                    sss = sss + "，" + pros.get(i).getPro_name();
                }
            }
            bbnViewHolder.tvCusItemPro.setText(sss);
        }else {
            bbnViewHolder.tvCusItemPro.setText("暂无分配项目");
        }
    }

    @Override
    public int getItemCount() {
        return others.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_cus_item_name)
        TextView tvCusItemName;
        @BindView(R.id.iv_cus_item_level)
        ImageView ivCusItemLevel;
        @BindView(R.id.tv_cus_item_pro)
        TextView tvCusItemPro;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
