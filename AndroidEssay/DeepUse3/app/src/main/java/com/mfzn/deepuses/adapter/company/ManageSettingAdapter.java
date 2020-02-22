package com.mfzn.deepuses.adapter.company;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.myTeam.ManageSettingModel;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.KnifeKit;


/**
 * Created by sun on 2018/6/12.
 */

public class ManageSettingAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ManageSettingModel> list;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private OnDeleteClickLister mDeleteClickListener = null;
    private OnItenClickLister onItenClickLister = null;

    public ManageSettingAdapter(Context mContext, List<ManageSettingModel> list) {
        this.context = mContext;
        this.list = list;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recycleview_manage_setting, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MoreViewHolder bbnViewHolder = (MoreViewHolder) holder;

        ManageSettingModel model = list.get(position);

        //"roleID": 1:创建者  2：超级管理员  3：普普通通管理员 4：普通员工
        int roleID = model.getRoleID();
        if(roleID == 1) {
            bbnViewHolder.tvManItemType.setText("创建者");
            bbnViewHolder.ivManItemYou.setVisibility(View.GONE);
        }else if(roleID == 2) {
            bbnViewHolder.tvManItemType.setText("超级管理员");
            bbnViewHolder.ivManItemYou.setVisibility(View.GONE);
        }else if(roleID == 3) {
            bbnViewHolder.tvManItemType.setText("普通管理员");
            bbnViewHolder.ivManItemYou.setVisibility(View.VISIBLE);
        }else if(roleID == 4) {
            bbnViewHolder.tvManItemType.setText("普通员工");
            bbnViewHolder.ivManItemYou.setVisibility(View.VISIBLE);
        }else {
            bbnViewHolder.ivManItemYou.setVisibility(View.GONE);
        }

        bbnViewHolder.tvManItemName.setText(model.getStaffName());

        bbnViewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteClickListener.onItemClick(v, position);
            }
        });
        bbnViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItenClickLister.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_man_item_type)
        TextView tvManItemType;
        @BindView(R.id.tv_man_item_name)
        TextView tvManItemName;
        @BindView(R.id.iv_man_item_you)
        ImageView ivManItemYou;
        @BindView(R.id.tv_delete)
        TextView tv_delete;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }

    public interface OnDeleteClickLister {
        void onItemClick(View view, int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnItenClickLister {
        void onItemClick(View view, int position);
    }

    public void setOnItenClickLister(OnItenClickLister listener) {
        this.onItenClickLister = listener;
    }
}
