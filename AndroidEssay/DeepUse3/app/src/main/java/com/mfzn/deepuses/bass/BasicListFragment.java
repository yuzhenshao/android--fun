package com.mfzn.deepuses.bass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public abstract class BasicListFragment<T> extends BaseFragment{

    private QMUITipDialog mTipDialog;
    protected List<T> mSourceList = new ArrayList<>();

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;
    protected BaseQuickAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        adapter = getAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        getResourceList();
    }

    protected abstract void getResourceList();

    protected abstract BaseQuickAdapter getAdapter();

    protected void refreshSource(List<T> resources) {
        hideDialog();
        emptyView.setVisibility(View.GONE);
        mSourceList.clear();
        adapter.notifyDataSetChanged();
        if (ListUtil.isEmpty(resources)) {
            showNoDataView();
        } else {
            mSourceList.addAll(resources);
            adapter.notifyDataSetChanged();
        }
    }

    protected void showErrorView(String text) {
        hideDialog();
        super.showErrorView(text);
    }

    protected void showNoDataView() {
        hideDialog();
        super.showNoDataView();
    }

    public void showDialog() {
        try {
            hideDialog();
            mTipDialog = new QMUITipDialog.Builder(getContext())
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .create();
            mTipDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideDialog() {
        try {
            if (null != mTipDialog && mTipDialog.isShowing()) {
                mTipDialog.dismiss();
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_view;
    }
}
