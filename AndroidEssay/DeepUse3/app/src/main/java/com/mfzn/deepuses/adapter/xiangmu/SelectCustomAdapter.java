package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectCustomAdapter extends BaseAdapter {

    private Context mContext;
    private List<CustomListModel> models;

    private OnitemclickLisenter mOnitemclickLisenter;

    public SelectCustomAdapter(Context context, List<CustomListModel> models) {
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
            convertView = View.inflate(mContext, R.layout.listview_select_custom, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CustomListModel customListModel = models.get(position);

        viewHolder.tvSeItemType.setText(customListModel.getTypeName());
        viewHolder.tvSeItemName.setText(customListModel.getName());
        viewHolder.tvSeItemPhone.setText(customListModel.getPhone());

        if(customListModel.getTypeSelect()) {
            viewHolder.llSeItem.setImageResource(R.mipmap.regi_xuanzhong);
        }else {
            viewHolder.llSeItem.setImageResource(R.mipmap.regi_weixuan);
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.ll_se_item)
        ImageView llSeItem;
        @BindView(R.id.tv_se_item_type)
        TextView tvSeItemType;
        @BindView(R.id.tv_se_item_name)
        TextView tvSeItemName;
        @BindView(R.id.tv_se_item_phone)
        TextView tvSeItemPhone;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnitemclickLisenter {
        void onItemClick(View view, int position);
    }

    public void setmOnitemclickLisenter(OnitemclickLisenter mOnitemclickLisenter) {
        this.mOnitemclickLisenter = mOnitemclickLisenter;
    }
}
