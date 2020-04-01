package com.mfzn.deepusesSer.activityxm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.jiagou.ZuzhiDepartmentAdapter;
import com.mfzn.deepusesSer.adapter.xiangmu.SelectPersonAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepusesSer.present.foundxm.SelectPersonPresent;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.view.MyListview;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectPersonActivity extends BaseMvpActivity<SelectPersonPresent> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.listview1)
    MyListview listview1;
    @BindView(R.id.listview2)
    MyListview listview2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_person;
    }

    @Override
    public SelectPersonPresent newP() {
        return new SelectPersonPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_select_person));

        getP().jiagouList();
    }

    @OnClick(R.id.iv_login_back)
    public void onViewClicked() {
        finish();
    }

    public void selectPersonSuccess(ZuzhiJiagouModel model) {
        SelectPersonAdapter adapter = new SelectPersonAdapter(this, model.getStaff());
        listview1.setAdapter(adapter);

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECT_PERSON_ID, model.getStaff().get(position).getUid());
                intent.putExtra(Constants.SELECT_PERSON_NAME, model.getStaff().get(position).getU_name());
                setResult(Constants.SELECT_PERSON,intent);
                finish();
            }
        });

        ZuzhiDepartmentAdapter zuzhiDepartmentAdapter = new ZuzhiDepartmentAdapter(this, model.getSons());
        listview2.setAdapter(zuzhiDepartmentAdapter);

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectPersonActivity.this, SelectPerson2Activity.class);
                intent.putExtra(Constants.EDIT_STAFF_BM_POSI, position);
                startActivity(intent);
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
