package com.mfzn.deepuses.activitymy.setting;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepuses.AppManager;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.login.LoginActivity;
import com.mfzn.deepuses.activity.login.SettingNicknameActivity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.utils.CleanDataUtils;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlertDialog;
import com.wevey.selector.dialog.ProjectConfirmDialog;
import com.wevey.selector.dialog.WeixinTishiDialog;
import com.wevey.selector.dialog.WeixinTishiDialog1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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

        tvSetTeam.setText(UserHelper.getCompanyName());

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if(eventMsg.getMsg().equals(Constants.COMPANY_NAME)){
                        tvSetTeam.setText(UserHelper.getCompanyName());
                    }
                }
            }
        });
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
                startActivity(new Intent(this, SwitchCompanyActivity.class));
                break;
            case R.id.ll_set_banben:
                startActivity(new Intent(this, EditionInfoActivity.class));
                break;
            case R.id.ll_set_hc:
                new WeixinTishiDialog1.Builder(this)
                        .setHeight(1f)  //屏幕高度*0.23
                        .setWidth(1f)  //屏幕宽度*0.65
                        .setContentText("确定要清除缓存吗?")
                        .setTitle("提示")
                        .setCanceledOnTouchOutside(false)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<WeixinTishiDialog1>() {
                            @Override
                            public void clickLeftButton(WeixinTishiDialog1 dialog, View view) {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickRightButton(WeixinTishiDialog1 dialog, View view) {
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
                UserHelper.logout();
                UserHelper.setOut(true);
                AppManager.getInstance().finishAllActivity();
                Router.newIntent(this).to(LoginActivity.class).launch();
                finish();
                break;
        }
    }
}
