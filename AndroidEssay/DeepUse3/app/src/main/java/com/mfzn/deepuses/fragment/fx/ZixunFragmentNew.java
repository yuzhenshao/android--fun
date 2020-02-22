package com.mfzn.deepuses.fragment.fx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.fx.ZixunDetailActivity;
import com.mfzn.deepuses.adapter.faxian.ZxItemAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.model.faxian.News;
import com.mfzn.deepuses.present.faxian.ZixunPresnet;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class ZixunFragmentNew extends BaseMvpFragment<ZixunPresnet> {

    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.zx_xrecycleview)
    XRecyclerContentLayout zxXrecycleview;

    private ZxItemAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_zixun_new;
    }

    @Override
    public ZixunPresnet newP() {
        return new ZixunPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        adapter = new ZxItemAdapter(getContext());
        zxXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        zxXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        zxXrecycleview.getRecyclerView().setAdapter(adapter);
        zxXrecycleview.getRecyclerView().setRefreshEnabled(true);
        zxXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        zxXrecycleview.showLoading();

        adapter.setOnItemClickListener(new ZxItemAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                News.DataBean dataBean = adapter.getDataSource().get(position);
                Intent intent = new Intent(getActivity(), ZixunDetailActivity.class);
                intent.putExtra("content",dataBean);
//                intent.putExtra("content",dataBean.getContent());
//                intent.putExtra("likeNum",String.valueOf(dataBean.getLikeNums()));
//                intent.putExtra("commentNum",String.valueOf(dataBean.getCommentsNums()));
//
//                intent.putExtra("rowNum",String.valueOf(dataBean.getRowNum()));
                startActivity(intent);
            }
        });

        zxXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                adapter.clearData();
                pages = 1;
                getP().newsList(pages);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().newsList(pages);
            }
        });
        zxXrecycleview.onRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clearData();
        pages = 1;
        getP().newsList(pages);
    }

    public void newsListSuccess(News model) {
        List<News.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llEmpty.setVisibility(View.GONE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            zxXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        }else {
            if (pages == 1) {
                llEmpty.setVisibility(View.VISIBLE);
            }
        }
    }
}
