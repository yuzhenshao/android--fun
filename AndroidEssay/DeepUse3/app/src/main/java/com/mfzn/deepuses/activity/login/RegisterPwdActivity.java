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
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.present.login.RegistPresent;
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
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RegisterPwdActivity extends BaseMvpActivity<RegistPwdPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_ri_pwd_title)
    TextView tvRiPwdTitle;
    @BindView(R.id.et_regi_pwd)
    EditText etRegiPwd;
    @BindView(R.id.iv_regi_eye)
    ImageButton ivRegiEye;
    @BindView(R.id.tv_regi_pwd_err)
    TextView tvRegiPwdErr;
    @BindView(R.id.et_regi_pwd2)
    EditText etRegiPwd2;
    @BindView(R.id.iv_regi_eye2)
    ImageButton ivRegiEye2;
    @BindView(R.id.tv_regi_pwd_err2)
    TextView tvRegiPwdErr2;
    @BindView(R.id.but_next)
    Button butNext;

    private boolean eyeType = false;
    private boolean eyeType2 = false;
    private String phone;
    private String code;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_pwd;
    }

    @Override
    public RegistPwdPresent newP() {
        return new RegistPwdPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("设置密码");
        tvRiPwdTitle.getPaint().setFakeBoldText(true);

        Intent intent = getIntent();
        phone = intent.getStringExtra(Constants.REGISTER_PHONE);
        code = intent.getStringExtra(Constants.REGISTER_CODE);

        etRegiPwd.addTextChangedListener(mOnInputChangeListener);
        etRegiPwd2.addTextChangedListener(mOnInputChangeListener);

        etRegiPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {// 获得焦点
                    tvRegiPwdErr.setVisibility(View.GONE);
                } else {// 失去焦点
                    if(etRegiPwd.getText().toString().length() < 6) {
                        tvRegiPwdErr.setVisibility(View.VISIBLE);
                    }else {
                        tvRegiPwdErr.setVisibility(View.GONE);
                    }
                }

            }
        });

        etRegiPwd2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {// 获得焦点
                    tvRegiPwdErr2.setVisibility(View.GONE);
                } else {// 失去焦点
                    if(etRegiPwd2.getText().toString().length() < 6) {
                        tvRegiPwdErr2.setVisibility(View.VISIBLE);
                    }else {
                        tvRegiPwdErr2.setVisibility(View.GONE);
                    }
                }

            }
        });

        //接受fragment中传递的数据，改变页面状态并切换viewpage
        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if(eventMsg.getMsg().equals(Constants.REGISTER)){
                        finish();
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.iv_regi_eye, R.id.iv_regi_eye2, R.id.but_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_regi_eye:
                if(eyeType) {
                    ivRegiEye.setImageResource(R.mipmap.pwd_close);
                    eyeType = false;
                    etRegiPwd.setInputType(129);
                }else {
                    ivRegiEye.setImageResource(R.mipmap.pwd_open);
                    eyeType = true;
                    etRegiPwd.setInputType(128);
                }
                break;
            case R.id.iv_regi_eye2:
                if(eyeType2) {
                    ivRegiEye2.setImageResource(R.mipmap.pwd_close);
                    eyeType2 = false;
                    etRegiPwd2.setInputType(129);
                }else {
                    ivRegiEye2.setImageResource(R.mipmap.pwd_open);
                    eyeType2 = true;
                    etRegiPwd2.setInputType(128);
                }
                break;
            case R.id.but_next:
                String pwd = etRegiPwd.getText().toString().trim();
                String pwd2 = etRegiPwd2.getText().toString().trim();
                if(pwd.equals(pwd2)) {
                    Intent intent = new Intent(this, SettingNicknameActivity.class);
                    intent.putExtra(Constants.REGISTER_PHONE,phone);
                    intent.putExtra(Constants.REGISTER_CODE,code);
                    intent.putExtra(Constants.REGISTER_PWD,pwd);
                    startActivity(intent);
                }else {
                    ToastUtil.showToast(this,"两次密码不一致");
                }
                break;
        }
    }

    private OnInputChangeListener mOnInputChangeListener = new OnInputChangeListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(etRegiPwd.getText().toString().trim()) &&
                    !TextUtils.isEmpty(etRegiPwd2.getText().toString().trim())) {
                butNext.setEnabled(true);
                butNext.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
            } else {
                butNext.setEnabled(false);
                butNext.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
            }
        }
    };
}
