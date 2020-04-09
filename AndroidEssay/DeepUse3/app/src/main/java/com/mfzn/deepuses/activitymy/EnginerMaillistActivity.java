package com.mfzn.deepuses.activitymy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.shgd.SelectEnginerActivity;
import com.mfzn.deepuses.adapter.khgl.WholeCustomerAdapter;
import com.mfzn.deepuses.adapter.xiangmu.EnginerListAdapter;
import com.mfzn.deepuses.adapter.xiangmu.MyEnginerListAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.EnginerListModel;
import com.mfzn.deepuses.present.my.EnginerMaillistPresent;
import com.mfzn.deepuses.utils.Constants;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.NormalAlert2Dialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

public class EnginerMaillistActivity extends BaseMvpActivity<EnginerMaillistPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.en_listview)
    XRecyclerContentLayout whXrecycleview;
    @BindView(R.id.ll_bass_add)
    LinearLayout llBassAdd;
    private int delPosition;
    private MyEnginerListAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_enginer_maillist;
    }

    @Override
    public EnginerMaillistPresent newP() {
        return new EnginerMaillistPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_enginer_list));
        llBassAdd.setVisibility(View.VISIBLE);
        getP().enginerList();
    }

    @OnClick({R.id.iv_login_back, R.id.ll_bass_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.ll_bass_add:
                startActivityForResult(new Intent(this, SelectEnginerActivity.class), Constants.SELECT_ENGINER);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.SELECT_ENGINER == requestCode) {
            if (data != null) {
                getP().enginerList();
            }
        }
    }

    public void enginerListSuccess(List<EnginerListModel> model) {
        adapter = new MyEnginerListAdapter(this);
        adapter.setData(model);
        adapter.setOnDelItemClickListener(new MyEnginerListAdapter.OnDelItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                dialogDelelte(position);
            }
        });
//        adapter.setOnItemClickListener(new MyEnginerListAdapter.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
//            }
//        });
        whXrecycleview.getRecyclerView().verticalLayoutManager(getContext());
        whXrecycleview.getRecyclerView().horizontalDivider(R.color.color_f5f7fa, R.dimen.app_10dp);//item之间的分割线
        whXrecycleview.getRecyclerView().setAdapter(adapter);
        whXrecycleview.getRecyclerView().setRefreshEnabled(false);
        whXrecycleview.getRecyclerView().setVerticalScrollBarEnabled(false);//隐藏右侧的线
        adapter.notifyDataSetChanged();
    }

    public void dialogDelelte(int position) {
        delPosition = position;
        EnginerListModel model = adapter.getDataSource().get(position);
        if (model == null) {
            return;
        }
        NormalAlert2Dialog normalAlertDialog = new NormalAlert2Dialog.Builder(this)
                .setHeight(0.25f)  //屏幕高度*0.23
                .setWidth(0.8f)  //屏幕宽度*0.65
                .setContentText("确认删除客户吗？")
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
                        showDialog();
                        getP().delEngineer(String.valueOf(model.getEngineerID()));
                        dialog.dismiss();
                    }
                })
                .build();
        normalAlertDialog.show();
    }

    public void enginerDelSuccess() {
        try {
            hideDialog();
            adapter.getDataSource().remove(delPosition);
            adapter.notifyItemRemoved(delPosition);
        }catch (Exception e){

        }
    }

}
