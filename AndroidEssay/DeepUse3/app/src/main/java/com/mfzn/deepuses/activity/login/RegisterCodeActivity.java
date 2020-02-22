package com.mfzn.deepuses.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.present.login.LoginPresent;
import com.mfzn.deepuses.present.login.RegistPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.SecurityCodeView;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RegisterCodeActivity extends BaseMvpActivity<RegistPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_code_title)
    TextView tvCodeTitle;
    @BindView(R.id.tv_code_phone)
    TextView tvCodePhone;
    @BindView(R.id.tv_code_second)
    TextView tvCodeSecond;
    @BindView(R.id.edit_security_code)
    SecurityCodeView editSecurityCode;
    @BindView(R.id.tv_code_err)
    TextView tvCodeErr;
    @BindView(R.id.but_next)
    Button butNext;

    private int recLen = 60;
    final Handler handler = new Handler(){
        public void handleMessage(Message msg){     // handle message
            switch (msg.what) {
                case 2:
                    recLen--;
                    tvCodeSecond.setText("（" + recLen + "秒）");
                    if(recLen > 0){
                        Message message = handler.obtainMessage(2);
                        handler.sendMessageDelayed(message, 1000);   // send message
                    }else{
                        tvCodeSecond.setText("重新发送");
                        butNext.setEnabled(true);
                        butNext.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private String phone;
    private String code;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_code;
    }

    @Override
    public RegistPresent newP() {
        return new RegistPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("注册");
        tvCodeTitle.getPaint().setFakeBoldText(true);

        phone = getIntent().getStringExtra(Constants.REGISTER_PHONE);
        String s = phone.substring(0, 3);
        String s1 = phone.substring(3, 7);
        String s2 = phone.substring(7, phone.length());
        tvCodePhone.setText(s + " " + s1 + " " + s2);

        getP().getSmsCode(phone);

        editSecurityCode.setInputCompleteListener(new SecurityCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                if(code.equals(editSecurityCode.getEditContent())) {
                    Intent intent = new Intent(RegisterCodeActivity.this, RegisterPwdActivity.class);
                    intent.putExtra(Constants.REGISTER_PHONE,phone);
                    intent.putExtra(Constants.REGISTER_CODE,code);
                    startActivity(intent);
                }else {
                    tvCodeErr.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void deleteContent(boolean isDelete) {
                tvCodeErr.setVisibility(View.GONE);
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

    @OnClick({R.id.iv_login_back, R.id.but_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_next:
                getP().getSmsCode(phone);
                break;
        }
    }

    //发送验证码成功
    public void smsCodeSuccess(String result) {
        code = result;

        Message message = handler.obtainMessage(2);// Message
        handler.sendMessageDelayed(message, 1000);
        tvCodeSecond.setText("（59秒）");
        recLen = 59;
        butNext.setEnabled(false);
        butNext.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
    }
}
