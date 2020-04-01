package com.mfzn.deepusesSer.adapter.xiangmu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepusesSer.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sun on 2018/6/12.
 */

public class AddPhotoAdapter extends RecyclerView.Adapter {

    private Context context;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    private List<String> datas;
    private OnAddItemClickListener mOnAddItemClickListener = null;
    private OnDeleteClickListener mOnDeleteClickListener = null;

    public AddPhotoAdapter(Context mContext, List<String> datas) {
        this.context = mContext;
        this.datas = datas;
        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreViewHolder(context, mLayoutInflater.inflate(R.layout.recyleview_add_photo, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MoreViewHolder viewHolder = (MoreViewHolder) holder;

        String bean = datas.get(position);
        if (TextUtils.isEmpty(bean)) {
            viewHolder.tvAddItemHide2.setVisibility(View.VISIBLE);
            viewHolder.flAddItemHide.setVisibility(View.VISIBLE);
            viewHolder.flAddItemShow.setVisibility(View.GONE);
        } else {
            viewHolder.tvAddItemHide2.setVisibility(View.GONE);
            viewHolder.flAddItemHide.setVisibility(View.GONE);
            viewHolder.flAddItemShow.setVisibility(View.VISIBLE);

//            Bitmap bitmap = null;
            //                bitmap = BitmapFileSetting.fileBitmap(bean.getBmp().get(bean.getBmp().size() - 1).getBitmap());
//                Glide.with(context).load(bean.getBmp().get(bean.getBmp().size() - 1).getBitmap()).into(viewHolder.ivAddItemPhoto);
            Glide.with(context).load(bean).into(viewHolder.ivAddItemPhoto);
        }

//        if (position == datas.size() - 1) {
//            viewHolder.tvAddItemHide.setVisibility(View.GONE);
//        } else {
//            viewHolder.tvAddItemHide.setVisibility(View.VISIBLE);
//        }

        viewHolder.flAddItemHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnAddItemClickListener.onItemAddClick(v, position);
            }
        });
        viewHolder.ivAddItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDeleteClickListener.onDeleteClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_add_item_hide2)
        TextView tvAddItemHide2;
        @BindView(R.id.fl_add_item_hide)
        FrameLayout flAddItemHide;
        @BindView(R.id.iv_add_item_photo)
        ImageView ivAddItemPhoto;
        @BindView(R.id.iv_add_item_delete)
        ImageView ivAddItemDelete;
        @BindView(R.id.fl_add_item_show)
        FrameLayout flAddItemShow;
        @BindView(R.id.tv_add_item_hide)
        TextView tvAddItemHide;

        public MoreViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnAddItemClickListener {
        void onItemAddClick(View view, int position);
    }

    public void setOnAddClickListener(OnAddItemClickListener listener) {
        this.mOnAddItemClickListener = listener;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(View view, int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.mOnDeleteClickListener = listener;
    }
}
