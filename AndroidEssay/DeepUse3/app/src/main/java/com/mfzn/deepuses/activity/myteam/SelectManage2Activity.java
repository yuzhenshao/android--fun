package com.mfzn.deepuses.activity.myteam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.jiagou.EditStaffActivity;
import com.mfzn.deepuses.activity.jiagou.ManageJiagou2Activity;
import com.mfzn.deepuses.activity.jiagou.ManageJiagou3Activity;
import com.mfzn.deepuses.activity.jiagou.PersonalInfoActivity;
import com.mfzn.deepuses.adapter.company.SelectManage2Adapter;
import com.mfzn.deepuses.adapter.company.SelectManageAdapter;
import com.mfzn.deepuses.adapter.jiagou.ManageJiagou2Adapter;
import com.mfzn.deepuses.adapter.jiagou.MoveStaffAdapter;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiDepartment2Adapter;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiDepartmentAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.present.myteam.SelectManage2Present;
import com.mfzn.deepuses.present.myteam.SelectManagePresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.view.MyListview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectManage2Activity extends BaseMvpActivity<SelectManage2Present> {

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
    private int positions;
    private List<ZuzhiJiagouModel.SonsBeanX.StaffBeanX> staff = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_manage2;
    }

    @Override
    public SelectManage2Present newP() {
        return new SelectManage2Present();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_select_manage));

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview.setLayoutManager(layoutManager2);

        stringExtra = getIntent().getStringExtra(Constants.ADD_MANAGE_TEXT);
        positions = getIntent().getIntExtra(Constants.EDIT_STAFF_BM_POSI, 0);

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
                for(int i = 0; i < staff.size(); i++) {
                    if(staff.get(i).getSelectType()) {
                        if(TextUtils.isEmpty(sss)) {
                            sss = staff.get(i).getUid();
                        }else {
                            sss = sss + "," + staff.get(i).getUid();
                        }
                    }
                }
                if(TextUtils.isEmpty(sss)) {
                    ToastUtil.showToast(this,"请选择管理员");
                }else {
                    Intent intent = new Intent();
                    intent.putExtra(Constants.ADD_MANAGE_ID, sss);
                    setResult(Constants.ADD_MANAGE2,intent);
                    finish();
                }
                break;
        }
    }

    public void jiagouListSuccess(ZuzhiJiagouModel model) {

        if(model.getSons().get(positions).getStaff().size() == 0 && model.getSons().get(positions).getSons().size() == 0) {
            llManEmpty.setVisibility(View.VISIBLE);
        }else {
            llManEmpty.setVisibility(View.GONE);
        }

        staff = model.getSons().get(positions).getStaff();

        String[]  strs = stringExtra.split(",");
        for(int i = 0; i < staff.size(); i++) {
            for(int i1 = 0; i1 < strs.length; i1++) {
                if(strs[i1].equals(staff.get(i).getUid())) {
                    staff.get(i).setMoren(true);
                }
            }
        }

        SelectManage2Adapter zuzhiPersonalAdapter = new SelectManage2Adapter(this, staff);
        listview1.setAdapter(zuzhiPersonalAdapter);

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ZuzhiJiagouModel.SonsBeanX.StaffBeanX beanXX = model.getSons().get(positions).getStaff().get(position);
                if(!beanXX.getMoren()) {
                    if(beanXX.getSelectType()) {
                        beanXX.setSelectType(false);
                    }else {
                        beanXX.setSelectType(true);
                    }
                    zuzhiPersonalAdapter.notifyDataSetChanged();
                    MoveStaffAdapter recycleAdapter = new MoveStaffAdapter(SelectManage2Activity.this,model,"2",positions,0);
                    recycleview.setAdapter(recycleAdapter);
                }
            }
        });

        ZuzhiDepartment2Adapter zuzhiDepartmentAdapter = new ZuzhiDepartment2Adapter(this,model.getSons().get(positions).getSons());
        listview2.setAdapter(zuzhiDepartmentAdapter);

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectManage2Activity.this, SelectManage3Activity.class);
                intent.putExtra(Constants.EDIT_STAFF_BM_POSI,positions);
                intent.putExtra(Constants.EDIT_STAFF_BM_POSI2,position);
                intent.putExtra(Constants.ADD_MANAGE_TEXT,stringExtra);
                startActivityForResult(intent, Constants.ADD_MANAGE3);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.ADD_MANAGE3 == requestCode) {
            if (data != null) {
                String uid = data.getStringExtra(Constants.ADD_MANAGE_ID);
                Intent intent = new Intent();
                intent.putExtra(Constants.ADD_MANAGE_ID, uid);
                setResult(Constants.ADD_MANAGE2,intent);
                finish();
            }
        }
    }
}
