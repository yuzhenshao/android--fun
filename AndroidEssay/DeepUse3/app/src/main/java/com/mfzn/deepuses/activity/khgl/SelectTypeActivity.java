package com.mfzn.deepuses.activity.khgl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.khgl.SelectKhdjAdapter;
import com.mfzn.deepuses.adapter.khgl.SelectKhztAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.present.customer.SelectTypePresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.view.MyRecyclerView;
import com.mfzn.deepuses.view.NoScrollGridLayoutManager;
import com.wevey.selector.dialog.bean.SelectModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectTypeActivity extends BaseMvpActivity<SelectTypePresnet> {

    @BindView(R.id.khdj_recyleview)
    MyRecyclerView khdjRecyleview;
    @BindView(R.id.khzt_recyleview)
    MyRecyclerView khztRecyleview;
    @BindView(R.id.tv_se_khdj)
    TextView tvSeKhdj;
    @BindView(R.id.tv_se_khzt)
    TextView tvSeKhzt;
    @BindView(R.id.tv_se_wu)
    TextView tvSeWu;
    @BindView(R.id.tv_se_you)
    TextView tvSeYou;

    private int jvPosition;
    private int djPosition;
    private int ztPosition;
    private String djID = "";
    private String ztID = "";
    private List<SelectModel.CustomerLevelBean> customerLevel = new ArrayList<>();
    private List<SelectModel.FollowStatusBean> followStatus = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_select_type;
    }

    @Override
    public SelectTypePresnet newP() {
        return new SelectTypePresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        Intent intent = getIntent();
        djPosition = intent.getIntExtra(Constants.SELECT_DJ, -1);
        ztPosition = intent.getIntExtra(Constants.SELECT_ZT, -1);
        jvPosition = intent.getIntExtra(Constants.SELECT_JV, -1);

        if(jvPosition == 2) {
            tvSeWu.setSelected(true);
        }else if(jvPosition == 1) {
            tvSeYou.setSelected(true);
        }

        NoScrollGridLayoutManager dlLayoutManager = new NoScrollGridLayoutManager(this,
                3, GridLayoutManager.VERTICAL, false);
        khdjRecyleview.setLayoutManager(dlLayoutManager);

        NoScrollGridLayoutManager ztLayoutManager = new NoScrollGridLayoutManager(this,
                3, GridLayoutManager.VERTICAL, false);
        khztRecyleview.setLayoutManager(ztLayoutManager);

        getP().getSelect();
    }

    @OnClick({R.id.ll_se_del, R.id.but_bc, R.id.tv_se_wu, R.id.tv_se_you})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_se_del:
                finish();
                break;
            case R.id.but_bc:
                if (customerLevel != null && customerLevel.size() != 0) {
                    for(int i = 0; i < customerLevel.size(); i++) {
                        if(customerLevel.get(i).getSelect()) {
                            djPosition = i;
                            djID = String.valueOf(customerLevel.get(i).getData_id());
                        }
                    }
                }
                if (followStatus != null && followStatus.size() != 0) {
                    for(int i = 0; i < followStatus.size(); i++) {
                        if(followStatus.get(i).getSelect()) {
                            ztPosition = i;
                            ztID = String.valueOf(followStatus.get(i).getData_id());
                        }
                    }
                }
                if(TextUtils.isEmpty(djID)) {
                    djPosition = -1;
                }
                if(TextUtils.isEmpty(ztID)) {
                    ztPosition = -1;
                }

                Intent intent = new Intent();
                intent.putExtra(Constants.SELECT_DJ, djPosition);
                intent.putExtra(Constants.SELECT_ZT, ztPosition);
                intent.putExtra(Constants.SELECT_JV, jvPosition);
                intent.putExtra(Constants.SELECT_DJID, djID);
                intent.putExtra(Constants.SELECT_ZTID, ztID);
                setResult(Constants.SELECT_BC,intent);
                finish();
                break;
            case R.id.tv_se_wu:
                if(tvSeWu.isSelected()) {
                    jvPosition = -1;
                    tvSeWu.setSelected(false);
                }else {
                    if(jvPosition == 1) {
                        tvSeYou.setSelected(false);
                    }
                    jvPosition = 2;
                    tvSeWu.setSelected(true);
                }
                break;
            case R.id.tv_se_you:
                if(tvSeYou.isSelected()) {
                    jvPosition = -1;
                    tvSeYou.setSelected(false);
                }else {
                    if(jvPosition == 2) {
                        tvSeWu.setSelected(false);
                    }
                    jvPosition = 1;
                    tvSeYou.setSelected(true);
                }
                break;
        }
    }

    public void getSelectSuccess(SelectModel model) {
        customerLevel = model.getCustomerLevel();
        if (customerLevel != null && customerLevel.size() != 0) {
            if(djPosition != -1) {
                customerLevel.get(djPosition).setSelect(true);
            }
            SelectKhdjAdapter czzxAdapter = new SelectKhdjAdapter(this, customerLevel);
            khdjRecyleview.setAdapter(czzxAdapter);
            czzxAdapter.setOnItemClickListener(new SelectKhdjAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if(customerLevel.get(position).getSelect()) {
                        customerLevel.get(position).setSelect(false);
                    }else {
                        for(int i = 0; i < customerLevel.size(); i++) {
                            customerLevel.get(i).setSelect(false);
                        }
                        customerLevel.get(position).setSelect(true);
                    }
                    czzxAdapter.notifyDataSetChanged();
                }
            });
        } else {
            tvSeKhdj.setVisibility(View.GONE);
            khdjRecyleview.setVisibility(View.GONE);
        }

        followStatus = model.getFollowStatus();
        if (followStatus != null && followStatus.size() != 0) {
            if(ztPosition != -1) {
                followStatus.get(ztPosition).setSelect(true);
            }
            SelectKhztAdapter czzxAdapter = new SelectKhztAdapter(this, followStatus);
            khztRecyleview.setAdapter(czzxAdapter);
            czzxAdapter.setOnItemClickListener(new SelectKhztAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if(followStatus.get(position).getSelect()) {
                        followStatus.get(position).setSelect(false);
                    }else {
                        for(int i = 0; i < followStatus.size(); i++) {
                            followStatus.get(i).setSelect(false);
                        }
                        followStatus.get(position).setSelect(true);
                    }
                    czzxAdapter.notifyDataSetChanged();
                }
            });
        } else {
            tvSeKhzt.setVisibility(View.GONE);
            khztRecyleview.setVisibility(View.GONE);
        }
    }

    @Override
    public void finish() {
        super.finish();
        //注释掉activity本身的过渡动画,不然activity自带的动画还是会执行
        overridePendingTransition(R.anim.pay_dialog_enter, R.anim.pay_dialog_exit);
    }
}
