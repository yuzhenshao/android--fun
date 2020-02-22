package com.mfzn.deepuses.activity.khgl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hmy.popwindow.PopWindow;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.home.HomeListAdapter;
import com.mfzn.deepuses.adapter.khgl.MyShareAdapter;
import com.mfzn.deepuses.adapter.khgl.SelectCusAdapter;
import com.mfzn.deepuses.adapter.khgl.WholeCustomerAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.khgl.MyShareModel;
import com.mfzn.deepuses.model.khgl.SelectCusModel;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.present.customer.MySharePresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.zxings.CaptureActivity;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlert2Dialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MyShareActivity extends BaseMvpActivity<MySharePresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.wh_xrecycleview)
    XRecyclerContentLayout whXrecycleview;
    @BindView(R.id.ll_wh_empty)
    LinearLayout llWhEmpty;

    private MyShareAdapter adapter;
    private int pages = 1;
    private int positions;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_share;
    }

    @Override
    public MySharePresnet newP() {
        return new MySharePresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("我的共享");

        adapter = new MyShareAdapter(getContext());
        whXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        whXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        whXrecycleview.getRecyclerView().setAdapter(adapter);
        whXrecycleview.getRecyclerView().setRefreshEnabled(true);
        whXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        whXrecycleview.showLoading();

        adapter.setOnItemClickListener(new MyShareAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Boolean showType = adapter.getDataSource().get(position).getShowType();
                if(showType) {
                    adapter.getDataSource().get(position).setShowType(false);
                }else {
                    adapter.getDataSource().get(position).setShowType(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setOnDelItemClickListener(new MyShareAdapter.OnDelItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                positions = position;
                dialogDelelte(String.valueOf(adapter.getDataSource().get(position).getData_id()));
            }
        });

        whXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getP().myShare(pages);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().myShare(pages);
            }
        });
        whXrecycleview.onRefresh();
    }

    @OnClick({R.id.iv_login_back, R.id.iv_sh_xj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_sh_xj:
                startActivityForResult(new Intent(this, AddShareActivity.class), Constants.ADD_SHARE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.ADD_SHARE == requestCode) {
            if (data != null) {
                pages = 1;
                getP().myShare(pages);
            }
        }
    }

    public void myShareSuccess(MyShareModel models) {
        List<MyShareModel.DataBean> data = models.getData();
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

    public void delMyShareSuccess() {
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
                .setContentText("确认删除这条共享消息吗？")
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
                        getP().delMyShare(djID);
                        dialog.dismiss();
                    }
                })
                .build();
        normalAlertDialog.show();
    }
}
