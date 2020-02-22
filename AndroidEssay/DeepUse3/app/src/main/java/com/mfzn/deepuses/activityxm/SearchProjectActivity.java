package com.mfzn.deepuses.activityxm;

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
import com.mfzn.deepuses.activityxm.staging.ProjectStagingActivity;
import com.mfzn.deepuses.adapter.xiangmu.SearchProjectAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.present.foundxm.SearchProjectPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.RxBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SearchProjectActivity extends BaseMvpActivity<SearchProjectPresnet> {

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
        return R.layout.activity_search_project;
    }

    @Override
    public SearchProjectPresnet newP() {
        return new SearchProjectPresnet();
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

        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    if (eventMsg.getMsg().equals(Constants.FOUNDPROJECT)) {
                        getP().xiangmuList(etSeSearch.getText().toString().trim());
                    }
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
                getP().xiangmuList(etSeSearch.getText().toString().trim());
                break;
            case R.id.iv_se_delete:
                etSeSearch.getText().clear();
                break;
        }
    }

    public void xiangmuListSuccess(XiangmuModel model) {
        List<XiangmuModel.DataBean> data = model.getData();
        SearchProjectAdapter adapter = new SearchProjectAdapter(this,data);
        seListview.setAdapter(adapter);

        seListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                XiangmuModel.DataBean dataBean = data.get(position);
                Intent intent = new Intent(SearchProjectActivity.this, ProjectStagingActivity.class);
                intent.putExtra(Constants.WORK_ORDER,dataBean);
                startActivity(intent);
            }
        });
    }
}
