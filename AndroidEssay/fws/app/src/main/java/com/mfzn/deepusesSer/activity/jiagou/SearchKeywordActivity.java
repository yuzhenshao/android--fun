package com.mfzn.deepusesSer.activity.jiagou;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.jiagou.SearchKeywordAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.jiagou.SearchKeywordModel;
import com.mfzn.deepusesSer.present.jiagou.SearchKeywordPresent;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchKeywordActivity extends BaseMvpActivity<SearchKeywordPresent> {

    @BindView(R.id.et_key_search)
    EditText etKeySearch;
    @BindView(R.id.iv_key_delete)
    ImageView ivKeyDelete;
    @BindView(R.id.keyListview)
    ListView keyListview;

    private List<SearchKeywordModel> models;

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

        models = new ArrayList<>();

        etKeySearch.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = etKeySearch.getText().toString().trim();
                if(TextUtils.isEmpty(trim)) {
                    ivKeyDelete.setVisibility(View.GONE);
                    SearchKeywordAdapter adapter = new SearchKeywordAdapter(SearchKeywordActivity.this,models);
                    keyListview.setAdapter(adapter);
                }else {
                    ivKeyDelete.setVisibility(View.VISIBLE);
                    getP().searchKeyword(trim);
                }
            }
        });
    }

    @OnClick({R.id.iv_key_delete, R.id.tv_key_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_key_delete:
                etKeySearch.getText().clear();
                break;
            case R.id.tv_key_cancel:
                finish();
                break;
        }
    }

    public void searchKeywordSuccess(List<SearchKeywordModel> models) {
        SearchKeywordAdapter adapter = new SearchKeywordAdapter(this,models);
        keyListview.setAdapter(adapter);

        String trim = etKeySearch.getText().toString().trim();
        adapter.setselect(trim);
    }
}
