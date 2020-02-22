package com.mfzn.deepuses.activityxm.shgd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.SelectEnginerModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.xmgd.SelectEnginerPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.view.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectEnginerActivity extends BaseMvpActivity<SelectEnginerPresent> {

    @BindView(R.id.et_se_search)
    EditText etSeSearch;
    @BindView(R.id.tv_se_qx)
    TextView tvSeQx;
    @BindView(R.id.tv_se_sousuo)
    TextView tvSeSousuo;
    @BindView(R.id.iv_se_icon)
    RoundImageView ivSeIcon;
    @BindView(R.id.tv_se_name)
    TextView tvSeName;
    @BindView(R.id.tv_se_phone)
    TextView tvSePhone;
    @BindView(R.id.ll_se_add)
    LinearLayout llSeAdd;
    private String data_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_enginer;
    }

    @Override
    public SelectEnginerPresent newP() {
        return new SelectEnginerPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        etSeSearch.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s + "")) {
                    llSeAdd.setVisibility(View.GONE);
                    tvSeSousuo.setVisibility(View.GONE);
                    tvSeQx.setVisibility(View.VISIBLE);
                } else {
                    tvSeSousuo.setVisibility(View.VISIBLE);
                    tvSeQx.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.tv_se_qx, R.id.tv_se_sousuo, R.id.tv_se_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_se_qx:
                finish();
                break;
            case R.id.tv_se_sousuo:
                String trim = etSeSearch.getText().toString().trim();
                getP().selectEnginer(trim);
                break;
            case R.id.tv_se_add:
                getP().addEnginer(data_id);
                break;
        }
    }

    public void selectEnginerSuccess(SelectEnginerModel model) {
        llSeAdd.setVisibility(View.VISIBLE);

        data_id = model.getData_id() + "";

        String u_head = model.getU_head();
        if(!TextUtils.isEmpty(u_head)) {
            Glide.with(this).load(ApiHelper.BASE_URL + u_head).into(ivSeIcon);
        }

        tvSeName.setText(model.getU_name());
        tvSePhone.setText(model.getU_phone());
    }

    public void addEnginerSuccess() {
        ToastUtil.showToast(this,"添加成功");
        Intent intent = new Intent();
        intent.putExtra("Sas", "sa");
        setResult(Constants.SELECT_ENGINER,intent);
        finish();
    }
}
