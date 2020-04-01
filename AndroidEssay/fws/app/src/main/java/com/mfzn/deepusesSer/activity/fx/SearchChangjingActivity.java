package com.mfzn.deepusesSer.activity.fx;

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
import com.mfzn.deepusesSer.adapter.faxian.SearchChangjingAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpActivity;
import com.mfzn.deepusesSer.model.faxian.HistorySearchModel;
import com.mfzn.deepusesSer.model.faxian.News;
import com.mfzn.deepusesSer.model.faxian.Videos;
import com.mfzn.deepusesSer.present.fx.SearchChangjingPresnet;
import com.mfzn.deepusesSer.utils.OnInputChangeListener;
import com.mfzn.deepusesSer.utils.UserHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchChangjingActivity extends BaseMvpActivity<SearchChangjingPresnet> {

    @BindView(R.id.et_cj_search)
    EditText etCjSearch;
    @BindView(R.id.iv_cj_delete)
    ImageView ivCjDelete;
    @BindView(R.id.tv_cj_qx)
    TextView tvCjQx;
    @BindView(R.id.tv_cj_sousuo)
    TextView tvCjSousuo;
    @BindView(R.id.cj_listview)
    ListView cjListview;
    @BindView(R.id.lscj_listview)
    ListView lscjListview;
    @BindView(R.id.ll_cj_ls)
    LinearLayout llCjLs;

    private HistorySearchAdapter accountAdapter;
    private List<HistorySearchModel> list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_changjing;
    }

    @Override
    public SearchChangjingPresnet newP() {
        return new SearchChangjingPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        
        setHistory();

        etCjSearch.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etCjSearch.getText().toString().trim())){
                    tvCjSousuo.setVisibility(View.GONE);
                    tvCjQx.setVisibility(View.VISIBLE);
                    ivCjDelete.setVisibility(View.GONE);
                }else {
                    tvCjSousuo.setVisibility(View.VISIBLE);
                    tvCjQx.setVisibility(View.GONE);
                    ivCjDelete.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick({R.id.iv_cj_delete, R.id.tv_cj_qx, R.id.tv_cj_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cj_delete:
                cjListview.setVisibility(View.GONE);
                llCjLs.setVisibility(View.VISIBLE);
                etCjSearch.getText().clear();
                setHistory();
                break;
            case R.id.tv_cj_qx:
                finish();
                break;
            case R.id.tv_cj_sousuo:
                historyStorage(etCjSearch.getText().toString().trim());
                break;
        }
    }

    public void newsListSuccess(Videos model) {
        List<Videos.DataBean> data = model.getData();
        if(data != null && data.size() != 0) {
            cjListview.setVisibility(View.VISIBLE);
            llCjLs.setVisibility(View.GONE);
            SearchChangjingAdapter adapter = new SearchChangjingAdapter(this,data);
            cjListview.setAdapter(adapter);
        }else {
            setHistory();
        }
    }

    private void setHistory() {

        String jsons = UserHelper.getHistorySearchCj();
        list = new Gson().fromJson(jsons, new TypeToken<List<HistorySearchModel>>() {}.getType());

        //设置初始的账号
        if (list != null && list.size() != 0) {
            llCjLs.setVisibility(View.VISIBLE);
            cjListview.setVisibility(View.GONE);
            accountAdapter = new HistorySearchAdapter(this, list);
            lscjListview.setAdapter(accountAdapter);

            lscjListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HistorySearchModel historySearchModel = list.get(position);
                    etCjSearch.setText(historySearchModel.getContent());
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
        UserHelper.setHistorySearchCj(json);

        getP().searchZixun(text);
    }
}
