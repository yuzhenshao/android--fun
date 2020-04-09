package com.mfzn.deepuses.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.my.SwitchCompanyAdapter;
import com.mfzn.deepuses.bass.BaseActivity;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.net.HttpResult;
import com.mfzn.deepuses.utils.ToastUtil;
import com.mfzn.deepuses.utils.UserHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * @author yz @date 2020-04-09
 */
public class CompanyListActivity extends BaseActivity {

    @BindView(R.id.tv_bass_title)
    TextView tvBassTitle;

    @BindView(R.id.swi_list)
    ListView swiList;

    private List<SelectCompanyModel> models = new ArrayList<>();
    String curCampanyName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvBassTitle.setText(getString(R.string.app_switch_company));
        companyList();
        swiList.setAdapter(adapter);

        swiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (models != null && models.size() > 0) {
                    curCampanyName = models.get(position).getCompanyName();
                    finish();
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_switch_company;
    }

    private void companyList() {
        ApiHelper.getApiService().companyList(UserHelper.getToken(), UserHelper.getUid())
                .compose(XApi.getApiTransformer())
                .compose(XApi.getScheduler())
                .subscribe(new ApiSubscriber<HttpResult<List<SelectCompanyModel>>>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showToast(CompanyListActivity.this, "公司信息获取失败");
                    }

                    @Override
                    public void onNext(HttpResult<List<SelectCompanyModel>> result) {
                        if (result != null) {
                            models.clear();
                            models.addAll(result.getRes());
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return models.size();
        }

        @Override
        public Object getItem(int position) {
            return models.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(CompanyListActivity.this, R.layout.company_item, null);
            TextView nameTv = convertView.findViewById(R.id.name);
            nameTv.setText(models.get(position).getCompanyName());
            return convertView;
        }
    };


    @OnClick(R.id.iv_login_back)
    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("companyName", curCampanyName);
        setResult(Activity.RESULT_OK, intent);
        super.finish();
    }
}
