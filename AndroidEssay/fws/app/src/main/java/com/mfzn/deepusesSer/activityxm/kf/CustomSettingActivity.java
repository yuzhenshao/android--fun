package com.mfzn.deepusesSer.activityxm.kf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.xiangmu.CustomSettingAdapter;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomSettingActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.cus_listview)
    ListView cusListview;
    @BindView(R.id.ll_cus_data)
    LinearLayout llCusData;
    @BindView(R.id.tv_cus_bold)
    TextView tvCusBold;
    @BindView(R.id.ll_cus_nodata)
    LinearLayout llCusNodata;

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_setting;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_custom_setting));
        tvCusBold.getPaint().setFakeBoldText(true);

        CustomSettingAdapter adapter = new CustomSettingAdapter(this);
        cusListview.setAdapter(adapter);

        adapter.setmOnitemclickLisenter(new CustomSettingAdapter.OnitemclickLisenter() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.tv_cus_add, R.id.but_cus_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_cus_add:
                startActivity(new Intent(this, AddCustomActivity.class));
                break;
            case R.id.but_cus_add:
                startActivity(new Intent(this, AddCustomActivity.class));
                break;
        }
    }
}
