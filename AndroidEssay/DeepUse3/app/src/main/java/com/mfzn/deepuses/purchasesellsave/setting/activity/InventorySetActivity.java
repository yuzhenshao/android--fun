package com.mfzn.deepuses.purchasesellsave.setting.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.libcommon.dialog.fragment.CustomDialog;
import com.libcommon.titlebar.TitleBar;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yz @date 2020-03-30
 */
public class InventorySetActivity extends BaseActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitleBar;
    @BindView(R.id.store_number)
    TextView mStoreNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        mTitleBar.updateTitleBar("设置库存");
    }

    @OnClick(R.id.setting_store)
    public void setStoreNumber() {
        new CustomDialog.Builder().setLayoutRes(R.layout.dialog_set_inventory)
                .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.BOTTOM)
                .setDialogAnimationRes(R.style.ActionSheetDialogAnimation)
                .addOnClickListener(R.id.btn_commit)
                .setOnViewClickListener((customDialog, bindViewHolder, view) -> {
                    EditText editText = bindViewHolder.getView(R.id.store_number_edit);
                    if (TextUtils.isEmpty(editText.getText())) {
                        ToastUtil.showToast(InventorySetActivity.this, "请输入库存");
                    } else {
                        mStoreNumber.setText(editText.getText());
                        if (customDialog != null) {
                            customDialog.dismiss();
                        }
                    }

                }).create().show(getSupportFragmentManager(), getClass().getName());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_store;
    }
}
