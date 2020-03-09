package com.mfzn.deepuses.activity.khgl;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.khgl.ProDetAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.DateUtils;
import com.wevey.selector.dialog.bean.DetailsModel;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProDetaActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.et_edit_proname)
    TextView etEditProname;
    @BindView(R.id.et_edit_address)
    TextView etEditAddress;
    @BindView(R.id.et_edit_detail)
    TextView etEditDetail;
    @BindView(R.id.et_edit_money)
    TextView etEditMoney;
    @BindView(R.id.et_edit_zbqx)
    TextView etEditZbqx;
    @BindView(R.id.tv_edit_start)
    TextView etEditStart;
    @BindView(R.id.et_edit_end)
    TextView etEditEnd;
    @BindView(R.id.tv_kehu)
    TextView tv_kehu;
    @BindView(R.id.ib_edit_zbyj)
    ImageButton ibEditZbyj;
    @BindView(R.id.or_recycleview)
    RecyclerView orRecycleview;


    @Override
    public int getLayoutId() {
        return R.layout.activity_pro_deta;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("项目详情");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        orRecycleview.setLayoutManager(layoutManager);

        DetailsModel.ProsBean dataBean = (DetailsModel.ProsBean) getIntent().getSerializableExtra(Constants.PRO_DETA);

        etEditProname.setText(dataBean.getPro_name());
        etEditAddress.setText(dataBean.getAreaName());
        etEditDetail.setText(dataBean.getDetailAddress());
        etEditMoney.setText(dataBean.getContractMoney() + " 元");


        String qualityBegin = dataBean.getQualityBegin();
        if(!TextUtils.isEmpty(qualityBegin) && !qualityBegin.equals("0")) {
            etEditStart.setText(DateUtils.stampDate(qualityBegin));
        }else {
            etEditStart.setText("暂无");
        }
        String qualityEnd = dataBean.getQualityEnd();
        if(!TextUtils.isEmpty(qualityEnd) && !qualityEnd.equals("0")) {
            etEditEnd.setText(DateUtils.stampDate(qualityEnd));
        }else {
            etEditEnd.setText("暂无");
        }
        String qualityTime = dataBean.getQualityTime();
        if(!qualityTime.equals("0")) {
            etEditZbqx.setText(qualityTime);
        }else {
            etEditZbqx.setText("暂无");
        }

        List<DetailsModel.ProsBean.OtherCustomersBean> otherCustomers = dataBean.getOtherCustomers();
        if(otherCustomers != null && otherCustomers.size() != 0) {
            ProDetAdapter recycleAdapter = new ProDetAdapter(this, otherCustomers);
            orRecycleview.setAdapter(recycleAdapter);
        }else {
            tv_kehu.setVisibility(View.GONE);
            orRecycleview.setVisibility(View.GONE);
        }

        String qualityRing = dataBean.getQualityRing();
        if(qualityRing.equals("1")) {
            ibEditZbyj.setImageResource(R.mipmap.shou_open);
        }else if(qualityRing.equals("0")){
            ibEditZbyj.setImageResource(R.mipmap.shou_close);
        }
    }

    @OnClick({R.id.iv_login_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
        }
    }
}
