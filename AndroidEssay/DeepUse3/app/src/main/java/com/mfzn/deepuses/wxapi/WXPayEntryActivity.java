package com.mfzn.deepuses.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.mfzn.deepuses.BaseApplication;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.sharesdk.wechat.utils.WXAppExtendObject;
import cn.sharesdk.wechat.utils.WXMediaMessage;
import cn.sharesdk.wechat.utils.WechatHandlerActivity;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, BaseApplication.APP_ID);
        api.handleIntent(getIntent(), this);
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
//        LogUtil.d("onPayFinish, errCode = " + baseResp.errCode + ", errStr = " + baseResp.errStr);
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (baseResp.errCode == 0) {
                Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
//                ToastUtils.shortToast("支付成功");
                // 支付
//                if (SharePreUtil.getBoolean("isWxPay", false)) {
//                    SharePreUtil.putBoolean("isWxPay", false);
//                    SharedPref.getInstance(BaseApplication.getContext()).putBoolean("isWxPaySuccess", true);
//                }
                SharedPref.getInstance(BaseApplication.getContext()).putBoolean("isWxPaySuccess", true);
            } else {
                Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
//                ToastUtils.shortToast("支付失败");
            }
        }
    }
}