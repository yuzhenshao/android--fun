package com.libcommon.dialog.adapter;

import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.libcommon.dialog.view.BindViewHolder;

import java.util.List;

public abstract class DialogAdapter<T> extends RecyclerView.Adapter<BindViewHolder> {

    private int layoutRes;
    private int recyclerRes;
    private List<T> datas;
    private OnAdapterItemClickListener mOnItemClickListener;

    protected abstract void onBind(BindViewHolder holder, T t);

    public DialogAdapter(@LayoutRes int layoutRes, @LayoutRes int recyclerRes, List<T> datas) {
        this.layoutRes = layoutRes;
        this.datas = datas;
        this.recyclerRes = recyclerRes;
    }

    @Override
    public BindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));
    }

    @Override
    public void onBindViewHolder(final BindViewHolder holder, final int position) {
        onBind(holder, getItem(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(DialogAdapter.this, v, position);
            }
        });
    }

    public T getItem(@IntRange(from = 0) int position) {
        if (position < datas.size())
            return datas.get(position);
        else
            return null;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public int getRecyclerRes() {
        return recyclerRes;
    }

    public void setOnItemClickListener(@Nullable OnAdapterItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnAdapterItemClickListener {
        void onItemClick(DialogAdapter adapter, View view, int position);
    }
}
