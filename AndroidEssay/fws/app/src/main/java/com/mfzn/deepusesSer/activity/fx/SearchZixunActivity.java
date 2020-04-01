package com.mfzn.deepusesSer.activity.fx;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.faxian.HistorySearchAdapter;
import com.mfzn.deepusesSer.adapter.faxian.SearchZixunAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.faxian.HistorySearchModel;
import com.mfzn.deepusesSer.model.faxian.News;
import com.mfzn.deepusesSer.present.fx.SearchZixunPresnet;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;
import com.mfzn.deepusesSer.utils.UserHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchZixunActivity extends BaseMvpActivity<SearchZixunPresnet> {

    @BindView(R.id.et_zx_search)
    EditText etZxSearch;
    @BindView(R.id.iv_zx_delete)
    ImageView ivZxDelete;
    @BindView(R.id.tv_zx_qx)
    TextView tvZxQx;
    @BindView(R.id.tv_zx_sousuo)
    TextView tvZxSousuo;
    @BindView(R.id.zx_listview)
    ListView zxListview;
    @BindView(R.id.ll_se_ls)
    LinearLayout ll_se_ls;
    @BindView(R.id.ls_listview)
    ListView ls_listview;

    private HistorySearchAdapter accountAdapter;
    private List<HistorySearchModel> list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_zixun;
    }

    @Override
    public SearchZixunPresnet newP() {
        return new SearchZixunPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        setHistory();

        etZxSearch.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etZxSearch.getText().toString().trim())){
                    tvZxSousuo.setVisibility(View.GONE);
                    tvZxQx.setVisibility(View.VISIBLE);
                    ivZxDelete.setVisibility(View.GONE);
                }else {
                    tvZxSousuo.setVisibility(View.VISIBLE);
                    tvZxQx.setVisibility(View.GONE);
                    ivZxDelete.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick({R.id.iv_zx_delete, R.id.tv_zx_qx, R.id.tv_zx_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_zx_delete:
                zxListview.setVisibility(View.GONE);
                ll_se_ls.setVisibility(View.VISIBLE);
                etZxSearch.getText().clear();
                setHistory();
                break;
            case R.id.tv_zx_qx:
                finish();
                break;
            case R.id.tv_zx_sousuo:
                historyStorage(etZxSearch.getText().toString().trim());
                break;
        }
    }

    public void newsListSuccess(News model) {
        List<News.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            zxListview.setVisibility(View.VISIBLE);
            ll_se_ls.setVisibility(View.GONE);
            SearchZixunAdapter adapter = new SearchZixunAdapter(this,data);
            zxListview.setAdapter(adapter);
            zxListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    News.DataBean dataBean = data.get(position);
                    Intent intent = new Intent(SearchZixunActivity.this, ZixunDetailActivity.class);
                    intent.putExtra("content",dataBean.getContent());
                    intent.putExtra("likeNum",String.valueOf(dataBean.getLikeCount()));
                    intent.putExtra("commentNum",String.valueOf(dataBean.getCommentCount()));
                    intent.putExtra("title",dataBean.getNewsTitle());
                    intent.putExtra("fbr",dataBean.getSourceName());
                    intent.putExtra("date",String.valueOf(dataBean.getAddTime()));

                    intent.putExtra("rowNum",String.valueOf(dataBean.getNewsID()));
                    startActivity(intent);
                }
            });
        }else {
            setHistory();
        }
    }

    private void setHistory() {

        String jsons = UserHelper.getHistorySearch();
        list = new Gson().fromJson(jsons, new TypeToken<List<HistorySearchModel>>() {}.getType());

        //设置初始的账号
        if (list != null && list.size() != 0) {
            ll_se_ls.setVisibility(View.VISIBLE);
            zxListview.setVisibility(View.GONE);
            accountAdapter = new HistorySearchAdapter(this, list);
            ls_listview.setAdapter(accountAdapter);

            ls_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HistorySearchModel historySearchModel = list.get(position);
                    etZxSearch.setText(historySearchModel.getContent());
                    historyStorage(historySearchModel.getContent());
                }
            });

            accountAdapter.setOnItemDeleteListener(new HistorySearchAdapter.OnItemDeleteListener() {
                @Override
                public void onItemDelete(View view, int position) {
                    list.remove(position);
                    accountAdapter.notifyDataSetChanged();
                }
            });
        }else {
            list = new ArrayList<>();
        }
    }

    public void historyStorage(String text) {

        boolean ss = false;//判断是否有一样的账号

        if (list != null && list.size() != 0) {
            for (int i = 0;i < list.size() ; i++){
                if(list.get(i).getContent().equals(text)){
                    list.remove(i);
                    HistorySearchModel historySearchModel = new HistorySearchModel();
                    historySearchModel.setContent(text);
                    list.add(0,historySearchModel);
                    ss = true;
                }
            }
            if(!ss){//如果有一样的就不需要再添加了，否则这里要添加新的
                HistorySearchModel dataBean = new HistorySearchModel();
                dataBean.setContent(text);
                list.add(0,dataBean);
                if(list.size() > 8){//判断只能最多保存5个账号
                    list.remove(list.size() - 1);
                }
            }
        }else {
            HistorySearchModel dataBean = new HistorySearchModel();
            dataBean.setContent(text);
            list.add(dataBean);
        }

        String json = new Gson().toJson(list);
        UserHelper.setHistorySearch(json);

        getP().searchZixun(text);
    }
}
