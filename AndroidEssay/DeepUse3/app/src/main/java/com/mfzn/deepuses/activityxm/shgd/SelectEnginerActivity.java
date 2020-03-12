package com.mfzn.deepuses.activityxm.shgd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.my.EnginerAdapter;
import com.mfzn.deepuses.adapter.my.SwitchCompanyAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.SelectEnginerModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.present.xmgd.SelectEnginerPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;

public class SelectEnginerActivity extends BaseMvpActivity<SelectEnginerPresent> {

    @BindView(R.id.et_se_search)
    EditText etSeSearch;
    @BindView(R.id.tv_se_qx)
    TextView tvSeQx;
    @BindView(R.id.tv_se_sousuo)
    TextView tvSeSousuo;
//    @BindView(R.id.iv_se_icon)
//    RoundImageView ivSeIcon;
//    @BindView(R.id.tv_se_name)
//    TextView tvSeName;
//    @BindView(R.id.tv_se_phone)
//    TextView tvSePhone;
//    @BindView(R.id.ll_se_add)
//    LinearLayout llSeAdd;
   // private String data_id;

    @BindView(R.id.enginer_list)
    ListView swiList;
    private List<SelectEnginerModel> mEnginerModelList=new ArrayList<>();
    private EnginerAdapter adapter;

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
                    tvSeSousuo.setVisibility(View.GONE);
                    tvSeQx.setVisibility(View.VISIBLE);
                } else {
                    tvSeSousuo.setVisibility(View.VISIBLE);
                    tvSeQx.setVisibility(View.GONE);
                }
            }
        });
        adapter = new EnginerAdapter(this,mEnginerModelList);
        swiList.setAdapter(adapter);
        swiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getP().addEnginer(mEnginerModelList.get(position).getEngineerUserID()+"");
            }
        });
    }

    @OnClick({R.id.tv_se_qx, R.id.tv_se_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_se_qx:
                finish();
                break;
            case R.id.tv_se_sousuo:
                String trim = etSeSearch.getText().toString().trim();
                getP().selectEnginer(trim);
                break;
        }
    }

    public void selectEnginerSuccess(List<SelectEnginerModel> modelList) {
        mEnginerModelList.clear();
        mEnginerModelList.addAll(modelList);
        adapter.notifyDataSetChanged();
//        if(modelList!=null&&modelList.size()>0) {
//            SelectEnginerModel model = modelList.get(0);
//            llSeAdd.setVisibility(View.VISIBLE);
//            data_id = model.getEngineerUserID() + "";
//
//            String u_head = model.getEngineerAvatar();
//            if (!TextUtils.isEmpty(u_head)) {
//                Glide.with(this).load(ApiHelper.BASE_URL + u_head).into(ivSeIcon);
//            }
//            tvSeName.setText(model.getEngineerName());
//            tvSePhone.setText(model.getEngineerPhone());
//        }
    }

    public void addEnginerSuccess() {
        ToastUtil.showToast(this,"添加成功");
        Intent intent = new Intent();
        intent.putExtra("Sas", "sa");
        setResult(Constants.SELECT_ENGINER,intent);
        finish();
    }
}
