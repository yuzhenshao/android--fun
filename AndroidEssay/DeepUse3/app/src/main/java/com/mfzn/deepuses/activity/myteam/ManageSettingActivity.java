package com.mfzn.deepuses.activity.myteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.company.SelectCompanyActivity;
import com.mfzn.deepuses.adapter.company.ManageSettingAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.model.myTeam.ManageSettingModel;
import com.mfzn.deepuses.present.myteam.ManageSettingPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;
import com.mfzn.deepuses.view.SlideRecyclerView;
import com.wevey.selector.dialog.AddBranchDialog;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.TishiDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

public class ManageSettingActivity extends BaseMvpActivity<ManageSettingPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.man_recycle)
    SlideRecyclerView manRecycle;

    private List<ManageSettingModel> models = new ArrayList<>();
    private int addType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_manage_setting;
    }

    @Override
    public ManageSettingPresent newP() {
        return new ManageSettingPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("管理权限设置");

        manRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        getP().manageSetting();
    }

    @OnClick({R.id.iv_login_back, R.id.tv_set_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_set_add:
                new AddBranchDialog.Builder(this)
                        .setHeight(1f)  //屏幕高度*0.23
                        .setWidth(1f)  //屏幕宽度*0.65
                        .setCanceledOnTouchOutside(false)
                        .setOnclickListener(new DialogInterface.OnApplyEquTypeClickListener<AddBranchDialog>() {
                            @Override
                            public void clickPrintButton(AddBranchDialog dialog, View view) {
                                dialog.dismiss();
                            }

                            @Override
                            public void clickScanButton(AddBranchDialog dialog, View view) {
                                addType = 1;
                                String sss = null;
                                for(int i = 0; i < models.size(); i++) {
                                    if(TextUtils.isEmpty(sss)) {
                                        sss = models.get(i).getUserID() + "";
                                    }else {
                                        sss = sss + "," + models.get(i).getUserID() + "";
                                    }
                                }
                                Intent intent = new Intent(ManageSettingActivity.this, SelectManageActivity.class);
                                intent.putExtra(Constants.ADD_MANAGE_TEXT,sss);
                                startActivityForResult(intent, Constants.ADD_MANAGE);
                                dialog.dismiss();
                            }

                            @Override
                            public void clickBobaoButton(AddBranchDialog dialog, View view) {
                                addType = 2;
                                String sss = null;
                                for(int i = 0; i < models.size(); i++) {
                                    if(TextUtils.isEmpty(sss)) {
                                        sss = models.get(i).getUserID() + "";
                                    }else {
                                        sss = sss + "," + models.get(i).getUserID() + "";
                                    }
                                }
                                Intent intent = new Intent(ManageSettingActivity.this, SelectManageActivity.class);
                                intent.putExtra(Constants.ADD_MANAGE_TEXT,sss);
                                startActivityForResult(intent, Constants.ADD_MANAGE);
                                dialog.dismiss();
                            }
                        })
                        .build()
                        .show();
                break;
        }
    }

    public void addAuthority(String models) {
        ToastUtil.showToast(this,models);
        getP().manageSetting();
    }

    public void manageSetting(List<ManageSettingModel> models) {
        this.models = models;
        ManageSettingAdapter adapter = new ManageSettingAdapter(this,models);
        manRecycle.setAdapter(adapter);

        adapter.setOnDeleteClickListener(new ManageSettingAdapter.OnDeleteClickLister() {
            @Override
            public void onItemClick(View view, int position) {
                getP().deleteManage(models.get(position).getUserID() + "");
                manRecycle.closeMenu();
            }
        });

        adapter.setOnItenClickLister(new ManageSettingAdapter.OnItenClickLister() {
            @Override
            public void onItemClick(View view, int position) {
                int roleID = models.get(position).getRoleID();
                if(roleID == 3 || roleID == 4) {
                    ManageSettingModel model = models.get(position);
                    Intent intent = new Intent(ManageSettingActivity.this, AuthoritySettingActivity.class);
                    intent.putExtra(Constants.QX_SET_MODEL,model);
                    startActivityForResult(intent, Constants.QUANXIAN_SET);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.QUANXIAN_SET == requestCode) {
            if (data != null) {
                getP().manageSetting();
            }
        }else if (Constants.ADD_MANAGE == requestCode) {
            if (data != null) {
                String uid = data.getStringExtra(Constants.ADD_MANAGE_ID);
                if(addType == 1) {
                    getP().addAuthority(uid,"2","","1","1","1","1","1");
                }else if(addType == 2) {
                    getP().addAuthority(uid,"3","","0","0","0","0","0");
                }
            }
        }
    }

    public void deleteManage(String models) {
        ToastUtil.showToast(this,models);
        getP().manageSetting();
    }
}
