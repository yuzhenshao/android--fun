package com.mfzn.deepuses.activity.company;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.MainActivity;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.company.SelectLableAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.company.SelectLableModel;
import com.mfzn.deepuses.model.login.UserModel;
import com.mfzn.deepuses.present.company.SelectLablePresent;
import com.mfzn.deepuses.present.login.LoginPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.router.Router;

public class SelectLableActivity extends BaseMvpActivity<SelectLablePresent> {


    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;
    @BindView(R.id.ll_la_hint)
    LinearLayout llLaHint;
    @BindView(R.id.sel_gridview)
    GridView selGridview;
    @BindView(R.id.but_confirm)
    Button butConfirm;

    private List<SelectLableModel> models;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_lable;
    }

    @Override
    public SelectLablePresent newP() {
        return new SelectLablePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvBassTitle.setText("选择标签");

        getP().companyLable();
    }

    @OnClick({R.id.iv_login_back, R.id.iv_la_delete, R.id.but_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.iv_la_delete:
                llLaHint.setVisibility(View.GONE);
                break;
            case R.id.but_confirm:
                String name = null;
                String typeID = null;
                for (int i = 0 ; i < models.size() ; i++){
                    if(models.get(i).getClick()){
                        if(TextUtils.isEmpty(name)) {
                            name = models.get(i).getName();
                            typeID = models.get(i).getData_id() + "";
                        }else {
                            name = name + "," + models.get(i).getName();
                            typeID = typeID + "," + models.get(i).getData_id();
                        }
                    }
                }
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECT_LAVLE_NAME, name);
                intent.putExtra(Constants.SELECT_LAVLE_ID, typeID);
                setResult(Constants.SELECT_LAVLE,intent);
                finish();
                break;
        }
    }

    public void companyLableSuccess(List<SelectLableModel> models) {
        this.models = models;
        SelectLableAdapter adapter = new SelectLableAdapter(this,models);
        selGridview.setAdapter(adapter);

        adapter.setOnItemClickLisenter(new SelectLableAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick() {
                boolean mclick = false;
                for (int i = 0 ; i < models.size() ; i++){
                    Boolean click = models.get(i).getClick();
                    if(click){
                        mclick = true;
                        break;
                    }else {
                        mclick = false;
                    }
                }
                if(mclick) {
                    butConfirm.setEnabled(true);
                    butConfirm.setBackgroundResource(R.drawable.yuanjiao_4a9012_button_shape);
                }else {
                    butConfirm.setEnabled(false);
                    butConfirm.setBackgroundResource(R.drawable.yuanjiao_bfc2cc_button_shape);
                }
            }
        });
    }
}
