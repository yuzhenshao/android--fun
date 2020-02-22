package com.mfzn.deepuses.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.present.login.ForgotCodePresent;
import com.mfzn.deepuses.present.login.RegistPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.SecurityCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ForgotCodeActivity extends BaseMvpActivity<ForgotCodePresent> {


    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_for_title)
    TextView tvForTitle;
    @BindView(R.id.tv_for_phone)
    TextView tvForPhone;
    @BindView(R.id.tv_for_second)
    TextView tvForSecond;
    @BindView(R.id.edit_security_code)
    SecurityCodeView editSecurityCode;
    @BindView(R.id.tv_for_err)
    TextView tvForErr;
    @BindView(R.id.but_next)
    Button butNext;

    private int recLen = 60;
    final Handler handler = new Handler(){
        public void handleMessage(Message msg){     // handle message
            switch (msg.what) {
                case 2:
                    recLen--;
                    tvForSecond.setText("（" + recLen + "秒）");
                    if(recLen > 0){
                        Message message = handler.obtainMessage(2);
                        handler.sendMessageDelayed(message, 1000);   // send message
                    }else{
                        tvForSecond.setText("重新发送");
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
        return R.layout.activity_forgot_code;
    }

    @Override
    public ForgotCodePresent newP() {
        return new ForgotCodePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        super.initData(savedInstanceState);
        tvBassTitle.setText("找回密码");
        tvForTitle.getPaint().setFakeBoldText(true);

        phone = getIntent().getStringExtra(Constants.FORGOT_PHONE);
        String s = phone.substring(0, 3);
        String s1 = phone.substring(3, 7);
        String s2 = phone.substring(7, phone.length());
        tvForPhone.setText(s + " " + s1 + " " + s2);

        getP().getSmsCode(phone);

        editSecurityCode.setInputCompleteListener(new SecurityCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                if(code.equals(editSecurityCode.getEditContent())) {
                    Intent intent = new Intent(ForgotCodeActivity.this, ForgotNewPwdActivity.class);
                    intent.putExtra(Constants.REGISTER_PHONE,phone);
                    intent.putExtra(Constants.REGISTER_CODE,code);
                    startActivity(intent);
                }else {
                    tvForErr.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void deleteContent(boolean isDelete) {
                tvForErr.setVisibility(View.GONE);
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
                    if(eventMsg.getMsg().equals(Constants.FORGOT)){
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
        tvForSecond.setText("（59秒）");
        recLen = 59;
        butNext.setEnabled(false);
        butNext.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
    }
}
