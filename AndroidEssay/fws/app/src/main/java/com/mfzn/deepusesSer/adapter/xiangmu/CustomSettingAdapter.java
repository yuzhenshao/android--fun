package com.mfzn.deepusesSer.adapter.xiangmu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomSettingAdapter extends BaseAdapter {

    private Context mContext;

    private OnitemclickLisenter mOnitemclickLisenter;

    public CustomSettingAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_custom_setting, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        ZuzhiJiagouModel.SonsBeanX.StaffBeanX staffBeanX = list.get(position);
//
//        Glide.with(mContext).load(ApiHelper.BASE_URL + staffBeanX.getU_head()).into(viewHolder.ivPerItemIcon);
//        viewHolder.ivPerItemName.setText(staffBeanX.getU_name());

        viewHolder.llCusItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnitemclickLisenter.onItemClick(v,position);
            }
        });

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.ll_cus_item_delete)
        LinearLayout llCusItemDelete;
        @BindView(R.id.tv_cus_item_type)
        TextView tvCusItemType;
        @BindView(R.id.tv_cus_item_name)
        TextView tvCusItemName;
        @BindView(R.id.tv_cus_item_phone)
        TextView tvCusItemPhone;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnitemclickLisenter{
        void onItemClick(View view,int position);
    }

    public void setmOnitemclickLisenter(OnitemclickLisenter mOnitemclickLisenter){
        this.mOnitemclickLisenter = mOnitemclickLisenter;
    }
}
