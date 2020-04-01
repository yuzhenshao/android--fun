package com.mfzn.deepusesSer.activityxm.shgd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class IfAcceptActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_if_bold)
    TextView tvIfBold;
    @BindView(R.id.iv_if_sl)
    ImageView ivIfSl;
    @BindView(R.id.iv_if_nosl)
    ImageView ivIfNosl;
    @BindView(R.id.et_if_content)
    EditText etIfContent;

    private int type;

    @Override
    public int getLayoutId() {
        return R.layout.activity_if_accept;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_if_accept));
        tvIfBold.getPaint().setFakeBoldText(true);

        Intent intent = getIntent();
        type = intent.getIntExtra(Constants.IF_ACCEPT_TYPE, 1);
        String stringExtra = intent.getStringExtra(Constants.IF_ACCEPT_CONTENT);
        if(type == 1) {
            ivIfSl.setImageResource(R.mipmap.work_xuanzhong);
            ivIfNosl.setImageResource(R.mipmap.work_weixuan);
            etIfContent.setVisibility(View.GONE);
        }else  if(type == 2) {
            ivIfSl.setImageResource(R.mipmap.work_weixuan);
            ivIfNosl.setImageResource(R.mipmap.work_xuanzhong);
            etIfContent.setVisibility(View.VISIBLE);
            etIfContent.setText(stringExtra);
        }
    }

    @OnClick({R.id.iv_login_back, R.id.ll_if_sl, R.id.ll_if_nosl, R.id.but_if_qr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_if_sl:
                type = 1;
                ivIfSl.setImageResource(R.mipmap.work_xuanzhong);
                ivIfNosl.setImageResource(R.mipmap.work_weixuan);
                etIfContent.setVisibility(View.GONE);
                break;
            case R.id.ll_if_nosl:
                type = 2;
                ivIfSl.setImageResource(R.mipmap.work_weixuan);
                ivIfNosl.setImageResource(R.mipmap.work_xuanzhong);
                etIfContent.setVisibility(View.VISIBLE);
                break;
            case R.id.but_if_qr:
                Intent intent = new Intent();
                intent.putExtra(Constants.IF_ACCEPT_TYPE, type);
                intent.putExtra(Constants.IF_ACCEPT_CONTENT, etIfContent.getText().toString().trim());
                setResult(Constants.IF_ACCEPT,intent);
                finish();
                break;
        }
    }
}
