package com.mfzn.deepuses.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.company.SelectCompanyActivity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.present.login.ForgotPwdPresent;
import com.mfzn.deepuses.present.login.RegistPwdPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

public class ForgotNewPwdActivity extends BaseMvpActivity<ForgotPwdPresent> {


    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_for_pwd_title)
    TextView tvForPwdTitle;
    @BindView(R.id.et_for_pwd)
    EditText etForPwd;
    @BindView(R.id.iv_for_eye)
    ImageButton ivForEye;
    @BindView(R.id.tv_for_pwd_err)
    TextView tvForPwdErr;
    @BindView(R.id.et_for_pwd2)
    EditText etForPwd2;
    @BindView(R.id.iv_for_eye2)
    ImageButton ivForEye2;
    @BindView(R.id.tv_for_pwd_err2)
    TextView tvForPwdErr2;
    @BindView(R.id.but_for)
    Button butFor;

    private boolean eyeType = false;
    private boolean eyeType2 = false;
    private String phone;
    private String code;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forgot_new_pwd;
    }

    @Override
    public ForgotPwdPresent newP() {
        return new ForgotPwdPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("找回密码");
        tvForPwdTitle.getPaint().setFakeBoldText(true);

        Intent intent = getIntent();
        phone = intent.getStringExtra(Constants.REGISTER_PHONE);
        code = intent.getStringExtra(Constants.REGISTER_CODE);

        etForPwd.addTextChangedListener(mOnInputChangeListener);
        etForPwd2.addTextChangedListener(mOnInputChangeListener);

        etForPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {// 获得焦点
                    tvForPwdErr.setVisibility(View.GONE);
                } else {// 失去焦点
                    if(etForPwd.getText().toString().length() < 6) {
                        tvForPwdErr.setVisibility(View.VISIBLE);
                    }else {
                        tvForPwdErr.setVisibility(View.GONE);
                    }
                }

            }
        });

        etForPwd2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {// 获得焦点
                    tvForPwdErr2.setVisibility(View.GONE);
                } else {// 失去焦点
                    if(etForPwd2.getText().toString().length() < 6) {
                        tvForPwdErr2.setVisibility(View.VISIBLE);
                    }else {
                        tvForPwdErr2.setVisibility(View.GONE);
                    }
                }

            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.iv_for_eye, R.id.iv_for_eye2, R.id.but_for})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                break;
            case R.id.iv_for_eye:
                if(eyeType) {
                    ivForEye.setImageResource(R.mipmap.pwd_close);
                    eyeType = false;
                    etForPwd.setInputType(129);
                }else {
                    ivForEye.setImageResource(R.mipmap.pwd_open);
                    eyeType = true;
                    etForPwd.setInputType(128);
                }
                break;
            case R.id.iv_for_eye2:
                if(eyeType2) {
                    ivForEye2.setImageResource(R.mipmap.pwd_close);
                    eyeType2 = false;
                    etForPwd2.setInputType(129);
                }else {
                    ivForEye2.setImageResource(R.mipmap.pwd_open);
                    eyeType2 = true;
                    etForPwd2.setInputType(128);
                }
                break;
            case R.id.but_for:
                String pwd = etForPwd.getText().toString().trim();
                String pwd2 = etForPwd2.getText().toString().trim();
                if(pwd.equals(pwd2)) {
                    getP().forgetPwd(phone,code,pwd,pwd2);
                }else {
                    ToastUtil.showToast(this,"两次密码不一致");
                }
                break;
        }
    }

    private OnInputChangeListener mOnInputChangeListener = new OnInputChangeListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(etForPwd.getText().toString().trim()) &&
                    !TextUtils.isEmpty(etForPwd2.getText().toString().trim())) {
                butFor.setEnabled(true);
                butFor.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
            } else {
                butFor.setEnabled(false);
                butFor.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
            }
        }
    };

    public void forgetPwdSuccess() {
        String pwd = etForPwd.getText().toString().trim();
        getP().login(phone, pwd);
    }

    public void loginSuccess(UserModel userModel) {
        String pwd = etForPwd.getText().toString().trim();
        UserHelper.login(userModel,pwd);
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.REGISTER);
        RxBus.getInstance().post(eventMsg);
        String companyId = UserHelper.getCompanyId();
        if(!TextUtils.isEmpty(companyId)) {
            Router.newIntent(context).to(MainActivity.class).launch();
        }else {
            Router.newIntent(context).to(SelectCompanyActivity.class).launch();
        }
        finish();
    }

    public void loginErr() {
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.REGISTER);
        RxBus.getInstance().post(eventMsg);
        finish();
    }
}
