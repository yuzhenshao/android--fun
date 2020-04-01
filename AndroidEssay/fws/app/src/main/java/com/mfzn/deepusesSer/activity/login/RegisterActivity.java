package com.mfzn.deepusesSer.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activitymy.WebviewX5Activity;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.EventMsg;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;
import com.mfzn.deepusesSer.utils.RxBus;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_ri_title)
    TextView tvRiTitle;
    @BindView(R.id.et_regi_phone)
    EditText etRegiPhone;
    @BindView(R.id.tv_ri_phoen_err)
    TextView tvRiPhoenErr;
    @BindView(R.id.iv_regi_select)
    ImageButton ivRegiSelect;
    @BindView(R.id.but_next)
    Button butNext;

    private boolean isSelect = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("注册");
        tvRiTitle.getPaint().setFakeBoldText(true);

        etRegiPhone.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvRiPhoenErr.setVisibility(View.GONE);
                judgeDisplay();
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

    @OnClick({R.id.iv_login_back, R.id.iv_regi_select, R.id.tv_ri_zhuce, R.id.tv_ri_yinsi, R.id.but_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_regi_select:
                if(isSelect) {
                    ivRegiSelect.setImageResource(R.mipmap.regi_weixuan);
                    isSelect = false;
                }else {
                    ivRegiSelect.setImageResource(R.mipmap.regi_xuanzhong);
                    isSelect = true;
                }
                judgeDisplay();
                break;
            case R.id.tv_ri_zhuce:
                Intent intent3 = new Intent(context, WebviewX5Activity.class);
                intent3.putExtra(Constants.WEBVIEW_URL, "https://yzs.mfzn.com.cn/mp/paper/agreement.html");
                intent3.putExtra(Constants.WEBVIEW_NAME, "注册协议");
                startActivity(intent3);
                break;
            case R.id.tv_ri_yinsi:
                Intent intent1 = new Intent(context, WebviewX5Activity.class);
                intent1.putExtra(Constants.WEBVIEW_URL, "https://yzs.mfzn.com.cn/mp/paper/privacypolicy.html");
                intent1.putExtra(Constants.WEBVIEW_NAME, "隐私政策");
                startActivity(intent1);
                break;
            case R.id.but_next:
                String phone = etRegiPhone.getText().toString().trim();
                if(phone.length() == 11) {
                    Intent intent = new Intent(this, RegisterCodeActivity.class);
                    intent.putExtra(Constants.REGISTER_PHONE,phone);
                    startActivity(intent);
                }else {
                    tvRiPhoenErr.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private void judgeDisplay() {
        if (!TextUtils.isEmpty(etRegiPhone.getText().toString().trim()) && isSelect) {
            butNext.setEnabled(true);
            butNext.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
        }else {
            butNext.setEnabled(false);
            butNext.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
        }
    }
}
