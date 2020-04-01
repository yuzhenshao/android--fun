package com.mfzn.deepusesSer.activityxm;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.xiangmu.SearchProjectAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.present.foundxm.SearchProjectPresnet;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchProjectActivity extends BaseMvpActivity<SearchProjectPresnet> {

    @BindView(R.id.et_se_search)
    EditText etSeSearch;
    @BindView(R.id.tv_se_qx)
    TextView tvSeQx;
    @BindView(R.id.tv_se_sousuo)
    TextView tvSeSousuo;
    @BindView(R.id.se_listview)
    ListView seListview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_project;
    }

    @Override
    public SearchProjectPresnet newP() {
        return new SearchProjectPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        SearchProjectAdapter adapter = new SearchProjectAdapter(this);
        seListview.setAdapter(adapter);

        etSeSearch.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etSeSearch.getText().toString().trim())){
                    tvSeSousuo.setVisibility(View.GONE);
                    tvSeQx.setVisibility(View.VISIBLE);
                }else {
                    tvSeSousuo.setVisibility(View.VISIBLE);
                    tvSeQx.setVisibility(View.GONE);
                }
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
                break;
        }
    }
}
