package com.mfzn.deepusesSer.activitymy.setting;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activity.login.LoginActivity;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.utils.CleanDataUtils;
import com.mfzn.deepusesSer.utils.ToastUtil;
import com.mfzn.deepusesSer.utils.UserHelper;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlertDialog;
import com.wevey.selector.dialog.NormalTishiDialog;
import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import cn.jpush.android.api.JPushInterface;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_set_team)
    TextView tvSetTeam;
    @BindView(R.id.tv_set_banben)
    TextView tvSetBanben;
    @BindView(R.id.tv_set_hc)
    TextView tvSetHc;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
         tvBassTitle.setText(getString(R.string.app_setting));
         try {
             tvSetHc.setText(CleanDataUtils.getTotalCacheSize(SettingActivity.this));
         }catch (Exception e){

         }
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;

            tvSetBanben.setText("当前版本：" + versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.iv_login_back, R.id.ll_set_info, R.id.ll_set_account, R.id.ll_set_pwd, R.id.ll_set_news,
            R.id.ll_set_team, R.id.ll_set_banben, R.id.ll_set_hc,R.id.tv_set_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_set_info:
                startActivity(new Intent(this, PersonInfoActivity.class));
                break;
            case R.id.ll_set_account:
                startActivity(new Intent(this, AccountSettingActivity.class));
                break;
            case R.id.ll_set_pwd:
                NormalAlertDialog normalAlertDialog = new NormalAlertDialog.Builder(this)
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
                                if (pwd.equals(UserHelper.getUpwd())){
                                    Intent intent = new Intent(SettingActivity.this, SettingNewsPwdActivity.class);
                                    startActivity(intent);
                                    dialog.dismiss();
                                }else{
                                    ToastUtil.showToast(SettingActivity.this,"密码错误，请重新输入");
                                }
                            }
                        })
                        .build();
                normalAlertDialog.show();
                break;
            case R.id.ll_set_news:
                startActivity(new Intent(this, NewsSettingActivity.class));
                break;
            case R.id.ll_set_team:
                break;
            case R.id.ll_set_banben:
//                startActivity(new Intent(this, EditionInfoActivity.class));
                break;
            case R.id.ll_set_hc:
                new NormalTishiDialog.Builder(this)
                        .setHeight(1f)  //屏幕高度*0.23
                        .setWidth(1f)  //屏幕宽度*0.65
                        .setContentText("确定要清除缓存吗?")
                        .setTitle("提示")
                        .setCanceledOnTouchOutside(false)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalTishiDialog>() {
                            @Override
                            public void clickLeftButton(NormalTishiDialog dialog, View view) {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickRightButton(NormalTishiDialog dialog, View view) {
                                dialog.dismiss();
                                try {
                                    CleanDataUtils.clearAllCache(SettingActivity.this);
                                    tvSetHc.setText(CleanDataUtils.getTotalCacheSize(SettingActivity.this));
                                }catch (Exception e){

                                }
                            }
                        })
                        .build()
                        .show();

                break;
            case R.id.tv_set_out:
                JPushInterface.deleteAlias(context,0);
                UserHelper.logout();
                UserHelper.setOut(true);
                Router.newIntent(this).to(LoginActivity.class).launch();
                finish();
                break;
        }
    }
}
