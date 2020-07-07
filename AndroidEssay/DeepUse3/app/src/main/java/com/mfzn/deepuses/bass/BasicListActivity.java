package com.mfzn.deepuses.bass;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * @author yz @date 2020-05-03
 */
public abstract class BasicListActivity<T> extends BasicActivity {

    @BindView(R.id.recycler_view)
   public RecyclerView recyclerView;
    @BindView(R.id.empty_view_root)
    LinearLayout emptyView;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_text)
    TextView emptyText;

    protected EditText serachEdit;

    protected List<T> mSourceList = new ArrayList<>();
    protected List<T> sourceAllList = new ArrayList<>();
    protected BaseQuickAdapter adapter;
    private boolean isSearching = false;

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
        isSearching = false;
        sourceAllList = resources;
        setSourceList(resources);
    }

    protected void refreshSearchSource(List<T> resources) {
        isSearching = true;
        setSourceList(resources);
    }

    private void setSourceList(List<T> resources) {
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

    protected void initSearch(String seachHint) {
        serachEdit = findViewById(R.id.serach_edit);
        serachEdit.setHint(seachHint);
        RxTextView.textChanges(serachEdit).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) {
                if (TextUtils.isEmpty(charSequence)) {
                    refreshSource(sourceAllList);
                }
            }
        });

        serachEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchWord = serachEdit.getText().toString().trim();
                    if (TextUtils.isEmpty(searchWord)) {
                        return true;
                    }
                    // 先隐藏键盘
                    ((InputMethodManager) serachEdit.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(BasicListActivity.this
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    if (!TextUtils.isEmpty(searchWord)) {
                        searchAction(searchWord);
                        return true;
                    }
                    return true;
                }
                return false;
            }
        });
    }

    protected void searchAction(String keyword) {

    }

    protected String getSearchKeyword() {
        if (serachEdit != null) {
            return serachEdit.getText().toString();
        }
        return null;
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
            serachEdit.onEditorAction(EditorInfo.IME_ACTION_SEARCH);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK && isSearching) {
            serachEdit.setText(null);
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
