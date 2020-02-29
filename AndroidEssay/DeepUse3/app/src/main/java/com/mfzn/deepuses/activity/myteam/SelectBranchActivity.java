package com.mfzn.deepuses.activity.myteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.company.MoveBranchAdapter;
import com.mfzn.deepuses.adapter.company.SelectBranchAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.present.myteam.SelectBranchPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectBranchActivity extends BaseMvpActivity<SelectBranchPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.seListview)
    ListView seListview;
    @BindView(R.id.serecycleview)
    RecyclerView serecycleview;

    private List<ZuzhiJiagouModel.SonsBeanX> sons;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_branch;
    }

    @Override
    public SelectBranchPresent newP() {
        return new SelectBranchPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_select_branch));

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        serecycleview.setLayoutManager(layoutManager2);

        getP().eepartment();
    }

    @OnClick({R.id.iv_login_back, R.id.tv_se_move})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_se_move:
                String sss = null;
                String name = null;
                for(int i = 0; i < sons.size(); i++) {
                    if(sons.get(i).getSelectDe()) {
                        if(TextUtils.isEmpty(sss)) {
                            sss = sons.get(i).getDepartmentID() + "";
                            name = sons.get(i).getDepartmentName();
                        }else {
                            sss = sss + "," + sons.get(i).getDepartmentID() + "";
                            name = name + "," + sons.get(i).getDepartmentName();
                        }
                    }
                    List<ZuzhiJiagouModel.SonsBean> sons1 = sons.get(i).getSons();
                    for(int i1 = 0; i1 < sons1.size(); i1++) {
                        if(sons1.get(i1).getSelectDe()) {
                            if(TextUtils.isEmpty(sss)) {
                                sss = sons1.get(i1).getDepartmentID() + "";
                                name = sons1.get(i1).getDepartmentName();
                            }else {
                                sss = sss + "," + sons1.get(i1).getDepartmentID() + "";
                                name = name + "," + sons1.get(i1).getDepartmentName();
                            }
                        }
                    }
                }
                if(TextUtils.isEmpty(sss)) {
                    ToastUtil.showToast(this,"请选择部门");
                }else {
                    Intent intent = new Intent();
                    intent.putExtra(Constants.SELECT_BU_TEXT, sss);
                    intent.putExtra(Constants.SELECT_BU_NAME, name);
                    setResult(Constants.SELECT_BU,intent);
                    finish();
                }
                break;
        }
    }

    public void eepartmentSuccess(ZuzhiJiagouModel model) {
        sons = model.getSons();
        List<String> branch = new ArrayList<>();
        List<Integer> branchID = new ArrayList<>();

        String models = getIntent().getStringExtra(Constants.QX_SET_TEXT);
        if(!TextUtils.isEmpty(models)) {
            String[] split = models.split(",");
            for(int i = 0; i < sons.size(); i++) {
                int departmentID = sons.get(i).getDepartmentID();
                for(int i1 = 0; i1 < split.length; i1++) {
                    if(split[i1].equals(String.valueOf(departmentID))) {
                        sons.get(i).setSelectDe(true);
                    }
                }
                List<ZuzhiJiagouModel.SonsBean> sons1 = this.sons.get(i).getSons();
                for(int i1 = 0; i1 < sons1.size(); i1++) {
                    int departmentID1 = sons1.get(i1).getDepartmentID();
                    for(int d = 0; d < split.length; d++) {
                        if(split[d].equals(String.valueOf(departmentID1))) {
                            sons1.get(i1).setSelectDe(true);
                        }
                    }
                }
            }
        }

        SelectBranchAdapter adapter = new SelectBranchAdapter(this, sons);
        seListview.setAdapter(adapter);

        adapter.setOnItemOnClickLisenter(new SelectBranchAdapter.OnItemOnClickLisenter() {
            @Override
            public void onItemClick() {

                branch.clear();
                branchID.clear();

                for(int i = 0; i < sons.size(); i++) {
                    if(sons.get(i).getSelectDe()) {
                        String departmentName = sons.get(i).getDepartmentName() + " X";
                        int departmentID = sons.get(i).getDepartmentID();
                        branch.add(departmentName);
                        branchID.add(departmentID);
                    }
                    List<ZuzhiJiagouModel.SonsBean> sons1 = sons.get(i).getSons();
                    for(int i1 = 0; i1 < sons1.size(); i1++) {
                        if(sons1.get(i1).getSelectDe()) {
                            String departmentName1 = sons1.get(i1).getDepartmentName() + " X";
                            int departmentID = sons1.get(i1).getDepartmentID();
                            branch.add(departmentName1);
                            branchID.add(departmentID);
                        }
                    }
                }

                MoveBranchAdapter recycleAdapter = new MoveBranchAdapter(SelectBranchActivity.this,branch);
                serecycleview.setAdapter(recycleAdapter);

                recycleAdapter.setOnDelItemClickListener(new MoveBranchAdapter.OnDelItemClickListener() {
                    @Override
                    public void onDelItemClick(View view, int position) {

                        for(int i = 0; i < sons.size(); i++) {
                            if(sons.get(i).getDepartmentID() == branchID.get(position)) {
                                ZuzhiJiagouModel.SonsBeanX sonsBeanX = sons.get(i);
                                sonsBeanX.setSelectDe(false);
                                break;
                            }
                            List<ZuzhiJiagouModel.SonsBean> sons1 = sons.get(i).getSons();
                            for(int i1 = 0; i1 < sons1.size(); i1++) {
                                if(sons1.get(i1).getDepartmentID()  == branchID.get(position)) {
                                    ZuzhiJiagouModel.SonsBean sonsBean = sons1.get(i1);
                                    sonsBean.setSelectDe(false);
                                    break;
                                }
                            }
                        }

                        branch.remove(position);
                        branchID.remove(position);
                        recycleAdapter.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        for(int i = 0; i < sons.size(); i++) {
            if(sons.get(i).getSelectDe()) {
                String departmentName = sons.get(i).getDepartmentName() + " X";
                int departmentID = sons.get(i).getDepartmentID();
                branch.add(departmentName);
                branchID.add(departmentID);
            }
            List<ZuzhiJiagouModel.SonsBean> sons1 = sons.get(i).getSons();
            for(int i1 = 0; i1 < sons1.size(); i1++) {
                if(sons1.get(i1).getSelectDe()) {
                    String departmentName1 = sons1.get(i1).getDepartmentName() + " X";
                    int departmentID1 = sons1.get(i1).getDepartmentID();
                    branch.add(departmentName1);
                    branchID.add(departmentID1);
                }
            }
        }

        MoveBranchAdapter recycleAdapter = new MoveBranchAdapter(SelectBranchActivity.this,branch);
        serecycleview.setAdapter(recycleAdapter);

        recycleAdapter.setOnDelItemClickListener(new MoveBranchAdapter.OnDelItemClickListener() {
            @Override
            public void onDelItemClick(View view, int position) {

                for(int i = 0; i < sons.size(); i++) {
                    if(sons.get(i).getDepartmentID() == branchID.get(position)) {
                        ZuzhiJiagouModel.SonsBeanX sonsBeanX = sons.get(i);
                        sonsBeanX.setSelectDe(false);
                        break;
                    }
                    List<ZuzhiJiagouModel.SonsBean> sons1 = sons.get(i).getSons();
                    for(int i1 = 0; i1 < sons1.size(); i1++) {
                        if(sons1.get(i1).getDepartmentID()  == branchID.get(position)) {
                            ZuzhiJiagouModel.SonsBean sonsBean = sons1.get(i1);
                            sonsBean.setSelectDe(false);
                            break;
                        }
                    }
                }

                branch.remove(position);
                branchID.remove(position);
                recycleAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        });
    }
}
