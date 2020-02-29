package com.mfzn.deepuses.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.AppManager;
import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.company.SelectCompanyActivity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.bean.request.LoginRequest;
import com.mfzn.deepuses.bean.request.RegisterRequest;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.present.login.RegistNickPresent;
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

public class SettingNicknameActivity extends BaseMvpActivity<RegistNickPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_nick_title)
    TextView tvNickTitle;
    @BindView(R.id.et_nick_name)
    EditText etNickName;
    @BindView(R.id.but_start)
    Button butStart;

    private String phone;
    private String code;
    private String pwd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting_nickname;
    }

    @Override
    public RegistNickPresent newP() {
        return new RegistNickPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("设置昵称");
        tvNickTitle.getPaint().setFakeBoldText(true);

        Intent intent = getIntent();
        phone = intent.getStringExtra(Constants.REGISTER_PHONE);
        code = intent.getStringExtra(Constants.REGISTER_CODE);
        pwd = intent.getStringExtra(Constants.REGISTER_PWD);

        etNickName.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(etNickName.getText().toString().trim())) {
                    butStart.setEnabled(true);
                    butStart.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
                } else {
                    butStart.setEnabled(false);
                    butStart.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.but_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_start:
                String name = etNickName.getText().toString().trim();
                RegisterRequest request = new RegisterRequest();
                request.setSmsCode(code);
                request.setUserPhone(phone);
                request.setUserName(name);
                request.setUserPwd(pwd);
                request.setUserRePwd(pwd);
                getP().appRegister(request);
                break;
        }
    }

    public void registSuccess() {
        getP().login(new LoginRequest(phone, pwd));
    }

    public void loginSuccess(UserResponse userResponse) {
        UserHelper.login(userResponse, pwd);
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.REGISTER);
        RxBus.getInstance().post(eventMsg);
        startActivity(new Intent(this, SelectCompanyActivity.class));
        finish();
    }

    public void loginErr() {
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.REGISTER);
        RxBus.getInstance().post(eventMsg);
        finish();
    }
}
