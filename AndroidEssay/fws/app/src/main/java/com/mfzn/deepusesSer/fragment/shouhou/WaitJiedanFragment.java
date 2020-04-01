package com.mfzn.deepusesSer.fragment.shouhou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activityxm.shgd.WaitReceiveActivity;
import com.mfzn.deepusesSer.adapter.shouhou.ShouhouDjdAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.fragment.XiangmuFragment;
import com.mfzn.deepusesSer.model.xiangmu.WorkorderListModel;
import com.mfzn.deepusesSer.present.shouhou.WaitJiedanPresnet;
import com.mfzn.deepusesSer.utils.Constants;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class WaitJiedanFragment extends BaseMvpFragment<WaitJiedanPresnet> {

    @BindView(R.id.ll_shgd_empty)
    LinearLayout llShgdEmpty;
    @BindView(R.id.shgd_xrecycleview)
    XRecyclerContentLayout shgdXrecycleview;

    private ShouhouDjdAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shouhou_gongdan;
    }

    @Override
    public WaitJiedanPresnet newP() {
        return new WaitJiedanPresnet();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clearData();
        pages = 1;
        getP().workorderList(pages,"3", XiangmuFragment.shType);
    }

    public void refreshData(String shType){
        adapter.clearData();
        pages = 1;
        getP().workorderList(pages,"3", shType);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        adapter = new ShouhouDjdAdapter(getContext());
        shgdXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        shgdXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        shgdXrecycleview.getRecyclerView().setAdapter(adapter);
        shgdXrecycleview.getRecyclerView().setRefreshEnabled(true);
        shgdXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        shgdXrecycleview.showLoading();

        adapter.setOnItemClickListener(new ShouhouDjdAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                WorkorderListModel.DataBean dataBean = adapter.getDataSource().get(position);
                Intent intent = new Intent(getActivity(), WaitReceiveActivity.class);
                intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                startActivity(intent);
            }
        });

        shgdXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                adapter.clearData();
                pages = 1;
                getP().workorderList(pages,"3",XiangmuFragment.shType);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().workorderList(pages,"3",XiangmuFragment.shType);
            }
        });
        shgdXrecycleview.onRefresh();
    }

    public void workorderListSuccess(WorkorderListModel model) {
        List<WorkorderListModel.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llShgdEmpty.setVisibility(View.GONE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            shgdXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        }else {
            if (pages == 1) {
                llShgdEmpty.setVisibility(View.VISIBLE);
            }
        }
    }
}
