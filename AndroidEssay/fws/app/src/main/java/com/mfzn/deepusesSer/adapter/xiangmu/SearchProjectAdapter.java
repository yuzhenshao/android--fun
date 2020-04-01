package com.mfzn.deepusesSer.adapter.xiangmu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchProjectAdapter extends BaseAdapter {

    private Context mContext;

    private OnitemclickLisenter mOnitemclickLisenter;

    public SearchProjectAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_search_project, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        holder.tvXmItemTitle.getPaint().setFakeBoldText(true);
//        holder.tvXmItemTitle.setText(bean.getPro_name());
//        holder.tvXmItemName.setText(bean.getCustomName());
//        holder.tvXmItemTime.setText(bean.getStart_time() + "~" + bean.getEnd_time());
//
//        int customLevel = bean.getCustomLevel();
//        if (customLevel == 4) {
//            holder.ivXmItemType.setImageResource(R.mipmap.xm_yixing);
//        } else if (customLevel == 5) {
//            holder.ivXmItemType.setImageResource(R.mipmap.xm_erxing);
//        } else if (customLevel == 6) {
//            holder.ivXmItemType.setImageResource(R.mipmap.xm_sanxing);
//        } else if (customLevel == 7) {
//            holder.ivXmItemType.setImageResource(R.mipmap.xm_sixing);
//        } else if (customLevel == 8) {
//            holder.ivXmItemType.setImageResource(R.mipmap.xm_wuxing);
//        }

        return convertView;
    }

    public interface OnitemclickLisenter {
        void onItemClick(View view, int position);
    }

    public void setmOnitemclickLisenter(OnitemclickLisenter mOnitemclickLisenter) {
        this.mOnitemclickLisenter = mOnitemclickLisenter;
    }

    class ViewHolder {
        @BindView(R.id.tv_se_item_title)
        TextView tvSeItemTitle;
        @BindView(R.id.tv_se_item_name)
        TextView tvSeItemName;
        @BindView(R.id.iv_se_item_type)
        ImageView ivSeItemType;
        @BindView(R.id.tv_se_item_time)
        TextView tvSeItemTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
