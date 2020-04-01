package com.mfzn.deepusesSer.fragment.shouhou;

import android.os.Bundle;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.xiangmu.ShouliListviewAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.model.shouhou.ChuliGuochengModel;
import com.mfzn.deepusesSer.present.shouhou.ChuliGuochengPresnet;
import com.mfzn.deepusesSer.utils.Constants;
import com.mfzn.deepusesSer.view.MyListview;

import java.util.List;

import butterknife.BindView;

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
