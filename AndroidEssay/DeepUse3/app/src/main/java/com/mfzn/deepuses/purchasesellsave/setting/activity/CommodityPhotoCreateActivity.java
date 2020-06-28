package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.net.ImageUploadManager;
import com.mfzn.deepuses.purchasesellsave.manager.JXCDataManager;
import com.mfzn.deepuses.purchasesellsave.setting.view.AddImageView;
import com.mfzn.deepuses.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author yz @date 2020-03-26
 */
public class CommodityPhotoCreateActivity extends BasicActivity {

    @BindView(R.id.add_image_view)
    AddImageView mAddImageView;

    @BindView(R.id.goods_name)
    EditText goodsName;
    @BindView(R.id.goods_unit)
    EditText unitEdit;
    @BindView(R.id.goods_num)
    EditText goodsNum;
    @BindView(R.id.store_container)
    TextView storeSet;
    protected CommodityRequest mRequest;

    private static int UNIT_CODE = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_ffffff));
        }
        mTitleBar.updateTitleBar("商品快速录入");
        mTitleBar.setStatusBarHide();
        mRequest = new CommodityRequest();
        mAddImageView.init(this, "商品图片");
    }

    @Override
    protected void onResume() {
        super.onResume();
        storeSet.setText(ListUtil.isEmpty(JXCDataManager.getInstance().getStoreModels()) ? "去设置" : "已设置");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAddImageView.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == UNIT_CODE) {
                mRequest.setGoodsUnitID(data.getStringExtra("Id"));
                unitEdit.setText(data.getStringExtra("Name"));
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_photo_create;
    }

    @OnClick({R.id.unit_container, R.id.btn_commit, R.id.store_container})
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.unit_container:
                intent.setClass(this, GoodsUnitListActivity.class);
                startActivityForResult(intent, UNIT_CODE);
                break;

            case R.id.store_container:
                intent.setClass(this, InventorySetActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_commit:
                initGoodsRequest();
                if (TextUtils.isEmpty(mRequest.getGoodsName())) {
                    showToast("请输入产品名称");
                    return;
                }

                if (TextUtils.isEmpty(mRequest.getGoodsUnitID())) {
                    showToast("请输入产品单位");
                    return;
                }
                if (TextUtils.isEmpty(mRequest.getGoodsNum())) {
                    showToast("请输入产品货号");
                    return;
                }
                if (ListUtil.isEmpty(mAddImageView.getBitmapFiles())) {
                    submit();
                } else {
                    mAddImageView.upLoadFile(new ImageUploadManager.ImageUploadCallback() {
                        @Override
                        public void uploadSuccess(String url) {
                            mRequest.setGoodsImages(url);
                            submit();
                        }

                        @Override
                        public void uoloadFailed(String error) {

                        }
                    });
                }
        }
    }

    protected void initGoodsRequest() {
        mRequest.setGoodsName(goodsName.getText().toString());
        mRequest.setStoreStockNum(JXCDataManager.getInstance().getStoreStockNum());
    }


    protected void submit() {
        ApiServiceManager.addGoods(mRequest)
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .compose(bindToLifecycle())
                .subscribe(new ApiSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(CommodityPhotoCreateActivity.this, error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult reuslt) {
                        ToastUtil.showToast(CommodityPhotoCreateActivity.this, "成功");
                        finish();
                    }
                });
    }

    @Override
    public void finish() {
       super.finish();
       JXCDataManager.getInstance().clearStore();
    }

}
