package com.mfzn.deepuses.purchasesellsave.shop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.setting.SetUserAuthRequest;
import com.mfzn.deepuses.bean.response.shop.UserAuthResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShopStaffAuthActivity extends BasicActivity {

    private String userId;
    private UserAuthResponse mUserAuthResponse;

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleBar.updateTitleBar("权限");
        userId = getIntent().getStringExtra(ParameterConstant.SHOP_STAFF_ID);
        ApiServiceManager.getUserAuth(userId)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<UserAuthResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showToast(error.getMessage());
                        finish();
                    }

                    @Override
                    public void onNext(HttpResult<UserAuthResponse> result) {
                        mUserAuthResponse = result.getRes();
                        initView();
                    }
                });
    }

    private void initView() {

    }

    protected void leftPressedAction() {
        saveSetAuth();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            saveSetAuth();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }


    /*
    * normalAuth常规权限，英文逗号隔开，示例：权限ID1,权限ID2 3,4,6
shopIDs 是可操作的门店IDs，英文逗号隔开，示例：门店ID1,门店ID2 1
storeAuth  仓库权限，示例：仓库ID1:权限ID1,权限ID2;仓库ID2:权限ID1
1:21,24;2:21
    * */

    private void saveSetAuth() {
        showDialog();
        SetUserAuthRequest request = new SetUserAuthRequest();
        request.setCompanyID(UserHelper.getCompanyId());
        request.setUserID(userId);
        request.setDiscountStart(mUserAuthResponse.getDiscountStart());
        //TODO
        ApiServiceManager.setUserAuth(request)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        hideDialog();
                        showToast(error.getMessage());

                    }

                    @Override
                    public void onNext(HttpResult result) {
                        hideDialog();
                        finish();
                    }
                });
    }

}
