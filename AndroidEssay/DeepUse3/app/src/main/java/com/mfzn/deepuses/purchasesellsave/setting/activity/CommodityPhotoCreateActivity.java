package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.libcommon.titlebar.TitleBar;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BasicActivity;
import com.mfzn.deepuses.bean.request.CommodityRequest;
import com.mfzn.deepuses.databinding.ActivityCommodityCreateBinding;
import com.mfzn.deepuses.purchasesellsave.setting.view.AddImageView;

import butterknife.BindView;

/**
 * @author syz @date 2020-03-26
 */
public class CommodityPhotoCreateActivity extends BasicActivity implements View.OnClickListener {

    @BindView(R.id.add_image_view)
    AddImageView mAddImageView;

    private CommodityRequest mRequest;

    private ActivityCommodityCreateBinding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void setContentViewId() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    private void init() {
        mTitleBar.updateTitleBar("商品录入");
        dataBinding.setOnClickListener(this::onClick);
        mRequest = new CommodityRequest();
        dataBinding.setMCommodity(mRequest);
        mAddImageView.init(this,"商品图片");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAddImageView.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_create;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.type_contaienr:
//                break;
//            case R.id.but_bu_commit:
//                ToastUtil.showToast(this, mRequest.getCatName());
//                break;
        }
    }
}
