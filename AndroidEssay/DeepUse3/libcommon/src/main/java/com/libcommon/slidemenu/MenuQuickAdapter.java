package com.libcommon.slidemenu;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yz on 2020/4/1.
 */
public abstract class MenuQuickAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    public static final int NORMAL_VIEW = 0;

    @LayoutRes
    private final int menuLayoutId;
    private final List<MenuItem> menuItems = new ArrayList<>();

    protected MenuQuickAdapter(int contentLayoutId, int menuLayoutId, @Nullable List<T> data) {
        super(contentLayoutId, data);
        this.menuLayoutId = menuLayoutId;
    }

    public void setOnMenuItemClickListener(MenuItemClickListener listener, int... ids) {
        MenuAdapterHelper.setOnMenuItemClickListener(menuItems, listener, ids);
    }

    @Override
    protected View getItemView(@LayoutRes int layoutResId, ViewGroup parent) {
        return MenuAdapterHelper.getItemView(layoutResId, menuLayoutId, parent);
    }

    @Override
    public void onBindViewHolder(K holder, int position) {
        MenuAdapterHelper.onBindViewHolder(menuItems, holder);
        super.onBindViewHolder(holder, position);
    }
}
