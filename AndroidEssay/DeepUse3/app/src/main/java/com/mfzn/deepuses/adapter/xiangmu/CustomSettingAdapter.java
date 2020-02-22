package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomSettingAdapter extends BaseAdapter {

    private Context mContext;
    private List<CustomListModel> models;

    private OnitemclickLisenter mOnitemclickLisenter;

    public CustomSettingAdapter(Context context, List<CustomListModel> models) {
        this.mContext = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
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

        CustomListModel customListModel = models.get(position);

        viewHolder.tvCusItemType.setText(customListModel.getTypeName());
        viewHolder.tvCusItemName.setText(customListModel.getName());
        viewHolder.tvCusItemPhone.setText(customListModel.getPhone());

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
        void onItemClick(View view, int position);
    }

    public void setmOnitemclickLisenter(OnitemclickLisenter mOnitemclickLisenter){
        this.mOnitemclickLisenter = mOnitemclickLisenter;
    }
}
