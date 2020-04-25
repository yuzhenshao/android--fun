package com.mfzn.deepuses.adapter.project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.utils.DateUtils;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

public class ProjectManageAdapter extends RecyclerAdapter<XiangmuModel.DataBean, ProjectManageAdapter.MsgBusinessHolder> {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public ProjectManageAdapter(Context context) {
        super(context);
    }

    @Override
    public MsgBusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_project_manage, parent, false);
        return new MsgBusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgBusinessHolder holder, int position) {
        holder.itemView.setTag(position);

        XiangmuModel.DataBean bean = data.get(position);

        holder.tvXmItemTitle.getPaint().setFakeBoldText(true);
        holder.tvXmItemTitle.setText(bean.getProName());

        StringBuffer customerNames=new StringBuffer();
        if(bean!=null&&bean.getCustomersInfo()!=null&&bean.getCustomersInfo().size()>0){
            for(XiangmuModel.DataBean.CustomersBean customers:bean.getCustomersInfo()){
                customerNames.append(customers.getCustomerName()).append(" ");
            }
        }

        holder.tvXmItemName.setText(customerNames.toString());
        String start_time = bean.getQualityBegin();
        String end_time = bean.getQualityEnd();
        if(!start_time.equals("0") && !end_time.equals("0")) {
            if(!TextUtils.isEmpty(start_time) && !TextUtils.isEmpty(end_time)) {
                holder.tvXmItemTime.setText(DateUtils.stampDate(start_time) + "~" + DateUtils.stampDate(end_time));
            }else {
                holder.tvXmItemTime.setText("暂无");
            }
        }else {
            holder.tvXmItemTime.setText("暂无");
        }

        int customLevel = bean.getCustomLevel();
        if (customLevel == 4) {
            holder.ivXmItemType.setImageResource(R.mipmap.xm_yixing);
        } else if (customLevel == 5) {
            holder.ivXmItemType.setImageResource(R.mipmap.xm_erxing);
        } else if (customLevel == 6) {
            holder.ivXmItemType.setImageResource(R.mipmap.xm_sanxing);
        } else if (customLevel == 7) {
            holder.ivXmItemType.setImageResource(R.mipmap.xm_sixing);
        } else if (customLevel == 8) {
            holder.ivXmItemType.setImageResource(R.mipmap.xm_wuxing);
        }else {
            holder.ivXmItemType.setImageResource(0);
        }

        int afterSaleStatus = bean.getAfterSaleStatus();
        int afterSaleInDate = bean.getAfterSaleInDate();//0已过期 1可使用
        if(afterSaleInDate == 0) {
            if(afterSaleStatus == 1) {
                holder.ivXmItemQx.setImageResource(R.mipmap.xm_goumai);
            }else {
                holder.ivXmItemQx.setImageResource(R.mipmap.aaa_bu);
            }
        }else {
            if(afterSaleStatus == 1) {
                holder.ivXmItemQx.setImageResource(R.mipmap.xm_goumai);
            }else if(afterSaleStatus == 2) {
                holder.ivXmItemQx.setImageResource(R.mipmap.xm_shiyong);
            }else {
                holder.ivXmItemQx.setImageResource(R.mipmap.xm_weikai);
            }
        }
//
//        if (afterSaleStatus == 0) {//0未开通 1购买开通  2试用开通
//            holder.ivXmItemQx.setImageResource(R.mipmap.xm_weikai);
//        } else if (afterSaleStatus == 1) {
//            holder.ivXmItemQx.setImageResource(R.mipmap.xm_goumai);
//        } else if (afterSaleStatus == 2) {
//            holder.ivXmItemQx.setImageResource(R.mipmap.xm_shiyong);
//        }else {
//            holder.ivXmItemQx.setImageResource(0);
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemClickListener.onItemClick(v, v.getTag().toString(), position);
                }
            }
        });
    }

    public class MsgBusinessHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_xm_item_title)
        TextView tvXmItemTitle;
        @BindView(R.id.tv_xm_item_name)
        TextView tvXmItemName;
        @BindView(R.id.iv_xm_item_type)
        ImageView ivXmItemType;
        @BindView(R.id.tv_xm_item_time)
        TextView tvXmItemTime;
        @BindView(R.id.iv_xm_item_qx)
        ImageView ivXmItemQx;

        public MsgBusinessHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
