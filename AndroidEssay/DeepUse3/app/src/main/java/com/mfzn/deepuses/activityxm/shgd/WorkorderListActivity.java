package com.mfzn.deepuses.activityxm.shgd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.xmgl.MyOrderActivity;
import com.mfzn.deepuses.adapter.fg.ShouhouGongdanAdapter;
import com.mfzn.deepuses.adapter.xiangmu.WorkorderListAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.brick.CompanyInfoModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.model.xmhf.VisitRrcordModel;
import com.mfzn.deepuses.popmune.DropPopMenu;
import com.mfzn.deepuses.popmune.MenuItem;
import com.mfzn.deepuses.present.xmgd.WorkorderListPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlert2Dialog;
import com.wevey.selector.dialog.OpenShouhouDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class WorkorderListActivity extends BaseMvpActivity<WorkorderListPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_sh)
    LinearLayout llBassSh;
    @BindView(R.id.ll_wo_empty)
    LinearLayout llWoEmpty;
    @BindView(R.id.wo_xrecycleview)
    XRecyclerContentLayout woXrecycleview;

    private String shType = "0";//0全部  1故障保修  2维护升级
    private String proid;

    private WorkorderListAdapter adapter;
    private int pages = 1;

//    private String customName;
//    private String customTel;
//    private String address;
    private XiangmuModel.DataBean dataBean;

    private List<WorkorderListModel.DataBean> dataSource = new ArrayList<>();
    private int selectType = 0;

    private int positions;
    private int clickType;// 1 详情 2 派工 3 受理

    private int sumZhuan;
    private int buyPrice;
    private String proID;

    @Override
    public int getLayoutId() {
        return R.layout.activity_workorder_list;
    }

    @Override
    public WorkorderListPresent newP() {
        return new WorkorderListPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_workorder_list));
        llBassSh.setVisibility(View.VISIBLE);

//        getP().getBrick();

        dataBean = (XiangmuModel.DataBean) getIntent().getSerializableExtra(Constants.WORK_ORDER);

//        Intent intent = getIntent();
        proid = String.valueOf(dataBean.getData_id());
//        customName = intent.getStringExtra(Constants.SHOUHOU_NAME);
//        customTel = intent.getStringExtra(Constants.SHOUHOU_PHONE);
//        address = intent.getStringExtra(Constants.SHOUHOU_ADDRESS);

        adapter = new WorkorderListAdapter(getContext());
        woXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        woXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_6dp);//item之间的分割线
        woXrecycleview.getRecyclerView().setAdapter(adapter);
        woXrecycleview.getRecyclerView().setRefreshEnabled(true);
        woXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        woXrecycleview.showLoading();

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

        woXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getP().workorderList(proid,shType,pages);
            }

            @Override
            public void onLoadMore(int page) {
                if(selectType == 0) {
                    pages = page;
                }else {
                    pages = 1;
                }
                getP().workorderList(proid,shType,pages);
            }
        });
        woXrecycleview.onRefresh();

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
                        getP().workorderList(proid,shType,pages);
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.iv_bass_select, R.id.iv_bass_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_bass_select:
                onClickPopIcon(view);
                break;
            case R.id.iv_bass_add:
                Intent intent1 = new Intent(this, AddWorkorderActivity.class);
//                intent1.putExtra(Constants.SHOUHOU_PROID,proid);
//                intent1.putExtra(Constants.SHOUHOU_NAME,customName);
//                intent1.putExtra(Constants.SHOUHOU_PHONE,customTel);
//                intent1.putExtra(Constants.SHOUHOU_ADDRESS,address);
                intent1.putExtra(Constants.WORK_ORDER,dataBean);
                startActivity(intent1);
                break;
        }
    }

    public void workorderListSuccess(WorkorderListModel model) {
        List<WorkorderListModel.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            if (pages == 1) {
                llWoEmpty.setVisibility(View.GONE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            woXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        }else {
            if (pages == 1) {
                llWoEmpty.setVisibility(View.VISIBLE);
            }
        }
        dataSource.clear();
        dataSource.addAll(adapter.getDataSource());
    }

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
                intent.putExtra(Constants.SHOUHOU_PROIDS, dataBean.getProID() + "");
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
                            ToastUtil.showToast(WorkorderListActivity.this,"您的砖数量不够，请先充值");
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

    public void onClickPopIcon(View view) {
        DropPopMenu dropPopMenu = new DropPopMenu(this);
        dropPopMenu.setTriangleIndicatorViewColor(Color.WHITE);
        dropPopMenu.setBackgroundResource(R.drawable.yuanjiao_ffffff_bg_shape);
        dropPopMenu.setItemTextColor(Color.BLACK);
        dropPopMenu.setIsShowIcon(true);
        dropPopMenu.setOnItemClickListener(new DropPopMenu.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id, MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if(itemId == 1) {
                    selectType = 0;
                    adapter.setData(dataSource);
                }else if(itemId == 2) {
                    selectType = 1;
                    List<WorkorderListModel.DataBean> models = new ArrayList<>();
                    for(int i = 0; i < dataSource.size(); i++) {
                        int shType = dataSource.get(i).getAsType();
                        if(shType == 1) { // 1故障保修  2维护升级
                            models.add(dataSource.get(i));
                        }
                    }
                    adapter.setData(models);
                }else if(itemId == 3) {
                    selectType = 1;
                    List<WorkorderListModel.DataBean> models = new ArrayList<>();
                    for(int i = 0; i < dataSource.size(); i++) {
                        int shType = dataSource.get(i).getAsType();
                        if(shType == 2) { // 1故障保修  2维护升级
                            models.add(dataSource.get(i));
                        }
                    }
                    adapter.setData(models);
                }
            }
        });
        dropPopMenu.setMenuList(getIconMenuList());
        dropPopMenu.show(view);
    }

    private List<MenuItem> getIconMenuList() {
        List<MenuItem> list = new ArrayList<>();
        list.add(new MenuItem(R.mipmap.quanbu, 1, "全部工单"));
        list.add(new MenuItem(R.mipmap.guzhang, 2, "故障报修"));
        list.add(new MenuItem(R.mipmap.weihu, 3, "维护升级"));
        return list;
    }
}
