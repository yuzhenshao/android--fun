package com.mfzn.deepuses.activity.jiagou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.jiagou.SearchKeywordAdapter;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiDepartmentAdapter;
import com.mfzn.deepuses.adapter.jiagou.ZuzhiPersonalAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.jiagou.SearchKeywordModel;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.present.jiagou.SearchKeywordPresent;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.OnInputChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchKeywordActivity extends BaseMvpActivity<SearchKeywordPresent> {

    @BindView(R.id.et_key_search)
    EditText etKeySearch;
    @BindView(R.id.iv_key_delete)
    ImageView ivKeyDelete;
    @BindView(R.id.keyListview)
    ListView keyListview;
    @BindView(R.id.tv_key_sous)
    TextView tv_key_sous;
    @BindView(R.id.tv_key_cancel)
    TextView tv_key_cancel;
    @BindView(R.id.ll_shgd_empty)
    LinearLayout ll_shgd_empty;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_keyword;
    }

    @Override
    public SearchKeywordPresent newP() {
        return new SearchKeywordPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        keyListview.setEmptyView(ll_shgd_empty);

        etKeySearch.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = etKeySearch.getText().toString().trim();
                if(TextUtils.isEmpty(trim)) {
                    ivKeyDelete.setVisibility(View.GONE);
                    tv_key_sous.setVisibility(View.GONE);
                    tv_key_cancel.setVisibility(View.VISIBLE);
                }else {
                    ivKeyDelete.setVisibility(View.VISIBLE);
                    tv_key_sous.setVisibility(View.VISIBLE);
                    tv_key_cancel.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.iv_key_delete, R.id.tv_key_cancel, R.id.tv_key_sous})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_key_delete:
                etKeySearch.getText().clear();
                break;
            case R.id.tv_key_cancel:
                finish();
                break;
            case R.id.tv_key_sous:
                searchData();
                break;
        }
    }

    private void searchData() {
        String trim = etKeySearch.getText().toString().trim();
        getP().searchKeyword(trim);
    }

    public void searchKeywordSuccess(List<SearchKeywordModel> models) {
        SearchKeywordAdapter adapter = new SearchKeywordAdapter(this,models);
        keyListview.setAdapter(adapter);

        String trim = etKeySearch.getText().toString().trim();
        adapter.setselect(trim);

        keyListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchKeywordModel model = models.get(position);
                Intent intent = new Intent(SearchKeywordActivity.this, PersonalInfoActivity.class);
                intent.putExtra(Constants.PERSONAL_INFO, model);
                intent.putExtra(Constants.PERSONAL_INFO_TYPE, "4");
                startActivity(intent);
            }
        });
    }
}
