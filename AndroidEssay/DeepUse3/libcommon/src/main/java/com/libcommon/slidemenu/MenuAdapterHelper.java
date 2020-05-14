package com.libcommon.slidemenu;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.R;

import java.util.List;


import static com.chad.library.adapter.base.BaseMultiItemQuickAdapter.TYPE_NOT_FOUND;
import static com.chad.library.adapter.base.BaseQuickAdapter.EMPTY_VIEW;
import static com.chad.library.adapter.base.BaseQuickAdapter.FOOTER_VIEW;
import static com.chad.library.adapter.base.BaseQuickAdapter.HEADER_VIEW;
import static com.chad.library.adapter.base.BaseQuickAdapter.LOADING_VIEW;

/**
 * Created by yz on 2020/4/1.
 */
class MenuAdapterHelper {
    public static void setOnMenuItemClickListener(List<MenuItem> menuItems, MenuItemClickListener listener, int... ids) {
        if (ids.length == 0) {
            throw new IllegalArgumentException("At least one id!");
        }
        for (int id : ids) {
            menuItems.add(new MenuItem(id, listener));
        }
    }

    public static View getItemView(@LayoutRes int layoutResId, @LayoutRes int menuLayoutId, ViewGroup parent) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_with_menu_view, parent, false);
        if (layoutResId != TYPE_NOT_FOUND) {
            ViewGroup contentContainer = itemView.findViewById(R.id.slide_menu_content);
            LayoutInflater.from(parent.getContext()).inflate(layoutResId, contentContainer, true);
        }
        if (menuLayoutId != TYPE_NOT_FOUND) {
            ViewGroup menuContainer = itemView.findViewById(R.id.slide_menu_menu);
            LayoutInflater.from(parent.getContext()).inflate(menuLayoutId, menuContainer, true);
            menuContainer.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), 0);
            menuContainer.getLayoutParams().width = menuContainer.getMeasuredWidth();
        }
        return itemView;
    }

    public static <K extends BaseViewHolder, T> void onBindViewHolder(List<MenuItem> menuItems, final K helper) {
        if (isNormalView(helper)) {
            onBindViewHolderInternal(menuItems, helper);
        }
    }

    public static boolean isNormalView(RecyclerView.ViewHolder holder) {
        return holder.getItemViewType() != LOADING_VIEW
                && holder.getItemViewType() != HEADER_VIEW
                && holder.getItemViewType() != EMPTY_VIEW
                && holder.getItemViewType() != FOOTER_VIEW;
    }


    private static <K extends BaseViewHolder> void onBindViewHolderInternal(List<MenuItem> menuItems, final K helper) {
        for (MenuItem menuItem : menuItems) {
            int id = menuItem.id;
            final MenuItemClickListener listener = menuItem.listener;
            if (listener == null) {
                helper.getView(id).setOnClickListener(null);
            } else {
                helper.getView(id).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onClick(helper.getAdapterPosition(),v);
                        }
                    }
                });
            }
        }
    }

}
