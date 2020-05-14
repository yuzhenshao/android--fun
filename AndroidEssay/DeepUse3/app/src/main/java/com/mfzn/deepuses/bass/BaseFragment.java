package com.mfzn.deepuses.bass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.utils.ToastUtil;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseFragment extends RxFragment {

    private boolean isPaused;

    protected View rootView;

    public boolean isViewDestroyed = true;

    private Unbinder unbinder;

    @BindView(R.id.empty_view_root)
    LinearLayout emptyView;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty_text)
    TextView emptyText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isPaused = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        isPaused = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        isPaused = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutId() != -1) {
            if (rootView == null) {
                rootView = inflater.inflate(getLayoutId(), container, false);
            }
        } else {
            rootView = super.onCreateView(inflater, container, savedInstanceState);
        }
        if (rootView != null) {
            unbinder = ButterKnife.bind(this, rootView);
            isViewDestroyed = false;
        }
        return rootView;
    }

    protected void showErrorView(String text) {
        emptyView.setVisibility(View.VISIBLE);
        emptyText.setText(text);
    }

    protected void showNoDataView() {
        emptyView.setVisibility(View.VISIBLE);
        emptyText.setText("暂无数据");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        isViewDestroyed = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected int getLayoutId() {
        return -1;
    }

    public boolean isPaused() {
        return isPaused;
    }
}
