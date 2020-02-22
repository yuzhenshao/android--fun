package com.mfzn.deepuses.activity.khgl;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.khgl.SearchCustomerAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.khgl.WholeCustomerModel;
import com.mfzn.deepuses.present.customer.SearchCustomerPresnet;
import com.mfzn.deepuses.present.customer.SearchMyCustomerPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.PhoneUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchMyCustomerActivity extends BaseMvpActivity<SearchMyCustomerPresnet> {

    @BindView(R.id.et_se_search)
    EditText etSeSearch;
    @BindView(R.id.tv_se_qx)
    TextView tvSeQx;
    @BindView(R.id.tv_se_sousuo)
    TextView tvSeSousuo;
    @BindView(R.id.se_listview)
    ListView seListview;
    @BindView(R.id.iv_se_delete)
    ImageView iv_se_delete;
    @BindView(R.id.ll_shgd_empty)
    LinearLayout ll_shgd_empty;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_search_customer;
    }

    @Override
    public SearchMyCustomerPresnet newP() {
        return new SearchMyCustomerPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        seListview.setEmptyView(ll_shgd_empty);

        etSeSearch.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etSeSearch.getText().toString().trim())){
                    tvSeSousuo.setVisibility(View.GONE);
                    tvSeQx.setVisibility(View.VISIBLE);
                    iv_se_delete.setVisibility(View.GONE);
                }else {
                    tvSeSousuo.setVisibility(View.VISIBLE);
                    tvSeQx.setVisibility(View.GONE);
                    iv_se_delete.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick({R.id.tv_se_qx, R.id.tv_se_sousuo, R.id.iv_se_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_se_qx:
                finish();
                break;
            case R.id.tv_se_sousuo:
                getP().wholeCustomer(1,etSeSearch.getText().toString().trim(),"","","");
                break;
            case R.id.iv_se_delete:
                etSeSearch.getText().clear();
                break;
        }
    }

    public void brickRecordSuccess(WholeCustomerModel models) {
        List<WholeCustomerModel.DataBean> data = models.getData();
        SearchCustomerAdapter adapter = new SearchCustomerAdapter(this,data);
        seListview.setAdapter(adapter);

        seListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchMyCustomerActivity.this, CustomerDetailsActivity.class);
                intent.putExtra(Constants.CUSTOMER_ID,String.valueOf(data.get(position).getData_id()));
                startActivity(intent);
            }
        });

        adapter.setOnPhoneItemClickListener(new SearchCustomerAdapter.OnPhoneItemClickListener() {
            @Override
            public void onItemClick(View view, String phone) {
                PhoneUtils.dialogPhone2(SearchMyCustomerActivity.this, "拨打",phone,phone);
            }
        });
    }
}
