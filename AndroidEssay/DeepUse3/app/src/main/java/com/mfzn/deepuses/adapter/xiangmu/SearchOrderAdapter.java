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
import com.mfzn.deepuses.adapter.fg.ShouhouGongdanAdapter;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchOrderAdapter extends BaseAdapter {

    private Context mContext;
    private List<WorkorderListModel.DataBean> data;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private OnItemPgSlClickListener onItemPgSlClickListener = null;

    public SearchOrderAdapter(Context context, List<WorkorderListModel.DataBean> data) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_search_order, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WorkorderListModel.DataBean model = data.get(position);
//
        holder.tvWoItemTitle.getPaint().setFakeBoldText(true);

        int shType = model.getAsType();
        if(shType == 1) {//0全部  1故障保修  2维护升级
            holder.tvWoItemTitle.setTextColor(mContext.getResources().getColor(R.color.color_3D7EFF));
        }else if(shType == 2) {
            holder.tvWoItemTitle.setTextColor(mContext.getResources().getColor(R.color.color_62C33A));
        }
        holder.tvWoItemTitle.setText(model.getShTypeName());

        int status = model.getStatus();//1待受理  2待派工 3待接单 4服务中 5待评价 已评价
        if(status == 1 || status == 2 || status == 3 || status == 4 ||
                status == 5 || status == 6) {
            holder.iv_wo_item_dz.setImageResource(R.mipmap.sh_dizhi);
            holder.iv_wo_item_time.setImageResource(R.mipmap.sh_shijian);
            holder.tvWoItemType.setTextColor(mContext.getResources().getColor(R.color.color_3D7EFF));

            holder.ll_wo_item_qux.setVisibility(View.VISIBLE);

            String u_name = model.getReceiverInfo().getUserName();
            if(TextUtils.isEmpty(u_name)) {
                holder.ll_wo_item_slr.setVisibility(View.INVISIBLE);
            }else {
                String substring1 = u_name.substring(0, 1);
                holder.tvWoItemSltx.setText(substring1);
                holder.tvWoItemSlname.setText(u_name);
            }

            if(status == 1 || status == 2) {
                holder.ll_wo_item_gcs.setVisibility(View.GONE);
                holder.ll_wo_item_pai.setVisibility(View.VISIBLE);
                if(status == 1) {
                    holder.tv_wo_item_pg.setVisibility(View.GONE);
                    holder.tv_wo_item_sl.setVisibility(View.VISIBLE);
                }else {
                    holder.tv_wo_item_pg.setVisibility(View.VISIBLE);
                    holder.tv_wo_item_sl.setVisibility(View.GONE);
                }
            }else {
                holder.ll_wo_item_pai.setVisibility(View.GONE);

                String u_name1 = model.getEngineerInfo().getUserName();
                if(TextUtils.isEmpty(u_name1)) {
                    holder.ll_wo_item_gcs.setVisibility(View.INVISIBLE);
                }else {
                    holder.ll_wo_item_gcs.setVisibility(View.VISIBLE);
                    String substring1 = u_name1.substring(0, 1);
                    holder.tvWoItemSctx.setText(substring1);
                    holder.tvWoItemGcname.setText(u_name1);
                }
            }

        }else if(status == 7 || status == 8) {// 7已取消 8已关闭
            holder.tvWoItemType.setTextColor(mContext.getResources().getColor(R.color.color_606266));
            holder.tvWoItemType.setTextColor(mContext.getResources().getColor(R.color.color_606266));
            holder.iv_wo_item_dz.setImageResource(R.mipmap.dizhi_hui);
            holder.iv_wo_item_time.setImageResource(R.mipmap.shijian_hui);
            holder.ll_wo_item_qux.setVisibility(View.GONE);
        }
        holder.tvWoItemType.setText(model.getStatusTypeName());

        holder.tvWoItemContent.setText(model.getContent());
        holder.tvWoItemAddress.setText(model.getDetailAddress());
        String wishTime = model.getWishTime();
        String substring = wishTime.substring(0, 10);
        holder.tvWoItemTime.setText(substring);

        int afterSaleInDate = model.getAfterSaleInDate();//0 bu 1 ke
        if(afterSaleInDate == 0) {
            holder.iv_wo_item_ky.setVisibility(View.GONE);
            holder.iv_wo_item_nky.setVisibility(View.VISIBLE);
        }else if(afterSaleInDate == 1) {
            holder.iv_wo_item_ky.setVisibility(View.VISIBLE);
            holder.iv_wo_item_nky.setVisibility(View.GONE);
        }

        holder.tv_wo_item_pg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemPgSlClickListener.onItemPgClick(v,position);
            }
        });
        holder.tv_wo_item_sl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemPgSlClickListener.onItemSlClick(v,position);
            }
        });

        return convertView;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemPgSlClickListener {
        void onItemPgClick(View view, int position);
        void onItemSlClick(View view, int position);
    }

    public void setOnItemPgSlClickListener(OnItemPgSlClickListener listener) {
        this.onItemPgSlClickListener = listener;
    }

    class ViewHolder {
        @BindView(R.id.tv_wo_item_title)
        TextView tvWoItemTitle;
        @BindView(R.id.tv_wo_item_type)
        TextView tvWoItemType;
        @BindView(R.id.tv_wo_item_content)
        TextView tvWoItemContent;
        @BindView(R.id.tv_wo_item_address)
        TextView tvWoItemAddress;
        @BindView(R.id.tv_wo_item_time)
        TextView tvWoItemTime;
        @BindView(R.id.tv_wo_item_sltx)
        TextView tvWoItemSltx;
        @BindView(R.id.tv_wo_item_slname)
        TextView tvWoItemSlname;
        @BindView(R.id.tv_wo_item_sctx)
        TextView tvWoItemSctx;
        @BindView(R.id.tv_wo_item_gcname)
        TextView tvWoItemGcname;
        @BindView(R.id.tv_wo_item_pg)
        TextView tv_wo_item_pg;
        @BindView(R.id.tv_wo_item_sl)
        TextView tv_wo_item_sl;
        @BindView(R.id.ll_wo_item_slr)
        LinearLayout ll_wo_item_slr;
        @BindView(R.id.ll_wo_item_qux)
        LinearLayout ll_wo_item_qux;
        @BindView(R.id.ll_wo_item_gcs)
        LinearLayout ll_wo_item_gcs;
        @BindView(R.id.ll_wo_item_pai)
        LinearLayout ll_wo_item_pai;
        @BindView(R.id.iv_wo_item_dz)
        ImageView iv_wo_item_dz;
        @BindView(R.id.iv_wo_item_time)
        ImageView iv_wo_item_time;
        @BindView(R.id.iv_wo_item_ky)
        ImageView iv_wo_item_ky;
        @BindView(R.id.iv_wo_item_nky)
        ImageView iv_wo_item_nky;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
