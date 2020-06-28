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
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

public class SelectCustomerAdapter extends RecyclerAdapter<WholeCustomerModel.DataBean, SelectCustomerAdapter.MsgBusinessHolder> {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public SelectCustomerAdapter(Context context) {
        super(context);
    }

    @Override
    public MsgBusinessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_select_customer, parent, false);
        return new MsgBusinessHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgBusinessHolder holder, int position) {
        holder.itemView.setTag(position);

        WholeCustomerModel.DataBean model = data.get(position);

        holder.tvCusItemName.setText(model.getU_name());
//        holder.tv_cus_item_phone.setText(model.getCustomerPhone());

        int customerLevelID = model.getCustomerLevelID();
        if (customerLevelID == 1) {
            holder.ivCusItemLevel.setImageResource(R.mipmap.cus_level1);
        } else if (customerLevelID == 2) {
            holder.ivCusItemLevel.setImageResource(R.mipmap.cus_level2);
        } else if (customerLevelID == 3) {
            holder.ivCusItemLevel.setImageResource(R.mipmap.cus_level3);
        } else if (customerLevelID == 4) {
            holder.ivCusItemLevel.setImageResource(R.mipmap.cus_level4);
        } else if (customerLevelID == 5) {
            holder.ivCusItemLevel.setImageResource(R.mipmap.cus_level5);
        } else {
            holder.ivCusItemLevel.setImageResource(0);
        }

//        int hasSalesperson = model.getHasSalesperson();//是否待分配 1是0否
//        if(hasSalesperson == 0) {
//            holder.tvCusItemPro.setText("暂无分配项目");
//        }else if(hasSalesperson == 1) {
//
//        }
        List<WholeCustomerModel.DataBean.ProsBean> pros = model.getPros();
        if (pros != null && pros.size() != 0) {
            String sss = null;
            for (int i = 0; i < pros.size(); i++) {
                if (TextUtils.isEmpty(sss)) {
                    sss = pros.get(i).getPro_name();
                } else {
                    sss = sss + "，" + pros.get(i).getPro_name();
                }
            }
            holder.tvCusItemPro.setText(sss);
        } else {
            holder.tvCusItemPro.setText("暂无分配项目");
        }

        if (model.getSelectType()) {
            holder.iv_cus_sel.setImageResource(R.mipmap.regi_xuanzhong);
        } else {
            holder.iv_cus_sel.setImageResource(R.mipmap.regi_weixuan);
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
    }

    public class MsgBusinessHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_cus_item_name)
        TextView tvCusItemName;
        @BindView(R.id.iv_cus_item_level)
        ImageView ivCusItemLevel;
        @BindView(R.id.tv_cus_item_pro)
        TextView tvCusItemPro;
        @BindView(R.id.iv_cus_sel)
        ImageView iv_cus_sel;

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
}
