package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchProjectAdapter extends BaseAdapter {

    private Context mContext;
    private List<XiangmuModel.DataBean> data;

    private OnitemclickLisenter mOnitemclickLisenter;

    public SearchProjectAdapter(Context context, List<XiangmuModel.DataBean> data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
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

        XiangmuModel.DataBean dataBean = data.get(position);

        viewHolder.tvSeItemTitle.getPaint().setFakeBoldText(true);
        viewHolder.tvSeItemTitle.setText(dataBean.getPro_name());
        viewHolder.tvSeItemName.setText(dataBean.getCustomName());
        String start_time = dataBean.getQualityBegin();
        String end_time = dataBean.getQualityEnd();
        if(!start_time.equals("0") && !end_time.equals("0")) {
            if(!TextUtils.isEmpty(start_time) && !TextUtils.isEmpty(end_time)) {
                viewHolder.tvSeItemTime.setText(DateUtils.stampDate(start_time) + "~" + DateUtils.stampDate(end_time));
            }else {
                viewHolder.tvSeItemTime.setText("暂无");
            }
        }else {
            viewHolder.tvSeItemTime.setText("暂无");
        }

        int customLevel = dataBean.getCustomLevel();
        if (customLevel == 4) {
            viewHolder.ivSeItemType.setImageResource(R.mipmap.xm_yixing);
        } else if (customLevel == 5) {
            viewHolder.ivSeItemType.setImageResource(R.mipmap.xm_erxing);
        } else if (customLevel == 6) {
            viewHolder.ivSeItemType.setImageResource(R.mipmap.xm_sanxing);
        } else if (customLevel == 7) {
            viewHolder.ivSeItemType.setImageResource(R.mipmap.xm_sixing);
        } else if (customLevel == 8) {
            viewHolder.ivSeItemType.setImageResource(R.mipmap.xm_wuxing);
        } else {
            viewHolder.ivSeItemType.setImageResource(0);
        }

        int afterSaleStatus = dataBean.getAfterSaleStatus();
        if (afterSaleStatus == 0) {//0未开通 1购买开通  2试用开通
            viewHolder.ivXmItemQx.setImageResource(R.mipmap.xm_weikai);
        } else if (afterSaleStatus == 1) {
            viewHolder.ivXmItemQx.setImageResource(R.mipmap.xm_goumai);
        } else if (afterSaleStatus == 2) {
            viewHolder.ivXmItemQx.setImageResource(R.mipmap.xm_shiyong);
        } else {
            viewHolder.ivXmItemQx.setImageResource(0);
        }

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
        @BindView(R.id.iv_xm_item_qx)
        ImageView ivXmItemQx;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
