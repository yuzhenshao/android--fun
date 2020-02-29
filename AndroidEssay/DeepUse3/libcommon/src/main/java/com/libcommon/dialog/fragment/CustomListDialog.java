package com.libcommon.dialog.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.libcommon.dialog.adapter.DialogAdapter;
import com.libcommon.dialog.module.DialogBaseBuilder;
import com.libcommon.dialog.module.DialogBaseParams;


public class CustomListDialog extends CustomDialog {

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        DialogAdapter adapter=mParams.getAdapter();
        if (adapter != null&&adapter.getRecyclerRes()>0) {
            RecyclerView recyclerView = view.findViewById(adapter.getRecyclerRes());
            RecyclerView.LayoutManager layoutManager = mParams.getLayoutManager();
            if (layoutManager == null) {
                new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
            }
            recyclerView.setLayoutManager(mParams.getLayoutManager());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            adapter.setOnItemClickListener(new DialogAdapter.OnAdapterItemClickListener() {
                @Override
                public void onItemClick(DialogAdapter adapter, View itemView, int position) {
                    if (mParams.getOnItemClickListener() != null) {
                        mParams.getOnItemClickListener().onItemClick(adapter, itemView, position);
                    }
                }
            });
        }
    }

    public static class Builder extends DialogBaseBuilder {

        public Builder() {
            super(new DialogBaseParams());
        }

        @Override
        public CustomListDialog create() {
            CustomListDialog customDialog = new CustomListDialog();
            params.apply(customDialog.mParams);
            return customDialog;
        }
    }
}
