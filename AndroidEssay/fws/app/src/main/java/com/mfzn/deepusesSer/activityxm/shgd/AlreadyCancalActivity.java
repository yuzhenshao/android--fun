package com.mfzn.deepusesSer.activityxm.shgd;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.utils.PhoneUtils;
import com.mfzn.deepusesSer.view.MyListview;

import butterknife.BindView;
import butterknife.OnClick;

public class AlreadyCancalActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_alre_type)
    TextView tvalreType;
    @BindView(R.id.tv_alre_typename)
    TextView tvalreTypename;
    @BindView(R.id.tv_alre_address)
    TextView tvalreAddress;
    @BindView(R.id.tv_alre_details)
    TextView tvalreDetails;
    @BindView(R.id.tv_alre_name)
    TextView tvalreName;
    @BindView(R.id.tv_alre_phone)
    TextView tvalrePhone;
    @BindView(R.id.tv_alre_gdsx)
    TextView tvalreGdsx;
    @BindView(R.id.tv_alre_gdbold)
    TextView tvalreGdbold;
    @BindView(R.id.tv_alre_clgc)
    TextView tvalreClgc;
    @BindView(R.id.tv_alre_clbold)
    TextView tvalreClbold;
    @BindView(R.id.ll_alre_clgc)
    LinearLayout llalreClgc;
    @BindView(R.id.tv_alre_khname)
    TextView tvalreKhname;
    @BindView(R.id.tv_alre_time)
    TextView tvalreTime;
    @BindView(R.id.tv_alre_whtime)
    TextView tvalreWhtime;
    @BindView(R.id.tv_alre_ms)
    TextView tvalreMs;
    @BindView(R.id.alre_recycleview)
    RecyclerView alreRecycleview;
    @BindView(R.id.ll_alre_gd)
    LinearLayout llalreGd;
    @BindView(R.id.alre_listview)
    MyListview alreListview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_already_cancal;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_stay_accept));
        tvalreTypename.getPaint().setFakeBoldText(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        alreRecycleview.setLayoutManager(layoutManager);
//        ShouliPhotoAdapter recycleAdapter = new ShouliPhotoAdapter(this);
//        alreRecycleview.setAdapter(recycleAdapter);

//        ShouliListviewAdapter adapter = new ShouliListviewAdapter(this);
//        alreListview.setAdapter(adapter);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_alre_phone, R.id.ll_alre_gdsx, R.id.ll_alre_clgc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_alre_phone:
                if (!TextUtils.isEmpty("12345678912")) {
                    PhoneUtils.dialogPhone(this, "12345678912");
                }
                break;
            case R.id.ll_alre_gdsx:
//                alreViewpager.setCurrentItem(0);
//                tvalreGdsx.getalrent().setFakeBoldText(true);
//                tvalreClgc.getalrent().setFakeBoldText(false);
                tvalreGdbold.setVisibility(View.VISIBLE);
                tvalreClbold.setVisibility(View.INVISIBLE);
                llalreGd.setVisibility(View.VISIBLE);
                alreListview.setVisibility(View.GONE);
                break;
            case R.id.ll_alre_clgc:
//                alreViewpager.setCurrentItem(1);
//                tvalreGdsx.getalrent().setFakeBoldText(false);
//                tvalreClgc.getalrent().setFakeBoldText(true);
                tvalreGdbold.setVisibility(View.INVISIBLE);
                tvalreClbold.setVisibility(View.VISIBLE);
                llalreGd.setVisibility(View.GONE);
                alreListview.setVisibility(View.VISIBLE);
                break;
        }
    }
}
