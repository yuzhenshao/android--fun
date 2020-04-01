package com.mfzn.deepusesSer.activityxm.shgd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.bass.BaseActivity;
import com.mfzn.deepusesSer.popmune.DropPopMenu;
import com.mfzn.deepusesSer.popmune.MenuItem;
import com.mfzn.deepusesSer.utils.PhoneUtils;
import com.mfzn.deepusesSer.view.MyListview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class StayAcceptActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_select)
    LinearLayout llBassSelect;
    @BindView(R.id.tv_acc_type)
    TextView tvAccType;
    @BindView(R.id.tv_acc_typename)
    TextView tvAccTypename;
    @BindView(R.id.tv_acc_sl)
    TextView tvAccSl;
    @BindView(R.id.tv_acc_address)
    TextView tvAccAddress;
    @BindView(R.id.tv_acc_details)
    TextView tvAccDetails;
    @BindView(R.id.tv_acc_name)
    TextView tvAccName;
    @BindView(R.id.tv_acc_phone)
    TextView tvAccPhone;
    @BindView(R.id.tv_acc_gdsx)
    TextView tvAccGdsx;
    @BindView(R.id.tv_acc_gdbold)
    TextView tvAccGdbold;
    @BindView(R.id.tv_acc_clgc)
    TextView tvAccClgc;
    @BindView(R.id.tv_acc_clbold)
    TextView tvAccClbold;
    @BindView(R.id.ll_acc_clgc)
    LinearLayout llAccClgc;
    @BindView(R.id.tv_acc_khname)
    TextView tvAccKhname;
    @BindView(R.id.tv_acc_time)
    TextView tvAccTime;
    @BindView(R.id.tv_acc_whtime)
    TextView tvAccWhtime;
    @BindView(R.id.tv_acc_ms)
    TextView tvAccMs;
    @BindView(R.id.acc_recycleview)
    RecyclerView accRecycleview;
    @BindView(R.id.ll_acc_gd)
    LinearLayout llAccGd;
    @BindView(R.id.acc_listview)
    MyListview accListview;
//    @BindView(R.id.acc_viewpager)
//    MyViewPager accViewpager;

//    private List<Fragment> list = new ArrayList<Fragment>();
//    private MyDuiZhangPagerAdapter myDuiZhangPagerAdapter;
//    private int vpType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_stay_accept;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_stay_accept));
        llBassSelect.setVisibility(View.VISIBLE);
        tvAccTypename.getPaint().setFakeBoldText(true);
//        tvAccGdsx.getPaint().setFakeBoldText(true);

//        JiandangFragment jiandangFragment = new JiandangFragment();
//        list.add(jiandangFragment);
//
//        ShenhuaShejiFragment shenhuaShejiFragment = new ShenhuaShejiFragment();
//        list.add(shenhuaShejiFragment);

//        myDuiZhangPagerAdapter = new MyDuiZhangPagerAdapter(getSupportFragmentManager(), list);
//        accViewpager.setAdapter(myDuiZhangPagerAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        accRecycleview.setLayoutManager(layoutManager);
//        ShouliPhotoAdapter recycleAdapter = new ShouliPhotoAdapter(this);
//        accRecycleview.setAdapter(recycleAdapter);

//        ShouliListviewAdapter adapter = new ShouliListviewAdapter(this);
//        accListview.setAdapter(adapter);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_bass_select, R.id.ll_acc_phone, R.id.ll_acc_gdsx, R.id.ll_acc_clgc, R.id.but_acc_sl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_bass_select:
                onClickPopIcon(view);
                break;
            case R.id.ll_acc_phone:
                if (!TextUtils.isEmpty("12345678912")) {
                    PhoneUtils.dialogPhone(this, "12345678912");
                }
                break;
            case R.id.ll_acc_gdsx:
//                accViewpager.setCurrentItem(0);
//                tvAccGdsx.getPaint().setFakeBoldText(true);
//                tvAccClgc.getPaint().setFakeBoldText(false);
                tvAccGdbold.setVisibility(View.VISIBLE);
                tvAccClbold.setVisibility(View.INVISIBLE);
                llAccGd.setVisibility(View.VISIBLE);
                accListview.setVisibility(View.GONE);
                break;
            case R.id.ll_acc_clgc:
//                accViewpager.setCurrentItem(1);
//                tvAccGdsx.getPaint().setFakeBoldText(false);
//                tvAccClgc.getPaint().setFakeBoldText(true);
                tvAccGdbold.setVisibility(View.INVISIBLE);
                tvAccClbold.setVisibility(View.VISIBLE);
                llAccGd.setVisibility(View.GONE);
                accListview.setVisibility(View.VISIBLE);
                break;
            case R.id.but_acc_sl:
                startActivity(new Intent(this, WorkorderAcceptActivity.class));
                finish();
                break;
        }
    }

    public void onClickPopIcon(View view) {
        DropPopMenu dropPopMenu = new DropPopMenu(this);
        dropPopMenu.setTriangleIndicatorViewColor(Color.WHITE);
        dropPopMenu.setBackgroundResource(R.drawable.yuanjiao_ffffff_bg_shape);
        dropPopMenu.setItemTextColor(Color.BLACK);
        dropPopMenu.setIsShowIcon(true);
        dropPopMenu.setOnItemClickListener(new DropPopMenu.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id, MenuItem menuItem) {
                startActivity(new Intent(StayAcceptActivity.this, EditWorkorderActivity.class));
            }
        });
        dropPopMenu.setMenuList(getIconMenuList());
        dropPopMenu.show(view);
    }

    private List<MenuItem> getIconMenuList() {
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem(R.mipmap.jiagou_bianji, 1, "编辑工单"));
        return list;
    }
}
