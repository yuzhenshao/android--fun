package com.mfzn.deepuses.activityxm.shgd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.xiangmu.EnginerListAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.EnginerListModel;
import com.mfzn.deepuses.present.xmgd.EnginerListPresent;
import com.mfzn.deepuses.present.xmgd.WorkorderDispatchPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkorderDispatchActivity extends BaseMvpActivity<WorkorderDispatchPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.et_dis_gcs)
    EditText etDisGcs;
    @BindView(R.id.tv_dis_phone)
    TextView tvDisPhone;
    @BindView(R.id.et_dis_remarks)
    EditText etDisRemarks;

    private String orderNo;
    private String proId;
    private String enginerID;
    private String name;
    private String phone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_workorder_dispatch;
    }

    @Override
    public WorkorderDispatchPresent newP() {
        return new WorkorderDispatchPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_workorder_dispatch));

        orderNo = getIntent().getStringExtra(Constants.SHOUHOU_ORDERNO);
        proId = getIntent().getStringExtra(Constants.SHOUHOU_PROIDS);
    }

    @OnClick({R.id.iv_login_back, R.id.et_dis_gcs, R.id.but_dis_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.et_dis_gcs:
                startActivityForResult(new Intent(this, EnginerListActivity.class),Constants.ENGINER_LIST);
                break;
            case R.id.but_dis_commit:
                String gcs = etDisGcs.getText().toString().trim();
                if(TextUtils.isEmpty(gcs)) {
                    ToastUtil.showToast(this,"请选择工程师");
                    return;
                }
                String res = etDisRemarks.getText().toString().trim();
                if(TextUtils.isEmpty(res)) {
                    ToastUtil.showToast(this,"请输入备注");
                    return;
                }
                getP().workorderDispatch(proId,orderNo,enginerID,name,phone,res);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.ENGINER_LIST == requestCode) {
            if (data != null) {
                enginerID = data.getStringExtra(Constants.ENGINER_ID);
                name = data.getStringExtra(Constants.ENGINER_NAME);
                phone = data.getStringExtra(Constants.ENGINER_PHONE);
                etDisGcs.setText(name);
                tvDisPhone.setText(phone);
            }
        }
    }

    public void workorderDispatchSuccess() {
        ToastUtil.showToast(this,"派工成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.GONGDAN);
        RxBus.getInstance().post(eventMsg);
        finish();
    }
}
