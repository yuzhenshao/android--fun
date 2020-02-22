package com.mfzn.deepuses.activitymy.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountSettingActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_acc_phone)
    TextView tvAccPhone;
//    @BindView(R.id.tv_acc_wb)
//    TextView tvAccWb;
//    @BindView(R.id.tv_acc_qq)
//    TextView tvAccQq;
//    @BindView(R.id.tv_acc_ali)
//    TextView tvAccAli;

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_setting;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_account_setting));
    }

    @OnClick({R.id.iv_login_back, R.id.ll_acc_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_acc_phone:
                new NormalAlertDialog.Builder(this)
                        .setHeight(0.3f)  //屏幕高度*0.23
                        .setWidth(0.85f)  //屏幕宽度*0.65
                        .setCanceledOnTouchOutside(true)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlertDialog>() {
                            @Override
                            public void clickLeftButton(NormalAlertDialog dialog, View view) {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickRightButton(NormalAlertDialog dialog, View view) {
                                String pwd = dialog.getmContent();
                                if (pwd.equals(UserHelper.getUpwd())) {
                                    startActivity(new Intent(AccountSettingActivity.this, ReplacePhoneActivity.class));
                                } else {
                                    ToastUtil.showToast(AccountSettingActivity.this, "密码错误，请重新输入");
                                }
                            }
                        })
                        .build()
                        .show();
                break;
//            case R.id.ll_acc_wb:
//                break;
//            case R.id.ll_acc_qq:
//                break;
//            case R.id.ll_acc_ali:
//                break;
        }
    }
}
