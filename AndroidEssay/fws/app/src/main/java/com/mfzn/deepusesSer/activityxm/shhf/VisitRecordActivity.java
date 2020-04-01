package com.mfzn.deepusesSer.activityxm.shhf;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.xiangmu.VisitRecordAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.xiangmu.VisitRecordModel;
import com.mfzn.deepusesSer.present.xmhf.VisitRecordPresent;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.VisitDetailsDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class VisitRecordActivity extends BaseMvpActivity<VisitRecordPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_bass_select)
    LinearLayout llBassSelect;
    @BindView(R.id.re_xrecycleview)
    XRecyclerContentLayout reXrecycleview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_visit_record;
    }

    @Override
    public VisitRecordPresent newP() {
        return new VisitRecordPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_visit_record));
        llBassSelect.setVisibility(View.VISIBLE);

        VisitRecordAdapter adapter = new VisitRecordAdapter(getContext());
        reXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        reXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa,R.dimen.app_10dp);//item之间的分割线
        reXrecycleview.getRecyclerView().setAdapter(adapter);
        reXrecycleview.getRecyclerView().setRefreshEnabled(true);
        reXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);
        reXrecycleview.showLoading();

        List<VisitRecordModel> list = new ArrayList<>();
        VisitRecordModel model = new VisitRecordModel(1);
        VisitRecordModel model2 = new VisitRecordModel(1);
        VisitRecordModel model3 = new VisitRecordModel(1);
        VisitRecordModel model4 = new VisitRecordModel(1);

        list.add(model);
        list.add(model2);
        list.add(model3);
        list.add(model4);

        adapter.addData(list);

        adapter.setOnItemClickListener(new VisitRecordAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                new VisitDetailsDialog.Builder(VisitRecordActivity.this)
                        .setHeight(1f)  //屏幕高度*0.23
                        .setWidth(1f)  //屏幕宽度*0.65
                        .setCanceledOnTouchOutside(true)
                        .setOnclickListener(new DialogInterface.OnSingleClickListener<VisitDetailsDialog>() {
                            @Override
                            public void clickSingleButton(VisitDetailsDialog dialog, View view) {
                                dialog.dismiss();
                            }
                        })
                        .build()
                        .show();
            }
        });

        reXrecycleview.getRecyclerView().setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
//                pages = 1;
//                getP().xiangmuList(1);
            }

            @Override
            public void onLoadMore(int page) {
//                pages = page;
//                getP().xiangmuList(page);
            }
        });
        reXrecycleview.onRefresh();
    }

    @OnClick({R.id.iv_login_back, R.id.ll_bass_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_bass_select:
                break;
        }
    }
}
