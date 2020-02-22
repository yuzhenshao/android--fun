package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xiangmu.EnginerListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EnginerListAdapter extends BaseAdapter {

    private Context mContext;
    private List<EnginerListModel> models;

    public EnginerListAdapter(Context context, List<EnginerListModel> models) {
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
            convertView = View.inflate(mContext, R.layout.listview_enginer_list, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        EnginerListModel model = models.get(position);

        String u_head = model.getU_head();
//        if(!TextUtils.isEmpty(u_head)) {
//            Glide.with(mContext).load(ApiHelper.BASE_URL + u_head).into(viewHolder.ivEnItemIcon);
//        }
        if(!TextUtils.isEmpty(u_head)) {
            viewHolder.ivEnItemIcon.setVisibility(View.VISIBLE);
            viewHolder.tvZuItemIcon.setVisibility(View.GONE);
            Glide.with(mContext).load(ApiHelper.BASE_URL + u_head).into(viewHolder.ivEnItemIcon);
        }else {
            viewHolder.ivEnItemIcon.setVisibility(View.GONE);
            viewHolder.tvZuItemIcon.setVisibility(View.VISIBLE);
            String staffName = model.getU_name();
            if(staffName.length() > 2) {
                String substring = staffName.substring(staffName.length() - 2, staffName.length());
                viewHolder.tvZuItemIcon.setText(substring);
            }else {
                viewHolder.tvZuItemIcon.setText(staffName);
            }
        }
        viewHolder.tvEnItemName.setText(model.getU_name());
        viewHolder.tvEnItemPhone.setText(model.getU_phone());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_en_item_icon)
        RoundImageView ivEnItemIcon;
        @BindView(R.id.tv_en_item_name)
        TextView tvEnItemName;
        @BindView(R.id.tv_en_item_phone)
        TextView tvEnItemPhone;
        @BindView(R.id.tv_zu_item_icon)
        TextView tvZuItemIcon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
