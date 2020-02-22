package com.mfzn.deepuses.fragment.xm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.xiangmu.ShouliListviewAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.model.xiangmu.ChuliGuochengModel;
import com.mfzn.deepuses.present.fragment.ChuliGuochengPresnet;
import com.mfzn.deepuses.utils.Constants;
import com.mfzn.deepuses.view.MyListview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ChuliGuochengFragment extends BaseMvpFragment<ChuliGuochengPresnet> {


    @BindView(R.id.acc_listview)
    MyListview accListview;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chuli_guocheng;
    }

    @Override
    public ChuliGuochengPresnet newP() {
        return new ChuliGuochengPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        String orderno = getArguments().getString(Constants.SHOUHOU_ORDERNO);

        getP().chuliGuocheng(orderno);
    }

    public void chuliGuochengSuccess(List<ChuliGuochengModel> models) {
        ShouliListviewAdapter adapter = new ShouliListviewAdapter(getActivity(),models);
        accListview.setAdapter(adapter);
    }
}
