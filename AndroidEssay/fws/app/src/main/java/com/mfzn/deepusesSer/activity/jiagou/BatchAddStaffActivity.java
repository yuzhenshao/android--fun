package com.mfzn.deepusesSer.activity.jiagou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.jiagou.BatchAddStaffAdapter;
import com.mfzn.deepusesSer.adapter.jiagou.MoveStaffAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.present.jiagou.BatchAddStaffPresent;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.utils.EventMsg;
import com.mfzn.deepusesSer.utils.RxBus;
import com.mfzn.deepusesSer.utils.ToastUtil;
import com.mfzn.deepusesSer.view.MyListview;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BatchAddStaffActivity extends BaseMvpActivity<BatchAddStaffPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_content)
    TextView tvBassContent;
    @BindView(R.id._balistview)
    MyListview Balistview;
    @BindView(R.id.ba_recycleview)
    RecyclerView baRecycleview;

    private String types;
    private int positions;
    private int positions2;

    private ZuzhiJiagouModel model;
    private BatchAddStaffAdapter addStaffAdapter;

    private String userIDs = null;
    private String oldDepartmentID = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_batch_add_staff;
    }

    @Override
    public BatchAddStaffPresent newP() {
        return new BatchAddStaffPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_batch_add_staff));
        tvBassContent.setVisibility(View.VISIBLE);
        tvBassContent.setText("全选");

        Intent intent = getIntent();
        types = intent.getStringExtra(Constants.MOVE_STAFF_TYPE1);
        positions = intent.getIntExtra(Constants.MOVE_STAFF_POSITION2,0);
        positions2 = intent.getIntExtra(Constants.MOVE_STAFF_POSITION3,0);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        baRecycleview.setLayoutManager(layoutManager2);

        getP().jiagouList();
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_content, R.id.tv_ba_move})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_content:
                wholeSelect();
                break;
            case R.id.tv_ba_move:
                moveStaff();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Constants.EDIT_STAFF_BM == requestCode){
            if(data != null) {
                String newDepartmentID = data.getStringExtra(Constants.EDIT_STAFF_ID);
//                String name = data.getStringExtra(Constants.EDIT_STAFF_NAME);
                getP().moveStaff(userIDs,oldDepartmentID,newDepartmentID);
            }
        }
    }

    public void jiagouListSuccess(ZuzhiJiagouModel model) {
        this.model = model;
        if(types.equals("1")) {
            addStaffAdapter = new BatchAddStaffAdapter(this, model,types,positions,positions2);
            Balistview.setAdapter(addStaffAdapter);
            Balistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ZuzhiJiagouModel.StaffBeanXX staffBeanXX = model.getStaff().get(position);
                    if (staffBeanXX.getSelectType()) {
                        staffBeanXX.setSelectType(false);
                    } else {
                        staffBeanXX.setSelectType(true);
                    }
                    addStaffAdapter.notifyDataSetChanged();
                    MoveStaffAdapter recycleAdapter = new MoveStaffAdapter(BatchAddStaffActivity.this,model,types,positions,positions2);
                    baRecycleview.setAdapter(recycleAdapter);
                }
            });
        } else if(types.equals("2")) {
            addStaffAdapter = new BatchAddStaffAdapter(this, model,types,positions,positions2);
            Balistview.setAdapter(addStaffAdapter);
            Balistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ZuzhiJiagouModel.SonsBeanX.StaffBeanX staffBeanXX = model.getSons().get(positions).getStaff().get(position);
                    if (staffBeanXX.getSelectType()) {
                        staffBeanXX.setSelectType(false);
                    } else {
                        staffBeanXX.setSelectType(true);
                    }
                    addStaffAdapter.notifyDataSetChanged();
                    MoveStaffAdapter recycleAdapter = new MoveStaffAdapter(BatchAddStaffActivity.this,model,types,positions,positions2);
                    baRecycleview.setAdapter(recycleAdapter);
                }
            });
        } else if(types.equals("3")) {
            addStaffAdapter = new BatchAddStaffAdapter(this, model,types,positions,positions2);
            Balistview.setAdapter(addStaffAdapter);
            Balistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean staffBean =
                            model.getSons().get(positions).getSons().get(positions2).getStaff().get(position);
                    if (staffBean.getSelectType()) {
                        staffBean.setSelectType(false);
                    } else {
                        staffBean.setSelectType(true);
                    }
                    addStaffAdapter.notifyDataSetChanged();
                    MoveStaffAdapter recycleAdapter = new MoveStaffAdapter(BatchAddStaffActivity.this,model,types,positions,positions2);
                    baRecycleview.setAdapter(recycleAdapter);
                }
            });
        }
    }

    public void moveStaffSuccess() {
        ToastUtil.showToast(this,"移动成功");
        EventMsg eventMsg = new EventMsg();
        eventMsg.setMsg(Constants.EDITSTAFF);
        RxBus.getInstance().post(eventMsg);
        finish();
    }

    //移动
    private void moveStaff() {
        if(model != null) {
            userIDs = "";
            if(types.equals("1")) {
                List<ZuzhiJiagouModel.StaffBeanXX> modelStaff = model.getStaff();
                for(int i = 0; i < modelStaff.size(); i++) {
                    ZuzhiJiagouModel.StaffBeanXX beanXX = modelStaff.get(i);
                    if (beanXX.getSelectType()) {
                        if(TextUtils.isEmpty(userIDs)) {
                            userIDs = beanXX.getUid();
                        }else {
                            userIDs = userIDs + "," + beanXX.getUid();
                        }
                    }
                }
                if(TextUtils.isEmpty(userIDs)) {
                    ToastUtil.showToast(this,"暂无成员");
                }else {
                    oldDepartmentID = model.getDepartmentID() + "";
                    startActivityForResult(new Intent(this, DepartmentActivity.class),Constants.EDIT_STAFF_BM);
                }
            }else if(types.equals("2")) {
                List<ZuzhiJiagouModel.SonsBeanX.StaffBeanX> staff = model.getSons().get(positions).getStaff();
                for(int i = 0; i < staff.size(); i++) {
                    ZuzhiJiagouModel.SonsBeanX.StaffBeanX beanXX = staff.get(i);
                    if (beanXX.getSelectType()) {
                        if(TextUtils.isEmpty(userIDs)) {
                            userIDs = beanXX.getUid();
                        }else {
                            userIDs = userIDs + "," + beanXX.getUid();
                        }
                    }
                }
                if(TextUtils.isEmpty(userIDs)) {
                    ToastUtil.showToast(this,"暂无成员");
                }else {
                    oldDepartmentID = model.getSons().get(positions).getDepartmentID() + "";
                    startActivityForResult(new Intent(this, DepartmentActivity.class),Constants.EDIT_STAFF_BM);
                }
            }else if(types.equals("3")) {
                List<ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean> staff = model.getSons().get(positions).getSons().get(positions2).getStaff();
                for(int i = 0; i < staff.size(); i++) {
                    ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean beanXX = staff.get(i);
                    if (beanXX.getSelectType()) {
                        if(TextUtils.isEmpty(userIDs)) {
                            userIDs = beanXX.getUid();
                        }else {
                            userIDs = userIDs + "," + beanXX.getUid();
                        }
                    }
                }
                if(TextUtils.isEmpty(userIDs)) {
                    ToastUtil.showToast(this,"暂无成员");
                }else {
                    oldDepartmentID = model.getSons().get(positions).getSons().get(positions2).getDepartmentID() + "";
                    startActivityForResult(new Intent(this, DepartmentActivity.class),Constants.EDIT_STAFF_BM);
                }
            }
        }
    }

    //全选
    private void wholeSelect() {
        if(model != null) {
            if(types.equals("1")) {
                List<ZuzhiJiagouModel.StaffBeanXX> modelStaff = model.getStaff();
                for(int i = 0; i < modelStaff.size(); i++) {
                    ZuzhiJiagouModel.StaffBeanXX beanXX = modelStaff.get(i);
                    if (!beanXX.getSelectType()) {
                        beanXX.setSelectType(true);
                    }
                }
                addStaffAdapter.notifyDataSetChanged();
                MoveStaffAdapter recycleAdapter = new MoveStaffAdapter(BatchAddStaffActivity.this,model,types,positions,positions2);
                baRecycleview.setAdapter(recycleAdapter);
            }else if(types.equals("2")) {
                List<ZuzhiJiagouModel.SonsBeanX.StaffBeanX> staff = model.getSons().get(positions).getStaff();
                for(int i = 0; i < staff.size(); i++) {
                    ZuzhiJiagouModel.SonsBeanX.StaffBeanX beanXX = staff.get(i);
                    if (!beanXX.getSelectType()) {
                        beanXX.setSelectType(true);
                    }
                }
                addStaffAdapter.notifyDataSetChanged();
                MoveStaffAdapter recycleAdapter = new MoveStaffAdapter(BatchAddStaffActivity.this,model,types,positions,positions2);
                baRecycleview.setAdapter(recycleAdapter);
            }else if(types.equals("3")) {
                List<ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean> staff = model.getSons().get(positions).getSons().get(positions2).getStaff();
                for(int i = 0; i < staff.size(); i++) {
                    ZuzhiJiagouModel.SonsBeanX.SonsBean.StaffBean beanXX = staff.get(i);
                    if (!beanXX.getSelectType()) {
                        beanXX.setSelectType(true);
                    }
                }
                addStaffAdapter.notifyDataSetChanged();
                MoveStaffAdapter recycleAdapter = new MoveStaffAdapter(BatchAddStaffActivity.this,model,types,positions,positions2);
                baRecycleview.setAdapter(recycleAdapter);
            }
        }
    }
}
