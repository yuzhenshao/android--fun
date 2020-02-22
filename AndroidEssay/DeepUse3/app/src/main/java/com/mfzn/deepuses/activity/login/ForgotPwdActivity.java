package com.mfzn.deepuses.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ForgotPwdActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_for_title)
    TextView tvForTitle;
    @BindView(R.id.et_for_phone)
    EditText etForPhone;
    @BindView(R.id.but_next)
    Button butNext;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forgot_pwd;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("找回密码");
        tvForTitle.getPaint().setFakeBoldText(true);

        etForPhone.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(etForPhone.getText().toString().trim())) {
                    butNext.setEnabled(true);
                    butNext.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
                } else {
                    butNext.setEnabled(false);
                    butNext.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
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
                String phone = etForPhone.getText().toString().trim();
                if(phone.length() == 11) {
                    Intent intent = new Intent(this, ForgotCodeActivity.class);
                    intent.putExtra(Constants.FORGOT_PHONE,phone);
                    startActivity(intent);
                }else {
                    etForPhone.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
