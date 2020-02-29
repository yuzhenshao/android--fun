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

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.company.SelectManage3Adapter;
import com.mfzn.deepuses.adapter.jiagou.MoveStaffAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.present.myteam.SelectManage3Present;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.view.MyListview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectManage3Activity extends BaseMvpActivity<SelectManage3Present> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.listview1)
    MyListview listview1;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.ll_man_empty)
    LinearLayout llManEmpty;

    private String stringExtra;
    private int positions;
    private int positions2;
    private List<ZuzhiJiagouModel.StaffBean> staff = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_manage3;
    }

    @Override
    public SelectManage3Present newP() {
        return new SelectManage3Present();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_select_manage));

        listview1.setEmptyView(llManEmpty);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview.setLayoutManager(layoutManager2);

        stringExtra = getIntent().getStringExtra(Constants.ADD_MANAGE_TEXT);
        positions = getIntent().getIntExtra(Constants.EDIT_STAFF_BM_POSI, 0);
        positions2 = getIntent().getIntExtra(Constants.EDIT_STAFF_BM_POSI2, 0);

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
                            sss = staff.get(i).getUserID();
                        }else {
                            sss = sss + "," + staff.get(i).getUserID();
                        }
                    }
                }
                if(TextUtils.isEmpty(sss)) {
                    ToastUtil.showToast(this,"请选择管理员");
                }else {
                    Intent intent = new Intent();
                    intent.putExtra(Constants.ADD_MANAGE_ID, sss);
                    setResult(Constants.ADD_MANAGE3,intent);
                    finish();
                }
                break;
        }
    }

    public void jiagouListSuccess(ZuzhiJiagouModel model) {

        this.staff = model.getSons().get(positions).getSons().get(positions2).getStaff();

        String[]  strs = stringExtra.split(",");
        for(int i = 0; i < this.staff.size(); i++) {
            for(int i1 = 0; i1 < strs.length; i1++) {
                if(strs[i1].equals(this.staff.get(i).getUserID())) {
                    this.staff.get(i).setMoren(true);
                }
            }
        }

        SelectManage3Adapter zuzhiPersonalAdapter = new SelectManage3Adapter(this, this.staff);
        listview1.setAdapter(zuzhiPersonalAdapter);

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ZuzhiJiagouModel.StaffBean beanXX = model.getSons().get(positions).getSons().get(positions2).getStaff().get(position);
                if(!beanXX.getMoren()) {
                    if(beanXX.getSelectType()) {
                        beanXX.setSelectType(false);
                    }else {
                        beanXX.setSelectType(true);
                    }
                    zuzhiPersonalAdapter.notifyDataSetChanged();
                    MoveStaffAdapter recycleAdapter = new MoveStaffAdapter(SelectManage3Activity.this,model,"3",positions,positions2);
                    recycleview.setAdapter(recycleAdapter);
                }
            }
        });
    }
}
