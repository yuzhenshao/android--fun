package com.mfzn.deepuses.activity.khgl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.khgl.MultipleSelectAdapter;
import com.mfzn.deepuses.adapter.khgl.SelectCustomer2Adapter;
import com.mfzn.deepuses.adapter.khgl.SelectCustomerAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.present.customer.MultipleSelect2Presnet;
import com.mfzn.deepuses.present.customer.MultipleSelectPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MultipleSelect2Activity extends BaseMvpActivity<MultipleSelect2Presnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.wh_xrecycleview)
    XRecyclerContentLayout whXrecycleview;
    @BindView(R.id.ll_wh_empty)
    LinearLayout llWhEmpty;
    @BindView(R.id.ba_recycleview)
    RecyclerView baRecycleview;
    @BindView(R.id.tv_bass_content)
    TextView tvBassContent;

    private SelectCustomer2Adapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_multiple_select2;
    }

    @Override
    public MultipleSelect2Presnet newP() {
        return new MultipleSelect2Presnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("选择客户");
        tvBassContent.setVisibility(View.VISIBLE);
        tvBassContent.setText("全选");

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        baRecycleview.setLayoutManager(layoutManager2);

        adapter = new SelectCustomer2Adapter(getContext());
        whXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        whXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        whXrecycleview.getRecyclerView().setAdapter(adapter);
        whXrecycleview.getRecyclerView().setRefreshEnabled(true);
        whXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        whXrecycleview.showLoading();

        adapter.setOnItemClickListener(new SelectCustomer2Adapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                WholeCustomerModel.DataBean dataBean = adapter.getDataSource().get(position);
                if (dataBean.getSelectType()) {
                    dataBean.setSelectType(false);
                } else {
                    dataBean.setSelectType(true);
                }
                adapter.notifyDataSetChanged();
                MultipleSelectAdapter recycleAdapter = new MultipleSelectAdapter(MultipleSelect2Activity.this,adapter.getDataSource());
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
    }

    @OnClick({R.id.iv_login_back, R.id.tv_ba_move,R.id.tv_bass_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_content:
                List<WholeCustomerModel.DataBean> dataSource1 = adapter.getDataSource();
                for(int i = 0; i < dataSource1.size(); i++) {
                    dataSource1.get(i).setSelectType(true);
                }
                adapter.notifyDataSetChanged();
                MultipleSelectAdapter recycleAdapter = new MultipleSelectAdapter(MultipleSelect2Activity.this,adapter.getDataSource());
                baRecycleview.setAdapter(recycleAdapter);
                break;
            case R.id.tv_ba_move:
                List<WholeCustomerModel.DataBean> dataSource = adapter.getDataSource();
                String name = null;
                String level = null;
                String pro = null;
                String id = null;
                for (int i = 0; i < dataSource.size(); i++) {
                    WholeCustomerModel.DataBean beanXX = dataSource.get(i);
                    if (beanXX.getSelectType()) {
                        if(TextUtils.isEmpty(name)) {
                            name = beanXX.getU_name();
                            level = String.valueOf(beanXX.getCustomerLevelID());
                            id = String.valueOf(beanXX.getUid());

                            List<WholeCustomerModel.DataBean.ProsBean> pros = beanXX.getPros();
                            if(pros != null && pros.size() != 0) {
                                String sss = null;
                                for(int i1 = 0; i1 < pros.size(); i1++) {
                                    if(TextUtils.isEmpty(sss)) {
                                        sss = pros.get(i1).getPro_name();
                                    }else {
                                        sss = sss + "，" + pros.get(i1).getPro_name();
                                    }
                                }
                                pro = sss;
                            }else {
                                pro = "暂无分配项目";
                            }


                        }else {
                            name = name + "," + beanXX.getU_name();
                            level = level + "," + beanXX.getCustomerLevelID();
                            id = id + "," + String.valueOf(beanXX.getUid());

                            List<WholeCustomerModel.DataBean.ProsBean> pros = beanXX.getPros();
                            if(pros != null && pros.size() != 0) {
                                String sss = null;
                                for(int i1 = 0; i1 < pros.size(); i1++) {
                                    if(TextUtils.isEmpty(sss)) {
                                        sss = pros.get(i1).getPro_name();
                                    }else {
                                        sss = sss + "，" + pros.get(i1).getPro_name();
                                    }
                                }
                                pro = pro + "/" + sss;
                            }else {
                                pro = pro + "/" + "暂无分配项目";
                            }
                        }
                    }
                }
                if(TextUtils.isEmpty(name)) {
                    ToastUtil.showToast(this,"请选择客户");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(Constants.CUS_NAME, name);
                intent.putExtra(Constants.CUS_LEVEL, level);
                intent.putExtra(Constants.CUS_PRO, pro);
                intent.putExtra(Constants.CUS_ID, id);
                setResult(Constants.SELECT_CUS, intent);
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
