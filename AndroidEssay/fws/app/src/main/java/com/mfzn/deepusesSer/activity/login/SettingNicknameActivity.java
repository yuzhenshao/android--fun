package com.mfzn.deepusesSer.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepusesSer.MainActivity;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.login.UserModel;
import com.mfzn.deepusesSer.present.login.RegistNickPresent;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.EventMsg;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;
import com.mfzn.deepusesSer.utils.RxBus;
import com.mfzn.deepusesSer.utils.UserHelper;

import butterknife.BindView;
import butterknife.OnClick;

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
                getP().appRegister(phone,code,pwd,pwd,name);
                break;
        }
    }

    public void registSuccess() {
        getP().login(phone, pwd);
    }

    public void loginSuccess(UserModel userModel) {
        UserHelper.login(userModel,pwd);
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.REGISTER);
        RxBus.getInstance().post(eventMsg);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void loginErr() {
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.REGISTER);
        RxBus.getInstance().post(eventMsg);
        finish();
    }
}
