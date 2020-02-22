package com.mfzn.deepuses.fragment.brick;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.brick.ConsumeBrickAdapter;
import com.mfzn.deepuses.adapter.brick.RechargeBrickAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.model.brick.BrickRecordModel;
import com.mfzn.deepuses.present.brick.ConsumeBrickPresnet;
import com.mfzn.deepuses.present.brick.RechargeBrickPresnet;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class ConsumeBrickFragment extends BaseMvpFragment<ConsumeBrickPresnet> {

    @BindView(R.id.wh_xrecycleview)
    XRecyclerContentLayout whXrecycleview;
    @BindView(R.id.ll_wh_empty)
    LinearLayout llWhEmpty;

    private ConsumeBrickAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_consume_brick;
    }

    @Override
    public ConsumeBrickPresnet newP() {
        return new ConsumeBrickPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        adapter = new ConsumeBrickAdapter(getContext());
        whXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
//        whXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_1dp);//item之间的分割线
        whXrecycleview.getRecyclerView().setAdapter(adapter);
        whXrecycleview.getRecyclerView().setRefreshEnabled(true);
        whXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        whXrecycleview.showLoading();

        whXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getP().brickRecord(pages);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().brickRecord(pages);
            }
        });
        whXrecycleview.onRefresh();
    }

    public void brickRecordSuccess(BrickRecordModel models) {
        List<BrickRecordModel.FinancialLogBean.DataBean> data = models.getFinancialLog().getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llWhEmpty.setVisibility(View.GONE);
                whXrecycleview.setVisibility(View.VISIBLE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            whXrecycleview.getRecyclerView().setPage(models.getFinancialLog().getCurrent_page(), models.getFinancialLog().getLast_page());
        }else {
            if (pages == 1) {
                llWhEmpty.setVisibility(View.VISIBLE);
            }
        }
    }
}
