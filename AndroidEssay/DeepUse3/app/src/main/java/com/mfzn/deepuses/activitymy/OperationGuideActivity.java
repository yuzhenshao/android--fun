package com.mfzn.deepuses.activitymy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.mfzn.deepuses.AppManager;
import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.fx.ZixunDetailActivity;
import com.mfzn.deepuses.adapter.faxian.CommentExpandAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.CommentExpandableListView;
import com.qmuiteam.qmui.widget.webview.QMUIWebView;
import com.qmuiteam.qmui.widget.webview.QMUIWebViewClient;
import com.weavey.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.utils.ScreenUtils;

public class OperationGuideActivity extends BaseActivity {

    @BindView(R.id.wb_view)
    QMUIWebView webView;

    private int czzn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_operation_guide;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        czzn = getIntent().getIntExtra("czzn",1);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(false);
        webView.getSettings().setLoadWithOverviewMode(false);
//        webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);

        webView.loadUrl("https://yzs.mfzn.com.cn/mp/operation_guide_v2/");

        webView.setWebViewClient(new QMUIWebViewClient(true,true){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //修改图片大小
                int screenWidth = ScreenUtils.getScreenSize(OperationGuideActivity.this).x;
                String width = String.valueOf(screenWidth- UiUtils.dp2px(OperationGuideActivity.this,50));
                String width2=String.valueOf(UiUtils.px2dip(OperationGuideActivity.this,screenWidth)-40);

                String javascript = "javascript:function ResizeImages() {" +
                        "var myimg,oldwidth;" +
                        "var maxwidth = document.body.clientWidth;" +
                        "for(i=0;i <document.images.length;i++){" +
                        "myimg = document.images[i];" +
                        "if(myimg.width > "+width2+"){" +
                        "oldwidth = myimg.width;" +
                        "myimg.width ="+width2+";" +
                        "}" +
                        "}" +
                        "}";
                view.loadUrl(javascript);
                view.loadUrl("javascript:ResizeImages();");
            }
        });
    }

    @OnClick({R.id.but_skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but_skip:
                if(czzn == 1) {
                    UserHelper.setCzzn(true);
                    startActivity(new Intent(this, MainActivity.class));
                }else {
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(czzn == 1) {
                UserHelper.setCzzn(true);
                startActivity(new Intent(this, MainActivity.class));
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
