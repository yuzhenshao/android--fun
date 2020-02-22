package com.mfzn.deepuses.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activitymy.WebviewX5Activity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
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
                Intent intent = new Intent(context, WebviewX5Activity.class);
                intent.putExtra(Constants.WEBVIEW_URL, "https://yzs.mfzn.com.cn/mp/paper/agreement.html");
                intent.putExtra(Constants.WEBVIEW_NAME, "注册协议");
                startActivity(intent);
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
                    Intent intent2 = new Intent(this, RegisterCodeActivity.class);
                    intent2.putExtra(Constants.REGISTER_PHONE,phone);
                    startActivity(intent2);
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
