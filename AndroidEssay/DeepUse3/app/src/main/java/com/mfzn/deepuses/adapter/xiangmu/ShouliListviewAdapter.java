package com.mfzn.deepuses.adapter.xiangmu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.activityxm.shgd.FwzHuizhiDetailActivity;
import com.mfzn.deepuses.model.jiagou.ZuzhiJiagouModel;
import com.mfzn.deepuses.model.xiangmu.ChuliGuochengModel;
import com.mfzn.deepuses.net.ApiHelper;
import com.mfzn.deepuses.utils.DateUtils;
import com.mfzn.deepuses.view.RoundImageView;

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
        String content = chuliGuochengModel.getContent();
        String realContent = "";
        if (!TextUtils.isEmpty(content)){
            i = content.indexOf('<');
            j = content.indexOf('>');
            if ( i != -1 ){
                final String huidanId = content.substring(i+1,j);//<>中的ID

                String s = content.toString();
                String substring = s.substring(0, i);
                String substring2 = s.substring(j + 1, s.length());

                String text = substring + substring2;

                SpannableString spannableString = new SpannableString(text);

                spannableString.setSpan(new UnderlineSpan() {
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.setColor(mContext.getResources().getColor(R.color.color_4A90E2));//设置颜色
                        ds.setUnderlineText(true);//下划线
                    }
                },  i, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, FwzHuizhiDetailActivity.class);
                        intent.putExtra("receiptId",huidanId);
                        mContext.startActivity(intent);
                    }
                };
                spannableString.setSpan(clickableSpan,
                        i, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
