package com.mfzn.deepuses.bass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author yz @date 2020-05-03
 */
public abstract class BasicListActivity<T> extends BasicActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.empty_view_root)
    LinearLayout emptyView;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_text)
    TextView emptyText;

    protected List<T> mSourceList = new ArrayList<>();
    protected BaseQuickAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        adapter = getAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getResourceList();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_view;
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
        if (ListUtil.isEmpty(mSourceList)) {
            emptyView.setVisibility(View.VISIBLE);
            emptyText.setText(text);
        } else {
            ToastUtil.showToast(this, text);
        }
    }

    protected void showNoDataView() {
        hideDialog();
        emptyView.setVisibility(View.VISIBLE);
        emptyText.setText("暂无数据");
    }

    protected void showEmptyView(int imgResourceId, String text) {
        emptyView.setVisibility(View.VISIBLE);
        emptyImg.setImageResource(imgResourceId);
        emptyText.setText(text);
    }

}
