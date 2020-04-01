package com.mfzn.deepusesSer.activitymy.setting;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class EditionInfoActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_edi_name)
    TextView tvEdiName;
    @BindView(R.id.tv_edi_bb)
    TextView tvEdiBb;
    @BindView(R.id.tv_edi_gx)
    TextView tvEdiGx;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edition_info;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_edition_info));
        tvEdiName.getPaint().setFakeBoldText(true);
        tvEdiBb.getPaint().setFakeBoldText(true);
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;

            tvEdiBb.setText("version：" + versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.iv_login_back, R.id.ll_edi_gx, R.id.ll_edi_sm, R.id.ll_edi_xy, R.id.ll_edi_pf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_edi_gx:
//                Beta.checkUpgrade();
                break;
            case R.id.ll_edi_sm:
                break;
            case R.id.ll_edi_xy:
                break;
            case R.id.ll_edi_pf:
                break;
        }
    }
}
