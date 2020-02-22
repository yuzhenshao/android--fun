package com.mfzn.deepuses.activitymy;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.view.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCardActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.riv_card_tx)
    RoundImageView rivCardTx;
    @BindView(R.id.tv_card_name)
    TextView tvCardName;
    @BindView(R.id.tv_card_company)
    TextView tvCardCompany;
    @BindView(R.id.iv_card_code)
    ImageView ivCardCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_card;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_my_card));
        tvCardName.getPaint().setFakeBoldText(true);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_card_xiazai, R.id.ll_card_qq, R.id.ll_card_weixin, R.id.ll_card_dd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_card_xiazai:
                break;
            case R.id.ll_card_qq:
                break;
            case R.id.ll_card_weixin:
                break;
            case R.id.ll_card_dd:
                break;
        }
    }
}
