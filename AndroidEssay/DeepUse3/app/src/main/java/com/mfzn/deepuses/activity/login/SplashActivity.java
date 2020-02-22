package com.mfzn.deepuses.activity.login;

import android.Manifest;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.company.SelectCompanyActivity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.utils.FullScreen;
import com.mfzn.deepuses.utils.UserHelper;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseActivity {

//    @BindView(R.id.tv_splash_second)
//    TextView tvSplashSecond;
    private boolean isFirstUse;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        FullScreen.setStatusBarMode(this);
        isFirstUse = SharedPref.getInstance(context).getBoolean("isFirstUse", true);
        getPerssion();
    }

    private void getPerssion() {
        getRxPermissions()
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (isFirstUse) {
                                    Router.newIntent(context).to(GuideActivity.class).launch();
                                    finish();
                                } else {
                                    //首页或者登陆页
                                    if (UserHelper.getOut()) {
                                        Router.newIntent(context).to(LoginActivity.class).launch();
                                        finish();
                                    } else {
                                        if(!TextUtils.isEmpty(UserHelper.getU_phone())) {
                                            String companyId = UserHelper.getCompanyId();
                                            if(!TextUtils.isEmpty(companyId)) {
                                                Router.newIntent(context).to(MainActivity.class).launch();finish();
                                                finish();
                                            }else {
                                                Router.newIntent(context).to(SelectCompanyActivity.class).launch();
                                                finish();
                                            }
                                        }else {
                                            Router.newIntent(context).to(LoginActivity.class).launch();
                                            finish();
                                        }
                                    }
                                }
                            }
                        }, 2000);
//                        if (aBoolean) {
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
////                                    login();
//                                    if (isFirstUse) {
//                                        Router.newIntent(context).to(GuideActivity.class).launch();
//                                        finish();
//                                    } else {
//                                        //首页或者登陆页
//                                        if (UserHelper.getOut()) {
//                                            Router.newIntent(context).to(LoginActivity.class).launch();
//                                            finish();
//                                        } else {
//                                            Router.newIntent(context).to(MainActivity.class).launch();
//                                            finish();
//                                        }
//                                    }
//                                }
//                            }, 2000);
//                        } else {
//                            getPerssion();
//                            getvDelegate().toastShort("亲，同意了权限才能更好的使用软件哦");
//                        }
                    }
                });
    }
}
