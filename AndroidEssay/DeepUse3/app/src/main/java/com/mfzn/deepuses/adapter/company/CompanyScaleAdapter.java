package com.mfzn.deepuses.adapter.company;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.company.SelectLableModel;
import com.mfzn.deepuses.model.myTeam.CompanyScaleModel;

import java.util.List;

public class CompanyScaleAdapter extends BaseAdapter {

    private Context mContext;
    private List<CompanyScaleModel> models;

    public CompanyScaleAdapter(Context context, List<CompanyScaleModel> model) {
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
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.listview_company_scale,null);
            viewHolder.tv_sc_item_content = convertView.findViewById(R.id.tv_sc_item_content);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CompanyScaleModel model = models.get(position);

        viewHolder.tv_sc_item_content.setText(model.getScale());

        return convertView;
    }

    class ViewHolder{
        TextView tv_sc_item_content;
    }
}
