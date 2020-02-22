package com.mfzn.deepuses.adapter.khgl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.khgl.SearchComModel;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sun on 2018/6/12.
 */

public class SelectComAdapter extends BaseAdapter {

    private Context mContext;
    private List<SearchComModel> models;

    public SelectComAdapter(Context context, List<SearchComModel> model) {
        this.mContext = context;
        this.models = model;
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
            convertView = View.inflate(mContext, R.layout.listview_select_com, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SearchComModel model = models.get(position);

        viewHolder.tvSwiItemName.setText(model.getCompanyName());

        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.tv_swi_item_name)
        TextView tvSwiItemName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
