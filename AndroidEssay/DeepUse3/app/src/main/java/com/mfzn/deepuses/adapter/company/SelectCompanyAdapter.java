package com.mfzn.deepuses.adapter.company;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.fg.WorkRecommendAdapter;
import com.mfzn.deepuses.model.company.SelectCompanyModel;

import java.util.List;

public class SelectCompanyAdapter extends BaseAdapter {

    private Context mContext;
    private List<SelectCompanyModel> models;

    public SelectCompanyAdapter(Context context, List<SelectCompanyModel> models) {
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
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.listview_select_company,null);
            viewHolder.tv_sel_item_name = convertView.findViewById(R.id.tv_sel_item_name);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SelectCompanyModel selectCompanyModel = models.get(position);

        viewHolder.tv_sel_item_name.setText(selectCompanyModel.getCompanyName());

        return convertView;
    }

    class ViewHolder{
        TextView tv_sel_item_name;
    }
}
