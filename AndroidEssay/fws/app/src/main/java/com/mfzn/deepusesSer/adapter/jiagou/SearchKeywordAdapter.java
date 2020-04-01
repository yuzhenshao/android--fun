package com.mfzn.deepusesSer.adapter.jiagou;

import android.content.Context;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mfzn.deepusesSer.R;
import com.mfzn.deepusesSer.model.jiagou.SearchKeywordModel;
import com.mfzn.deepusesSer.net.ApiHelper;
import com.mfzn.deepusesSer.view.RoundImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Html.fromHtml;


public class SearchKeywordAdapter extends BaseAdapter {

    private Context mContext;
    private List<SearchKeywordModel> models;
    private String a;

    public SearchKeywordAdapter(Context context, List<SearchKeywordModel> models) {
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
            convertView = View.inflate(mContext, R.layout.listview_search_keyword, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SearchKeywordModel model = models.get(position);

        Glide.with(mContext).load(ApiHelper.BASE_URL + model.getU_head()).into(viewHolder.iv_key_item_icon);
        viewHolder.iv_key_item_bumen.setText(model.getDepartmentName());

        String sub_bank_name = model.getPositionName();

        if (sub_bank_name.contains(a)){
            int index = sub_bank_name.toLowerCase().indexOf(a.toLowerCase());
            int length = a.length();
            Spanned temp = fromHtml(sub_bank_name.substring(0, index)
                    + "<u><font color=#4A90E2>"
                    + sub_bank_name.substring(index, index + length)
//                    + "</font></u>"
                    + sub_bank_name.substring(index + length,
                    sub_bank_name.length()));

            viewHolder.iv_key_item_name.setText(temp);
        }else {
            viewHolder.iv_key_item_name.setText(sub_bank_name);
        }

        return convertView;
    }

    public void setselect(String trim) {
        a = trim;
    }

    class ViewHolder {
        @BindView(R.id.iv_zu_item_icon)
        RoundImageView iv_key_item_icon;
        @BindView(R.id.iv_zu_item_name)
        TextView iv_key_item_name;
        @BindView(R.id.iv_key_item_bumen)
        TextView iv_key_item_bumen;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
