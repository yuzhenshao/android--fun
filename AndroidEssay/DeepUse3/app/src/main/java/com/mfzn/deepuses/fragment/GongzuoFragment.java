package com.mfzn.deepuses.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.mfzn.deepuses.activitymy.brick.BrickActivity;
import com.mfzn.deepuses.activitymy.brick.RechargeActivity;
import com.mfzn.deepuses.activitymy.brick.TransactionRecordActivity;
import com.mfzn.deepuses.activityxm.FoundProjectActivity;
import com.mfzn.deepuses.activityxm.kf.CustomSettingActivity;
import com.mfzn.deepuses.adapter.home.HomeCzzxAdapter;
import com.mfzn.deepuses.adapter.home.HomeKbppAdapter;
import com.mfzn.deepuses.adapter.home.HomeKehuglAdapter;
import com.mfzn.deepuses.adapter.home.HomeKhglAdapter;
import com.mfzn.deepuses.adapter.home.HomeListAdapter;
import com.mfzn.deepuses.adapter.home.HomeTdglAdapter;
import com.mfzn.deepuses.adapter.home.HomeWdxmAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.bean.response.UserResponse;
import com.mfzn.deepuses.model.LookQuanxian2Model;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.home.HomeShowModel;
import com.mfzn.deepuses.model.home.JudgeLevelModel;
import com.mfzn.deepuses.model.home.KanbDataModel;
import com.mfzn.deepuses.model.my.UserInfoModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.fragment.GongzuoPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.ObtainTime;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.MyListview;
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
    @BindView(R.id.tv_kanban_project)
    TextView tvKanbanProject;
    @BindView(R.id.tv_kanban_money)
    TextView tvKanbanMoney;
    @BindView(R.id.tv_kanban_number)
    TextView tvKanbanNumber;
    @BindView(R.id.shgl_recyleview)
    MyRecyclerView shglRecyleview;
    @BindView(R.id.tdgl_recyleview)
    MyRecyclerView tdglRecyleview;
    @BindView(R.id.xmgl_recyleview)
    MyRecyclerView xmglRecyleview;
    @BindView(R.id.kbpp_recyleview)
    MyRecyclerView kbppRecyleview;
    @BindView(R.id.czzx_recyleview)
    MyRecyclerView czzxRecyleview;
    @BindView(R.id.khgl_recyleview)
    MyRecyclerView khglRecyleview;
    @BindView(R.id.tv_home_czzx)
    TextView tv_home_czzx;

    private PopWindow popWindow;
    private List<SelectCompanyModel> models = new ArrayList<>();
    
    private List<HomeShowModel> homeShowModels = new ArrayList<>();

    private List<HomeShowModel> tdglModels = new ArrayList<>();

    //项目管理
    private List<HomeShowModel> xmglModel = new ArrayList<>();
    //客户管理
    private List<HomeShowModel> khglModel = new ArrayList<>();
    //口碑品牌
    private List<HomeShowModel> kbppModel = new ArrayList<>();
    //充值中心
    private List<HomeShowModel> czzxModel = new ArrayList<>();

    private String roleID;
    private String authCreate;
    private String authManage;

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
        getP().companyList();
        getP().userInfo();
        if(!TextUtils.isEmpty(UserHelper.getCompanyId())) {
            getP().quanxian();
        }

        if(TextUtils.isEmpty(UserHelper.getCompanyName())) {
            tvWorkCompany.setText("请选择公司");
        }else {
            tvWorkCompany.setText(UserHelper.getCompanyName());
        }

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
                    if(eventMsg.getMsg().equals(Constants.ESTABLSISH)){
                        getP().companyList();
                    }else if (eventMsg.getMsg().equals(Constants.COMPANY_NAME)) {
                        tvWorkCompany.setText(UserHelper.getCompanyName());
                    } else if (eventMsg.getMsg().equals(Constants.COMPANYJC)) {
                        getP().companyList();
                        tvWorkCompany.setText(UserHelper.getCompanyName());
                    }
                }
            }
        });
    }

    public void setTime(){
        Date d = new Date();
        if(d.getHours()<11){
            tvWorkType.setText("上午好!");
        }else if(d.getHours()<13){
            tvWorkType.setText("中午好!");
        }else if(d.getHours()<18){
            tvWorkType.setText("下午好!");
        }else if(d.getHours()<24){
            tvWorkType.setText("晚上好!");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            tvWorkName.setText(UserHelper.getU_name() + " ");
            setTime();
            getP().kanbData();
            getP().companyList();
            getP().judgeLevel();
            getP().userInfo();
        }
    }

    @OnClick({R.id.ll_kanban_project, R.id.ll_kanban_money, R.id.ll_kanban_number,R.id.iv_work_scan, R.id.iv_work_xia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_kanban_project:
                break;
            case R.id.ll_kanban_money:
                break;
            case R.id.ll_kanban_number:
                break;
            case R.id.iv_work_scan:
                startActivity(new Intent(getActivity(), CaptureActivity.class));
                break;
            case R.id.iv_work_xia:
                View customView = View.inflate(getActivity(), R.layout.home_company_list, null);
                ListView home_list = customView.findViewById(R.id.home_list);

                popWindow = new PopWindow.Builder(getActivity())
                        .setStyle(PopWindow.PopWindowStyle.PopDown)
                        .setView(customView)
                        .show(view);

                HomeListAdapter adapter = new HomeListAdapter(getActivity(),models,tvWorkCompany.getText().toString());
                home_list.setAdapter(adapter);
                home_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SelectCompanyModel selectCompanyModel = models.get(position);
                        tvWorkCompany.setText(selectCompanyModel.getCompanyName());
                        UserHelper.setCompany(selectCompanyModel.getCompanyID(),selectCompanyModel.getCompanyName());
                        getP().kanbData();
                        getP().judgeLevel();
                        getP().quanxian2();
                        popWindow.dismiss();
                    }
                });

                popWindow.setOnClickDissmiss(new PopWindow.onClickDissmiss() {
                    @Override
                    public void myOnClickDissmiss() {
                    }
                });
                break;
        }
    }

    public void lookQxSuccess(LookQuanxian2Model model) {
        int leftDays = model.getCompanyModule().getAfterSale().getLeftDays();
        if (TextUtils.isEmpty(UserHelper.getLookqx())){
            UserHelper.setLookqx(ObtainTime.endTime());
            if(leftDays > 0) {
                PhoneUtils.dialogPhone2(getActivity(), "提示",
                        "售后板块试用期还剩" + leftDays + "天，到期后\n" +
                                "如需继续使用，请拨打客服电话\n" +
                                "400-055-2011","4000552011");
            }else {
//                PhoneUtils.dialogPhone2(getActivity(), "提示",
//                        "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011","4000552011");
            }
        }else {
            if(!ObtainTime.endTime().equals(UserHelper.getLookqx())) {
                UserHelper.setLookqx(ObtainTime.endTime());
                if(leftDays > 0) {
                    PhoneUtils.dialogPhone2(getActivity(), "提示",
                            "售后板块试用期还剩" + leftDays + "天，到期后\n" +
                                    "如需继续使用，请拨打客服电话\n" +
                                    "400-055-2011","4000552011");
                }else {
//                    PhoneUtils.dialogPhone2(getActivity(), "提示",
//                            "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011","4000552011");
                }
            }
        }
    }

    public void lookQxSuccess2(LookQuanxian2Model model) {
        int leftDays = model.getCompanyModule().getAfterSale().getLeftDays();
        UserHelper.setLookqx(ObtainTime.endTime());
        if(leftDays > 0) {
            PhoneUtils.dialogPhone2(getActivity(), "提示",
                    "售后板块试用期还剩" + leftDays + "天，到期后\n" +
                            "如需继续使用，请拨打客服电话\n" +
                            "400-055-2011","4000552011");
        }else {
//            PhoneUtils.dialogPhone2(getActivity(), "提示",
//                    "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011","4000552011");
        }
    }

    private void setRecyleview() {
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
        NoScrollGridLayoutManager kbppLayoutManagerw = new NoScrollGridLayoutManager(getActivity(),
                4, GridLayoutManager.VERTICAL, false);
        kbppRecyleview.setLayoutManager(kbppLayoutManagerw);
    }

    //用户信息成功返回
    public void userInfoSuccess(UserResponse result) {
        if (!TextUtils.isEmpty(result.getUserAvatar())){
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
        HomeShowModel showModel6 = new HomeShowModel("组织架构","zzjg",R.mipmap.work_jiagou);
        HomeShowModel showModel7 = new HomeShowModel("企业设置","qysz",R.mipmap.home_qysz);
        HomeShowModel showModel15 = new HomeShowModel("企业二维码","qyewm",R.mipmap.pro_code);
        HomeShowModel showModel14 = new HomeShowModel("管理员设置","glysz",R.mipmap.home_glysz);
        HomeShowModel showModel8 = new HomeShowModel("创建团队","cjtd",R.mipmap.home_chuangjian2);
        HomeShowModel showModel9 = new HomeShowModel("加入团队","jrtd",R.mipmap.home_jiaru);
        tdglModels.add(showModel6);
        tdglModels.add(showModel7);
        tdglModels.add(showModel15);
        if (authCreate.equals("1")) {
            tdglModels.add(showModel14);
        }
        tdglModels.add(showModel8);
        tdglModels.add(showModel9);

        HomeTdglAdapter tdglAdapter = new HomeTdglAdapter(getActivity(),tdglModels);
        tdglRecyleview.setAdapter(tdglAdapter);

        tdglAdapter.setOnItemClickListener(new HomeTdglAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String type = tdglModels.get(position).getType();
                switch (type) {
                    case "zzjg":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
//                            if (roleID.equals("1") || roleID.equals("2")) {
                            if (authCreate.equals("1")) {
                                startActivity(new Intent(context, ManageJiagouActivity.class));
                            } else {
                                startActivity(new Intent(context, ZuzhiJiagouActivity.class));
                            }
                        }
                        break;
                    case "qysz":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            if(authCreate.equals("1")) {
                                startActivity(new Intent(context, TeamManageActivity.class));
                            }else {
                                ToastUtil.showToast(getActivity(),"您还没有该权限，请联系管理员");
                            }
                        }
                        break;
                    case "qyewm":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            startActivity(new Intent(getActivity(), ShareCodeActivity.class));
                        }
                        break;
                    case "glysz":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            if(authCreate.equals("1")) {
                                startActivity(new Intent(getActivity(), ManageSettingActivity.class));
                            }else {
                                ToastUtil.showToast(getActivity(),"您还没有该权限，请联系管理员");
                            }
                        }
                        break;
                    case "cjtd":
                        startActivity(new Intent(getActivity(), EstablishCompanyActivity.class));
                        break;
                    case "jrtd":
                        Intent intent = new Intent(getActivity(), EstablishJoinActivity.class);
                        intent.putExtra("EstablishJoin",2);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void setKhgl() {
        khglModel.clear();
        //客户管理
        HomeShowModel showMode20 = new HomeShowModel("客户管理","khgl",R.mipmap.home_khgl);
        HomeShowModel showMode21 = new HomeShowModel("我的客户","wdkh",R.mipmap.home_wdkh);
        HomeShowModel showMode22 = new HomeShowModel("添加客户","xjkh",R.mipmap.home_xjkh);
        HomeShowModel showMode23 = new HomeShowModel("分享客户","fxkh",R.mipmap.home_wdgx);
        HomeShowModel showMode24 = new HomeShowModel("新增跟进","gjjd",R.mipmap.home_gjjd);
        HomeShowModel showMode25 = new HomeShowModel("我的任务","wdrw",R.mipmap.home_wdrw);

        if(authCreate.equals("1")) {// 1显示 0不显示
            khglModel.add(showMode20);
        }
        khglModel.add(showMode21);
        khglModel.add(showMode22);
        if(authCreate.equals("1")) {// 1显示 0不显示
            khglModel.add(showMode23);
        }
        khglModel.add(showMode24);
        khglModel.add(showMode25);
        HomeKehuglAdapter khglAdapter = new HomeKehuglAdapter(getActivity(),khglModel);
        khglRecyleview.setAdapter(khglAdapter);

        khglAdapter.setOnItemClickListener(new HomeKehuglAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String type = khglModel.get(position).getType();
                switch (type) {
                    case "khgl":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            startActivity(new Intent(getActivity(), CustomerMangerActivity.class));
                        }
                        break;
                    case "wdkh":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            startActivity(new Intent(getActivity(), MyCustomerActivity.class));
                        }
                        break;
                    case "xjkh":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            startActivity(new Intent(getActivity(), BulidCustomerActivity.class));
                        }
                        break;
                    case "fxkh":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            startActivity(new Intent(getActivity(), MyShareActivity.class));
                        }
                        break;
                    case "gjjd":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            Intent intent = new Intent(getActivity(), SelectCustomerActivity.class);
                            intent.putExtra("add_aa",3);
                            startActivity(intent);
                        }
                        break;
                    case "wdrw":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            startActivity(new Intent(getActivity(), MyTaskActivity.class));
                        }
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
            HomeShowModel showModel17 = new HomeShowModel("公司会员","gshy",R.mipmap.home_huiyuan);
            HomeShowModel showModel16 = new HomeShowModel("充值中心","czzx",R.mipmap.home_czzx);
            HomeShowModel showModel18 = new HomeShowModel("用砖记录","yzjl",R.mipmap.home_jilu);
            czzxModel.add(showModel17);
            czzxModel.add(showModel16);
            czzxModel.add(showModel18);
            HomeCzzxAdapter czzxAdapter = new HomeCzzxAdapter(getActivity(),czzxModel);
            czzxRecyleview.setAdapter(czzxAdapter);
            czzxAdapter.setOnItemClickListener(new HomeCzzxAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    String type = czzxModel.get(position).getType();
                    switch (type) {
                        case "gshy":
                            if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                                ToastUtil.showToast(getActivity(),"请先选择公司");
                            }else {
                                startActivity(new Intent(getActivity(), BrickActivity.class));
                            }
                            break;
                        case "czzx":
                            if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                                ToastUtil.showToast(getActivity(),"请先选择公司");
                            }else {
                                startActivity(new Intent(getActivity(), RechargeActivity.class));
                            }
                            break;
                        case "yzjl":
                            if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                                ToastUtil.showToast(getActivity(),"请先选择公司");
                            }else {
                                startActivity(new Intent(getActivity(), TransactionRecordActivity.class));
                            }
                            break;
                    }
                }
            });
        } else {
            czzxRecyleview.setVisibility(View.GONE);
            tv_home_czzx.setVisibility(View.GONE);
        }
    }

    public void kanbDataSuccess(KanbDataModel model) {
        tvKanbanProject.setText(model.getShNums());
        tvKanbanMoney.setText(model.getWbNums());
        tvKanbanNumber.setText(model.getProNums());
    }

    public void companyListSuccess(List<SelectCompanyModel> models) {
        this.models = models;
    }

    private void setDatas(){
        //项目管理
        HomeShowModel showModel1 = new HomeShowModel("新建项目","xjxm",R.mipmap.home_chuangjian);
        HomeShowModel showModel2 = new HomeShowModel("项目管理","xmgl",R.mipmap.home_guanli);
        HomeShowModel showModel13 = new HomeShowModel("我的项目","wdxm",R.mipmap.home_wdxm);
        xmglModel.add(showModel1);
        xmglModel.add(showModel2);
        xmglModel.add(showModel13);
        HomeWdxmAdapter wdxmAdapter = new HomeWdxmAdapter(getActivity(),xmglModel);
        xmglRecyleview.setAdapter(wdxmAdapter);

        wdxmAdapter.setOnItemClickListener(new HomeWdxmAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String type = xmglModel.get(position).getType();
                switch (type) {
                    case "xjxm":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            if(authCreate.equals("1")) {// 1显示 0不显示
                                startActivity(new Intent(getActivity(), FoundProjectActivity.class));
                            }else {
                                ToastUtil.showToast(getActivity(),"您还没有该权限，请联系管理员");
                            }
                        }
                        break;
                    case "xmgl":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            if(authManage.equals("1")) {// 1显示 0不显示
                                startActivity(new Intent(getActivity(), ProjectManageActivity.class));
                            }else {
                                ToastUtil.showToast(getActivity(),"您还没有该权限，请联系管理员");
                            }
                        }
                        break;
                    case "wdxm":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            startActivity(new Intent(getActivity(), MyProjectActivity.class));
                        }
                        break;
                }
            }
        });

        //售后管理
        HomeShowModel showModel10 = new HomeShowModel("新建工单","xjgd",R.mipmap.stag_xjgd);
        HomeShowModel showModel11 = new HomeShowModel("售后工单","shgd",R.mipmap.stag_shgd);
        HomeShowModel showModel12 = new HomeShowModel("售后设置","shsz",R.mipmap.stag_shsz);
        HomeShowModel showModel3 = new HomeShowModel("客服管理","kfgl",R.mipmap.stag_shkf);
        homeShowModels.add(showModel10);
        homeShowModels.add(showModel11);
        homeShowModels.add(showModel12);
        homeShowModels.add(showModel3);

        HomeKhglAdapter homeKhglAdapter = new HomeKhglAdapter(getActivity(),homeShowModels);
        shglRecyleview.setAdapter(homeKhglAdapter);

        homeKhglAdapter.setOnItemClickListener(new HomeKhglAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String type = homeShowModels.get(position).getType();
                switch (type) {
                    case "xjgd":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            Intent intent = new Intent(getActivity(), SelectProjectActivity.class);
                            intent.putExtra(Constants.SELECT_PROJECT,1);
                            startActivity(intent);
                        }
                        break;
                    case "shgd":
                        startActivity(new Intent(getActivity(), MyOrderActivity.class));
                        break;
                    case "shsz":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            Intent intent2 = new Intent(getActivity(), SelectProjectActivity.class);
                            intent2.putExtra(Constants.SELECT_PROJECT,2);
                            startActivity(intent2);
                        }
                        break;
                    case "kfgl":
                        if(TextUtils.isEmpty(UserHelper.getCompanyId())) {
                            ToastUtil.showToast(getActivity(),"请先选择公司");
                        }else {
                            startActivity(new Intent(getActivity(), CustomSettingActivity.class));
                        }
                        break;
                }
            }
        });

        //口碑品牌
        HomeShowModel showModel4 = new HomeShowModel("口碑包装","kbbz",R.mipmap.work_koubei);
        HomeShowModel showModel5 = new HomeShowModel("品牌传播","ppcb",R.mipmap.work_pinpai);
        kbppModel.add(showModel4);
        kbppModel.add(showModel5);

        HomeKbppAdapter kbppAdapter = new HomeKbppAdapter(getActivity(),kbppModel);
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
