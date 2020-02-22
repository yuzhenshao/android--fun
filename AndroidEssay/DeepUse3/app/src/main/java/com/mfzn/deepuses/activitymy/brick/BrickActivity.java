package com.mfzn.deepuses.activitymy.brick;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.brick.CompanyInfoModel;
import com.mfzn.deepuses.present.brick.BrickPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.x5webview.X5WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class BrickActivity extends BaseMvpActivity<BrickPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_content)
    TextView tvBassContent;
    @BindView(R.id.tv_br_title)
    TextView tvBrTitle;
    @BindView(R.id.iv_br_type)
    ImageView ivBrType;
    @BindView(R.id.tv_br_money)
    TextView tvBrMoney;
    @BindView(R.id.x5_webview)
    X5WebView x5Webview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_brick;
    }

    @Override
    public BrickPresnet newP() {
        return new BrickPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_brick));
        tvBassContent.setText("交易记录");
        tvBassContent.setTextColor(getResources().getColor(R.color.color_3D7EFF));
        tvBassContent.setVisibility(View.VISIBLE);

        getP().getCompanyInfo();

        //播放视频设置
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//（这个对宿主没什么影响，建议声明）
        x5Webview.getSettings().setJavaScriptEnabled(true);// 支持js
        x5Webview.setWebViewClient(new WebViewClient());//防止加载网页时调起系统浏览器

        initHardwareAccelerate();
        x5Webview.loadUrl("https://yzs.mfzn.com.cn/mp/level/");

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if(eventMsg.getMsg().equals(Constants.PAYMENT)){
                        getP().getCompanyInfo();
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_content, R.id.ll_br_cz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_content:
                startActivity(new Intent(this, TransactionRecordActivity.class));
                break;
            case R.id.ll_br_cz:
                startActivity(new Intent(this, RechargeActivity.class));
                break;
        }
    }

    public void getCompanySuccess(CompanyInfoModel model) {
        tvBrTitle.setText(model.getCompanyName());
        int zhuan = (int) Double.parseDouble(model.getZhuan());
        int zhuan2 = (int) Double.parseDouble(model.getGiftZhuan());
        int sumZhuan = zhuan + zhuan2;
        tvBrMoney.setText(String.valueOf(sumZhuan));
        int companyLevel = model.getCompanyLevel();
        if(companyLevel == 1) {
            ivBrType.setImageResource(R.mipmap.br_vip1);
        }else if(companyLevel == 2) {
            ivBrType.setImageResource(R.mipmap.br_vip2);
        }else if(companyLevel == 3) {
            ivBrType.setImageResource(R.mipmap.br_vip3);
        }else if(companyLevel == 4) {
            ivBrType.setImageResource(R.mipmap.br_vip4);
        }else if(companyLevel == 5) {
            ivBrType.setImageResource(R.mipmap.br_vip5);
        }else if(companyLevel == 6) {
            ivBrType.setImageResource(R.mipmap.br_vip6);
        }
    }

    /**
     * 启用硬件加速
     */
    private void initHardwareAccelerate() {
        try {
            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
                getWindow()
                        .setFlags(
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
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
