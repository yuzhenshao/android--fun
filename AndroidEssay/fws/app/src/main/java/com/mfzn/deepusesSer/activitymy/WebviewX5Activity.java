package com.mfzn.deepusesSer.activitymy;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;


import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.x5webview.X5WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.OnClick;

public class WebviewX5Activity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.x5_webview)
    X5WebView x5Webview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview_x5;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //默认显示
        Intent intent = getIntent();
        String url = intent.getStringExtra(Constants.WEBVIEW_URL);
        String name = intent.getStringExtra(Constants.WEBVIEW_NAME);

        tvBassTitle.setText(name);

        //播放视频设置
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//（这个对宿主没什么影响，建议声明）
        x5Webview.getSettings().setJavaScriptEnabled(true);// 支持js
        x5Webview.setWebViewClient(new WebViewClient());//防止加载网页时调起系统浏览器

        initHardwareAccelerate();
        x5Webview.loadUrl(url);
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 启用硬件加速
     */
    private void initHardwareAccelerate() {
        try {
            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
                getWindow()
                        .setFlags(
                                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (x5Webview != null && x5Webview.canGoBack()) {
                x5Webview.goBack();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        //释放资源 如没有此操作，可能会出现，当你在网页上播放一个视频的时候，直接按home键退出应用，视频仍在播放
        if (x5Webview != null)
            x5Webview.destroy();
        super.onDestroy();
    }
}
