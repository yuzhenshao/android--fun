package com.mfzn.deepuses.activityxm.kf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.present.xmkefu.AddCustomPresnet;
import com.mfzn.deepuses.present.xmkefu.EditCustomPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.ToastUtil;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditCustomActivity extends BaseMvpActivity<EditCustomPresnet> {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.tv_bass_comlate)
    TextView tvBassComlate;
    @BindView(R.id.et_ed_type)
    EditText etEdType;
    @BindView(R.id.et_ed_name)
    EditText etEdName;
    @BindView(R.id.et_ed_phone)
    EditText etEdPhone;

    private String typeID;
    private String kefuID;
    private int positions = -1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_custom;
    }

    @Override
    public EditCustomPresnet newP() {
        return new EditCustomPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_edit_custom));
        tvBassComlate.setVisibility(View.VISIBLE);

        CustomListModel customListModel = (CustomListModel) getIntent().getSerializableExtra(Constants.EDIT_KEFU_MODEL);
        typeID = customListModel.getKfTypeID() + "";
        kefuID = customListModel.getData_id() + "";
        etEdType.setText(customListModel.getTypeName());
        etEdName.setText(customListModel.getName());
        etEdPhone.setText(customListModel.getPhone());
    }

    @OnClick({R.id.iv_login_back, R.id.tv_bass_comlate, R.id.et_ed_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_bass_comlate:
                String name = etEdName.getText().toString().trim();
                if(TextUtils.isEmpty(name)) {
                    ToastUtil.showToast(this,"请输入名字");
                    return;
                }
                String phone = etEdPhone.getText().toString().trim();
                if(TextUtils.isEmpty(phone)) {
                    ToastUtil.showToast(this,"请输入电话");
                    return;
                }
                getP().editCustom(kefuID,typeID,name,phone);
                break;
            case R.id.et_ed_type:
                Intent intent = new Intent(this, CustomTypeActivity.class);
                intent.putExtra(Constants.KEFU_TYPE_POSI,positions);
                startActivityForResult(intent, Constants.KEFU_TYPE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.KEFU_TYPE == requestCode) {
            if (data != null) {
                positions = data.getIntExtra(Constants.KEFU_TYPE_POSI,-1);
                String name = data.getStringExtra(Constants.KEFU_TYPE_NAME);
                typeID = data.getStringExtra(Constants.KEFU_TYPE_ID);
                etEdType.setText(name);
            }
        }
    }

    public void editCustomSuccess(String models) {
        ToastUtil.showToast(this,models);
        Intent intent = new Intent();
        intent.putExtra("bvbv", "nmnm");
        setResult(Constants.ADD_KEFU,intent);
        finish();
    }
}
