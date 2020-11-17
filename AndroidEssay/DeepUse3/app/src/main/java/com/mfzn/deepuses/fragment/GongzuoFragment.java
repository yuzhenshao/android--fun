package com.mfzn.deepuses.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hmy.popwindow.PopWindow;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.KbbzActivity;
import com.mfzn.deepuses.activity.PpcbActivity;
import com.mfzn.deepuses.activity.company.EstablishCompanyActivity;
import com.mfzn.deepuses.activity.company.EstablishJoinActivity;
import com.mfzn.deepuses.activity.jiagou.ManageJiagouActivity;
import com.mfzn.deepuses.activity.jiagou.ShareCodeActivity;
import com.mfzn.deepuses.activity.jiagou.ZuzhiJiagouActivity;
import com.mfzn.deepuses.activity.khgl.BulidCustomerActivity;
import com.mfzn.deepuses.activity.khgl.CustomerMangerActivity;
import com.mfzn.deepuses.activity.khgl.MyCustomerActivity;
import com.mfzn.deepuses.activity.khgl.MyShareActivity;
import com.mfzn.deepuses.activity.khgl.MyTaskActivity;
import com.mfzn.deepuses.activity.khgl.SelectCustomerActivity;
import com.mfzn.deepuses.activity.myteam.ManageSettingActivity;
import com.mfzn.deepuses.activity.myteam.TeamManageActivity;
import com.mfzn.deepuses.activity.project.ProjectManageActivity;
import com.mfzn.deepuses.activity.xmgl.MyOrderActivity;
import com.mfzn.deepuses.activity.xmgl.MyProjectActivity;
import com.mfzn.deepuses.activity.xmgl.SelectProjectActivity;
import com.mfzn.deepuses.activityfx.ChangjingListActivity;
import com.mfzn.deepuses.activityfx.ZixunListActivity;
import com.mfzn.deepuses.activitymy.brick.BrickActivity;
import com.mfzn.deepuses.activitymy.brick.RechargeActivity;
import com.mfzn.deepuses.activitymy.brick.TransactionRecordActivity;
import com.mfzn.deepuses.activityxm.FoundProjectActivity;
import com.mfzn.deepuses.activityxm.kf.CustomSettingActivity;
import com.mfzn.deepuses.adapter.home.HomeCzzxAdapter;
import com.mfzn.deepuses.adapter.home.HomeKbppAdapter;
import com.mfzn.deepuses.adapter.home.HomeKehuglAdapter;
import com.mfzn.deepuses.adapter.home.HomeKhglAdapter;
import com.mfzn.deepuses.adapter.home.HomeTdglAdapter;
import com.mfzn.deepuses.adapter.home.HomeWdxmAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.bean.constants.ParameterConstant;
import com.mfzn.deepuses.bean.response.AppStatisticsDataResponse;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.common.homecompany.HomeCompanyAdapter;
import com.mfzn.deepuses.common.homecompany.HomeCompanyView;
import com.mfzn.deepuses.model.LookQuanxian2Model;
import com.mfzn.deepuses.model.company.CompanyRepository;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.home.HomeShowModel;
import com.mfzn.deepuses.model.home.JudgeLevelModel;
import com.mfzn.deepuses.model.home.KanbDataModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.fragment.GongzuoPresnet;
import com.mfzn.deepuses.purchasesellsave.AppStatisticsDataActivity;
import com.mfzn.deepuses.purchasesellsave.capital.activity.AddBorrowActivity;
import com.mfzn.deepuses.purchasesellsave.capital.activity.AddIncomeExpenseActivity;
import com.mfzn.deepuses.purchasesellsave.capital.activity.BorrowListActivity;
import com.mfzn.deepuses.purchasesellsave.capital.activity.IncomeExpenseListActivity;
import com.mfzn.deepuses.purchasesellsave.capital.activity.IncomeExpenseTypeListActivity;
import com.mfzn.deepuses.purchasesellsave.capital.activity.ShouldGatherePayActivity;
import com.mfzn.deepuses.purchasesellsave.purchase.AddOrderPurchaseActivity;
import com.mfzn.deepuses.purchasesellsave.purchase.OrderPurchaseListActivity;
import com.mfzn.deepuses.purchasesellsave.sale.activity.AddOrderOfferActivity;
import com.mfzn.deepuses.purchasesellsave.sale.activity.AddOrderSalesActivity;
import com.mfzn.deepuses.purchasesellsave.sale.activity.AddOrderSalesBackActivity;
import com.mfzn.deepuses.purchasesellsave.sale.activity.AddOrderTakeActivity;
import com.mfzn.deepuses.purchasesellsave.sale.activity.AddOrderTakeBackActivity;
import com.mfzn.deepuses.purchasesellsave.sale.activity.OrderOfferListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.CommodityCreateActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.CommodityPhotoCreateActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsCategoryManagerActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.GoodsUnitListManagetActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.MoneyAccountListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.OtherCostActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.MyStoreListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.PersonStoreListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.SettingCustomerMangerActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.StoreListActivity;
import com.mfzn.deepuses.purchasesellsave.setting.activity.SupplierListManagerActivity;
import com.mfzn.deepuses.purchasesellsave.shop.activity.AddShopActivity;
import com.mfzn.deepuses.purchasesellsave.shop.activity.ShopListManagerActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.AddOrderAllotActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.AddOrderOtherInActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.AddOrderOtherOutActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.OrderAllotListActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.OrderOtherInOutListActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.AddOrderStockCheckActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.OrderWaitInOutListActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.StockListActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.StockLogListActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.StockWarningListActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.StoreAllCheckListActivity;
import com.mfzn.deepuses.purchasesellsave.store.activity.StoreCheckListActivity;
import com.mfzn.deepuses.purchasesellsave.user.TodoOrderCheckActivity;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.ObtainTime;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.MyRecyclerView;
import com.mfzn.deepuses.view.NoScrollGridLayoutManager;
import com.mfzn.deepuses.view.RoundImageView;
import com.mfzn.deepuses.zxings.CaptureActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class GongzuoFragment extends BaseMvpFragment<GongzuoPresnet> {

    @BindView(R.id.iv_work_icon)
    RoundImageView ivWorkIcon;
    @BindView(R.id.tv_work_name)
    TextView tvWorkName;
    @BindView(R.id.tv_work_type)
    TextView tvWorkType;
    @BindView(R.id.tv_work_company)
    TextView tvWorkCompany;

    @BindView(R.id.today_in_store_view)
    TextView mTodayInStoreView;
    @BindView(R.id.today_out_store_view)
    TextView mTodayOutStoreView;
    @BindView(R.id.sum_store_num_view)
    TextView mSumStoreNumView;
    @BindView(R.id.today_sales_income_view)
    TextView mTodaySalesIncomeView;
    @BindView(R.id.today_money_view)
    TextView mTodayMoneyView;
    @BindView(R.id.customer_should_gathering_view)
    TextView mCustomerShouldGatheringView;


    @BindView(R.id.jcsz_recyleview)
    MyRecyclerView jcszRecyleview;
    @BindView(R.id.spgl_recyleview)
    MyRecyclerView spglRecyleview;
    @BindView(R.id.xsgl_recyleview)
    MyRecyclerView xsglRecyleview;
    @BindView(R.id.ckgl_recyleview)
    MyRecyclerView ckglRecyleview;
    @BindView(R.id.mdgl_recyleview)
    MyRecyclerView mdglRecyleview;

    @BindView(R.id.cwgl_recyleview)
    MyRecyclerView cwglRecyleview;
    @BindView(R.id.cggl_recyleview)
    MyRecyclerView cgglRecyleview;
    @BindView(R.id.shgl_recyleview)
    MyRecyclerView shglRecyleview;
    @BindView(R.id.tdgl_recyleview)
    MyRecyclerView tdglRecyleview;
    @BindView(R.id.xmgl_recyleview)
    MyRecyclerView xmglRecyleview;
    @BindView(R.id.kbpp_recyleview)
    MyRecyclerView kbppRecyleview;
    @BindView(R.id.fxzx_recyleview)
    MyRecyclerView fxzxRecyleview;
    @BindView(R.id.czzx_recyleview)
    MyRecyclerView czzxRecyleview;
    @BindView(R.id.khgl_recyleview)
    MyRecyclerView khglRecyleview;
    @BindView(R.id.tv_home_czzx)
    TextView tv_home_czzx;
    @BindView(R.id.toco_number)
    TextView tocoNumberView;
    @BindView(R.id.todo_container)
    RelativeLayout todoContainer;

    private PopWindow popWindow;
    //private List<SelectCompanyModel> models = new ArrayList<>();

    private List<HomeShowModel> homeShowModels = new ArrayList<>();

    private List<HomeShowModel> tdglModels = new ArrayList<>();


    //进销存基础设置
    private List<HomeShowModel> jxcjcszModel = new ArrayList<>();
    //商品管理
    private List<HomeShowModel> spglModel = new ArrayList<>();
    //销售管理
    private List<HomeShowModel> xsglModel = new ArrayList<>();
    //仓库管理
    private List<HomeShowModel> ckglModel = new ArrayList<>();
    //门店管理
    private List<HomeShowModel> mdglModel = new ArrayList<>();
    //财务管理
    private List<HomeShowModel> cwglModel = new ArrayList<>();
    //财务管理
    private List<HomeShowModel> cgglModel = new ArrayList<>();
    //项目管理
    private List<HomeShowModel> xmglModel = new ArrayList<>();
    //客户管理
    private List<HomeShowModel> khglModel = new ArrayList<>();
    //发现咨询
    private List<HomeShowModel> fxzxModel = new ArrayList<>();
    //口碑品牌
    private List<HomeShowModel> kbppModel = new ArrayList<>();
    //充值中心
    private List<HomeShowModel> czzxModel = new ArrayList<>();

    private String roleID;
    private String authCreate;
    private String authManage;
    private SelectCompanyModel curCompany;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gongzuo;
    }

    @Override
    public GongzuoPresnet newP() {
        return new GongzuoPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvWorkName.getPaint().setFakeBoldText(true);
        tvWorkType.getPaint().setFakeBoldText(true);

        tvWorkName.setText(UserHelper.getU_name() + " ");

        setTime();

        getP().judgeLevel();
        getP().kanbData();
        getP().userInfo();
        getCompanyList();
        setRecyleview();
        setDatas();
        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if (eventMsg.getMsg().equals(Constants.ESTABLSISH)) {
                        getP().companyList();
                    } else if (eventMsg.getMsg().equals(Constants.COMPANY_NAME)) {
                        tvWorkCompany.setText(UserHelper.getCompanyName());
                    } else if (eventMsg.getMsg().equals(Constants.COMPANYJC)) {
                        getP().companyList();
                        tvWorkCompany.setText(UserHelper.getCompanyName());
                    }
                }
            }
        });
    }

    private void getCompanyList() {
        showDialog();
        getP().companyList();
    }

    public void companyListSuccess(List<SelectCompanyModel> models) {
        hideDialog();
        CompanyRepository.getInstance().setCompanyModels(models);
        curCompany = CompanyRepository.getInstance().getCurCompany();
        if (curCompany != null) {
            getP().quanxian();
            tvWorkCompany.setText(curCompany.getCompanyName());
        } else {
            tvWorkCompany.setText("请您创建或者加入公司");
        }
    }

    public void setTime() {
        Date d = new Date();
        if (d.getHours() < 11) {
            tvWorkType.setText("上午好!");
        } else if (d.getHours() < 13) {
            tvWorkType.setText("中午好!");
        } else if (d.getHours() < 18) {
            tvWorkType.setText("下午好!");
        } else if (d.getHours() < 24) {
            tvWorkType.setText("晚上好!");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            tvWorkName.setText(UserHelper.getU_name() + " ");
            setTime();
            getP().kanbData();
            getP().companyList();
            getP().judgeLevel();
            getP().userInfo();
        }
    }

    @OnClick({R.id.more_data_btn,R.id.iv_work_scan, R.id.iv_work_xia, R.id.todo_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.more_data_btn:
                startActivity(new Intent(getActivity(), AppStatisticsDataActivity.class));
                break;
            case R.id.iv_work_scan:
                startActivity(new Intent(getActivity(), CaptureActivity.class));
                break;
            case R.id.iv_work_xia:
                if (curCompany == null) {
                    Intent intent = new Intent(getActivity(), EstablishJoinActivity.class);
                    intent.putExtra("EstablishJoin", 2);
                    startActivity(intent);
                } else {
                    HomeCompanyView customView = new HomeCompanyView(getActivity(), new HomeCompanyAdapter.CompanyShopListener() {

                        @Override
                        public void companyShopSelected(SelectCompanyModel companyModel, SelectCompanyModel.ShopResponse shop) {
                            curCompany = companyModel;
                            CompanyRepository.getInstance().setCurCompany(companyModel);
                            CompanyRepository.getInstance().setCurShopResponse(shop);
                            tvWorkCompany.setText(curCompany.getCompanyName());
                            UserHelper.setCompany(curCompany.getCompanyID(), curCompany.getCompanyName());
                            UserHelper.setShopId(shop.getShopID());
                            getP().kanbData();
                            getP().judgeLevel();
                            getP().quanxian2();
                            popWindow.dismiss();
                        }
                    });
                    customView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popWindow.dismiss();
                        }
                    });
                    popWindow = new PopWindow.Builder(getActivity())
                            .setStyle(PopWindow.PopWindowStyle.PopDown)
                            .setView(customView)
                            .show(view);
                }
                break;
            case R.id.todo_container:
                startActivity(new Intent(getActivity(), TodoOrderCheckActivity.class));
                break;
        }
    }

    public void lookQxSuccess(LookQuanxian2Model model) {
        int leftDays = model.getCompanyModule().getAfterSale().getLeftDays();
        if (TextUtils.isEmpty(UserHelper.getLookqx())) {
            UserHelper.setLookqx(ObtainTime.endTime());
            if (leftDays > 0) {
                PhoneUtils.dialogPhone2(getActivity(), "提示",
                        "售后板块试用期还剩" + leftDays + "天，到期后\n" +
                                "如需继续使用，请拨打客服电话\n" +
                                "400-055-2011", "4000552011");
            } else {
//                PhoneUtils.dialogPhone2(getActivity(), "提示",
//                        "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011","4000552011");
            }
        } else {
            if (!ObtainTime.endTime().equals(UserHelper.getLookqx())) {
                UserHelper.setLookqx(ObtainTime.endTime());
                if (leftDays > 0) {
                    PhoneUtils.dialogPhone2(getActivity(), "提示",
                            "售后板块试用期还剩" + leftDays + "天，到期后\n" +
                                    "如需继续使用，请拨打客服电话\n" +
                                    "400-055-2011", "4000552011");
                } else {
//                    PhoneUtils.dialogPhone2(getActivity(), "提示",
//                            "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011","4000552011");
                }
            }
        }
    }

    public void lookQxSuccess2(LookQuanxian2Model model) {
        int leftDays = model.getCompanyModule().getAfterSale().getLeftDays();
        UserHelper.setLookqx(ObtainTime.endTime());
        if (leftDays > 0) {
            PhoneUtils.dialogPhone2(getActivity(), "提示",
                    "售后板块试用期还剩" + leftDays + "天，到期后\n" +
                            "如需继续使用，请拨打客服电话\n" +
                            "400-055-2011", "4000552011");
        } else {
//            PhoneUtils.dialogPhone2(getActivity(), "提示",
//                    "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011","4000552011");
        }
    }

    private void setRecyleview() {
        NoScrollGridLayoutManager jcshLayoutManager = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        jcszRecyleview.setLayoutManager(jcshLayoutManager);
        //商品管理
        NoScrollGridLayoutManager spLayoutManager = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        spglRecyleview.setLayoutManager(spLayoutManager);
        //销售管理
        NoScrollGridLayoutManager xsLayoutManager = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        xsglRecyleview.setLayoutManager(xsLayoutManager);
        //仓库管理
        NoScrollGridLayoutManager ckLayoutManager = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        ckglRecyleview.setLayoutManager(ckLayoutManager);
        //门店管理
        NoScrollGridLayoutManager mdLayoutManager = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        mdglRecyleview.setLayoutManager(mdLayoutManager);

        //财务管理
        NoScrollGridLayoutManager cwLayoutManager = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        cwglRecyleview.setLayoutManager(cwLayoutManager);

        //财务管理
        NoScrollGridLayoutManager cgLayoutManager = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        cgglRecyleview.setLayoutManager(cgLayoutManager);

        //充值中心
        NoScrollGridLayoutManager czLayoutManager = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        czzxRecyleview.setLayoutManager(czLayoutManager);

        //项目管理
        NoScrollGridLayoutManager xmLayoutManager = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        xmglRecyleview.setLayoutManager(xmLayoutManager);

        //客户管理
        NoScrollGridLayoutManager khglLayoutManager = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        khglRecyleview.setLayoutManager(khglLayoutManager);

        //售后管理
        NoScrollGridLayoutManager appLayoutManager = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        shglRecyleview.setLayoutManager(appLayoutManager);

        //团队管理
        NoScrollGridLayoutManager appLayoutManagerw = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        tdglRecyleview.setLayoutManager(appLayoutManagerw);

        //口碑品牌
        NoScrollGridLayoutManager fxzxLayoutManagerw = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        fxzxRecyleview.setLayoutManager(fxzxLayoutManagerw);

        NoScrollGridLayoutManager kbppLayoutManagerw = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        kbppRecyleview.setLayoutManager(kbppLayoutManagerw);
    }

    //用户信息成功返回
    public void userInfoSuccess(UserResponse result) {
        if (!TextUtils.isEmpty(result.getUserAvatar())) {
            Glide.with(context).load(ApiHelper.BASE_URL + result.getUserAvatar()).into(ivWorkIcon);
        }
    }

    public void judgeLevelSuccess(JudgeLevelModel model) {
        roleID = model.getRoleID();  //1:创建者  2：超级管理员  3：普普通通管理员 4：普通员工
        UserHelper.setRoleID(model.getRoleID());
        authCreate = model.getAuthCreate();
        UserHelper.setAuthCreate(authCreate);
        authManage = model.getAuthManage();
        setVip();
        setKhgl();
        setGlysz();
    }

    private void setGlysz() {
        tdglModels.clear();
        //团队管理
        HomeShowModel showModel6 = new HomeShowModel("组织架构", "zzjg", R.mipmap.work_jiagou);
        HomeShowModel showModel7 = new HomeShowModel("企业设置", "qysz", R.mipmap.home_qysz);
        HomeShowModel showModel15 = new HomeShowModel("企业二维码", "qyewm", R.mipmap.pro_code);
        HomeShowModel showModel14 = new HomeShowModel("管理员设置", "glysz", R.mipmap.home_glysz);
        HomeShowModel showModel8 = new HomeShowModel("创建团队", "cjtd", R.mipmap.home_chuangjian2);
        HomeShowModel showModel9 = new HomeShowModel("加入团队", "jrtd", R.mipmap.home_jiaru);
        tdglModels.add(showModel6);
        tdglModels.add(showModel7);
        tdglModels.add(showModel15);
        if (authCreate.equals("1")) {
            tdglModels.add(showModel14);
        }
        tdglModels.add(showModel8);
        tdglModels.add(showModel9);

        HomeTdglAdapter tdglAdapter = new HomeTdglAdapter(getActivity(), tdglModels);
        tdglRecyleview.setAdapter(tdglAdapter);

        tdglAdapter.setOnItemClickListener(new HomeTdglAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String type = tdglModels.get(position).getType();
                if ("cjtd".equals(type)) {
                    startActivity(new Intent(getActivity(), EstablishCompanyActivity.class));
                    return;
                }
                if ("jrtd".equals(type)) {
                    Intent intent = new Intent(getActivity(), EstablishJoinActivity.class);
                    intent.putExtra("EstablishJoin", 2);
                    startActivity(intent);
                    return;
                }

                if (curCompany == null) {
                    ToastUtil.showToast(getActivity(), "请先选择公司");
                    return;
                }
                switch (type) {
                    case "zzjg":

//                            if (roleID.equals("1") || roleID.equals("2")) {
                        if (authCreate.equals("1")) {
                            startActivity(new Intent(context, ManageJiagouActivity.class));
                        } else {
                            startActivity(new Intent(context, ZuzhiJiagouActivity.class));
                        }

                        break;
                    case "qysz":

                        if (authCreate.equals("1")) {
                            startActivity(new Intent(context, TeamManageActivity.class));
                        } else {
                            ToastUtil.showToast(getActivity(), "您还没有该权限，请联系管理员");
                        }

                        break;
                    case "qyewm":

                        startActivity(new Intent(getActivity(), ShareCodeActivity.class));
                        break;
                    case "glysz":
                        if (authCreate.equals("1")) {
                            startActivity(new Intent(getActivity(), ManageSettingActivity.class));
                        } else {
                            ToastUtil.showToast(getActivity(), "您还没有该权限，请联系管理员");
                        }
                        break;
                }
            }
        });
    }

    private void setKhgl() {
        khglModel.clear();
        //客户管理
        HomeShowModel showMode20 = new HomeShowModel("公司客户", "khgl", R.mipmap.home_khgl);
        HomeShowModel showMode21 = new HomeShowModel("我的客户", "wdkh", R.mipmap.home_wdkh);
        HomeShowModel showMode22 = new HomeShowModel("添加客户", "xjkh", R.mipmap.home_xjkh);
        HomeShowModel showMode23 = new HomeShowModel("分享客户", "fxkh", R.mipmap.home_wdgx);
        HomeShowModel showMode24 = new HomeShowModel("新增跟进", "gjjd", R.mipmap.home_gjjd);
        HomeShowModel showMode25 = new HomeShowModel("我的任务", "wdrw", R.mipmap.home_wdrw);

        if (authCreate.equals("1")) {// 1显示 0不显示
            khglModel.add(showMode20);
        }
        khglModel.add(showMode21);
        khglModel.add(showMode22);
        if (authCreate.equals("1")) {// 1显示 0不显示
            khglModel.add(showMode23);
        }
        khglModel.add(showMode24);
        khglModel.add(showMode25);
        HomeKehuglAdapter khglAdapter = new HomeKehuglAdapter(getActivity(), khglModel);
        khglRecyleview.setAdapter(khglAdapter);

        khglAdapter.setOnItemClickListener(new HomeKehuglAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (curCompany == null) {
                    ToastUtil.showToast(getActivity(), "请先选择公司");
                    return;
                }
                String type = khglModel.get(position).getType();
                switch (type) {
                    case "khgl":
                        startActivity(new Intent(getActivity(), CustomerMangerActivity.class));
                        break;
                    case "wdkh":
                        startActivity(new Intent(getActivity(), MyCustomerActivity.class));
                        break;
                    case "xjkh":
                        startActivity(new Intent(getActivity(), BulidCustomerActivity.class));
                        break;
                    case "fxkh":
                        startActivity(new Intent(getActivity(), MyShareActivity.class));
                        break;
                    case "gjjd":
                        Intent intent = new Intent(getActivity(), SelectCustomerActivity.class);
                        intent.putExtra("add_aa", 3);
                        startActivity(intent);
                        break;
                    case "wdrw":
                        startActivity(new Intent(getActivity(), MyTaskActivity.class));
                        break;
                }
            }
        });
    }

    private void setVip() {
        if (roleID.equals("1") || roleID.equals("2")) {
            czzxRecyleview.setVisibility(View.VISIBLE);
            tv_home_czzx.setVisibility(View.VISIBLE);
            czzxModel.clear();
            //充值中心
            HomeShowModel showModel17 = new HomeShowModel("公司会员", "gshy", R.mipmap.home_huiyuan);
            HomeShowModel showModel16 = new HomeShowModel("充值中心", "czzx", R.mipmap.home_czzx);
            HomeShowModel showModel18 = new HomeShowModel("用砖记录", "yzjl", R.mipmap.home_jilu);
            czzxModel.add(showModel17);
            czzxModel.add(showModel16);
            czzxModel.add(showModel18);
            HomeCzzxAdapter czzxAdapter = new HomeCzzxAdapter(getActivity(), czzxModel);
            czzxRecyleview.setAdapter(czzxAdapter);
            czzxAdapter.setOnItemClickListener(new HomeCzzxAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if (curCompany == null) {
                        ToastUtil.showToast(getActivity(), "请先选择公司");
                        return;
                    }
                    String type = czzxModel.get(position).getType();
                    switch (type) {
                        case "gshy":
                            startActivity(new Intent(getActivity(), BrickActivity.class));
                            break;
                        case "czzx":
                            startActivity(new Intent(getActivity(), RechargeActivity.class));
                            break;
                        case "yzjl":
                            startActivity(new Intent(getActivity(), TransactionRecordActivity.class));
                            break;
                    }
                }
            });
        } else {
            czzxRecyleview.setVisibility(View.GONE);
            tv_home_czzx.setVisibility(View.GONE);
        }
    }

    public void kanbDataSuccess(AppStatisticsDataResponse mAppStatisticsDataResponse) {
        mTodayInStoreView.setText(mAppStatisticsDataResponse.getTodayInStore()+"");
        mTodayOutStoreView.setText(mAppStatisticsDataResponse.getTodayOutStore()+"");
        mSumStoreNumView.setText(mAppStatisticsDataResponse.getSumStoreNum()+"");
        mTodaySalesIncomeView.setText(mAppStatisticsDataResponse.getTodaySalesIncome()+"");
        mTodayMoneyView.setText(mAppStatisticsDataResponse.getTodayMoney()+"");
        mCustomerShouldGatheringView.setText(mAppStatisticsDataResponse.getCustomerShouldGathering()+"");
    }

    private void setDatas() {
        jxcjcszModel.add(new HomeShowModel("客户管理", "khgl", R.mipmap.home_khgl));
        jxcjcszModel.add(new HomeShowModel("供应商管理", "gysgl", R.mipmap.icon_gygls));
        jxcjcszModel.add(new HomeShowModel("仓库管理", "ckgl", R.mipmap.icon_ckgl));
        jxcjcszModel.add(new HomeShowModel("其他费用管理", "qtfygl", R.mipmap.icon_qtfygl));
        jxcjcszModel.add(new HomeShowModel("商品类别", "splb", R.mipmap.icon_flgl));
        jxcjcszModel.add(new HomeShowModel("商品单位", "spdw", R.mipmap.icon_dwgl));
        HomeWdxmAdapter jcszAdapter = new HomeWdxmAdapter(getActivity(), jxcjcszModel);
        jcszRecyleview.setAdapter(jcszAdapter);
        jcszAdapter.setOnItemClickListener(new HomeWdxmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (curCompany == null) {
                    ToastUtil.showToast(getActivity(), "请先选择公司");
                    return;
                }
                String type = jxcjcszModel.get(position).getType();
                Intent intent = new Intent();
                switch (type) {
                    case "khgl":
                        intent.setClass(getActivity(), SettingCustomerMangerActivity.class);
                        startActivity(intent);
                        break;
                    case "gysgl":
                        intent.setClass(getActivity(), SupplierListManagerActivity.class);
                        startActivity(intent);
                        break;
                    case "ckgl":
                        intent.setClass(getActivity(), StoreListActivity.class);
                        startActivity(intent);
                        break;
                    case "qtfygl":
                        intent.setClass(getActivity(), OtherCostActivity.class);
                        startActivity(intent);
                        break;
                    case "splb":
                        intent.setClass(getActivity(), GoodsCategoryManagerActivity.class);
                        startActivity(intent);
                        break;
                    case "spdw":
                        intent.setClass(getActivity(), GoodsUnitListManagetActivity.class);
                        startActivity(intent);
                        break;
                }


            }
        });

        spglModel.add(new HomeShowModel("商品中心", "spzx", R.mipmap.icon_spzx));
        spglModel.add(new HomeShowModel("新建商品", "xjsp", R.mipmap.icon_xjsp));
        spglModel.add(new HomeShowModel("拍照创建商品", "pzcjsp", R.mipmap.icon_pzxjsp));
        HomeWdxmAdapter spglAdapter = new HomeWdxmAdapter(getActivity(), spglModel);
        spglRecyleview.setAdapter(spglAdapter);

        spglAdapter.setOnItemClickListener(new HomeWdxmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (curCompany == null) {
                    ToastUtil.showToast(getActivity(), "请先选择公司");
                    return;
                }
                SelectCompanyModel.ShopResponse shop = CompanyRepository.getInstance().getCurShopResponse();
                if (shop == null) {
                    shop = curCompany.getMainShop();
                }
                Intent intent = new Intent();
                intent.putExtra(ParameterConstant.SHOP_ID, shop.getShopID());
                String type = spglModel.get(position).getType();
                switch (type) {
                    case "spzx":
                        intent.setClass(getActivity(), GoodsListActivity.class);
                        startActivity(intent);
                        break;
                    case "xjsp":
                        intent.setClass(getActivity(), CommodityCreateActivity.class);
                        startActivity(intent);
                        break;
                    case "pzcjsp":
                        intent.setClass(getActivity(), CommodityPhotoCreateActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        //销售
        xsglModel.add(new HomeShowModel("销售单据中心", R.mipmap.icon_xsdjzx));
        xsglModel.add(new HomeShowModel("新建报价单", R.mipmap.icon_xjbjd));
        xsglModel.add(new HomeShowModel("新建销售订单", R.mipmap.icon_xjxsdd));
        xsglModel.add(new HomeShowModel("新建零售单", R.mipmap.icon_xjlsd));
        xsglModel.add(new HomeShowModel("新建销售退货单", R.mipmap.icon_xjxsthd));
        xsglModel.add(new HomeShowModel("新建零售退货单", R.mipmap.icon_xjlsthd));
        xsglModel.add(new HomeShowModel("新建个人领货单", R.mipmap.icon_xjgrlhd));
        xsglModel.add(new HomeShowModel("新建领货归还单", R.mipmap.icon_xjlhghd));
        xsglModel.add(new HomeShowModel("个人仓库", R.mipmap.icon_khgl));//R.mipmap.icon_grck));
        HomeWdxmAdapter xsglAdapter = new HomeWdxmAdapter(getActivity(), xsglModel);
        xsglRecyleview.setAdapter(xsglAdapter);

        xsglAdapter.setOnItemClickListener(new HomeWdxmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (curCompany == null) {
                    ToastUtil.showToast(getActivity(), "请先选择公司");
                    return;
                }
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(getActivity(), OrderOfferListActivity.class);
                        break;
                    case 1:
                        intent.setClass(getActivity(), AddOrderOfferActivity.class);
                        break;
                    case 2:
                        intent.putExtra(ParameterConstant.IS_RETAIL_CREATE, false);
                        intent.setClass(getActivity(), AddOrderSalesActivity.class);
                        break;
                    case 3:
                        intent.putExtra(ParameterConstant.IS_RETAIL_CREATE, true);
                        intent.setClass(getActivity(), AddOrderSalesActivity.class);
                        break;
                    case 4:
                        intent.putExtra(ParameterConstant.IS_RETAIL_CREATE, false);
                        intent.setClass(getActivity(), AddOrderSalesBackActivity.class);
                        break;
                    case 5:
                        intent.putExtra(ParameterConstant.IS_RETAIL_CREATE, true);
                        intent.setClass(getActivity(), AddOrderSalesBackActivity.class);
                        break;
                    case 6:
                        intent.setClass(getActivity(), AddOrderTakeActivity.class);
                        break;
                    case 7:
                        intent.setClass(getActivity(), AddOrderTakeBackActivity.class);
                        break;
                    case 8:
                        intent.setClass(getActivity(), PersonStoreListActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });


        ckglModel.add(new HomeShowModel("待出库入库", R.mipmap.icon_dckrk));
        ckglModel.add(new HomeShowModel("其他出入库", R.mipmap.icon_qtcrk));
        ckglModel.add(new HomeShowModel("新建其他出库单", R.mipmap.icon_xjqtckd));
        ckglModel.add(new HomeShowModel("新建其他入库单", R.mipmap.icon_xjqtrkd));
        ckglModel.add(new HomeShowModel("调拨单", R.mipmap.icon_dbd));
        ckglModel.add(new HomeShowModel("新建调拨单", R.mipmap.icon_xjdbd));
        ckglModel.add(new HomeShowModel("盘点单", R.mipmap.icon_pdd));
        //支持筛选，item没有点击
        ckglModel.add(new HomeShowModel("新建盘点单", R.mipmap.icon_xjpdd));
        ckglModel.add(new HomeShowModel("全库盘点管理", R.mipmap.icon_qkpdgl));
        //只有列表和库存总量，item没有点击
        ckglModel.add(new HomeShowModel("库存流水", R.mipmap.icon_kcls));
        ckglModel.add(new HomeShowModel("库存查询", R.mipmap.icon_kccx));
        ckglModel.add(new HomeShowModel("库存预警", R.mipmap.icon_kcyj));

        HomeWdxmAdapter ckglAdapter = new HomeWdxmAdapter(getActivity(), ckglModel);
        ckglRecyleview.setAdapter(ckglAdapter);

        ckglAdapter.setOnItemClickListener(new HomeWdxmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (curCompany == null) {
                    ToastUtil.showToast(getActivity(), "请先选择公司");
                    return;
                }

                SelectCompanyModel.ShopResponse shop = CompanyRepository.getInstance().getCurShopResponse();
                if (shop == null) {
                    shop = curCompany.getMainShop();
                }
                Intent intent = new Intent();
                intent.putExtra(ParameterConstant.SHOP_ID, shop.getShopID());
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), OrderWaitInOutListActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), OrderOtherInOutListActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), AddOrderOtherOutActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), AddOrderOtherInActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), OrderAllotListActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), AddOrderAllotActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(), StoreCheckListActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(getActivity(), AddOrderStockCheckActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(getActivity(), StoreAllCheckListActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(getActivity(), StockLogListActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(getActivity(), StockListActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(getActivity(), StockWarningListActivity.class));
                        break;
                }
            }
        });


        mdglModel.add(new HomeShowModel("新建门店", R.mipmap.icon_xjmd));
        mdglModel.add(new HomeShowModel("门店管理", R.mipmap.icon_mdgl));
        HomeWdxmAdapter mdglAdapter = new HomeWdxmAdapter(getActivity(), mdglModel);
        mdglRecyleview.setAdapter(mdglAdapter);
        mdglAdapter.setOnItemClickListener(new HomeWdxmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (curCompany == null) {
                    ToastUtil.showToast(getActivity(), "请先选择公司");
                    return;
                }
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), AddShopActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), ShopListManagerActivity.class));
                        break;
                }
            }
        });

        //财务管理
        cwglModel.add(new HomeShowModel("应收应付", R.mipmap.icon_sz));
        cwglModel.add(new HomeShowModel("收支管理", R.mipmap.icon_sz_manager));
        cwglModel.add(new HomeShowModel("新增收入支出", R.mipmap.icon_add_sz));
        cwglModel.add(new HomeShowModel("借入借出", R.mipmap.icon_jr_jc));
        cwglModel.add(new HomeShowModel("新增借入借出", R.mipmap.icon_add_in_out));
        cwglModel.add(new HomeShowModel("账户管理", R.mipmap.icon_account_manager));
        cwglModel.add(new HomeShowModel("收支类别管理", R.mipmap.icon_sz_type));
        HomeWdxmAdapter cwglAdapter = new HomeWdxmAdapter(getActivity(), cwglModel);
        cwglRecyleview.setAdapter(cwglAdapter);
        cwglAdapter.setOnItemClickListener(new HomeWdxmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (curCompany == null) {
                    ToastUtil.showToast(getActivity(), "请先选择公司");
                    return;
                }
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), ShouldGatherePayActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), IncomeExpenseListActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), AddIncomeExpenseActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), BorrowListActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), AddBorrowActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), MoneyAccountListActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(), IncomeExpenseTypeListActivity.class));
                        break;
                }
            }
        });


        //采购管理
        cgglModel.add(new HomeShowModel("采购单据中心", R.mipmap.icon_cgdj));
        cgglModel.add(new HomeShowModel("新建采购单", R.mipmap.icon_cgd));
        cgglModel.add(new HomeShowModel("新建采购退货单", R.mipmap.icon_cgthd));
        HomeWdxmAdapter cgglAdapter = new HomeWdxmAdapter(getActivity(), cgglModel);
        cgglRecyleview.setAdapter(cgglAdapter);
        cgglAdapter.setOnItemClickListener(new HomeWdxmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (curCompany == null) {
                    ToastUtil.showToast(getActivity(), "请先选择公司");
                    return;
                }
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(getActivity(), OrderPurchaseListActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(getActivity(), AddOrderPurchaseActivity.class);
                        intent.putExtra(ParameterConstant.IS_PURCHASE_CREATE, true);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(getActivity(), AddOrderPurchaseActivity.class);
                        intent.putExtra(ParameterConstant.IS_PURCHASE_CREATE, false);
                        startActivity(intent);
                        break;
                }
            }
        });

        //项目管理
        HomeShowModel showModel1 = new HomeShowModel("新建项目", "xjxm", R.mipmap.home_chuangjian);
        HomeShowModel showModel2 = new HomeShowModel("项目管理", "xmgl", R.mipmap.home_guanli);
        HomeShowModel showModel13 = new HomeShowModel("我的项目", "wdxm", R.mipmap.home_wdxm);
        xmglModel.add(showModel1);
        xmglModel.add(showModel2);
        xmglModel.add(showModel13);
        HomeWdxmAdapter wdxmAdapter = new HomeWdxmAdapter(getActivity(), xmglModel);
        xmglRecyleview.setAdapter(wdxmAdapter);

        wdxmAdapter.setOnItemClickListener(new HomeWdxmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (curCompany == null) {
                    ToastUtil.showToast(getActivity(), "请先选择公司");
                    return;
                }
                String type = xmglModel.get(position).getType();
                switch (type) {
                    case "xjxm":
                        if (authCreate.equals("1")) {// 1显示 0不显示
                            startActivity(new Intent(getActivity(), FoundProjectActivity.class));
                        } else {
                            ToastUtil.showToast(getActivity(), "您还没有该权限，请联系管理员");
                        }

                        break;
                    case "xmgl":
                        if (authManage.equals("1")) {// 1显示 0不显示
                            startActivity(new Intent(getActivity(), ProjectManageActivity.class));
                        } else {
                            ToastUtil.showToast(getActivity(), "您还没有该权限，请联系管理员");
                        }

                        break;
                    case "wdxm":
                        startActivity(new Intent(getActivity(), MyProjectActivity.class));
                        break;
                }
            }
        });

        //售后管理
        HomeShowModel showModel10 = new HomeShowModel("新建工单", "xjgd", R.mipmap.stag_xjgd);
        HomeShowModel showModel11 = new HomeShowModel("售后工单", "shgd", R.mipmap.stag_shgd);
        HomeShowModel showModel12 = new HomeShowModel("售后设置", "shsz", R.mipmap.stag_shsz);
        HomeShowModel showModel3 = new HomeShowModel("客服管理", "kfgl", R.mipmap.stag_shkf);
        homeShowModels.add(showModel10);
        homeShowModels.add(showModel11);
        homeShowModels.add(showModel12);
        homeShowModels.add(showModel3);

        HomeKhglAdapter homeKhglAdapter = new HomeKhglAdapter(getActivity(), homeShowModels);
        shglRecyleview.setAdapter(homeKhglAdapter);

        homeKhglAdapter.setOnItemClickListener(new HomeKhglAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (curCompany == null) {
                    ToastUtil.showToast(getActivity(), "请先选择公司");
                    return;
                }
                String type = homeShowModels.get(position).getType();
                switch (type) {
                    case "xjgd":

                        Intent intent = new Intent(getActivity(), SelectProjectActivity.class);
                        intent.putExtra(Constants.SELECT_PROJECT, 1);
                        startActivity(intent);

                        break;
                    case "shgd":
                        startActivity(new Intent(getActivity(), MyOrderActivity.class));
                        break;
                    case "shsz":

                        Intent intent2 = new Intent(getActivity(), SelectProjectActivity.class);
                        intent2.putExtra(Constants.SELECT_PROJECT, 2);
                        startActivity(intent2);

                        break;
                    case "kfgl":
                        startActivity(new Intent(getActivity(), CustomSettingActivity.class));
                        break;
                }
            }
        });

        HomeShowModel showModel4 = new HomeShowModel("资讯", "zx", R.mipmap.icon_zixun);
        HomeShowModel showModel5 = new HomeShowModel("场景", "cj", R.mipmap.icon_changjing);
        fxzxModel.add(showModel4);
        fxzxModel.add(showModel5);

        HomeKbppAdapter fxzxAdapter = new HomeKbppAdapter(getActivity(), fxzxModel);
        fxzxRecyleview.setAdapter(fxzxAdapter);

        fxzxAdapter.setOnItemClickListener(new HomeKbppAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String type = fxzxModel.get(position).getType();
                switch (type) {
                    case "zx":
                        startActivity(new Intent(getActivity(), ZixunListActivity.class));
                        break;
                    case "cj":
                        startActivity(new Intent(getActivity(), ChangjingListActivity.class));
                        break;
                }
            }
        });


        //口碑品牌
        HomeShowModel showModel6 = new HomeShowModel("口碑包装", "kbbz", R.mipmap.work_koubei);
        HomeShowModel showModel7 = new HomeShowModel("品牌传播", "ppcb", R.mipmap.work_pinpai);
        kbppModel.add(showModel6);
        kbppModel.add(showModel7);

        HomeKbppAdapter kbppAdapter = new HomeKbppAdapter(getActivity(), kbppModel);
        kbppRecyleview.setAdapter(kbppAdapter);

        kbppAdapter.setOnItemClickListener(new HomeKbppAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String type = kbppModel.get(position).getType();
                switch (type) {
                    case "kbbz":
                        startActivity(new Intent(getActivity(), KbbzActivity.class));
                        break;
                    case "ppcb":
                        startActivity(new Intent(getActivity(), PpcbActivity.class));
                        break;
                }
            }
        });
    }
}
