package com.mfzn.deepuses.adapter.khgl;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchCustomerAdapter extends BaseAdapter {

    private Context mContext;
    private List<WholeCustomerModel.DataBean> data;

    private OnPhoneItemClickListener mOnPhoneItemClickListener = null;

    public SearchCustomerAdapter(Context context, List<WholeCustomerModel.DataBean> data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_search_customer, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WholeCustomerModel.DataBean model = data.get(position);

        holder.tvCusItemName.setText(model.getU_name());

        int customerLevelID = model.getCustomerLevelID();
        if(customerLevelID == 4) {
            holder.ivCusItemLevel.setImageResource(R.mipmap.cus_level1);
        }else if(customerLevelID == 5) {
            holder.ivCusItemLevel.setImageResource(R.mipmap.cus_level2);
        }else if(customerLevelID == 6) {
            holder.ivCusItemLevel.setImageResource(R.mipmap.cus_level3);
        }else if(customerLevelID == 7) {
            holder.ivCusItemLevel.setImageResource(R.mipmap.cus_level4);
        }else if(customerLevelID == 8) {
            holder.ivCusItemLevel.setImageResource(R.mipmap.cus_level5);
        }else {
            holder.ivCusItemLevel.setImageResource(0);
        }

        int hasSalesperson = model.getHasSalesperson();//是否待分配 1是0否
        if(hasSalesperson == 0) {
            holder.ivCusItemType.setVisibility(View.VISIBLE);
        }else if(hasSalesperson == 1) {
            holder.ivCusItemType.setVisibility(View.GONE);
        }

        List<WholeCustomerModel.DataBean.ProsBean> pros = model.getPros();
        if(pros != null && pros.size() != 0) {
            String sss = null;
            for(int i = 0; i < pros.size(); i++) {
                if(TextUtils.isEmpty(sss)) {
                    sss = pros.get(i).getPro_name();
                }else {
                    sss = sss + "，" + pros.get(i).getPro_name();
                }
            }
            holder.tvCusItemPro.setText(sss);
        }else {
            holder.tvCusItemPro.setText("暂无分配项目");
        }

        holder.ivCusItemPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnPhoneItemClickListener != null) {
                    mOnPhoneItemClickListener.onItemClick(v, model.getCustomerPhone());
                }
            }
        });

        return convertView;
    }

    public interface OnPhoneItemClickListener {
        void onItemClick(View view, String phone);
    }

    public void setOnPhoneItemClickListener(OnPhoneItemClickListener listener) {
        this.mOnPhoneItemClickListener = listener;
    }

    class ViewHolder {
        @BindView(R.id.tv_cus_item_name)
        TextView tvCusItemName;
        @BindView(R.id.iv_cus_item_level)
        ImageView ivCusItemLevel;
        @BindView(R.id.iv_cus_item_type)
        ImageView ivCusItemType;
        @BindView(R.id.tv_cus_item_pro)
        TextView tvCusItemPro;
        @BindView(R.id.iv_cus_item_phone)
        ImageView ivCusItemPhone;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
