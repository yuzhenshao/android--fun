package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;
import com.wevey.selector.dialog.adapter.ChoiseBuzhouAdapter;
import com.wevey.selector.dialog.bean.ObtainProcessModel;

import java.util.List;

/**
 * Created by Weavey on 2016/9/3.
 */
public class ChoiseBuzhouDialog {

    private Dialog mDialog;
    private View mDialogView;
    private Builder mBuilder;

    private TextView tv_ch_cancel;
    private TextView tv_ch_queding;
    private TextView tv_finish;
    private ListView ch_listview;

    public ChoiseBuzhouDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.bottomDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.choise_buzhou_dialog, null);
        mDialog.setContentView(mDialogView); // 一定要在setAttributes(lp)之前才有效

        tv_ch_cancel = (TextView) mDialogView.findViewById(R.id.tv_ch_cancel);//新
        tv_ch_queding = (TextView) mDialogView.findViewById(R.id.tv_ch_queding);//新
        ch_listview = (ListView) mDialogView.findViewById(R.id.ch_listview);//新
        tv_finish = (TextView) mDialogView.findViewById(R.id.tv_finish);//新


        mDialogView.setMinimumHeight((int) (ScreenSizeUtils.getInstance(mBuilder.getContext())
                .getScreenHeight() * builder.getHeight()));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mBuilder.getContext()).getScreenWidth() *
                builder.getWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);

        initDialog(builder);
    }

    private void initDialog(Builder builder) {

        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());

        List<ObtainProcessModel> listModel = builder.getListModel();
        final List<ObtainProcessModel.MainNodesBean> mainNodes = listModel.get(builder.getPositions()).getMainNodes();

        final ChoiseBuzhouAdapter adapter = new ChoiseBuzhouAdapter(builder.getContext(),mainNodes);
        ch_listview.setAdapter(adapter);

//        ch_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ObtainProcessModel.MainNodesBean mainNodesBean = mainNodes.get(position);
//                if(mainNodesBean.getIsChoise()){
//                    mainNodesBean.setIsChoise(false);
//                }else {
//                    mainNodesBean.setIsChoise(true);
//                }
//                adapter.setPosition(mainNodes);
//            }
//        });
        tv_ch_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = null;
                String name =  null;
                for (int i = 0 ; i < mainNodes.size() ; i++){
                    if(mainNodes.get(i).getIsChoise()){
                        if(TextUtils.isEmpty(name)){
                            id = mainNodes.get(i).getData_id() + "";
                            name = mainNodes.get(i).getName() + "";
                        }else {
                            id = id + "," +  mainNodes.get(i).getData_id();
                            name = name + "," +  mainNodes.get(i).getName();
                        }
                    }
                }

                if (mBuilder.getSingleListener() != null) {
                    mBuilder.getSingleListener().clickSingleButton(ChoiseBuzhouDialog.this,
                            tv_ch_queding,id,name);
                }
            }
        });
        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_ch_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show() {

        mDialog.show();
    }

    public void dismiss() {

        mDialog.dismiss();
    }

    public Dialog getDialog() {

        return mDialog;
    }

    public static class Builder {

        private DialogInterface.OnChoiseClickListener<ChoiseBuzhouDialog> singleListener;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private Context mContext;
        private List<ObtainProcessModel> res;
        private int positions;

        public Builder(Context context) {

            mContext = context;
            singleListener = null;
            isTouchOutside = true;
            height = 0.23f;
            width = 0.65f;
        }

        public List<ObtainProcessModel> getListModel() {
            return res;
        }

        public Builder setListModel(List<ObtainProcessModel> res) {
            this.res = res;
            return this;
        }

        public int getPositions() {
            return positions;
        }

        public Builder setPositions(int positions) {
            this.positions = positions;
            return this;
        }

        public Context getContext() {

            return mContext;
        }

        public DialogInterface.OnChoiseClickListener<ChoiseBuzhouDialog> getSingleListener() {
            return singleListener;
        }

        public Builder setSingleListener(DialogInterface.OnChoiseClickListener<ChoiseBuzhouDialog>
                                                                     singleListener) {
            this.singleListener = singleListener;
            return this;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean isTouchOutside) {

            this.isTouchOutside = isTouchOutside;
            return this;
        }

        public float getHeight() {
            return height;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public float getWidth() {
            return width;
        }

        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public ChoiseBuzhouDialog build() {

            return new ChoiseBuzhouDialog(this);
        }
    }


}
