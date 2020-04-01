package com.mfzn.deepusesSer.adapter.xiangmu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.model.xiangmu.WorkorderTujianModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WorkorderTujianAdapter extends BaseAdapter {

    private Context mContext;
    private List<WorkorderTujianModel> models;
    private OnitemclickLisenter mOnitemclickLisenter;

    public WorkorderTujianAdapter(Context context, List<WorkorderTujianModel> models) {
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
            convertView = View.inflate(mContext, R.layout.listview_workorder_tujian, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.llWoItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnitemclickLisenter.onItemClick(v,position);
            }
        });

        return convertView;
    }

    public interface OnitemclickLisenter {
        void onItemClick(View view, int position);
    }

    public void setmOnitemclickLisenter(OnitemclickLisenter mOnitemclickLisenter) {
        this.mOnitemclickLisenter = mOnitemclickLisenter;
    }

    class ViewHolder {
        @BindView(R.id.et_wo_item_type)
        EditText etWoItemType;
        @BindView(R.id.et_wo_item_name)
        EditText etWoItemName;
        @BindView(R.id.et_wo_item_phone)
        EditText etWoItemPhone;
        @BindView(R.id.ll_wo_item_delete)
        LinearLayout llWoItemDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
