package com.mfzn.deepusesSer.adapter.company;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.model.company.SelectLableModel;

import java.util.List;

public class SelectLableAdapter extends BaseAdapter {

    private Context mContext;
    private List<SelectLableModel> models;

    private OnItemClickLisenter mOnItemClickLisenter;

    public SelectLableAdapter(Context context, List<SelectLableModel> models) {
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
            convertView = View.inflate(mContext, R.layout.listview_select_lable,null);
            viewHolder.tv_la_item_name = convertView.findViewById(R.id.tv_la_item_name);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SelectLableModel model = models.get(position);

        viewHolder.tv_la_item_name.setText(model.getName());

        viewHolder.tv_la_item_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean click = model.getClick();
                int sum = 0;
                for (int i = 0 ; i < models.size() ; i++){
                    Boolean clicks = models.get(i).getClick();
                    if(clicks){
                        sum++;
                    }
                }
                if(sum < 5){
                    if(click) {
                        model.setClick(false);
                        viewHolder.tv_la_item_name.setSelected(false);
                    }else {
                        model.setClick(true);
                        viewHolder.tv_la_item_name.setSelected(true);
                    }
                    mOnItemClickLisenter.onItemClick();
                }else {
                    if(click) {
                        model.setClick(false);
                        viewHolder.tv_la_item_name.setSelected(false);
                    }
                    mOnItemClickLisenter.onItemClick();
                }
            }
        });

        return convertView;
    }

    class ViewHolder{
        TextView tv_la_item_name;
    }

    public interface OnItemClickLisenter {
        void onItemClick();
    }

    public void setOnItemClickLisenter(OnItemClickLisenter onItemClickLisenter) {
        this.mOnItemClickLisenter = onItemClickLisenter;
    }
}
