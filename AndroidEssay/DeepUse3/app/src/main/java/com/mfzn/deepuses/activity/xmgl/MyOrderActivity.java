package com.mfzn.deepuses.activity.xmgl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.SearchOrderActivity;
import com.mfzn.deepuses.activityxm.shgd.AlreadyAppraiseActivity;
import com.mfzn.deepuses.activityxm.shgd.AlreadyCancalActivity;
import com.mfzn.deepuses.activityxm.shgd.AlreadyCloseActivity;
import com.mfzn.deepuses.activityxm.shgd.DaiPaigongActivity;
import com.mfzn.deepuses.activityxm.shgd.InServiceActivity;
import com.mfzn.deepuses.activityxm.shgd.StayAcceptActivity;
import com.mfzn.deepuses.activityxm.shgd.WaitAppraiseActivity;
import com.mfzn.deepuses.activityxm.shgd.WaitReceiveActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderAcceptActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderDispatchActivity;
import com.mfzn.deepuses.activityxm.staging.ProjectStagingActivity;
import com.mfzn.deepuses.adapter.fg.ShouhouGongdanAdapter;
import com.mfzn.deepuses.adapter.fg.XiangmuAdapter;
import com.mfzn.deepuses.adapter.xiangmu.WorkorderListAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.brick.CompanyInfoModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.present.xmgl.MyOrderPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlert2Dialog;
import com.wevey.selector.dialog.OpenShouhouDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MyOrderActivity extends BaseMvpActivity<MyOrderPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_search)
    LinearLayout llBassSelect;
    @BindView(R.id.or_xrecycleview)
    XRecyclerContentLayout orXrecycleview;
    @BindView(R.id.ll_or_empty)
    LinearLayout llOrEmpty;

    private WorkorderListAdapter adapter;
    private int pages = 1;

    private int positions;
    private int clickType;// 1 详情 2 派工 3 受理

    private int sumZhuan;
    private int buyPrice;
    private String proID;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    public MyOrderPresnet newP() {
        return new MyOrderPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_workorder_list));
        llBassSelect.setVisibility(View.VISIBLE);

        adapter = new WorkorderListAdapter(this);
        orXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        orXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        orXrecycleview.getRecyclerView().setAdapter(adapter);
        orXrecycleview.getRecyclerView().setRefreshEnabled(true);
        orXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        orXrecycleview.showLoading();

