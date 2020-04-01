package com.mfzn.deepusesSer.activityxm.shgd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.xiangmu.WorkorderListAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.xiangmu.WorkorderListModel;
import com.mfzn.deepusesSer.present.xmgd.WorkorderListPresent;
import com.mfzn.deepusesSer.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class WorkorderListActivity extends BaseMvpActivity<WorkorderListPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_sh)
    LinearLayout llBassSh;
    @BindView(R.id.ll_wo_empty)
    LinearLayout llWoEmpty;
    @BindView(R.id.wo_xrecycleview)
    XRecyclerContentLayout woXrecycleview;

    private String shType = "0";//0全部  1故障保修  2维护升级
    private String proid;

    private WorkorderListAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_workorder_list;
    }

    @Override
    public WorkorderListPresent newP() {
        return new WorkorderListPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_workorder_list));
        llBassSh.setVisibility(View.VISIBLE);

        proid = getIntent().getStringExtra(Constants.SHOUHOU_PROID);

        adapter = new WorkorderListAdapter(getContext());
        woXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        woXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        woXrecycleview.getRecyclerView().setAdapter(adapter);
        woXrecycleview.getRecyclerView().setRefreshEnabled(true);
        woXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        woXrecycleview.showLoading();

//        List<VisitRecordModel> list = new ArrayList<>();
//        VisitRecordModel model = new VisitRecordModel(1);
//        VisitRecordModel model2 = new VisitRecordModel(2);
//        VisitRecordModel model3 = new VisitRecordModel(3);
//        VisitRecordModel model4 = new VisitRecordModel(4);
//        VisitRecordModel model5 = new VisitRecordModel(5);
//        VisitRecordModel model6 = new VisitRecordModel(6);
//        VisitRecordModel model7 = new VisitRecordModel(7);
//        VisitRecordModel model8 = new VisitRecordModel(8);
//        VisitRecordModel model9 = new VisitRecordModel(9);
//
//        list.add(model);
//        list.add(model9);
//        list.add(model2);
//        list.add(model3);
//        list.add(model4);
//        list.add(model5);
//        list.add(model6);
//        list.add(model7);
//        list.add(model8);
//
//        adapter.addData(list);

        adapter.setOnItemClickListener(new WorkorderListAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                int sss = adapter.getDataSource().get(position).getStatus();
                if(sss == 1) {//待受理
                    startActivity(new Intent(WorkorderListActivity.this, StayAcceptActivity.class));
                }else if(sss == 2) {//待派工
                    startActivity(new Intent(WorkorderListActivity.this, DaiPaigongActivity.class));
                }else if(sss == 3) {//待接单
                    startActivity(new Intent(WorkorderListActivity.this, WaitReceiveActivity.class));
                }else if(sss == 4) {//服务中
                    startActivity(new Intent(WorkorderListActivity.this, InServiceActivity.class));
                }else if(sss == 5) {//待评价
                    startActivity(new Intent(WorkorderListActivity.this, WaitAppraiseActivity.class));
                }else if(sss == 6) {//已评价
                    startActivity(new Intent(WorkorderListActivity.this, CheckAppraiseActivity.class));
                } else if(sss == 7) {//已取消
                    startActivity(new Intent(WorkorderListActivity.this, AlreadyCancalActivity.class));
                }else if(sss == 8) {//已关闭
                    startActivity(new Intent(WorkorderListActivity.this, AlreadyCloseActivity.class));
                }
            }
        });

        woXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getP().workorderList(shType,pages,"0");
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().workorderList(shType,pages,"");
            }
        });
        woXrecycleview.onRefresh();
    }

    @OnClick({R.id.iv_login_back, R.id.iv_bass_select, R.id.iv_bass_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_bass_select:
                break;
            case R.id.iv_bass_add:
                startActivity(new Intent(this, AddWorkorderActivity.class));
                break;
        }
    }

    public void workorderListSuccess(WorkorderListModel model) {
        List<WorkorderListModel.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llWoEmpty.setVisibility(View.GONE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            woXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        }else {
            if (pages == 1) {
                llWoEmpty.setVisibility(View.VISIBLE);
            }
        }
    }
}
