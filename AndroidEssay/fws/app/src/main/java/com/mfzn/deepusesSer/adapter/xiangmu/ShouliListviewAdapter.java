package com.mfzn.deepusesSer.adapter.xiangmu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.activityxm.shgd.FwzHuizhiDetailActivity;
import com.mfzn.deepusesSer.model.shouhou.ChuliGuochengModel;
import com.mfzn.deepusesSer.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShouliListviewAdapter extends BaseAdapter {

    private Context mContext;
    private List<ChuliGuochengModel> models;

    public ShouliListviewAdapter(Context context,List<ChuliGuochengModel> models) {
        this.mContext = context;
        this.models = models;
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
            convertView = View.inflate(mContext, R.layout.listview_shouli_chuli, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ChuliGuochengModel chuliGuochengModel = models.get(position);

        int i = 0;
        int j = 0;
        String aa = "";
        String content = chuliGuochengModel.getContent();
        String realContent = "";
        if (!TextUtils.isEmpty(content)){
            i = content.indexOf('<');
            j = content.indexOf('>');
            if ( i != -1 ){
                final String huidanId = content.substring(i+1,j);
                aa = content.substring(i,j+1);
                SpannableString spannableString = new SpannableString(content);
                spannableString.setSpan(new UnderlineSpan(),
                        j+1, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE),
                        j+1, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, FwzHuizhiDetailActivity.class);
                        intent.putExtra("receiptId",huidanId);
                        mContext.startActivity(intent);
                    }
                };
                spannableString.setSpan(clickableSpan,
                        j+1, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                //注意：此时必须加这一句，不然点击事件不会生效
                viewHolder.tvAccItemContent.setMovementMethod(LinkMovementMethod.getInstance());
                viewHolder.tvAccItemContent.setText(spannableString);
            }else{
                realContent = chuliGuochengModel.getContent();
                viewHolder.tvAccItemContent.setText(realContent);
            }
        }
        viewHolder.tvAccItemTime.setText(DateUtils.stampToDateTime(chuliGuochengModel.getAddTime() + ""));
        viewHolder.tvAccItemType.setText(chuliGuochengModel.getTypeName());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_acc_item_time)
        TextView tvAccItemTime;
        @BindView(R.id.tv_acc_item_type)
        TextView tvAccItemType;
        @BindView(R.id.tv_acc_item_content)
        TextView tvAccItemContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
