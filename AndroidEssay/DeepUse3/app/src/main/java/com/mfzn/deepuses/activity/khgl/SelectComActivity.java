package com.mfzn.deepuses.activity.khgl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import com.mfzn.deepuses.adapter.khgl.SelectComAdapter;
import com.mfzn.deepuses.adapter.my.SwitchCompanyAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.model.khgl.SearchComModel;
import com.mfzn.deepuses.present.customer.SelectComPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectComActivity extends BaseMvpActivity<SelectComPresnet> {

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
        return R.layout.activity_select_com;
    }

    @Override
    public SelectComPresnet newP() {
        return new SelectComPresnet();
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
                getP().searchCom(etSeSearch.getText().toString().trim());
                break;
            case R.id.iv_se_delete:
                etSeSearch.getText().clear();
                break;
        }
    }

    public void companyListSuccess(List<SearchComModel> models) {
        SelectComAdapter adapter = new SelectComAdapter(this,models);
        seListview.setAdapter(adapter);

        seListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchComModel dataBean = models.get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.COM_ID, String.valueOf(dataBean.getData_id()));
                intent.putExtra(Constants.COM_NAME, dataBean.getCompanyName());
                setResult(Constants.SELECT_COM,intent);
                finish();
            }
        });
    }
}