//        getP().getBrick();

        adapter.setOnItemClickListener(new WorkorderListAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                positions = position;
                clickType = 1;
                proID = String.valueOf(adapter.getDataSource().get(position).getProID());
                getP().quanxian(String.valueOf(adapter.getDataSource().get(position).getProID()));
            }
        });

        adapter.setOnItemPgSlClickListener(new WorkorderListAdapter.OnItemPgSlClickListener() {
            @Override
            public void onItemPgClick(View view, int position) {
                positions = position;
                clickType = 2;
                getP().quanxian(String.valueOf(adapter.getDataSource().get(position).getProID()));
            }

            @Override
            public void onItemSlClick(View view, int position) {
                positions = position;
                clickType = 3;
                getP().quanxian(String.valueOf(adapter.getDataSource().get(position).getProID()));
            }
        });

        orXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getP().workorderList(pages);
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getP().workorderList(pages);
            }
        });
        orXrecycleview.onRefresh();

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if (eventMsg.getMsg().equals(Constants.GONGDAN)) {
                        pages = 1;
                        getP().workorderList(pages);
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.ll_bass_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_bass_search:
                startActivity(new Intent(this, SearchOrderActivity.class));
                break;
        }
    }

    public void workorderListSuccess(WorkorderListModel model) {
        List<WorkorderListModel.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llOrEmpty.setVisibility(View.GONE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            orXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        }else {
            if (pages == 1) {
                llOrEmpty.setVisibility(View.VISIBLE);
            }
        }
    }

    //TODO 重构优化
    public void lookQxSuccess(LookQuanxianModel model) {
//        int leftDays = model.getProjectModule().getAfterSale().getLeftDays();
//        if(leftDays > 0) {
            if(clickType == 1) {
                WorkorderListModel.DataBean dataBean = adapter.getDataSource().get(positions);
                int sss = dataBean.getStatus();
                if(sss == 1) {//待受理
                    Intent intent = new Intent(this, StayAcceptActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 2) {//待派工
                    Intent intent = new Intent(this, DaiPaigongActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 3) {//待接单
                    Intent intent = new Intent(this, WaitReceiveActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 4) {//服务中
                    Intent intent = new Intent(this, InServiceActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 5) {//待评价
                    Intent intent = new Intent(this, WaitAppraiseActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 6) {//已评价
                    Intent intent = new Intent(this, AlreadyAppraiseActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                } else if(sss == 7) {//已取消
                    Intent intent = new Intent(this, AlreadyCancalActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 8) {//已关闭
                    Intent intent = new Intent(this, AlreadyCloseActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }
            }else if(clickType == 2) {
                WorkorderListModel.DataBean dataBean = adapter.getDataSource().get(positions);
                Intent intent = new Intent(this, WorkorderDispatchActivity.class);
                intent.putExtra(Constants.SHOUHOU_ORDERNO, dataBean.getOrderNo());
                intent.putExtra(Constants.SHOUHOU_PROIDS, dataBean.getProID());
//                startActivityForResult(intent,Constants.ACCEPT_GONGDAN);
                startActivity(intent);
            }else if(clickType == 3) {
                String orderNo = adapter.getDataSource().get(positions).getOrderNo();
                Intent intent = new Intent(this, WorkorderAcceptActivity.class);
                intent.putExtra(Constants.SHOUHOU_ORDERNO, orderNo);
//                startActivityForResult(intent,Constants.ACCEPT_GONGDAN);
                startActivity(intent);
            }
//        }else {
////            if (UserHelper.getRoleID().equals("1") || UserHelper.getRoleID().equals("2")) {
////                setOpen();
////            } else {
////                PhoneUtils.dialogPhone2(this, "提示",
////                        "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011","4000552011");
////            }
//            setOpen();
//        }
    }

    public void getCompanySuccess(CompanyInfoModel model) {
        int zhuan = (int) Double.parseDouble(model.getZhuan());
        int zhuan2 = (int) Double.parseDouble(model.getGiftZhuan());
        sumZhuan = zhuan + zhuan2;
        List<CompanyInfoModel.LevelRightsBean.ModulePriceBean> modulePrice = model.getLevelRights().getModulePrice();
        for(int i = 0; i < modulePrice.size(); i++) {
            if(modulePrice.get(i).getModuleType() == 1) {
                buyPrice = modulePrice.get(i).getBuyPrice();
            }
        }
    }

    private void setOpen(){
        NormalAlert2Dialog normalAlertDialog = new NormalAlert2Dialog.Builder(this)
                .setHeight(0.25f)  //屏幕高度*0.23
                .setWidth(0.8f)  //屏幕宽度*0.65
                .setContentText("对不起,此项目的售后板块未开通，\n" +
                        "如需使用，请联系企业管理员开\n" +
                        "通售后功能")
                .setContentTextColor(R.color.color_030303)
                .setContentTextSize(15)
                .setLeftButtonText("取消")
                .setLeftButtonTextColor(R.color.color_007AFF)
                .setRightButtonText("知道")
                .setRightButtonTextColor(R.color.color_007AFF)
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
                        dialog.dismiss();
//                        setOpenShouhou();
                    }
                })
                .build();
        normalAlertDialog.show();
    }

    private void setOpenShouhou(){
        new OpenShouhouDialog.Builder(this)
                .setHeight(1f)  //屏幕高度*0.23
                .setWidth(1f)  //屏幕宽度*0.65
                .setBrick(sumZhuan + "")
                .setPrice(buyPrice + "")
                .setName("该项目")
                .setCanceledOnTouchOutside(false)
                .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<OpenShouhouDialog>() {
                    @Override
                    public void clickLeftButton(OpenShouhouDialog dialog, View view) {
                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(OpenShouhouDialog dialog, View view) {
                        if(sumZhuan < buyPrice) {
                            ToastUtil.showToast(MyOrderActivity.this,"您的砖数量不够，请先充值");
                        }else {
                            getP().openBk(proID,"1",buyPrice + "");
                        }
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }

    public void openBk() {
        ToastUtil.showToast(this,"开通成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.GONGDAN);
        RxBus.getInstance().post(eventMsg);
    }
}
