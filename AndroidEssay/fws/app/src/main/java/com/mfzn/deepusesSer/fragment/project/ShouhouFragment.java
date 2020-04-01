package com.mfzn.deepusesSer.fragment.project;

import android.os.Bundle;
import android.widget.ListView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.adapter.project.ShouhouAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.present.fragment.ShouhouPresnet;

import butterknife.BindView;

public class ShouhouFragment extends BaseMvpFragment<ShouhouPresnet> {

    @BindView(R.id.sh_listview)
    ListView shListview;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shouhou;
    }

    @Override
    public ShouhouPresnet newP() {
        return new ShouhouPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        ShouhouAdapter adapter = new ShouhouAdapter(context);
        shListview.setAdapter(adapter);
    }
}
