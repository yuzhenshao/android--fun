package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.khgl.WholeCustomerAdapter;
import com.mfzn.deepuses.model.xiangmu.EnginerListModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * @author yz @date 2020-04-08
 */
public class MyEnginerListAdapter extends RecyclerAdapter<EnginerListModel, MyEnginerListAdapter.ViewHolder> {

    private OnDelItemClickListener mOnDelItemClickListener = null;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public MyEnginerListAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_enginer_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyEnginerListAdapter.ViewHolder viewHolder, int position) {
        viewHolder.itemView.setTag(position);

        EnginerListModel model = data.get(position);

        String u_head = model.getEngineerAvatar();
        if (!TextUtils.isEmpty(u_head)) {
            viewHolder.ivEnItemIcon.setVisibility(View.VISIBLE);
            viewHolder.tvZuItemIcon.setVisibility(View.GONE);
            Glide.with(mContext).load(ApiHelper.BASE_URL + u_head).into(viewHolder.ivEnItemIcon);
        } else {
            viewHolder.ivEnItemIcon.setVisibility(View.GONE);
            viewHolder.tvZuItemIcon.setVisibility(View.VISIBLE);
            String staffName = model.getEngineerName();
            if (staffName.length() > 2) {
                String substring = staffName.substring(staffName.length() - 2, staffName.length());
                viewHolder.tvZuItemIcon.setText(substring);
            } else {
                viewHolder.tvZuItemIcon.setText(staffName);
            }
        }
        viewHolder.tvEnItemName.setText(model.getEngineerName());
        viewHolder.tvEnItemPhone.setText(model.getEngineerPhone());

        viewHolder.tv_cus_item_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDelItemClickListener != null) {
                    mOnDelItemClickListener.onItemClick(v, position);
                }
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_en_item_icon)
        RoundImageView ivEnItemIcon;
        @BindView(R.id.tv_en_item_name)
        TextView tvEnItemName;
        @BindView(R.id.tv_en_item_phone)
        TextView tvEnItemPhone;
        @BindView(R.id.tv_zu_item_icon)
        TextView tvZuItemIcon;
        @BindView(R.id.tv_cus_item_del)
        TextView tv_cus_item_del;

        ViewHolder(View view) {
            super(view);
            KnifeKit.bind(this, itemView);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnDelItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnDelItemClickListener(OnDelItemClickListener listener) {
        this.mOnDelItemClickListener = listener;
    }
}