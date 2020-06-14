package com.mfzn.deepuses.activity.myteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.company.SelectManageAdapter;
import com.mfzn.deepuses.adapter.jiagou.MoveStaffAdapter;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiDepartmentAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.present.myteam.SelectManagePresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.view.MyListview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectManageActivity extends BaseMvpActivity<SelectManagePresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.listview1)
    MyListview listview1;
    @BindView(R.id.listview2)
    MyListview listview2;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.ll_man_empty)
    LinearLayout llManEmpty;

    private String stringExtra;
    private List<ZuzhiJiagouModel.StaffBean> modelStaff = new ArrayList<>();
    private boolean isSingle = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_manage;
    }

    @Override
    public SelectManagePresent newP() {
        return new SelectManagePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_select_manage));
        isSingle = getIntent().getBooleanExtra(Constants.SINGLE, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview.setLayoutManager(layoutManager2);

        stringExtra = getIntent().getStringExtra(Constants.ADD_MANAGE_TEXT);

        getP().jiagouList();
    }

    @OnClick({R.id.iv_login_back, R.id.tv_se_move})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_se_move:
                String sss = null;
                ZuzhiJiagouModel.StaffBean staffBean = null;
                for (int i = 0; i < modelStaff.size(); i++) {
                    if (modelStaff.get(i).getSelectType()) {
                        staffBean = modelStaff.get(i);
                        if (TextUtils.isEmpty(sss)) {
                            sss = modelStaff.get(i).getUserID();
                        } else {
                            sss = sss + "," + modelStaff.get(i).getUserID();
                        }
                    }
                }
                if (TextUtils.isEmpty(sss)) {
                    ToastUtil.showToast(this, "请选择管理员");
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(Constants.ADD_MANAGE_ID, sss);
                    intent.putExtra(Constants.STAFFBEAN, staffBean);
                    setResult(Constants.ADD_MANAGE, intent);
                    finish();
                }
                break;
        }
    }

    public void jiagouListSuccess(ZuzhiJiagouModel model) {

        if (model.getStaff().size() == 0 && model.getSons().size() == 0) {
            llManEmpty.setVisibility(View.VISIBLE);
        } else {
            llManEmpty.setVisibility(View.GONE);
        }

        modelStaff = model.getStaff();

        if (!TextUtils.isEmpty(stringExtra)) {
            String[] strs = stringExtra.split(",");
            for (int i = 0; i < modelStaff.size(); i++) {
                for (int i1 = 0; i1 < strs.length; i1++) {
                    if (strs[i1].equals(modelStaff.get(i).getUserID())) {
                        modelStaff.get(i).setMoren(true);
                    }
                }
            }
        }

        SelectManageAdapter zuzhiPersonalAdapter = new SelectManageAdapter(this, modelStaff);
        listview1.setAdapter(zuzhiPersonalAdapter);

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<ZuzhiJiagouModel.StaffBean> staffBeanList = model.getStaff();
                if (isSingle && !ListUtil.isEmpty(staffBeanList)) {
                    for (ZuzhiJiagouModel.StaffBean staffBean : staffBeanList) {
                        staffBean.setSelectType(false);
                    }
                }

                ZuzhiJiagouModel.StaffBean beanXX = staffBeanList.get(position);
                if (!beanXX.getMoren()) {
                    if (beanXX.getSelectType()) {
                        beanXX.setSelectType(false);
                    } else {
                        beanXX.setSelectType(true);
                    }
                    zuzhiPersonalAdapter.notifyDataSetChanged();
                    MoveStaffAdapter recycleAdapter = new MoveStaffAdapter(SelectManageActivity.this, model, "1", 0, 0);
                    recycleview.setAdapter(recycleAdapter);
                }
            }
        });

        ZuzhiDepartmentAdapter zuzhiDepartmentAdapter = new ZuzhiDepartmentAdapter(this, model.getSons());
        listview2.setAdapter(zuzhiDepartmentAdapter);

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectManageActivity.this, SelectManage2Activity.class);
                intent.putExtra(Constants.SINGLE, isSingle);
                intent.putExtra(Constants.EDIT_STAFF_BM_POSI, position);
                intent.putExtra(Constants.ADD_MANAGE_TEXT, stringExtra);
                startActivityForResult(intent, Constants.ADD_MANAGE2);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.ADD_MANAGE2 == requestCode) {
            if (data != null) {
                String uid = data.getStringExtra(Constants.ADD_MANAGE_ID);
                Intent intent = new Intent();
                intent.putExtra(Constants.ADD_MANAGE_ID, uid);
                intent.putExtra(Constants.STAFFBEAN,( ZuzhiJiagouModel.StaffBean)data.getSerializableExtra(Constants.STAFFBEAN));
                setResult(Constants.ADD_MANAGE, intent);
                finish();
            }
        }
    }
}
