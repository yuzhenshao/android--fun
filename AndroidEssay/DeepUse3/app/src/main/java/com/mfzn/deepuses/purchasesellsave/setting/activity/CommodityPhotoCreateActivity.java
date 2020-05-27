package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.libcommon.titlebar.TitleBar;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.databinding.ActivityCommodityPhotoCreateBinding;
import com.mfzn.deepuses.net.ApiServiceManager;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.purchasesellsave.setting.view.AddImageView;
import com.mfzn.deepuses.utils.ToastUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author yz @date 2020-03-26
 */
public class CommodityPhotoCreateActivity extends BasicActivity implements View.OnClickListener {

    @BindView(R.id.add_image_view)
    AddImageView mAddImageView;

    @BindView(R.id.goods_name)
    EditText goodsName;
    @BindView(R.id.goods_unit)
    EditText unitEdit;
    @BindView(R.id.goods_position)
    EditText goodsPosition;

    private CommodityRequest mRequest;


    private static int CATEGORY_CODE = 100;
    private static int UNIT_CODE = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    private void init() {
        mTitleBar.updateTitleBar("商品快速录入");
        mRequest = new CommodityRequest();
        mAddImageView.init(this, "商品图片");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAddImageView.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CATEGORY_CODE) {
                mRequest.setGoodsCatID(data.getStringExtra(""));
            } else if (requestCode == UNIT_CODE) {
                mRequest.setGoodsUnitID(data.getStringExtra(""));
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_photo_create;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.unit_id_container:
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                intent.setClass(CommodityPhotoCreateActivity.this, GoodsCategoryActivity.class);
                startActivityForResult(intent, CATEGORY_CODE);
                break;
            case R.id.position_container:
                intent.putExtra(ParameterConstant.IS_SELECTED, true);
                intent.setClass(CommodityPhotoCreateActivity.this, GoodsUnitListActivity.class);
                startActivityForResult(intent, UNIT_CODE);
                break;
            case R.id.but_bu_commit:

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
                            }
                        });
                break;
        }
    }

    private void initGoodsRequest(){
        mRequest.setGoodsName(goodsName.getText().toString());
        mRequest.setGoodsPosition(goodsPosition.getText().toString());
    }
}
