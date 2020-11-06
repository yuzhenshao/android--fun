package com.mfzn.deepuses.purchasesellsave.shop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.setting.SetUserAuthRequest;
import com.mfzn.deepuses.bean.response.shop.UserAuthResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class ShopStaffAuthActivity extends BasicActivity {

    private String userId;
    private UserAuthResponse mUserAuthResponse;
    @BindView(R.id.staff_auth_container)
    LinearLayout staffAuthContainer;
    @BindView(R.id.discount_start)
    EditText discountStart;

    List<UserAuthResponse.AuthSons> normalAuths = new ArrayList<>();
    List<UserAuthResponse.StoreAuthReponse> storeAuthReponseList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_staff_auth;
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
                        if (mUserAuthResponse == null) {
                            showToast(result.getMsg());
                            finish();
                        } else {
                            initAuthData();
                            initView();
                        }
                    }
                });
    }

    private void initAuthData() {
        if (!ListUtil.isEmpty(mUserAuthResponse.getNormalAuth())) {
            normalAuths.clear();
            for (UserAuthResponse.AuthSons sons : mUserAuthResponse.getNormalAuth()) {
                getAuthSonChild(normalAuths, sons);
            }
        }
        if (!ListUtil.isEmpty(mUserAuthResponse.getStoreAuth())) {
            storeAuthReponseList.clear();
            for (UserAuthResponse.StoreAuthReponse store : mUserAuthResponse.getStoreAuth()) {
                if (!ListUtil.isEmpty(store.getStoreAuth())) {
                    List<UserAuthResponse.AuthSons> result = new ArrayList<>();
                    for (UserAuthResponse.AuthSons sons : store.getStoreAuth()) {
                        getAuthSonChild(result, sons);
                    }
                    store.setStoreAuth(result);
                    storeAuthReponseList.add(store);
                }
            }
        }
    }

    private void getAuthSonChild(List<UserAuthResponse.AuthSons> result, UserAuthResponse.AuthSons authSon) {
        if (!ListUtil.isEmpty(authSon.getSons())) {
            for (UserAuthResponse.AuthSons auth : authSon.getSons()) {
                if (ListUtil.isEmpty(auth.getSons())) {
                    result.add(auth);
                } else {
                    getAuthSonChild(result, auth);
                }
            }
        }
    }


    private void initView() {
        if (!ListUtil.isEmpty(normalAuths)) {
            for (UserAuthResponse.AuthSons sons : normalAuths) {
                staffAuthContainer.addView(getIntemView(sons));//开关view
            }
        }

        if (!ListUtil.isEmpty(mUserAuthResponse.getShopIDs())) {
            staffAuthContainer.addView(getIntemNameView("门店权限"));//门店权限
            for (UserAuthResponse.ShopIDsResponse shopIDsResponse : mUserAuthResponse.getShopIDs()) {
                staffAuthContainer.addView(getIntemView(shopIDsResponse));////开关view
            }
        }

        if (!ListUtil.isEmpty(storeAuthReponseList)) {
            for (UserAuthResponse.StoreAuthReponse store : storeAuthReponseList) {
                staffAuthContainer.addView(getIntemNameView(store.getStoreName()));//store name
                for (UserAuthResponse.AuthSons authSons : store.getStoreAuth()) {
                    staffAuthContainer.addView(getIntemView(authSons));//开关view
                }
            }
        }

    }

    private View getIntemNameView(String storeName) {
        View view = View.inflate(context, R.layout.active_name_item, null);
        TextView nameView = view.findViewById(R.id.store_name);
        nameView.setText(storeName);
        return view;
    }

    private View getIntemView(UserAuthResponse.AuthSons authSons) {
        View view = View.inflate(context, R.layout.active_item, null);
        TextView nameView = view.findViewById(R.id.name);
        ImageView switchBtn = view.findViewById(R.id.switch_button);
        nameView.setText(authSons.getAuthDescription());
        switchBtn.setImageResource(authSons.getIsActive() == 1 ? R.mipmap.icon_switch_on : R.mipmap.icon_switch_off);
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authSons.setIsActive(authSons.getIsActive() == 0 ? 1 : 0);
                switchBtn.setImageResource(authSons.getIsActive() == 1 ? R.mipmap.icon_switch_on : R.mipmap.icon_switch_off);
            }
        });
        return view;
    }

    private View getIntemView(UserAuthResponse.ShopIDsResponse shopIDsResponse) {
        View view = View.inflate(context, R.layout.active_item, null);
        TextView nameView = view.findViewById(R.id.name);
        ImageView switchBtn = view.findViewById(R.id.switch_button);
        nameView.setText(shopIDsResponse.getShopName());
        switchBtn.setImageResource(shopIDsResponse.getIsActive() == 1 ? R.mipmap.icon_switch_on : R.mipmap.icon_switch_off);
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopIDsResponse.setIsActive(shopIDsResponse.getIsActive() == 0 ? 1 : 0);
                switchBtn.setImageResource(shopIDsResponse.getIsActive() == 1 ? R.mipmap.icon_switch_on : R.mipmap.icon_switch_off);
            }
        });
        return view;
    }


    /*
    * normalAuth常规权限，英文逗号隔开，示例：权限ID1,权限ID2 3,4,6
shopIDs 是可操作的门店IDs，英文逗号隔开，示例：门店ID1,门店ID2 1
storeAuth  仓库权限，示例：仓库ID1:权限ID1,权限ID2;仓库ID2:权限ID1
1:21,24;2:21
    * */

    @OnClick(R.id.btn_commit)
    public void saveSetAuth() {
        showDialog();
        SetUserAuthRequest request = new SetUserAuthRequest();
        request.setCompanyID(UserHelper.getCompanyId());
        request.setUserID(userId);
        request.setDiscountStart(discountStart.getText().toString());

        String normalAuth = "";
        if (!ListUtil.isEmpty(normalAuths)) {
            for (UserAuthResponse.AuthSons authSons : normalAuths) {
                if (authSons.getIsActive() == 1) {
                    if (TextUtils.isEmpty(normalAuth)) {
                        normalAuth += authSons.getAuthID();
                    } else {
                        normalAuth += ("," + authSons.getAuthID());
                    }
                }
            }
        }
        request.setNormalAuth(normalAuth);

        String shopIDs = "";
        if (!ListUtil.isEmpty(mUserAuthResponse.getShopIDs())) {
            for (UserAuthResponse.ShopIDsResponse shopIDsResponse : mUserAuthResponse.getShopIDs()) {
                if (shopIDsResponse.getIsActive() == 1) {
                    if (TextUtils.isEmpty(shopIDs)) {
                        shopIDs += shopIDsResponse.getShopID();
                    } else {
                        shopIDs += (","+ shopIDsResponse.getShopID());
                    }
                }
            }
        }
        request.setShopIDs(shopIDs);

        String storeAuth = "";
        if (!ListUtil.isEmpty(storeAuthReponseList)) {
            for (UserAuthResponse.StoreAuthReponse storeAuthReponse : storeAuthReponseList) {
                if (!ListUtil.isEmpty(storeAuthReponse.getStoreAuth())) {
                    String storeAuthSons = "";
                    for (UserAuthResponse.AuthSons authSons : storeAuthReponse.getStoreAuth()) {
                        if (authSons.getIsActive() == 1) {
                            if (TextUtils.isEmpty(storeAuthSons)) {
                                storeAuthSons += authSons.getAuthID();
                            } else {
                                storeAuthSons += ("," + authSons.getAuthID());
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(storeAuthSons)) {
                        storeAuth += storeAuthReponse.getStoreID() + ":" + storeAuthSons + ";";
                    }
                }
            }
        }
        request.setStoreAuth(storeAuth);

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
                        showToast(result.getMsg());
                        finish();
                    }
                });
    }

}
