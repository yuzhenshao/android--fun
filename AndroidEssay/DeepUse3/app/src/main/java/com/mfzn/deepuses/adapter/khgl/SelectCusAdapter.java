package com.mfzn.deepuses.adapter.khgl;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.khgl.MyShareModel;
import com.mfzn.deepuses.model.khgl.SelectCusModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sun on 2018/6/12.
 */

public class SelectCusAdapter extends BaseAdapter {

    private Context mContext;
    private List<SelectCusModel> models;
    private OnDelItemClickListener mOnDelItemClickListener = null;

    public SelectCusAdapter(Context context, List<SelectCusModel> model) {
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
            convertView = View.inflate(mContext, R.layout.listview_select_cus, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SelectCusModel model = models.get(position);

        viewHolder.tvCusItemName.setText(model.getName());

        String customerLevelID = model.getId();
        switch (customerLevelID) {
            case "4":
                viewHolder.ivCusItemLevel.setImageResource(R.mipmap.cus_level1);
                break;
            case "5":
                viewHolder.ivCusItemLevel.setImageResource(R.mipmap.cus_level2);
                break;
            case "6":
                viewHolder.ivCusItemLevel.setImageResource(R.mipmap.cus_level3);
                break;
            case "7":
                viewHolder.ivCusItemLevel.setImageResource(R.mipmap.cus_level4);
                break;
            case "8":
                viewHolder.ivCusItemLevel.setImageResource(R.mipmap.cus_level5);
                break;
            default:
                viewHolder.ivCusItemLevel.setImageResource(0);
                break;
        }

        viewHolder.tvCusItemPro.setText(model.getPro());

        viewHolder.ll_cus_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDelItemClickListener != null) {
                    mOnDelItemClickListener.onItemClick(v, position);
                }
            }
        });

        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.tv_cus_item_name)
        TextView tvCusItemName;
        @BindView(R.id.iv_cus_item_level)
        ImageView ivCusItemLevel;
        @BindView(R.id.tv_cus_item_pro)
        TextView tvCusItemPro;
        @BindView(R.id.ll_cus_del)
        LinearLayout ll_cus_del;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnDelItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnDelItemClickListener(OnDelItemClickListener listener) {
        this.mOnDelItemClickListener = listener;
    }
}
