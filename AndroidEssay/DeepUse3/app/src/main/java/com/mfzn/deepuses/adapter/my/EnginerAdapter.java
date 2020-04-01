package com.mfzn.deepuses.adapter.my;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.xiangmu.SelectEnginerModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author syz @date 2020-03-11
 */
public class EnginerAdapter extends BaseAdapter {

    private Context mContext;
    private List<SelectEnginerModel> mEnginerList;

    public EnginerAdapter(Context context, List<SelectEnginerModel> enginerList) {
        this.mContext = context;
        this.mEnginerList = enginerList;
    }

    @Override
    public int getCount() {
        return mEnginerList.size();
    }

    @Override
    public Object getItem(int position) {
        return mEnginerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.select_enginer_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SelectEnginerModel model = mEnginerList.get(position);
        String u_head = model.getEngineerAvatar();
        if (!TextUtils.isEmpty(u_head)) {
            Glide.with(mContext).load(ApiHelper.BASE_URL + u_head).into(viewHolder.ivSeIcon);
        }
        viewHolder.tvSeName.setText(model.getEngineerName());
        viewHolder.tvSePhone.setText(model.getEngineerPhone());
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_se_icon)
        RoundImageView ivSeIcon;
        @BindView(R.id.tv_se_name)
        TextView tvSeName;
        @BindView(R.id.tv_se_phone)
        TextView tvSePhone;
        @BindView(R.id.tv_se_add)
        TextView tvSeAdd;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
