package com.mfzn.deepuses.fragment.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activity.project.AppointPersonActivity;
import com.mfzn.deepuses.adapter.project.ProgressAdapter;
import com.mfzn.deepuses.bass.BaseMvpFragment;
import com.mfzn.deepuses.present.fragment.ProgressPresnet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProgressFragment extends BaseMvpFragment<ProgressPresnet> {

    @BindView(R.id.pr_listview)
    ListView prListview;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_proover;
    }

    @Override
    public ProgressPresnet newP() {
        return new ProgressPresnet();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        ProgressAdapter adapter = new ProgressAdapter(context);
        prListview.setAdapter(adapter);

        prListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(context, AppointPersonActivity.class));
            }
        });
    }
}
