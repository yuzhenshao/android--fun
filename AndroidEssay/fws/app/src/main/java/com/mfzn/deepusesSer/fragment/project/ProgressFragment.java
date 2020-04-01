package com.mfzn.deepusesSer.fragment.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activity.project.AppointPersonActivity;
import com.mfzn.deepusesSer.adapter.project.ProgressAdapter;
import com.mfzn.deepusesSer.bass.BaseMvpFragment;
import com.mfzn.deepusesSer.present.fragment.ProgressPresnet;

import butterknife.BindView;

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
