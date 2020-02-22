package com.mfzn.deepuses.fragment.xm;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.jiagou.ManageJiagouActivity;
import com.mfzn.deepuses.activity.jiagou.ZuzhiJiagouActivity;
import com.mfzn.deepuses.activity.project.ProjectDetailsActivity;
import com.mfzn.deepuses.activityxm.FoundProjectActivity;
import com.mfzn.deepuses.activityxm.SearchProjectActivity;
import com.mfzn.deepuses.activityxm.shgd.AddWorkorderActivity;
import com.mfzn.deepuses.activityxm.shgd.AlreadyAppraiseActivity;
import com.mfzn.deepuses.activityxm.shgd.AlreadyCancalActivity;
import com.mfzn.deepuses.activityxm.shgd.AlreadyCloseActivity;
import com.mfzn.deepuses.activityxm.shgd.CheckAppraiseActivity;
import com.mfzn.deepuses.activityxm.shgd.DaiPaigongActivity;
import com.mfzn.deepuses.activityxm.shgd.InServiceActivity;
import com.mfzn.deepuses.activityxm.shgd.StayAcceptActivity;
import com.mfzn.deepuses.activityxm.shgd.WaitAppraiseActivity;
import com.mfzn.deepuses.activityxm.shgd.WaitReceiveActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderAcceptActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderDispatchActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderListActivity;
import com.mfzn.deepuses.activityxm.staging.ProjectStagingActivity;
import com.mfzn.deepuses.adapter.fg.ShouhouGongdanAdapter;
import com.mfzn.deepuses.adapter.fg.XiangmuAdapter;
import com.mfzn.deepuses.adapter.xiangmu.WorkorderListAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.brick.CompanyInfoModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.present.fragment.ShouhouGongdanPresnet;
import com.mfzn.deepuses.present.fragment.XiangmuPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlert2Dialog;
import com.wevey.selector.dialog.OpenShouhouDialog;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class ShouhouGongdanFragment extends BaseMvpFragment<ShouhouGongdanPresnet> {

    @BindView(R.id.ll_shgd_empty)
    LinearLayout llShgdEmpty;
    @BindView(R.id.shgd_xrecycleview)
    XRecyclerContentLayout shgdXrecycleview;

    private ShouhouGongdanAdapter adapter;
    private int pages = 1;

    private int positions;
    private int clickType;// 1 详情 2 派工 3 受理

    private int sumZhuan;
    private int buyPrice;
    private String proID;

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

//        getP().getBrick();

        adapter.setOnItemClickListener(new ShouhouGongdanAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                positions = position;
                clickType = 1;
                proID = String.valueOf(adapter.getDataSource().get(position).getProId());
                getP().quanxian(String.valueOf(adapter.getDataSource().get(position).getProId()));
            }
        });

        adapter.setOnItemPgSlClickListener(new ShouhouGongdanAdapter.OnItemPgSlClickListener() {
            @Override
            public void onItemPgClick(View view, int position) {
                positions = position;
                clickType = 2;
                getP().quanxian(String.valueOf(adapter.getDataSource().get(position).getProId()));
            }

            @Override
            public void onItemSlClick(View view, int position) {
                positions = position;
                clickType = 3;
                getP().quanxian(String.valueOf(adapter.getDataSource().get(position).getProId()));
            }
        });

        shgdXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
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
        shgdXrecycleview.onRefresh();

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

    public void lookQxSuccess(LookQuanxianModel model) {
//        int leftDays = model.getProjectModule().getAfterSale().getLeftDays();
//        if(leftDays > 0) {
            if(clickType == 1) {
                WorkorderListModel.DataBean dataBean = adapter.getDataSource().get(positions);
                int sss = dataBean.getStatus();
                if(sss == 1) {//待受理
                    Intent intent = new Intent(getActivity(), StayAcceptActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 2) {//待派工
                    Intent intent = new Intent(getActivity(), DaiPaigongActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 3) {//待接单
                    Intent intent = new Intent(getActivity(), WaitReceiveActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 4) {//服务中
                    Intent intent = new Intent(getActivity(), InServiceActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 5) {//待评价
                    Intent intent = new Intent(getActivity(), WaitAppraiseActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 6) {//已评价
                    Intent intent = new Intent(getActivity(), AlreadyAppraiseActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                } else if(sss == 7) {//已取消
                    Intent intent = new Intent(getActivity(), AlreadyCancalActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 8) {//已关闭
                    Intent intent = new Intent(getActivity(), AlreadyCloseActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }
            }else if(clickType == 2) {
                WorkorderListModel.DataBean dataBean = adapter.getDataSource().get(positions);
                Intent intent = new Intent(getActivity(), WorkorderDispatchActivity.class);
                intent.putExtra(Constants.SHOUHOU_ORDERNO, dataBean.getOrderNo());
                intent.putExtra(Constants.SHOUHOU_PROIDS, dataBean.getProId() + "");
//                startActivityForResult(intent,Constants.ACCEPT_GONGDAN);
                startActivity(intent);
            }else if(clickType == 3) {
                String orderNo = adapter.getDataSource().get(positions).getOrderNo();
                Intent intent = new Intent(getActivity(), WorkorderAcceptActivity.class);
                intent.putExtra(Constants.SHOUHOU_ORDERNO, orderNo);
//                startActivityForResult(intent,Constants.ACCEPT_GONGDAN);
                startActivity(intent);
            }
//        }else {
////            if (UserHelper.getRoleID().equals("1") || UserHelper.getRoleID().equals("2")) {
////                setOpen();
////            } else {
////                PhoneUtils.dialogPhone2(getActivity(), "提示",
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
        NormalAlert2Dialog normalAlertDialog = new NormalAlert2Dialog.Builder(getActivity())
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
        new OpenShouhouDialog.Builder(getActivity())
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
                            ToastUtil.showToast(getActivity(),"您的砖数量不够，请先充值");
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
        ToastUtil.showToast(getActivity(),"开通成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.GONGDAN);
        RxBus.getInstance().post(eventMsg);
    }
}
