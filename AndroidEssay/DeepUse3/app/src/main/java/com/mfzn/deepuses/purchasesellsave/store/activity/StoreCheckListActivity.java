package com.mfzn.deepuses.purchasesellsave.store.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.khgl.SelectTypeActivity;
import com.mfzn.deepuses.bass.BasicListActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.store.StoreAllCheckListResponse;
import com.mfzn.deepuses.bean.response.store.StoreCheckResponse;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.adapter.SupplierAdapter;
import com.mfzn.deepuses.purchasesellsave.store.adapter.StoreCheckAdapter;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ObtainTime;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import cn.droidlover.xdroidmvp.router.Router;

public class StoreCheckListActivity extends BasicListActivity<StoreCheckResponse> {
    private String shopId;
    private static int REQUESTCODE = 1001;
    @BindView(R.id.serach_edit)
    EditText serachEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serachEdit.setHint("搜索单据编号、商品名称、货号、编码");
        mTitleBar.updateTitleBar("盘点", R.mipmap.icon_titlebar_add);
        shopId = getIntent().getStringExtra(ParameterConstant.SHOP_ID);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_check_list;
    }


    @Override
    protected void getResourceList() {
        showDialog();
        ApiServiceManager.getOrderStockCheckList(shopId, "", "0", -1)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult<StoreAllCheckListResponse>>() {
                    @Override
                    protected void onFail(NetError error) {
                        showErrorView(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<StoreAllCheckListResponse> reuslt) {
                        StoreAllCheckListResponse response = reuslt.getRes();
                        if (response != null) {
                            if (response.getData() != null) {
                                refreshSource(response.getData());
                                return;
                            }
                        }
                        showNoDataView();
                    }
                });
    }

    @Override
    protected BaseQuickAdapter getAdapter() {
        StoreCheckAdapter mAdapter = new StoreCheckAdapter(this, mSourceList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int i) {

            }
        });
        return mAdapter;
    }

    @OnClick({R.id.filter, R.id.search_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.filter:
                Router.newIntent(this).to(SelectTypeActivity.class)
                        .requestCode(Constants.SELECT_BC)
                        .anim(R.anim.pay_dialog_enter, R.anim.pay_dialog_exit)
                        .launch();
                break;
            case R.id.search_container:
                break;
        }
    }

    protected void rightPressed() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && requestCode == RESULT_OK) {
            getResourceList();
        }
    }
}
