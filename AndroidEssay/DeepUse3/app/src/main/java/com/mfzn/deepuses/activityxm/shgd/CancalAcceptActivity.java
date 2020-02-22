package com.mfzn.deepuses.activityxm.shgd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.present.xmgd.CancalAcceptPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CancalAcceptActivity extends BaseMvpActivity<CancalAcceptPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_can_bold)
    TextView tvCanBold;
    @BindView(R.id.et_can_content)
    EditText etCanContent;

    private String orderNo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cancal_accept;
    }

    @Override
    public CancalAcceptPresent newP() {
        return new CancalAcceptPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_cancal_accept));
        tvCanBold.setVisibility(View.VISIBLE);

        orderNo = getIntent().getStringExtra(Constants.SHOUHOU_ORDERNO);
    }

    @OnClick({R.id.iv_login_back, R.id.but_can_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.but_can_commit:
                String trim = etCanContent.getText().toString().trim();
                if(TextUtils.isEmpty(trim)) {
                    ToastUtil.showToast(this,"请输入原因");
                    return;
                }
                getP().cancalAccept(orderNo,trim);
                break;
        }
    }

    public void cancalAcceptSuccess() {
        ToastUtil.showToast(this,"取消成功");
        Intent intent = new Intent();
        intent.putExtra("gfgf", "Fafas");
        setResult(Constants.ACCEPT_GONGDAN,intent);
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.GONGDAN);
        RxBus.getInstance().post(eventMsg);
        finish();
    }
}
