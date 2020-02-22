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
import com.mfzn.deepuses.activityxm.shgd.AlreadyAppraiseActivity;
import com.mfzn.deepuses.activityxm.shgd.AlreadyCancalActivity;
import com.mfzn.deepuses.activityxm.shgd.AlreadyCloseActivity;
import com.mfzn.deepuses.activityxm.shgd.CheckAppraiseActivity;
import com.mfzn.deepuses.activityxm.shgd.DaiPaigongActivity;
import com.mfzn.deepuses.activityxm.shgd.InServiceActivity;
import com.mfzn.deepuses.activityxm.shgd.StayAcceptActivity;
import com.mfzn.deepuses.activityxm.shgd.WaitAppraiseActivity;
import com.mfzn.deepuses.activityxm.shgd.WaitReceiveActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderAcceptActivity;
import com.mfzn.deepuses.activityxm.shgd.WorkorderDispatchActivity;
import com.mfzn.deepuses.activityxm.staging.ProjectStagingActivity;
import com.mfzn.deepuses.adapter.fg.ShouhouGongdanAdapter;
import com.mfzn.deepuses.adapter.xiangmu.SearchOrderAdapter;
import com.mfzn.deepuses.adapter.xiangmu.SearchProjectAdapter;
import com.mfzn.deepuses.bass.BaseMvpActivity;
import com.mfzn.deepuses.model.LookQuanxianModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderListModel;
import com.mfzn.deepuses.model.xiangmu.XiangmuModel;
import com.mfzn.deepuses.present.foundxm.SearchOrderPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.OnInputChangeListener;
import com.mfzn.deepuses.utils.PhoneUtils;
import com.mfzn.deepuses.utils.RxBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SearchOrderActivity extends BaseMvpActivity<SearchOrderPresnet> {

    @BindView(R.id.et_or_search)
    EditText etOrSearch;
    @BindView(R.id.iv_or_delete)
    ImageView ivOrDelete;
    @BindView(R.id.tv_or_qx)
    TextView tvOrQx;
    @BindView(R.id.tv_or_sousuo)
    TextView tvOrSousuo;
    @BindView(R.id.or_listview)
    ListView orListview;
    @BindView(R.id.ll_shgd_empty)
    LinearLayout ll_shgd_empty;

    private int positions;
    private int clickType;// 1 详情 2 派工 3 受理
    private List<WorkorderListModel.DataBean> data;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_order;
    }

    @Override
    public SearchOrderPresnet newP() {
        return new SearchOrderPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        orListview.setEmptyView(ll_shgd_empty);

        etOrSearch.addTextChangedListener(new OnInputChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etOrSearch.getText().toString().trim())){
                    tvOrSousuo.setVisibility(View.GONE);
                    tvOrQx.setVisibility(View.VISIBLE);
                    ivOrDelete.setVisibility(View.GONE);
                }else {
                    tvOrSousuo.setVisibility(View.VISIBLE);
                    tvOrQx.setVisibility(View.GONE);
                    ivOrDelete.setVisibility(View.VISIBLE);
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
                    if (eventMsg.getMsg().equals(Constants.GONGDAN)) {
                        getP().workorderList(etOrSearch.getText().toString().trim());
                    }
                }
            }
        });
    }

    @OnClick({R.id.iv_or_delete, R.id.tv_or_qx, R.id.tv_or_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_or_delete:
                etOrSearch.getText().clear();
                break;
            case R.id.tv_or_qx:
                finish();
                break;
            case R.id.tv_or_sousuo:
                getP().workorderList(etOrSearch.getText().toString().trim());
                break;
        }
    }

    public void workorderListSuccess(WorkorderListModel model) {
        data = model.getData();
        SearchOrderAdapter adapter = new SearchOrderAdapter(this, data);
        orListview.setAdapter(adapter);

        orListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positions = position;
                clickType = 1;
                getP().quanxian(String.valueOf(data.get(position).getProId()));
            }
        });

        adapter.setOnItemPgSlClickListener(new SearchOrderAdapter.OnItemPgSlClickListener() {
            @Override
            public void onItemPgClick(View view, int position) {
                positions = position;
                clickType = 2;
                getP().quanxian(String.valueOf(data.get(position).getProId()));
            }

            @Override
            public void onItemSlClick(View view, int position) {
                positions = position;
                clickType = 3;
                getP().quanxian(String.valueOf(data.get(position).getProId()));
            }
        });
    }

    public void lookQxSuccess(LookQuanxianModel model) {
        int leftDays = model.getProjectModule().getAfterSale().getLeftDays();
        if(leftDays > 0) {
            if(clickType == 1) {
                WorkorderListModel.DataBean dataBean = data.get(positions);
                int sss = dataBean.getStatus();
                if(sss == 1) {//待受理
                    Intent intent = new Intent(SearchOrderActivity.this, StayAcceptActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 2) {//待派工
                    Intent intent = new Intent(SearchOrderActivity.this, DaiPaigongActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 3) {//待接单
                    Intent intent = new Intent(SearchOrderActivity.this, WaitReceiveActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 4) {//服务中
                    Intent intent = new Intent(SearchOrderActivity.this, InServiceActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 5) {//待评价
                    Intent intent = new Intent(SearchOrderActivity.this, WaitAppraiseActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 6) {//已评价
                    Intent intent = new Intent(SearchOrderActivity.this, CheckAppraiseActivity.class);
                    intent.putExtra(Constants.SHOUHOU_ORDERNO,dataBean.getOrderNo());
                    startActivity(intent);
                } else if(sss == 7) {//已取消
                    Intent intent = new Intent(SearchOrderActivity.this, AlreadyCancalActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }else if(sss == 8) {//已关闭
                    Intent intent = new Intent(SearchOrderActivity.this, AlreadyCloseActivity.class);
                    intent.putExtra(Constants.SHOUHOU_DETAILS,dataBean);
                    startActivity(intent);
                }
            }else if(clickType == 2) {
                WorkorderListModel.DataBean dataBean = data.get(positions);
                Intent intent = new Intent(this, WorkorderDispatchActivity.class);
                intent.putExtra(Constants.SHOUHOU_ORDERNO, dataBean.getOrderNo());
                intent.putExtra(Constants.SHOUHOU_PROIDS, dataBean.getProId() + "");
//                startActivityForResult(intent,Constants.ACCEPT_GONGDAN);
                startActivity(intent);
            }else if(clickType == 3) {
                String orderNo = data.get(positions).getOrderNo();
                Intent intent = new Intent(this, WorkorderAcceptActivity.class);
                intent.putExtra(Constants.SHOUHOU_ORDERNO, orderNo);
//                startActivityForResult(intent,Constants.ACCEPT_GONGDAN);
                startActivity(intent);
            }
        }else {
            PhoneUtils.dialogPhone2(this, "提示",
                    "对不起,您的售后板块试用期已结束\n，如需继续使用，请拨打客服电话\n400-055-2011","4000552011");
        }
    }
}
