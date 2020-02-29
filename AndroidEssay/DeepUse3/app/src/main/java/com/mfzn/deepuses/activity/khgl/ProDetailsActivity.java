package com.mfzn.deepuses.activity.khgl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.kf.CustomSettingActivity;
import com.mfzn.deepuses.activityxm.shgd.AddWorkorderActivity;
import com.mfzn.deepuses.activityxm.shgd.ShouhuSettingActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderListActivity;
import com.mfzn.deepuses.activityxm.shhf.VisitRecordActivity;
import com.mfzn.deepuses.adapter.xiangmu.ProjectStagingAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.xiangmu.ProjectStagingModel;
import com.mfzn.deepuses.model.xiangmu.StagingListModel;
import com.mfzn.deepuses.present.customer.ProDetailsPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.view.MyRecyclerView;
import com.mfzn.deepuses.view.NoScrollGridLayoutManager;
import com.wevey.selector.dialog.bean.DetailsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProDetailsActivity extends BaseMvpActivity<ProDetailsPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_stag_time_bold1)
    TextView tvStagTimeBold1;
    @BindView(R.id.tv_stag_time)
    TextView tvStagTime;
    @BindView(R.id.tv_stag_gzbx)
    TextView tvStagGzbx;
    @BindView(R.id.tv_stag_wbsj)
    TextView tvStagWbsj;
    @BindView(R.id.tv_stag_hfjl)
    TextView tvStagHfjl;
    @BindView(R.id.tv_stag_shgl)
    TextView tvStagShgl;
    @BindView(R.id.tv_stag_xmcy)
    TextView tvStagXmcy;
    @BindView(R.id.stag_recyleview)
    MyRecyclerView stagRecyleview;
    @BindView(R.id.tv_stag_qx)
    TextView tv_stag_qx;

    private String pro_uid;

    private String customName;
    private String customTel;
    private String address;

    private boolean judgeQx = false;
    private int leftDays;
    private DetailsModel.ProsBean dataBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pro_details;
    }

    @Override
    public ProDetailsPresnet newP() {
        return new ProDetailsPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        dataBean = (DetailsModel.ProsBean) getIntent().getSerializableExtra(Constants.PRO_DETA);

        customName = dataBean.getCustomName();
        customTel = dataBean.getCustomTel();
        address = dataBean.getAreaName() + dataBean.getDetailAddress();

        tvBassTitle.setText(dataBean.getPro_name());
        String start_time = dataBean.getQualityBegin();
        String end_time = dataBean.getQualityEnd();
        if (!start_time.equals("0") && !end_time.equals("0")) {
            if (!TextUtils.isEmpty(start_time) && !TextUtils.isEmpty(end_time)) {
                tvStagTime.setText(DateUtils.stampDate(start_time) + "~" + DateUtils.stampDate(end_time));
            } else {
                tvStagTime.setText("暂无");
            }
        } else {
            tvStagTime.setText("暂无");
        }

        tvStagTimeBold1.getPaint().setFakeBoldText(true);
        tvStagGzbx.getPaint().setFakeBoldText(true);
        tvStagWbsj.getPaint().setFakeBoldText(true);
        tvStagHfjl.getPaint().setFakeBoldText(true);
        tvStagShgl.getPaint().setFakeBoldText(true);
        tvStagXmcy.getPaint().setFakeBoldText(true);

        pro_uid = dataBean.getData_id() + "";
        getP().projectStaging(pro_uid);
        getP().stagingList(pro_uid);

        int afterSaleInDate = dataBean.getAfterSaleInDate();//0已过期 1可使用
        if (afterSaleInDate == 0) {
            judgeQx = false;
            tv_stag_qx.setText("已过期");
        } else if (afterSaleInDate == 1) {
            judgeQx = true;
            getP().quanxian(pro_uid);
        }

        NoScrollGridLayoutManager appLayoutManager = new NoScrollGridLayoutManager(this,
                4, GridLayoutManager.VERTICAL, false);
        stagRecyleview.setLayoutManager(appLayoutManager);
    }

    @OnClick({R.id.iv_login_back, R.id.ll_stag_shgd, R.id.ll_stag_xjgd,
            R.id.ll_stag_shhf, R.id.ll_stag_shkf, R.id.ll_stag_shsz, R.id.ll_stag_qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_stag_shgd:
                if (judgeQx) {
                    Intent intent = new Intent(this, WorkorderListActivity.class);
                    intent.putExtra(Constants.WORK_ORDER, dataBean);
                    startActivity(intent);
                } else {
                    PhoneUtils.dialogPhone2(this, "提示",
                            "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011", "4000552011");
                }
                break;
            case R.id.ll_stag_xjgd:
                if (judgeQx) {
                    Intent intent1 = new Intent(this, AddWorkorderActivity.class);
                    intent1.putExtra(Constants.WORK_ORDER, dataBean);
                    startActivity(intent1);
                } else {
                    PhoneUtils.dialogPhone2(this, "提示",
                            "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011", "4000552011");
                }
                break;
            case R.id.ll_stag_shsz:
                if (judgeQx) {
                    Intent intent3 = new Intent(this, ShouhuSettingActivity.class);
                    intent3.putExtra(Constants.SHOUHOU_PROID, pro_uid);
                    intent3.putExtra(Constants.SHOUHOU_NAME, customName);
                    intent3.putExtra(Constants.SHOUHOU_PHONE, customTel);
                    intent3.putExtra(Constants.SHOUHOU_ADDRESS, address);
                    startActivity(intent3);
                } else {
                    PhoneUtils.dialogPhone2(this, "提示",
                            "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011", "4000552011");
                }
                break;
            case R.id.ll_stag_shhf:
                if (judgeQx) {
                    Intent intent2 = new Intent(this, VisitRecordActivity.class);
                    intent2.putExtra(Constants.SHOUHOU_PROID, pro_uid);
                    startActivity(intent2);
                } else {
                    PhoneUtils.dialogPhone2(this, "提示",
                            "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011", "4000552011");
                }
                break;
            case R.id.ll_stag_shkf:
                if (judgeQx) {
                    startActivity(new Intent(this, CustomSettingActivity.class));
                } else {
                    PhoneUtils.dialogPhone2(this, "提示",
                            "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011", "4000552011");
                }
                break;
            case R.id.ll_stag_qx:
                if (judgeQx) {
                    PhoneUtils.dialogPhone2(this, "提示",
                            "售后板块试用期还剩" + leftDays + "天，到期后\n" +
                                    "如需继续使用，请拨打客服电话\n" +
                                    "400-055-2011", "4000552011");
                } else {
                    PhoneUtils.dialogPhone2(this, "提示",
                            "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011", "4000552011");
                }
                break;
        }
    }

    public void lookQxSuccess(LookQuanxianModel model) {
        leftDays = model.getProjectModule().getAfterSale().getLeftDays();
        tv_stag_qx.setText("售后试用期还剩" + leftDays + "天");
    }

    public void projectStagingSuccess(ProjectStagingModel model) {
        tvStagGzbx.setText(model.getGzNum() + "");
        tvStagWbsj.setText(model.getWbNum() + "");
        tvStagHfjl.setText(model.getHfNum() + "");
    }

    public void stagingListSuccess(StagingListModel stagingListModel) {
        ProjectStagingAdapter adapter = new ProjectStagingAdapter(this, stagingListModel.getAllEnginerList());
        stagRecyleview.setAdapter(adapter);
    }
}
