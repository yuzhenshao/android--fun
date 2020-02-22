package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xiangmu.CustomTypeModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomTypeAdapter extends BaseAdapter {

    private Context mContext;
    private List<CustomTypeModel> models;
    private int posotions;

    public CustomTypeAdapter(Context context, List<CustomTypeModel> models,int posotions) {
        this.mContext = context;
        this.models = models;
        this.posotions = posotions;
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
            convertView = View.inflate(mContext, R.layout.listview_custom_type, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CustomTypeModel customListModel = models.get(position);

        viewHolder.tvCuItemName.setText(customListModel.getName());

        if(posotions == position) {
            viewHolder.ivCuItemType.setImageResource(R.mipmap.regi_xuanzhong);
        }else {
            viewHolder.ivCuItemType.setImageResource(R.mipmap.regi_weixuan);
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_cu_item_name)
        TextView tvCuItemName;
        @BindView(R.id.iv_cu_item_type)
        ImageView ivCuItemType;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
