package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.model.xiangmu.ProjectLevelModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProjectLevelAdapter extends BaseAdapter {

    private Context mContext;
    private int types;
    private List<ProjectLevelModel> models;

    public ProjectLevelAdapter(Context context, List<ProjectLevelModel> models, int types) {
        this.mContext = context;
        this.models = models;
        this.types = types;
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
            convertView = View.inflate(mContext, R.layout.listview_project_level, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ProjectLevelModel model = models.get(position);

        viewHolder.tvDjItemName.setText(model.getLevelName());

        if (types == position) {
            viewHolder.ivDjItemSelect.setImageResource(R.mipmap.regi_xuanzhong);
        } else {
            viewHolder.ivDjItemSelect.setImageResource(R.mipmap.regi_weixuan);
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_dj_item_select)
        ImageView ivDjItemSelect;
        @BindView(R.id.iv_dj_item_name)
        TextView tvDjItemName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
