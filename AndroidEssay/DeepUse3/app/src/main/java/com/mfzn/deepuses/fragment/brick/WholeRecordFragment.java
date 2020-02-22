package com.mfzn.deepuses.fragment.brick;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.brick.WholeBrickAdapter;
import com.mfzn.deepuses.adapter.brick.WholeRecordAdapter;
import com.mfzn.deepuses.adapter.xiangmu.ShouliListviewAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.model.brick.BrickRecordModel;
import com.mfzn.deepuses.model.brick.TransactionRecordModel;
import com.mfzn.deepuses.model.xiangmu.ChuliGuochengModel;
import com.mfzn.deepuses.present.brick.WholeRecordPresnet;
import com.mfzn.deepuses.present.fragment.ChuliGuochengPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.view.MyListview;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class WholeRecordFragment extends BaseMvpFragment<WholeRecordPresnet> {

    @BindView(R.id.wh_xrecycleview)
    XRecyclerContentLayout whXrecycleview;
    @BindView(R.id.ll_wh_empty)
    LinearLayout llWhEmpty;

    private WholeRecordAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_whole_record;
    }

    @Override
    public WholeRecordPresnet newP() {
        return new WholeRecordPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        adapter = new WholeRecordAdapter(getContext());
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
                getP().transactionRecord(pages);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().transactionRecord(pages);
            }
        });
        whXrecycleview.onRefresh();
    }

    public void transactionRecord(TransactionRecordModel models) {
        List<TransactionRecordModel.FinancialLogBean.DataBean> data = models.getFinancialLog().getData();
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
