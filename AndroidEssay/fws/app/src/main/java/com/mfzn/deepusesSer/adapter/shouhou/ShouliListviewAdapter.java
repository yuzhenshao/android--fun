package com.mfzn.deepusesSer.adapter.shouhou;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.model.shouhou.ChuliGuochengModel;
import com.mfzn.deepusesSer.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShouliListviewAdapter extends BaseAdapter {

    private Context mContext;
    private List<ChuliGuochengModel> models;

    public ShouliListviewAdapter(Context context,List<ChuliGuochengModel> models) {
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
            convertView = View.inflate(mContext, R.layout.listview_shouli_chuli, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ChuliGuochengModel chuliGuochengModel = models.get(position);

        viewHolder.tvAccItemTime.setText(DateUtils.stampToDateTime(chuliGuochengModel.getAddTime() + ""));
        viewHolder.tvAccItemType.setText(chuliGuochengModel.getTypeName());
        viewHolder.tvAccItemContent.setText(chuliGuochengModel.getContent());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_acc_item_time)
        TextView tvAccItemTime;
        @BindView(R.id.tv_acc_item_type)
        TextView tvAccItemType;
        @BindView(R.id.tv_acc_item_content)
        TextView tvAccItemContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
