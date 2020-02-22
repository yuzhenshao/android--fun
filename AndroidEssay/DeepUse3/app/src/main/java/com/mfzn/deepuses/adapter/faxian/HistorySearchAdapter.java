package com.mfzn.deepuses.adapter.faxian;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.HistorySearchModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sun on 2018/5/25.
 */

public class HistorySearchAdapter extends BaseAdapter {

    private Context context;
    private List<HistorySearchModel> list;

    private OnItemDeleteListener mOnItemDeleteListener;

    public HistorySearchAdapter(Context context, List<HistorySearchModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.listview_history_search, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HistorySearchModel dataBean = list.get(position);

        viewHolder.tvHisItemName.setText(dataBean.getContent());

        viewHolder.llHisItemDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemDeleteListener.onItemDelete(v,position);
            }
        });

        return convertView;
    }

    public interface OnItemDeleteListener {
        void onItemDelete(View view, int position);
    }

    public void setOnItemDeleteListener(OnItemDeleteListener onItemDeleteListener) {
        mOnItemDeleteListener = onItemDeleteListener;
    }

    class ViewHolder {
        @BindView(R.id.tv_his_item_name)
        TextView tvHisItemName;
        @BindView(R.id.ll_his_item_del)
        LinearLayout llHisItemDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
