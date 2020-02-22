package com.mfzn.deepuses.adapter.khgl;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.home.HomeCzzxAdapter;
import com.mfzn.deepuses.model.khgl.MyShareModel;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.view.MyRecyclerView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

public class MyShareAdapter extends RecyclerAdapter<MyShareModel.DataBean, MyShareAdapter.MsgBusinessHolder> {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private OnPhoneItemClickListener mOnPhoneItemClickListener = null;
    private OnDelItemClickListener mOnDelItemClickListener = null;

    private Context context;

    public MyShareAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public MsgBusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_share, parent, false);
        return new MsgBusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgBusinessHolder holder, int position) {
        holder.itemView.setTag(position);

        int i1 = position + 1;
        holder.tvShareCompany.setText("共享公司" + i1);

        MyShareModel.DataBean model = data.get(position);

        holder.tvShareName.setText(model.getCompanyName());

        int customerLevelID = model.getCompanyLevel();
        if (customerLevelID == 1) {
            holder.ivShareVip.setImageResource(R.mipmap.br_vip1);
        } else if (customerLevelID == 2) {
            holder.ivShareVip.setImageResource(R.mipmap.br_vip2);
        } else if (customerLevelID == 3) {
            holder.ivShareVip.setImageResource(R.mipmap.br_vip3);
        } else if (customerLevelID == 4) {
            holder.ivShareVip.setImageResource(R.mipmap.br_vip4);
        } else if (customerLevelID == 5) {
            holder.ivShareVip.setImageResource(R.mipmap.br_vip5);
        } else if (customerLevelID == 6) {
            holder.ivShareVip.setImageResource(R.mipmap.br_vip6);
        } else {
            holder.ivShareVip.setImageResource(0);
        }

        if(model.getShowType()) {
            List<MyShareModel.DataBean.CustomerInfoBean> customerInfo = model.getCustomerInfo();
            if(customerInfo != null && customerInfo.size() != 0) {
                holder.shareRecyleview.setVisibility(View.VISIBLE);
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                holder.shareRecyleview.setLayoutManager(layoutManager);
                ShareCusAdapter czzxAdapter = new ShareCusAdapter(context,customerInfo);
                holder.shareRecyleview.setAdapter(czzxAdapter);
            }else {
                holder.shareRecyleview.setVisibility(View.GONE);
            }
        }else {
            holder.shareRecyleview.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });

        holder.tvShareDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDelItemClickListener != null) {
                    mOnDelItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    public class MsgBusinessHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_share_company)
        TextView tvShareCompany;
        @BindView(R.id.tv_share_del)
        TextView tvShareDel;
        @BindView(R.id.tv_share_name)
        TextView tvShareName;
        @BindView(R.id.iv_share_vip)
        ImageView ivShareVip;
        @BindView(R.id.share_recyleview)
        MyRecyclerView shareRecyleview;

        public MsgBusinessHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnPhoneItemClickListener {
        void onItemClick(View view, String phone);
    }

    public void setOnPhoneItemClickListener(OnPhoneItemClickListener listener) {
        this.mOnPhoneItemClickListener = listener;
    }

    public interface OnDelItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnDelItemClickListener(OnDelItemClickListener listener) {
        this.mOnDelItemClickListener = listener;
    }
}
