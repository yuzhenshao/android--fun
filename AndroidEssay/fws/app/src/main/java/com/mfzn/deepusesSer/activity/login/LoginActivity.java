package com.mfzn.deepusesSer.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mfzn.deepusesSer.AppManager;
import com.mfzn.deepusesSer.MainActivity;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.login.UserModel;
import com.mfzn.deepusesSer.present.login.LoginPresent;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;
import com.mfzn.deepusesSer.utils.UserHelper;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

public class LoginActivity extends BaseMvpActivity<LoginPresent> {

    @BindView(R.id.tv_login_title)
    TextView tvLoginTitle;
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.tv_login_phoen_err)
    TextView tvLoginPhoenErr;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.iv_login_eye)
    ImageButton ivLoginEye;
    @BindView(R.id.tv_login_pwd_err)
    TextView tvLoginPwdErr;
    @BindView(R.id.but_login)
    Button butLogin;

    private boolean eyeType = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresent newP() {
        return new LoginPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        setStatusBarMode(this);//改变顶部系统文字的颜色  黑色  安卓6.0以上才有效果

        tvLoginTitle.getPaint().setFakeBoldText(true);

        if(!TextUtils.isEmpty(UserHelper.getU_phone())) {
            Router.newIntent(context).to(MainActivity.class).launch();
        }

        etLoginPhone.addTextChangedListener(mOnInputChangeListener);
        etLoginPwd.addTextChangedListener(mOnInputChangeListener);
    }

    @OnClick({R.id.iv_login_eye, R.id.tv_login_forgot, R.id.but_login, R.id.tv_login_regi, R.id.iv_login_qq,
            R.id.iv_login_wx, R.id.iv_login_ali})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_eye:
                if(eyeType) {
                    ivLoginEye.setImageResource(R.mipmap.pwd_close);
                    eyeType = false;
                    etLoginPwd.setInputType(129);
                }else {
                    ivLoginEye.setImageResource(R.mipmap.pwd_open);
                    eyeType = true;
                    etLoginPwd.setInputType(128);
                }
                break;
            case R.id.tv_login_forgot:
                startActivity(new Intent(this, ForgotPwdActivity.class));
                break;
            case R.id.but_login:
                String phone = etLoginPhone.getText().toString().trim();
                String pwd = etLoginPwd.getText().toString().trim();
                getP().login(phone,pwd);
                break;
            case R.id.tv_login_regi:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.iv_login_qq:
                break;
            case R.id.iv_login_wx:
                break;
            case R.id.iv_login_ali:
                break;
        }
    }

    private OnInputChangeListener mOnInputChangeListener = new OnInputChangeListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(etLoginPhone.getText().toString().trim()) &&
                    !TextUtils.isEmpty(etLoginPwd.getText().toString().trim())) {
                butLogin.setEnabled(true);
                butLogin.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
            } else {
                butLogin.setEnabled(false);
                butLogin.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
            }
        }
    };

    public void loginSuccess(UserModel userModel) {
        String pwd = etLoginPwd.getText().toString().trim();
        UserHelper.login(userModel,pwd);
        UserHelper.setOut(false);
//        //保存登录信息
//        LoginStorageUtils.loginStorage(userModel.getU_phone(), et_user_pwd.getText().toString().trim(), userModel.getU_head());
//
//        BusProvider.getBus().post(new UserEvent());
        mHandler.postDelayed(() -> {
//            String companyId = UserHelper.getCompanyId();
//            if(!TextUtils.isEmpty(companyId)) {
//                Router.newIntent(context).to(MainActivity.class).launch();
//            }else {
//                Router.newIntent(context).to(SelectCompanyActivity.class).launch();
//            }
            Router.newIntent(context).to(MainActivity.class).launch();
            finish();
        }, 1500);
    }

    //点击返回键返回到桌面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            Intent home = new Intent(Intent.ACTION_MAIN);
//            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            home.addCategory(Intent.CATEGORY_HOME);
//            startActivity(home);
            AppManager.getInstance().appExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
