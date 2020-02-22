package com.mfzn.deepuses.fragment.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.adapter.project.ProgressAdapter;
import com.mfzn.deepuses.adapter.project.ShouhouAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.present.fragment.ShouhouPresnet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
