package com.mfzn.deepuses.activity.khgl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.khgl.SelectCustomerAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.present.customer.SelectCustomerPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SelectCustomerActivity extends BaseMvpActivity<SelectCustomerPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_content2)
    TextView tvBassContent2;
    @BindView(R.id.wh_xrecycleview)
    XRecyclerContentLayout whXrecycleview;
    @BindView(R.id.ll_wh_empty)
    LinearLayout llWhEmpty;

    private SelectCustomerAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_customer;
    }

    @Override
    public SelectCustomerPresnet newP() {
        return new SelectCustomerPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("选择客户");
        tvBassContent2.setVisibility(View.VISIBLE);
        tvBassContent2.setText("新建客户");

        int add_aa = getIntent().getIntExtra("add_aa", 0);

        adapter = new SelectCustomerAdapter(getContext());
        whXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        whXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        whXrecycleview.getRecyclerView().setAdapter(adapter);
        whXrecycleview.getRecyclerView().setRefreshEnabled(true);
        whXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        whXrecycleview.showLoading();

        adapter.setOnItemClickListener(new SelectCustomerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                WholeCustomerModel.DataBean dataBean = adapter.getDataSource().get(position);
                if(add_aa == 3) {
                    Intent intent = new Intent(SelectCustomerActivity.this, AddFollowActivity.class);
                    intent.putExtra(Constants.ADD_FOLL,String.valueOf(dataBean.getUid()));
                    intent.putExtra(Constants.COMPANY_CODE,String.valueOf(dataBean.getCompanyID()));
                    intent.putExtra(Constants.ADD_FOLL_NAME,dataBean.getU_name());

                    intent.putExtra(Constants.ADD_FOLL_STATUS,dataBean.getFollowStatusID());

                    startActivity(intent);
                }else {
                    Intent intent = new Intent();
                    intent.putExtra(Constants.CREAT_ID, String.valueOf(dataBean.getData_id()));
                    intent.putExtra(Constants.CREAT_NAME, dataBean.getU_name());
                    intent.putExtra(Constants.CREAT_PHONE, dataBean.getCustomerPhone());
                    setResult(Constants.SELECT_KH,intent);
                }
                finish();
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

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if (eventMsg.getMsg().equals(Constants.CREAT_SUCC)) {
                        pages = 1;
                        getP().wholeCustomer(pages,"","","","");
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_content2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_content2:
                Intent intent = new Intent(this, BulidCustomerActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void brickRecordSuccess(WholeCustomerModel models) {
        List<WholeCustomerModel.DataBean> data = models.getData();
        if (data != null && data.size() != 0) {
            if (pages == 1) {
                llWhEmpty.setVisibility(View.GONE);
                whXrecycleview.setVisibility(View.VISIBLE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            whXrecycleview.getRecyclerView().setPage(models.getCurrent_page(), models.getLast_page());
        } else {
            if (pages == 1) {
                llWhEmpty.setVisibility(View.VISIBLE);
            }
        }
    }
}
