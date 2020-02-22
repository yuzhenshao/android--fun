package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.xiangmu.CustomListModel;
import com.mfzn.deepuses.model.xiangmu.WorkorderTujianModel;
import com.mfzn.deepuses.utils.OnInputChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WorkorderTujianAdapter extends BaseAdapter {

    private Context mContext;
    private List<CustomListModel> models;
    private OnitemclickLisenter mOnitemclickLisenter;
//    private OnTypeLisenter mOnTypeLisenter;
//    private OnNameLisenter mOnNameLisenter;
//    private OnPhoneLisenter mOnPhoneLisenter;

    public WorkorderTujianAdapter(Context context) {
        this.mContext = context;
        this.models = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_workorder_tujian, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CustomListModel customListModel = models.get(position);

        viewHolder.etWoItemType.setText(customListModel.getTypeName());
        viewHolder.etWoItemName.setText(customListModel.getName());
        viewHolder.etWoItemPhone.setText(customListModel.getPhone());
//        viewHolder.etWoItemType.setTag(position);
//        viewHolder.etWoItemType.clearFocus();
//        viewHolder.etWoItemName.setTag(position);
//        viewHolder.etWoItemName.clearFocus();
//        viewHolder.etWoItemPhone.setTag(position);
//        viewHolder.etWoItemPhone.clearFocus();

//        viewHolder.etWoItemType.addTextChangedListener(new OnInputChangeListener() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                int pos = (int) viewHolder.etWoItemType.getTag();
//                WorkorderTujianModel model = models.get(pos);
//                model.setType(s + "");
//            }
//        });
//        viewHolder.etWoItemName.addTextChangedListener(new OnInputChangeListener() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                int pos = (int) viewHolder.etWoItemName.getTag();
//                WorkorderTujianModel model = models.get(pos);
//                model.setName(s + "");
//            }
//        });
//        viewHolder.etWoItemPhone.addTextChangedListener(new OnInputChangeListener() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                int pos = (int) viewHolder.etWoItemPhone.getTag();
//                WorkorderTujianModel model = models.get(pos);
//                model.setPhone(s + "");
//            }
//        });

        viewHolder.llWoItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnitemclickLisenter.onItemClick(v,position);
            }
        });

        return convertView;
    }

    public interface OnitemclickLisenter {
        void onItemClick(View view, int position);
    }

    public void setmOnitemclickLisenter(OnitemclickLisenter mOnitemclickLisenter) {
        this.mOnitemclickLisenter = mOnitemclickLisenter;
    }

//    public interface OnTypeLisenter {
//        void onTypeClick(int position,String content);
//    }
//
//    public void setOnTypeLisenter(OnTypeLisenter mOnitemclickLisenter) {
//        this.mOnTypeLisenter = mOnitemclickLisenter;
//    }

    public void setData(List<CustomListModel> models){
        this.models = models;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.et_wo_item_type)
        TextView etWoItemType;
        @BindView(R.id.et_wo_item_name)
        TextView etWoItemName;
        @BindView(R.id.et_wo_item_phone)
        TextView etWoItemPhone;
        @BindView(R.id.ll_wo_item_delete)
        LinearLayout llWoItemDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
