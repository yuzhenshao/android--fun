package com.mfzn.deepuses.activitymy.setting;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activitymy.WebviewX5Activity;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
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

            tvEdiBb.setText("Version " + versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.iv_login_back, R.id.ll_edi_gx, R.id.ll_edi_sm, R.id.ll_edi_ys})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_edi_gx:
                break;
            case R.id.ll_edi_sm:
                Intent intent = new Intent(context, WebviewX5Activity.class);
                intent.putExtra(Constants.WEBVIEW_URL, "https://yzs.mfzn.com.cn/mp/paper/agreement.html");
                intent.putExtra(Constants.WEBVIEW_NAME, "注册协议");
                startActivity(intent);
                break;
            case R.id.ll_edi_ys:
                Intent intent1 = new Intent(context, WebviewX5Activity.class);
                intent1.putExtra(Constants.WEBVIEW_URL, "https://yzs.mfzn.com.cn/mp/paper/privacypolicy.html");
                intent1.putExtra(Constants.WEBVIEW_NAME, "隐私政策");
                startActivity(intent1);
                break;
//            case R.id.ll_edi_xy:
//                break;
//            case R.id.ll_edi_pf:
//                break;
        }
    }
}
