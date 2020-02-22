package com.mfzn.deepuses.fragment.khgl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.khgl.CustomerDetailsActivity;
import com.mfzn.deepuses.adapter.khgl.WholeCustomerAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.present.customer.WholeCustomerPresnet;
import com.mfzn.deepuses.utils.PhoneUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class WholeCustomerFragment extends BaseMvpFragment<WholeCustomerPresnet> {

    @BindView(R.id.wh_xrecycleview)
    XRecyclerContentLayout whXrecycleview;
    @BindView(R.id.ll_wh_empty)
    LinearLayout llWhEmpty;

    private WholeCustomerAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_whole_customer;
    }

    @Override
    public WholeCustomerPresnet newP() {
        return new WholeCustomerPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        adapter = new WholeCustomerAdapter(getContext());
        whXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        whXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        whXrecycleview.getRecyclerView().setAdapter(adapter);
        whXrecycleview.getRecyclerView().setRefreshEnabled(true);
        whXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        whXrecycleview.showLoading();

        adapter.setOnItemClickListener(new WholeCustomerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                startActivity(new Intent(getActivity(), CustomerDetailsActivity.class));
            }
        });
        adapter.setOnPhoneItemClickListener(new WholeCustomerAdapter.OnPhoneItemClickListener() {
            @Override
            public void onItemClick(View view, String phone) {
                PhoneUtils.dialogPhone2(getActivity(), "拨打",phone,phone);
            }
        });

        whXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getP().wholeCustomer(pages,"","","","");
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().wholeCustomer(pages,"","","","");
            }
        });
        whXrecycleview.onRefresh();
    }

    @OnClick({R.id.et_cus_search, R.id.ll_cus_sx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_cus_search:
                break;
            case R.id.ll_cus_sx:
                break;
        }
    }

    public void brickRecordSuccess(WholeCustomerModel models) {
        List<WholeCustomerModel.DataBean> data = models.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llWhEmpty.setVisibility(View.GONE);
                whXrecycleview.setVisibility(View.VISIBLE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            whXrecycleview.getRecyclerView().setPage(models.getCurrent_page(), models.getLast_page());
        }else {
            if (pages == 1) {
                llWhEmpty.setVisibility(View.VISIBLE);
            }
        }
    }
}
