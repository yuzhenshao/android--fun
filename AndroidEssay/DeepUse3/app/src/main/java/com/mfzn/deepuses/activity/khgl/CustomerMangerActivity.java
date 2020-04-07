package com.mfzn.deepuses.activity.khgl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.khgl.WholeCustomerAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.present.customer.CustomerManagePresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlert2Dialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class CustomerMangerActivity extends BaseMvpActivity<CustomerManagePresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;

    @BindView(R.id.wh_xrecycleview)
    XRecyclerContentLayout whXrecycleview;
    @BindView(R.id.ll_wh_empty)
    LinearLayout llWhEmpty;

    private WholeCustomerAdapter adapter;
    private int pages = 1;
    private int positions;

    private int dj = -1;
    private int zt = -1;
    private int jv = -1;
    private String djID = "";
    private String ztID = "";
    private String jvID = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_customer_manger;
    }

    @Override
    public CustomerManagePresnet newP() {
        return new CustomerManagePresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_customer_manger));

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
                WholeCustomerModel.DataBean dataBean=adapter.getDataSource().get(position);
                if(dataBean!=null) {
                    Intent intent = new Intent(CustomerMangerActivity.this, CustomerDetailsActivity.class);
                    intent.putExtra(Constants.CUSTOMER_ID, String.valueOf(dataBean.getData_id()));
                    intent.putExtra(Constants.USER_ID, String.valueOf(dataBean.getUid()));
                    intent.putExtra(Constants.COMPANY_CODE, String.valueOf(dataBean.getCompanyID()));
                    intent.putExtra(Constants.CUSTOMER_NAME, dataBean.getU_name());
                    startActivity(intent);
                }
            }
        });
        adapter.setOnPhoneItemClickListener(new WholeCustomerAdapter.OnPhoneItemClickListener() {
            @Override
            public void onItemClick(View view, String phone) {
                PhoneUtils.dialogPhone2(CustomerMangerActivity.this, "拨打",phone,phone);
            }
        });
        adapter.setOnDelItemClickListener(new WholeCustomerAdapter.OnDelItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                positions = position;
                dialogDelelte(String.valueOf(adapter.getDataSource().get(position).getUid()));
            }
        });

        whXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getP().wholeCustomer(pages,"",djID,ztID,jvID);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().wholeCustomer(pages,"",djID,ztID,jvID);
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
                    if (eventMsg.getMsg().equals(Constants.EDIT_SUCC) || eventMsg.getMsg().equals(Constants.CREAT_SUCC) ||
                            eventMsg.getMsg().equals(Constants.FOUNDPROJECT)) {
                        pages = 1;
                        getP().wholeCustomer(pages,"",djID,ztID,jvID);
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.ll_cus_sx, R.id.et_cus_search,R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_cus_sx:
//                startActivity(new Intent(this, SelectTypeActivity.class));
                Router.newIntent(this).to(SelectTypeActivity.class)
                        .putInt(Constants.SELECT_DJ,dj)
                        .putInt(Constants.SELECT_ZT,zt)
                        .putInt(Constants.SELECT_JV,jv)
                        .requestCode(Constants.SELECT_BC)
                        .anim(R.anim.pay_dialog_enter, R.anim.pay_dialog_exit)
                        .launch();
                break;
            case R.id.et_cus_search:
                startActivity(new Intent(this, SearchCustomerActivity.class));
                break;
            case R.id.iv_add:
                startActivity(new Intent(this, BulidCustomerActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.SELECT_BC == requestCode) {
            if (data != null) {
                dj = data.getIntExtra(Constants.SELECT_DJ, -1);
                zt = data.getIntExtra(Constants.SELECT_ZT, -1);
                jv = data.getIntExtra(Constants.SELECT_JV, -1);
                djID = data.getStringExtra(Constants.SELECT_DJID);
                ztID = data.getStringExtra(Constants.SELECT_ZTID);
                if(jv != -1) {
                    jvID = jv + "";
                }else {
                    jvID = "";
                }
                pages = 1;
                getP().wholeCustomer(pages,"",djID,ztID,jvID);
            }
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

    public void delCustomerSuccess() {
        ToastUtil.showToast(this,"删除成功");
        adapter.getDataSource().remove(positions);
        adapter.notifyDataSetChanged();
//        pages = 1;
//        getP().wholeCustomer(pages,"","","","");
    }

    public void dialogDelelte(String djID) {
        NormalAlert2Dialog normalAlertDialog = new NormalAlert2Dialog.Builder(this)
                .setHeight(0.25f)  //屏幕高度*0.23
                .setWidth(0.8f)  //屏幕宽度*0.65
                .setContentText("确认删除客户吗？")
                .setContentTextColor(R.color.color_606266)
                .setContentTextSize(16)
                .setLeftButtonText("取消")
                .setLeftButtonTextColor(R.color.color_4A90E2)
                .setRightButtonText("删除")
                .setRightButtonTextColor(R.color.color_d0021b)
                .setButtonTextSize(17)
                .setTitleText("提示")
                .setTitleTextColor(R.color.color_030303)
                .setTitleTextSize(20)
                .setCanceledOnTouchOutside(true)
                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<NormalAlert2Dialog>() {
                    @Override
                    public void clickLeftButton(NormalAlert2Dialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(NormalAlert2Dialog dialog, View view) {
                        getP().delCustomer(djID);
                        dialog.dismiss();
                    }
                })
                .build();
        normalAlertDialog.show();
    }
}
