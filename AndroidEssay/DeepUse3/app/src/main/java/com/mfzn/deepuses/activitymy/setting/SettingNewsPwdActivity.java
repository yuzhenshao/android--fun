package com.mfzn.deepuses.activitymy.setting;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mfzn.deepuses.AppManager;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.login.LoginActivity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.present.my.ExchangePswPresent;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.UserHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

public class SettingNewsPwdActivity extends BaseMvpActivity<ExchangePswPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_news_pwd_title)
    TextView tvNewsPwdTitle;
    @BindView(R.id.et_news_pwd)
    EditText etNewsPwd;
    @BindView(R.id.iv_news_eye)
    ImageButton ivNewsEye;
    @BindView(R.id.tv_news_pwd_err)
    TextView tvNewsPwdErr;
    @BindView(R.id.et_news_pwd2)
    EditText etNewsPwd2;
    @BindView(R.id.iv_news_eye2)
    ImageButton ivNewsEye2;
    @BindView(R.id.tv_news_pwd_err2)
    TextView tvNewsPwdErr2;
    @BindView(R.id.but_news_next)
    Button butNewsNext;

    private boolean eyeType = false;
    private boolean eyeType2 = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting_news_pwd;
    }

    @Override
    public ExchangePswPresent newP() {
        return new ExchangePswPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvNewsPwdTitle.getPaint().setFakeBoldText(true);

        etNewsPwd.addTextChangedListener(mOnInputChangeListener);
        etNewsPwd2.addTextChangedListener(mOnInputChangeListener);

        etNewsPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {// 获得焦点
                    tvNewsPwdErr.setVisibility(View.GONE);
                } else {// 失去焦点
                    if(etNewsPwd.getText().toString().length() < 6) {
                        tvNewsPwdErr.setVisibility(View.VISIBLE);
                    }else {
                        tvNewsPwdErr.setVisibility(View.GONE);
                    }
                }

            }
        });

        etNewsPwd2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {// 获得焦点
                    tvNewsPwdErr2.setVisibility(View.GONE);
                } else {// 失去焦点
                    if(etNewsPwd2.getText().toString().length() < 6) {
                        tvNewsPwdErr2.setVisibility(View.VISIBLE);
                    }else {
                        if (etNewsPwd2.getText().toString().equals(etNewsPwd.getText().toString())) {
                            tvNewsPwdErr2.setVisibility(View.GONE);
                        }else{
                            tvNewsPwdErr2.setText("两次输入的密码不一致");
                            tvNewsPwdErr2.setVisibility(View.VISIBLE);
                        }
                    }
                }

            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.iv_news_eye, R.id.iv_news_eye2, R.id.but_news_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                break;
            case R.id.iv_news_eye:
                if(eyeType) {
                    ivNewsEye.setImageResource(R.mipmap.pwd_close);
                    eyeType = false;
                    etNewsPwd.setInputType(129);
                }else {
                    ivNewsEye.setImageResource(R.mipmap.pwd_open);
                    eyeType = true;
                    etNewsPwd.setInputType(128);
                }
                break;
            case R.id.iv_news_eye2:
                if(eyeType2) {
                    ivNewsEye2.setImageResource(R.mipmap.pwd_close);
                    eyeType2 = false;
                    etNewsPwd2.setInputType(129);
                }else {
                    ivNewsEye2.setImageResource(R.mipmap.pwd_open);
                    eyeType2 = true;
                    etNewsPwd2.setInputType(128);
                }
                break;
            case R.id.but_news_next:
                getP().modifyPsw(UserHelper.getUpwd(), etNewsPwd.getText().toString().trim(),etNewsPwd2.getText().toString().trim());
                break;
        }
    }

    private OnInputChangeListener mOnInputChangeListener = new OnInputChangeListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if ((etNewsPwd.getText().toString().trim().length() >= 6) &&
                    (etNewsPwd2.getText().toString().trim().length() >= 6) && etNewsPwd.getText().toString().trim().equals(etNewsPwd2.getText().toString().trim())) {
                butNewsNext.setEnabled(true);
                butNewsNext.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
            } else {
                butNewsNext.setEnabled(false);
                butNewsNext.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
            }
        }
    };

    public void modifyPswSuccess() {
        UserHelper.logout();
        UserHelper.setOut(true);
        AppManager.getInstance().finishAllActivity();
        Router.newIntent(this).to(LoginActivity.class).launch();
        finish();
    }
}

