package com.mfzn.deepusesSer.fragment.xm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activityxm.shgd.AlreadyCancalActivity;
import com.mfzn.deepusesSer.activityxm.shgd.AlreadyCloseActivity;
import com.mfzn.deepusesSer.activityxm.shgd.CheckAppraiseActivity;
import com.mfzn.deepusesSer.activityxm.shgd.DaiPaigongActivity;
import com.mfzn.deepusesSer.activityxm.shgd.InServiceActivity;
import com.mfzn.deepusesSer.activityxm.shgd.StayAcceptActivity;
import com.mfzn.deepusesSer.activityxm.shgd.WaitAppraiseActivity;
import com.mfzn.deepusesSer.activityxm.shgd.WaitReceiveActivity;
import com.mfzn.deepusesSer.adapter.fg.ShouhouGongdanAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.model.xiangmu.WorkorderListModel;
import com.mfzn.deepusesSer.present.fragment.ShouhouGongdanPresnet;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;


public class ShouhouGongdanFragment extends BaseMvpFragment<ShouhouGongdanPresnet> {

    @BindView(R.id.ll_shgd_empty)
    LinearLayout llShgdEmpty;
    @BindView(R.id.shgd_xrecycleview)
    XRecyclerContentLayout shgdXrecycleview;

    private ShouhouGongdanAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shouhou_gongdan;
    }

    @Override
    public ShouhouGongdanPresnet newP() {
        return new ShouhouGongdanPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        adapter = new ShouhouGongdanAdapter(getContext());
        shgdXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        shgdXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        shgdXrecycleview.getRecyclerView().setAdapter(adapter);
        shgdXrecycleview.getRecyclerView().setRefreshEnabled(true);
        shgdXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        shgdXrecycleview.showLoading();

        adapter.setOnItemClickListener(new ShouhouGongdanAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                int sss = adapter.getDataSource().get(position).getStatus();
                if(sss == 1) {//待受理
                    startActivity(new Intent(getActivity(), StayAcceptActivity.class));
                }else if(sss == 2) {//待派工
                    startActivity(new Intent(getActivity(), DaiPaigongActivity.class));
                }else if(sss == 3) {//待接单
                    startActivity(new Intent(getActivity(), WaitReceiveActivity.class));
                }else if(sss == 4) {//服务中
                    startActivity(new Intent(getActivity(), InServiceActivity.class));
                }else if(sss == 5) {//待评价
                    startActivity(new Intent(getActivity(), WaitAppraiseActivity.class));
                }else if(sss == 6) {//已评价
                    startActivity(new Intent(getActivity(), CheckAppraiseActivity.class));
                } else if(sss == 7) {//已取消
                    startActivity(new Intent(getActivity(), AlreadyCancalActivity.class));
                }else if(sss == 8) {//已关闭
                    startActivity(new Intent(getActivity(), AlreadyCloseActivity.class));
                }
            }
        });

        shgdXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getP().workorderList(pages,"1");
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().workorderList(pages,"1");
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
