package com.mfzn.deepusesSer.activityxm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.jiagou.ZuzhiDepartment2Adapter;
import com.mfzn.deepusesSer.adapter.xiangmu.SelectPerson2Adapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.present.foundxm.SelectPerson2Present;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.view.MyListview;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectPerson2Activity extends BaseMvpActivity<SelectPerson2Present> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.listview1)
    MyListview listview1;
    @BindView(R.id.listview2)
    MyListview listview2;
    @BindView(R.id.ll_per_empty)
    LinearLayout llPerEmpty;

    private int positions;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_person2;
    }

    @Override
    public SelectPerson2Present newP() {
        return new SelectPerson2Present();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_select_person));

        positions = getIntent().getIntExtra(Constants.EDIT_STAFF_BM_POSI, 0);

        getP().jiagouList();
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void selectPersonSuccess(ZuzhiJiagouModel model) {
        if(model.getSons().get(positions).getStaff().size() == 0 && model.getSons().get(positions).getSons().size() == 0) {
            llPerEmpty.setVisibility(View.VISIBLE);
        }else {
            llPerEmpty.setVisibility(View.GONE);
        }

        List<ZuzhiJiagouModel.SonsBeanX.StaffBeanX> staff = model.getSons().get(positions).getStaff();
        SelectPerson2Adapter zuzhiPersonalAdapter = new SelectPerson2Adapter(this,staff);
        listview1.setAdapter(zuzhiPersonalAdapter);

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ZuzhiJiagouModel.SonsBeanX.StaffBeanX staffBeanX = model.getSons().get(positions).getStaff().get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECT_PERSON_ID, staffBeanX.getUid());
                intent.putExtra(Constants.SELECT_PERSON_NAME, staffBeanX.getU_name());
                setResult(Constants.SELECT_PERSON,intent);
                finish();
            }
        });
        ZuzhiDepartment2Adapter zuzhiDepartmentAdapter = new ZuzhiDepartment2Adapter(this,model.getSons().get(positions).getSons());
        listview2.setAdapter(zuzhiDepartmentAdapter);

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectPerson2Activity.this, SelectPerson3Activity.class);
                intent.putExtra(Constants.EDIT_STAFF_BM_POSI,positions);
                intent.putExtra(Constants.EDIT_STAFF_BM_POSI2,position);
                startActivityForResult(intent, Constants.SELECT_PERSON);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(Constants.SELECT_PERSON == requestCode){
            if(data != null) {
                String gwID = data.getStringExtra(Constants.SELECT_PERSON_ID);
                String name = data.getStringExtra(Constants.SELECT_PERSON_NAME);
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECT_PERSON_ID, gwID);
                intent.putExtra(Constants.SELECT_PERSON_NAME, name);
                setResult(Constants.SELECT_PERSON,intent);
                finish();
            }
        }
    }
}
