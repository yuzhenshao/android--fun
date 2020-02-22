package com.mfzn.deepuses.activity.project;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.myteam.ManageSettingActivity;
import com.mfzn.deepuses.activity.myteam.SelectManageActivity;
import com.mfzn.deepuses.activityxm.ProjectCodeActivity;
import com.mfzn.deepuses.activityxm.SelectPersonActivity;
import com.mfzn.deepuses.activityxm.shgd.ShouhuSettingActivity;
import com.mfzn.deepuses.adapter.project.ProjectDetailsAdapter;
import com.mfzn.deepuses.adapter.xiangmu.ProjectStagingAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.brick.CompanyInfoModel;
import com.mfzn.deepuses.model.brick.LevelRightsModel;
import com.mfzn.deepuses.model.xiangmu.ProjectChengyModel;
import com.mfzn.deepuses.model.xiangmu.StagingListModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.present.project.ProjectDetailsPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.MyRecyclerView;
import com.mfzn.deepuses.view.NoScrollGridLayoutManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wevey.selector.dialog.AddBranchDialog;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlert2Dialog;
import com.wevey.selector.dialog.OpenShouhouDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ProjectDetailsActivity extends BaseMvpActivity<ProjectDetailsPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_detele2)
    LinearLayout llBassDetele;
    @BindView(R.id.tv_pr_khgl)
    TextView tvPrKhgl;
    @BindView(R.id.tv_pr_cygl)
    TextView tvPrCygl;
    @BindView(R.id.tv_pr_edit)
    TextView tv_pr_edit;
    @BindView(R.id.tv_pr_wc)
    TextView tv_pr_wc;
    @BindView(R.id.pr_recyleview)
    MyRecyclerView prRecyleview;
    @BindView(R.id.ll_pr_pay)
    LinearLayout ll_pr_pay;
    @BindView(R.id.tv_pr_price)
    TextView tv_pr_price;

    private String pro_uid;
    private String userID;
    private ProjectDetailsAdapter adapter;

    private int positions;//记录删除的位置
    private List<ProjectChengyModel> modelList = new ArrayList<>();;

    private XiangmuModel.DataBean dataBean;

    private String customName;
    private String customTel;
    private String address;

    private int sumZhuan;
    private int buyPrice;

    @Override
    public int getLayoutId() {
        return R.layout.activity_project_details;
    }

    @Override
    public ProjectDetailsPresent newP() {
        return new ProjectDetailsPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvPrKhgl.getPaint().setFakeBoldText(true);
        tvPrCygl.getPaint().setFakeBoldText(true);
        llBassDetele.setVisibility(View.VISIBLE);

        dataBean = (XiangmuModel.DataBean) getIntent().getSerializableExtra(Constants.WORK_ORDER);

        int afterSaleStatus = dataBean.getAfterSaleStatus();
        if (afterSaleStatus == 0 || afterSaleStatus == 2) {//0未开通 1购买开通  2试用开通
            ll_pr_pay.setVisibility(View.VISIBLE);
            getP().getBrick();
        } else if (afterSaleStatus == 1) {
            ll_pr_pay.setVisibility(View.GONE);
        }

        customName = dataBean.getCustomName();
        customTel = dataBean.getCustomTel();
        address = dataBean.getAreaName() + dataBean.getDetailAddress();

        tvBassTitle.setText(dataBean.getPro_name());

        pro_uid = dataBean.getData_id() + "";
        getP().stagingList(pro_uid);

        NoScrollGridLayoutManager appLayoutManager = new NoScrollGridLayoutManager(this,
                4, GridLayoutManager.VERTICAL, false);
        prRecyleview.setLayoutManager(appLayoutManager);

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if (eventMsg.getMsg().equals(Constants.FOUNDPROJECT)) {
                        finish();
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_login_back, R.id.ll_bass_detele2, R.id.ll_pr_xxbj, R.id.ll_pr_code, R.id.ll_pr_xmxx,
            R.id.tv_pr_edit, R.id.tv_pr_wc, R.id.ll_pr_shsz,R.id.tv_pr_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_bass_detele2:
                dialogDelelte();
                break;
            case R.id.ll_pr_xxbj:
                Intent intent = new Intent(this, EditProjectActivity.class);
                intent.putExtra(Constants.WORK_ORDER,dataBean);
                startActivity(intent);
                break;
            case R.id.ll_pr_code:
                Intent intent2 = new Intent(this, ProjectCodeActivity.class);
                intent2.putExtra(Constants.FOUND_PROJECT_PROID,pro_uid);
                intent2.putExtra(Constants.FOUND_PROJECT_NAME,dataBean.getPro_name());
                startActivity(intent2);
                break;
            case R.id.ll_pr_xmxx:
                Intent intent3 = new Intent(this, ProjectNewsActivity.class);
                intent3.putExtra(Constants.FOUND_PROJECT_PROID,pro_uid);
                startActivity(intent3);
                break;
            case R.id.tv_pr_edit:
                tv_pr_edit.setVisibility(View.GONE);
                tv_pr_wc.setVisibility(View.VISIBLE);
                adapter.setType(1);
                break;
            case R.id.tv_pr_wc:
                tv_pr_edit.setVisibility(View.VISIBLE);
                tv_pr_wc.setVisibility(View.GONE);
                adapter.setType(-1);
                break;
            case R.id.ll_pr_shsz:
                Intent intent4 = new Intent(this, ShouhuSettingActivity.class);
                intent4.putExtra(Constants.SHOUHOU_PROID,pro_uid);
                intent4.putExtra(Constants.SHOUHOU_NAME,customName);
                intent4.putExtra(Constants.SHOUHOU_PHONE,customTel);
                intent4.putExtra(Constants.SHOUHOU_ADDRESS,address);
                startActivity(intent4);
                break;
            case R.id.tv_pr_pay:
                new OpenShouhouDialog.Builder(this)
                        .setHeight(1f)  //屏幕高度*0.23
                        .setWidth(1f)  //屏幕宽度*0.65
                        .setBrick(sumZhuan + "")
                        .setPrice(buyPrice + "")
                        .setName(dataBean.getPro_name())
                        .setCanceledOnTouchOutside(false)
                        .setOnclickListener(new DialogInterface.OnLeftAndRightClickListener<OpenShouhouDialog>() {
                            @Override
                            public void clickLeftButton(OpenShouhouDialog dialog, View view) {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickRightButton(OpenShouhouDialog dialog, View view) {
                                if(sumZhuan < buyPrice) {
                                    ToastUtil.showToast(ProjectDetailsActivity.this,"您的砖数量不够，请先充值");
                                }else {
                                    getP().openBk(pro_uid,"1",buyPrice + "");
                                }
                            }
                        })
                        .build()
                        .show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.SELECT_PERSON == requestCode) {
            if (data != null) {
                userID = data.getStringExtra(Constants.SELECT_PERSON_ID);
                String name = data.getStringExtra(Constants.SELECT_PERSON_NAME);
//                etFouGw.setText(name);
                getP().addStaging(pro_uid,userID);
            }
        }
    }

    public void getCompanySuccess(CompanyInfoModel model) {
        int zhuan = (int) Double.parseDouble(model.getZhuan());
        int zhuan2 = (int) Double.parseDouble(model.getGiftZhuan());
        sumZhuan = zhuan + zhuan2;
        List<CompanyInfoModel.LevelRightsBean.ModulePriceBean> modulePrice = model.getLevelRights().getModulePrice();
        for(int i = 0; i < modulePrice.size(); i++) {
            if(modulePrice.get(i).getModuleType() == 1) {
                buyPrice = modulePrice.get(i).getBuyPrice();
                tv_pr_price.setText(buyPrice + "");
            }
        }
    }

    public void openBk() {
        ToastUtil.showToast(this,"开通成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.FOUNDPROJECT);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    public void addStagingSuccess() {
        ToastUtil.showToast(this,"添加成功");
        getP().stagingList(pro_uid);
    }

    public void deleteStagingSuccess() {
        ToastUtil.showToast(this,"删除成功");
        modelList.remove(positions);
        adapter.notifyDataSetChanged();
    }

    public void deleteProjectSuccess() {
        ToastUtil.showToast(this,"删除成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.FOUNDPROJECT);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    public void stagingListSuccess(StagingListModel model) {
        modelList.clear();
        modelList.add(new ProjectChengyModel());

        List<StagingListModel.OthersBean> others = model.getOthers();
        if(others != null && others.size() != 0) {
            for(int i = 0; i < others.size(); i++) {
                ProjectChengyModel model1 = new ProjectChengyModel();
                StagingListModel.OthersBean othersBean = others.get(i);
                model1.setProID(othersBean.getProID());
                model1.setUserID(othersBean.getUserID());
                model1.setIs_del(othersBean.getIs_del());
                model1.setAddTime(othersBean.getAddTime());
                model1.setAddUserID(othersBean.getAddUserID());
                model1.setUpdateTime(othersBean.getUpdateTime());
                model1.setUpdateUserID(othersBean.getUpdateUserID());
                model1.setLabel(othersBean.getLabel());
                model1.setU_name(othersBean.getU_name());
                model1.setU_head(othersBean.getU_head());
                model1.setU_type(othersBean.getU_type());
                model1.setLabelName(othersBean.getLabelName());
                model1.setData_id(othersBean.getData_id());
                model1.setData_en_id(othersBean.getData_en_id());
                modelList.add(model1);
            }
        }
        List<StagingListModel.EnginerBean> enginer = model.getEnginer();
        if(enginer != null && enginer.size() != 0) {
            for(int i = 0; i < enginer.size(); i++) {
                ProjectChengyModel model1 = new ProjectChengyModel();
                StagingListModel.EnginerBean othersBean = enginer.get(i);
                model1.setProID(othersBean.getProID());
                model1.setUserID(othersBean.getUserID());
                model1.setIs_del(othersBean.getIs_del());
                model1.setAddTime(othersBean.getAddTime());
                model1.setAddUserID(othersBean.getAddUserID());
                model1.setUpdateTime(othersBean.getUpdateTime());
                model1.setUpdateUserID(othersBean.getUpdateUserID());
                model1.setLabel(othersBean.getLabel());
                model1.setU_name(othersBean.getU_name());
                model1.setU_head(othersBean.getU_head());
                model1.setU_type(othersBean.getU_type());
                model1.setLabelName(othersBean.getLabelName());
                model1.setData_id(othersBean.getData_id());
                model1.setData_en_id(othersBean.getData_en_id());
                modelList.add(model1);
            }
        }

        adapter = new ProjectDetailsAdapter(this,modelList);
        prRecyleview.setAdapter(adapter);

        adapter.setOnAddItemClickListener(new ProjectDetailsAdapter.OnAddItemClickListener() {
            @Override
            public void onAddItemClick(View view, int position) {
                UserHelper.setSelectNmae("2");
                startActivityForResult(new Intent(ProjectDetailsActivity.this, SelectPersonActivity.class), Constants.SELECT_PERSON);
            }
        });

        adapter.setOnDeleteItemClickListener(new ProjectDetailsAdapter.OnDeleteItemClickListener() {
            @Override
            public void onDeteleItemClick(View view, int position) {
                positions = position;
                dialogDelelte2(modelList.get(position).getUserID() + "",modelList.get(position).getU_name());
            }
        });
    }

    public void dialogDelelte() {
        NormalAlert2Dialog normalAlertDialog = new NormalAlert2Dialog.Builder(this)
                .setHeight(0.25f)  //屏幕高度*0.23
                .setWidth(0.8f)  //屏幕宽度*0.65
                .setContentText("删除后，项目将不存在，确定删除" + dataBean.getPro_name() + " 项目吗？")
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
                        getP().deleteProject(pro_uid);
                        dialog.dismiss();
                    }
                })
                .build();
        normalAlertDialog.show();
    }

    public void dialogDelelte2(String userID,String name) {
        NormalAlert2Dialog normalAlertDialog = new NormalAlert2Dialog.Builder(this)
                .setHeight(0.25f)  //屏幕高度*0.23
                .setWidth(0.8f)  //屏幕宽度*0.65
                .setContentText("是否确定将“" + name + " ”从该项目中删除，他将无法完成相应工作")
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
                        getP().deleteStaging(pro_uid,userID);
                        dialog.dismiss();
                    }
                })
                .build();
        normalAlertDialog.show();
    }
}
