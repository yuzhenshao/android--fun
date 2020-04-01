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

public class DaiPaigongActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_select)
    LinearLayout llBassSelect;
    @BindView(R.id.tv_pai_type)
    TextView tvpaiType;
    @BindView(R.id.tv_pai_typename)
    TextView tvpaiTypename;
    @BindView(R.id.tv_pai_address)
    TextView tvpaiAddress;
    @BindView(R.id.tv_pai_details)
    TextView tvpaiDetails;
    @BindView(R.id.tv_pai_name)
    TextView tvpaiName;
    @BindView(R.id.tv_pai_phone)
    TextView tvpaiPhone;
    @BindView(R.id.tv_pai_gdsx)
    TextView tvpaiGdsx;
    @BindView(R.id.tv_pai_gdbold)
    TextView tvpaiGdbold;
    @BindView(R.id.tv_pai_clgc)
    TextView tvpaiClgc;
    @BindView(R.id.tv_pai_clbold)
    TextView tvpaiClbold;
    @BindView(R.id.ll_pai_clgc)
    LinearLayout llpaiClgc;
    @BindView(R.id.tv_pai_khname)
    TextView tvpaiKhname;
    @BindView(R.id.tv_pai_time)
    TextView tvpaiTime;
    @BindView(R.id.tv_pai_whtime)
    TextView tvpaiWhtime;
    @BindView(R.id.tv_pai_ms)
    TextView tvpaiMs;
    @BindView(R.id.pai_recycleview)
    RecyclerView paiRecycleview;
    @BindView(R.id.ll_pai_gd)
    LinearLayout llpaiGd;
    @BindView(R.id.pai_listview)
    MyListview paiListview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dai_paigong;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_stay_accept));
        llBassSelect.setVisibility(View.VISIBLE);
        tvpaiTypename.getPaint().setFakeBoldText(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        paiRecycleview.setLayoutManager(layoutManager);
//        ShouliPhotoAdapter recycleAdapter = new ShouliPhotoAdapter(this);
//        paiRecycleview.setAdapter(recycleAdapter);

//        ShouliListviewAdapter adapter = new ShouliListviewAdapter(this);
//        paiListview.setAdapter(adapter);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_bass_select, R.id.ll_pai_phone, R.id.ll_pai_gdsx, R.id.ll_pai_clgc, R.id.but_pai_sl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_bass_select:
                onClickPopIcon(view);
                break;
            case R.id.ll_pai_phone:
                if (!TextUtils.isEmpty("12345678912")) {
                    PhoneUtils.dialogPhone(this, "12345678912");
                }
                break;
            case R.id.ll_pai_gdsx:
//                paiViewpager.setCurrentItem(0);
//                tvpaiGdsx.getPaint().setFakeBoldText(true);
//                tvpaiClgc.getPaint().setFakeBoldText(false);
                tvpaiGdbold.setVisibility(View.VISIBLE);
                tvpaiClbold.setVisibility(View.INVISIBLE);
                llpaiGd.setVisibility(View.VISIBLE);
                paiListview.setVisibility(View.GONE);
                break;
            case R.id.ll_pai_clgc:
//                paiViewpager.setCurrentItem(1);
//                tvpaiGdsx.getPaint().setFakeBoldText(false);
//                tvpaiClgc.getPaint().setFakeBoldText(true);
                tvpaiGdbold.setVisibility(View.INVISIBLE);
                tvpaiClbold.setVisibility(View.VISIBLE);
                llpaiGd.setVisibility(View.GONE);
                paiListview.setVisibility(View.VISIBLE);
                break;
            case R.id.but_pai_sl:
                startActivity(new Intent(this, WorkorderDispatchActivity.class));
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
                int itemId = menuItem.getItemId();
                if(itemId == 1) {
                    startActivity(new Intent(DaiPaigongActivity.this, EditWorkorderActivity.class));
                }else if(itemId == 2) {
                    startActivity(new Intent(DaiPaigongActivity.this, CancalAcceptActivity.class));
                }
            }
        });
        dropPopMenu.setMenuList(getIconMenuList());
        dropPopMenu.show(view);
    }

    private List<MenuItem> getIconMenuList() {
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem(R.mipmap.jiagou_bianji, 1, "编辑工单"));
        list.add(new MenuItem(R.mipmap.work_delete2, 2, "取消工单"));
        return list;
    }
}
