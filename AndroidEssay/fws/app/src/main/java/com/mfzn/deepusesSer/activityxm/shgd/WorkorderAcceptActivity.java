package com.mfzn.deepusesSer.activityxm.shgd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.xiangmu.WorkorderTujianAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.xiangmu.WorkorderTujianModel;
import com.mfzn.deepusesSer.present.foundxm.WorkorderAcceptPresnet;
import com.mfzn.deepusesSer.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WorkorderAcceptActivity extends BaseMvpActivity<WorkorderAcceptPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_wo_slbold)
    TextView tvWoSlbold;
    @BindView(R.id.tv_wo_sl)
    TextView tvWoSl;
    @BindView(R.id.tv_wo_tj)
    TextView tvWoTj;
    @BindView(R.id.wo_listview)
    ListView woListview;

    private List<WorkorderTujianModel> models;
    private WorkorderTujianAdapter adapter;

    private int iftype = 1;
    private String ifcontent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_workorder_accept;
    }

    @Override
    public WorkorderAcceptPresnet newP() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_workorder_accept));
        tvWoSlbold.getPaint().setFakeBoldText(true);
        tvWoTj.getPaint().setFakeBoldText(true);

        models = new ArrayList<>();

        adapter = new WorkorderTujianAdapter(this,models);
        woListview.setAdapter(adapter);
        adapter.setmOnitemclickLisenter(new WorkorderTujianAdapter.OnitemclickLisenter() {
            @Override
            public void onItemClick(View view, int position) {
                models.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.ll_wo_sl, R.id.ll_wo_tj, R.id.but_wo_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_wo_sl:
                Intent intent = new Intent(this, IfAcceptActivity.class);
                intent.putExtra(Constants.IF_ACCEPT_TYPE,iftype);
                intent.putExtra(Constants.IF_ACCEPT_CONTENT,ifcontent);
                startActivityForResult(intent, Constants.IF_ACCEPT);
                break;
            case R.id.ll_wo_tj:
                models.add(new WorkorderTujianModel());
                adapter.notifyDataSetChanged();
                break;
            case R.id.but_wo_commit:
                finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.IF_ACCEPT == requestCode) {
            if (data != null) {
                iftype = data.getIntExtra(Constants.IF_ACCEPT_TYPE, 1);
                ifcontent = data.getStringExtra(Constants.IF_ACCEPT_CONTENT);
                if(iftype == 1) {
                    tvWoSl.setText("受理");
                    tvWoSl.setTextColor(getResources().getColor(R.color.color_606266));
                }else  if(iftype == 2) {
                    tvWoSl.setText("不受理");
                    tvWoSl.setTextColor(getResources().getColor(R.color.color_3D7EFF));
                }
            }
        }
    }
}
