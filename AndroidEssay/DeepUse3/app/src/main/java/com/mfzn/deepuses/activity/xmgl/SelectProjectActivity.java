package com.mfzn.deepuses.activity.xmgl;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.shgd.AddWorkorderActivity;
import com.mfzn.deepuses.activityxm.shgd.ShouhuSettingActivity;
import com.mfzn.deepuses.adapter.project.ProjectManageAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.bean.request.ProjectListRequest;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.present.xmgl.SelectProjectPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class SelectProjectActivity extends BaseMvpActivity<SelectProjectPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.se_xrecycleview)
    XRecyclerContentLayout seXrecycleview;
    @BindView(R.id.ll_se_empty)
    LinearLayout llSeEmpty;

    private ProjectManageAdapter adapter;
    private int pages = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_project;
    }

    @Override
    public SelectProjectPresent newP() {
        return new SelectProjectPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("选择项目");

        int intExtra = getIntent().getIntExtra(Constants.SELECT_PROJECT, 0);

        adapter = new ProjectManageAdapter(getContext());
        seXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        seXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa, R.dimen.app_10dp);//item之间的分割线
        seXrecycleview.getRecyclerView().setAdapter(adapter);
        seXrecycleview.getRecyclerView().setRefreshEnabled(true);
        seXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        seXrecycleview.showLoading();

        adapter.setOnItemClickListener(new ProjectManageAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                XiangmuModel.DataBean dataBean = adapter.getDataSource().get(position);

                int afterSaleStatus = dataBean.getAfterSaleStatus();
                int afterSaleInDate = dataBean.getAfterSaleInDate();//0已过期 1可使用
                if (afterSaleInDate == 0 && afterSaleStatus != 1) {
                    PhoneUtils.dialogPhone2(SelectProjectActivity.this, "提示",
                            "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011", "4000552011");
                } else {
                    if (intExtra == 1) {
                        Intent intent1 = new Intent(SelectProjectActivity.this, AddWorkorderActivity.class);
                        intent1.putExtra(Constants.WORK_ORDER, dataBean);
                        startActivity(intent1);
                    } else if (intExtra == 2) {
                        Intent intent3 = new Intent(SelectProjectActivity.this, ShouhuSettingActivity.class);
                        intent3.putExtra(Constants.SHOUHOU_PROID, String.valueOf(dataBean.getData_id()));
                        intent3.putExtra(Constants.SHOUHOU_NAME, dataBean.getCustomName());
                        intent3.putExtra(Constants.SHOUHOU_PHONE, dataBean.getCustomTel());
                        intent3.putExtra(Constants.SHOUHOU_ADDRESS, dataBean.getAreaName() + dataBean.getDetailAddress());
                        startActivity(intent3);
                    } else if (intExtra == 3) {
                        Intent intent = new Intent();
                        intent.putExtra(Constants.SHOUHOU_PROID, String.valueOf(dataBean.getData_id()));
                        setResult(Constants.SELECT_PRO, intent);
                    }
                    finish();
                }
            }
        });

        seXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                pages = 1;
                getProjectList();
            }

            @Override
            public void onLoadMore(int page) {
                pages = page;
                getProjectList();
            }
        });
        seXrecycleview.onRefresh();
    }

    private void getProjectList() {
        boolean isAuthCreate = !TextUtils.isEmpty(UserHelper.getAuthCreate()) && UserHelper.getAuthCreate().equals("1");
        getP().getProjectList(pages, isAuthCreate ? ProjectListRequest.COMPANY_PROJECTS : ProjectListRequest.MY_PROJECTS);
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void xiangmuListSuccess(XiangmuModel model) {
        List<XiangmuModel.DataBean> data = model.getData();
        if (data != null && data.size() != 0) {
            if (pages == 1) {
                llSeEmpty.setVisibility(View.GONE);
                seXrecycleview.setVisibility(View.VISIBLE);
                adapter.setData(data);
            } else {
                adapter.addData(data);
            }
            seXrecycleview.getRecyclerView().setPage(model.getCurrent_page(), model.getLast_page());
        } else {
            if (pages == 1) {
                llSeEmpty.setVisibility(View.VISIBLE);
                seXrecycleview.setVisibility(View.GONE);
            }
        }
    }
}
