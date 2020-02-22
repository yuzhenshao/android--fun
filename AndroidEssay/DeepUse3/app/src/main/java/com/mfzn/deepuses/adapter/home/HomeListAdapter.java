package com.mfzn.deepuses.adapter.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeListAdapter extends BaseAdapter {

    private Context mContext;
    private List<SelectCompanyModel> models;
    private String text;

    public HomeListAdapter(Context context, List<SelectCompanyModel> model,String text) {
        this.mContext = context;
        this.models = model;
        this.text = text;
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
            convertView = View.inflate(mContext, R.layout.listview_home_list, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SelectCompanyModel model = models.get(position);

        String logo = model.getLogo();
        if(!TextUtils.isEmpty(logo)) {
            Glide.with(mContext).load(ApiHelper.BASE_URL + logo).into(viewHolder.tv_home_item_icon);
        }

        String companyName = model.getCompanyName();
        if(text.equals(companyName)) {
            viewHolder.ivHomeItemDh.setVisibility(View.VISIBLE);
        }else {
            viewHolder.ivHomeItemDh.setVisibility(View.GONE);
        }

        int companyLevel = model.getCompanyLevel();
        if(companyLevel == 1) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.vip1);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            viewHolder.tvHomeItemName.setCompoundDrawables(null, null, drawable, null);
        }else if(companyLevel == 2) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.vip2);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            viewHolder.tvHomeItemName.setCompoundDrawables(null, null, drawable, null);
        }else if(companyLevel == 3) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.vip3);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            viewHolder.tvHomeItemName.setCompoundDrawables(null, null, drawable, null);
        }else if(companyLevel == 4) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.vip4);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            viewHolder.tvHomeItemName.setCompoundDrawables(null, null, drawable, null);
        }else if(companyLevel == 5) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.vip5);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            viewHolder.tvHomeItemName.setCompoundDrawables(null, null, drawable, null);
        }else if(companyLevel == 6) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.vip6);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            viewHolder.tvHomeItemName.setCompoundDrawables(null, null, drawable, null);
        }

        viewHolder.tvHomeItemName.setText(companyName);

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_home_item_name)
        TextView tvHomeItemName;
        @BindView(R.id.iv_home_item_dh)
        ImageView ivHomeItemDh;
        @BindView(R.id.tv_home_item_icon)
        RoundImageView tv_home_item_icon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
