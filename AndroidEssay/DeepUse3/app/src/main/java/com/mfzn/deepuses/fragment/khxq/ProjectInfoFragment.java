package com.mfzn.deepuses.fragment.khxq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.khgl.AddProActivity;
import com.mfzn.deepuses.activity.xmgl.SelectProjectActivity;
import com.mfzn.deepuses.adapter.khgl.CustomerProAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.present.customer.ProjectInfoPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.utils.EventMsg;
import com.mfzn.deepuses.utils.RxBus;
import com.mfzn.deepuses.utils.ToastUtil;
import com.wevey.selector.dialog.bean.DetailsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ProjectInfoFragment extends BaseMvpFragment<ProjectInfoPresnet> {

    @BindView(R.id.pr_recycleview)
    RecyclerView prRecycleview;
    @BindView(R.id.ll_pr_empty)
    LinearLayout ll_pr_empty;

    private String dataid;
    private String customerName;
    private String customerPhone;
    private String cusID;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project_info;
    }

    @Override
    public ProjectInfoPresnet newP() {
        return new ProjectInfoPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        prRecycleview.setLayoutManager(layoutManager);

        dataid = getArguments().getString(Constants.CUS_DETA_ID);
        getP().customerDetails(dataid);

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
                        getP().customerDetails(dataid);
                    }
                }
            }
        });
    }

    @OnClick({R.id.ll_pr_gl, R.id.ll_pr_xj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_pr_gl:
                Intent intent2 = new Intent(getActivity(), SelectProjectActivity.class);
                intent2.putExtra(Constants.SELECT_PROJECT,3);
                startActivityForResult(intent2, Constants.SELECT_PRO);
                break;
            case R.id.ll_pr_xj:
                Intent intent = new Intent(getActivity(), AddProActivity.class);
                intent.putExtra(Constants.ADD_NAME,customerName);
                intent.putExtra(Constants.ADD_PHONE,customerPhone);
                intent.putExtra(Constants.ADD_ID,cusID);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constants.SELECT_PRO == requestCode) {
            if (data != null) {
                String proid = data.getStringExtra(Constants.SHOUHOU_PROID);
                getP().guanliPro(proid,dataid);
            }
        }
    }

    public void customerDetailsSuccess(DetailsModel models) {
        customerName = models.getCustomerName();
        customerPhone = models.getCustomerPhone();
        cusID = String.valueOf(models.getData_id());
        List<DetailsModel.ProsBean> pros = models.getPros();
        if(pros != null && pros.size() != 0) {
            ll_pr_empty.setVisibility(View.GONE);
            CustomerProAdapter recycleAdapter = new CustomerProAdapter(getActivity(),pros);
            prRecycleview.setAdapter(recycleAdapter);
        }else {
            ll_pr_empty.setVisibility(View.VISIBLE);
        }
    }

    public void guanliProSuccess() {
        ToastUtil.showToast(getActivity(),"关联成功");
        getP().customerDetails(dataid);
    }
}
