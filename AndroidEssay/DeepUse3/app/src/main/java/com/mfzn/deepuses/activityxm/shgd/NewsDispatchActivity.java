package com.mfzn.deepuses.activityxm.shgd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.bean.request.ReSendAsOrderRequest;
import com.mfzn.deepuses.bean.request.SendAsOrderRequest;
import com.mfzn.deepuses.present.xmgd.NewsDispatchPresent;
import com.mfzn.deepuses.present.xmgd.WorkorderDispatchPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.http.Field;

public class NewsDispatchActivity extends BaseMvpActivity<NewsDispatchPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.et_dis_gcs)
    EditText etDisGcs;
    @BindView(R.id.tv_dis_phone)
    TextView tvDisPhone;
    @BindView(R.id.et_dis_remarks)
    EditText etDisRemarks;

    private String orderNo;
    private String jobid;
    private String enginerID;
    private String name;
    private String phone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_dispatch;
    }

    @Override
    public NewsDispatchPresent newP() {
        return new NewsDispatchPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_news_dispatch));

        orderNo = getIntent().getStringExtra(Constants.SHOUHOU_ORDERNO);
        jobid = getIntent().getStringExtra(Constants.SHOUHOU_JOBID);
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
//                @Field("orderNo") String orderNo, @Field("enginerID") String enginerID,
//                @Field("name") String name, @Field("phone") String phone,
//                @Field("note") String note, @Field("shJobID") String shJobID)
                ReSendAsOrderRequest request = new ReSendAsOrderRequest();
                request.setProID(getIntent().getIntExtra(Constants.SHOUHOU_PROID,0));
                request.setOrderNo(orderNo);
                request.setEngineerID(enginerID);
                request.setName(name);
                request.setPhone(phone);
                request.setNote(res);
                request.setShJobID(jobid);
                getP().newsDispatch(request);
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

    public void newsDispatchSuccess() {
        ToastUtil.showToast(this,"重新派工成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.GONGDAN);
        RxBus.getInstance().post(eventMsg);
        finish();
    }
}
