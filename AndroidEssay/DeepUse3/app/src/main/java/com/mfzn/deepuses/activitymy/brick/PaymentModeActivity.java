package com.mfzn.deepuses.activitymy.brick;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.mfzn.deepuses.AppManager;
import com.mfzn.deepuses.BaseApplication;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.brick.AppWXPaymentModel;
import com.mfzn.deepuses.present.brick.PaymentModePresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.pay.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wevey.selector.dialog.TishiDialog;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.cache.SharedPref;

public class PaymentModeActivity extends BaseMvpActivity<PaymentModePresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.iv_pay_ali)
    ImageView ivPayAli;
    @BindView(R.id.iv_pay_wx)
    ImageView ivPayWx;
    @BindView(R.id.tv_pay_money)
    TextView tvPayMoney;

    private int payType = 1;// 1 支付宝  2  微信
    private String comboID;

    private static final int SDK_PAY_FLAG = 1;

    private int outType = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        outType = 2;
                        showPaySuccess(1, "支付成功");
                        EventMsg eventMsg = new EventMsg();
                        eventMsg.setMsg(Constants.PAYMENT);
                        RxBus.getInstance().post(eventMsg);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showPaySuccess(2, "支付失败");
                    }
                    break;
                }
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment_mode;
    }

    @Override
    public PaymentModePresent newP() {
        return new PaymentModePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_recharge));

        String money = getIntent().getStringExtra(Constants.PAY_MONEY);
        comboID = getIntent().getStringExtra(Constants.PAY_TYPE);
        tvPayMoney.setText("当前需要支付 " + money + " 元");
    }

    @OnClick({R.id.iv_login_back, R.id.ll_pay_ali, R.id.ll_pay_wx, R.id.but_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_pay_ali:
                payType = 1;
                ivPayAli.setImageResource(R.mipmap.regi_xuanzhong);
                ivPayWx.setImageResource(R.mipmap.regi_weixuan);
                break;
            case R.id.ll_pay_wx:
                payType = 2;
                ivPayWx.setImageResource(R.mipmap.regi_xuanzhong);
                ivPayAli.setImageResource(R.mipmap.regi_weixuan);
                break;
            case R.id.but_pay:
                if (payType == 1) {
                    getP().appAliPayment(comboID, payType + "");
                } else if (payType == 2) {
                    getP().appWXPayment(comboID, payType + "");
                }
                break;
        }
    }

    /**
     * 支付宝账户授权业务示例
     */
    public void appAliPay(String data) {
        Runnable authRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(PaymentModeActivity.this);
                Map<String, String> result = alipay.payV2(data, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    //微信
    public void appwxpaySuccess(AppWXPaymentModel entity) {
//        SharePreUtil.putBoolean("isWxPay", true);
        String appid = entity.getAppid();
        IWXAPI api = WXAPIFactory.createWXAPI(this, appid);
        api.registerApp(appid);
        PayReq req = new PayReq();
        req.appId = appid;
        req.partnerId = entity.getPartnerid();
        req.prepayId = entity.getPrepayid();
        req.nonceStr = entity.getNoncestr();
        req.timeStamp = entity.getTimestamp();
        req.packageValue = entity.getPackageX();
        req.sign = entity.getSign();
//        req.extData			= "app data"; // optional
//        Toast.makeText(this, "正常调起支付", Toast.LENGTH_SHORT).show();
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SharedPref.getInstance(BaseApplication.getContext()).getBoolean("isWxPaySuccess", false)) {
            SharedPref.getInstance(BaseApplication.getContext()).putBoolean("isWxPaySuccess", false);
//            paySuccess("微信");
            outType = 2;
            showPaySuccess(1, "支付成功");
            EventMsg eventMsg = new EventMsg();
            eventMsg.setMsg(Constants.PAYMENT);
            RxBus.getInstance().post(eventMsg);
        }
    }

    private void showPaySuccess(int type, String info) {
        new TishiDialog.Builder(this)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setContentText(info)
                .setbuttext("知道")
                .setCanceledOnTouchOutside(false)
                .setSingleListener(new com.wevey.selector.dialog.DialogInterface.OnSingleClickListener<TishiDialog>() {
                    @Override
                    public void clickSingleButton(TishiDialog dialog, View view) {
                        if (type == 1) {
                            dialog.dismiss();
                            finish();
                        } else {
                            dialog.dismiss();
                        }
                    }
                })
                .build()
                .show();
    }

    //点击返回键返回到桌面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(outType == 2) {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
