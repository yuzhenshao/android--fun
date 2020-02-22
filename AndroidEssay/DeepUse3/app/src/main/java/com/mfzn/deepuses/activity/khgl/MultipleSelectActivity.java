package com.mfzn.deepuses.activity.khgl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.jiagou.BatchAddStaffActivity;
import com.mfzn.deepuses.adapter.jiagou.MoveStaffAdapter;
import com.mfzn.deepuses.adapter.khgl.MultipleSelectAdapter;
import com.mfzn.deepuses.adapter.khgl.SelectCustomerAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.present.customer.MultipleSelectPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MultipleSelectActivity extends BaseMvpActivity<MultipleSelectPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_content2)
    TextView tvBassContent2;
    @BindView(R.id.wh_xrecycleview)
    XRecyclerContentLayout whXrecycleview;
    @BindView(R.id.ll_wh_empty)
    LinearLayout llWhEmpty;
    @BindView(R.id.ba_recycleview)
    RecyclerView baRecycleview;

    private SelectCustomerAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_multiple_select;
    }

    @Override
    public MultipleSelectPresnet newP() {
        return new MultipleSelectPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("选择客户");
        tvBassContent2.setVisibility(View.VISIBLE);
        tvBassContent2.setText("新建客户");

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        baRecycleview.setLayoutManager(layoutManager2);

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
                if (dataBean.getSelectType()) {
                    dataBean.setSelectType(false);
                } else {
                    dataBean.setSelectType(true);
                }
                adapter.notifyDataSetChanged();
                MultipleSelectAdapter recycleAdapter = new MultipleSelectAdapter(MultipleSelectActivity.this,adapter.getDataSource());
                baRecycleview.setAdapter(recycleAdapter);
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

    @OnClick({R.id.iv_login_back, R.id.tv_bass_content2, R.id.tv_ba_move})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_content2:
                startActivity(new Intent(this, BulidCustomerActivity.class));
                break;
            case R.id.tv_ba_move:
                List<WholeCustomerModel.DataBean> dataSource = adapter.getDataSource();
                String name = null;
                String phone = null;
                String id = null;
                for (int i = 0; i < dataSource.size(); i++) {
                    WholeCustomerModel.DataBean beanXX = dataSource.get(i);
                    if (beanXX.getSelectType()) {
                        if(TextUtils.isEmpty(name)) {
                            name = beanXX.getU_name();
                            phone = beanXX.getCustomerPhone();
                            id = String.valueOf(beanXX.getUid());
                        }else {
                            name = name + "," + beanXX.getU_name();
                            phone = phone + "," + beanXX.getCustomerPhone();
                            id = id + "," + String.valueOf(beanXX.getUid());
                        }
                    }
                }
                if(TextUtils.isEmpty(name)) {
                    ToastUtil.showToast(this,"请选择客户");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(Constants.MULTI_NAME, name);
                intent.putExtra(Constants.MULTI_PHONE, phone);
                intent.putExtra(Constants.MULTI_ID, id);
                setResult(Constants.MAP_LOCATION, intent);
                finish();
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
