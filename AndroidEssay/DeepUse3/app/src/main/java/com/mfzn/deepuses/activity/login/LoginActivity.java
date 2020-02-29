package com.mfzn.deepuses.activity.login;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mfzn.deepuses.AppManager;
import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.company.EstablishCompanyActivity;
import com.mfzn.deepuses.activity.company.SelectCompanyActivity;
import com.mfzn.deepuses.activitymy.WebviewX5Activity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.present.login.LoginPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.UserHelper;

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
    @BindView(R.id.tv_agreement)
    TextView tv_agreement;
    @BindView(R.id.tv_privacy)
    TextView tv_privacy;
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
        tv_agreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_privacy.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线

        if (!TextUtils.isEmpty(UserHelper.getU_phone())) {
            String companyId = UserHelper.getCompanyId();
            if (!TextUtils.isEmpty(companyId)) {
                Router.newIntent(context).to(MainActivity.class).launch();
            } else {
                Router.newIntent(context).to(SelectCompanyActivity.class).launch();
            }
//            Router.newIntent(context).to(SelectCompanyActivity.class).launch();
        }

        etLoginPhone.addTextChangedListener(mOnInputChangeListener);
        etLoginPwd.addTextChangedListener(mOnInputChangeListener);
    }

    @OnClick({R.id.iv_login_eye, R.id.tv_login_forgot, R.id.but_login, R.id.tv_login_regi,
            R.id.tv_agreement, R.id.tv_privacy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_eye:
                if (eyeType) {
                    ivLoginEye.setImageResource(R.mipmap.pwd_close);
                    eyeType = false;
                    etLoginPwd.setInputType(129);
                } else {
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
                getP().login(phone, pwd);
                break;
            case R.id.tv_login_regi:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
//            case R.id.iv_login_qq:
//                break;
//            case R.id.iv_login_wx:
//                break;
//            case R.id.iv_login_ali:
//                break;
            case R.id.tv_agreement:
                Intent intent = new Intent(context, WebviewX5Activity.class);
                intent.putExtra(Constants.WEBVIEW_URL, "https://yzs.mfzn.com.cn/mp/paper/agreement.html");
                intent.putExtra(Constants.WEBVIEW_NAME, "注册协议");
                startActivity(intent);
                break;
            case R.id.tv_privacy:
                Intent intent1 = new Intent(context, WebviewX5Activity.class);
                intent1.putExtra(Constants.WEBVIEW_URL, "https://yzs.mfzn.com.cn/mp/paper/privacypolicy.html");
                intent1.putExtra(Constants.WEBVIEW_NAME, "隐私政策");
                startActivity(intent1);
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

    public void loginSuccess(UserResponse userResponse) {
        String pwd = etLoginPwd.getText().toString().trim();
        UserHelper.login(userResponse,pwd);
        UserHelper.setOut(false);
        mHandler.postDelayed(() -> {
            String companyId = UserHelper.getCompanyId();
            if (!TextUtils.isEmpty(companyId)) {
                Router.newIntent(context).to(MainActivity.class).launch();
            } else {
                Router.newIntent(context).to(SelectCompanyActivity.class).launch();
            }
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
