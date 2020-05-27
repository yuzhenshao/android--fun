package com.mfzn.deepuses.common.homecompany;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.libcommon.utils.ListUtil;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.model.company.CompanyRepository;
import com.mfzn.deepuses.model.company.SelectCompanyModel;
import com.mfzn.deepuses.net.ApiHelper;

import java.util.List;

public class HomeCompanyAdapter extends BaseQuickAdapter<SelectCompanyModel, BaseViewHolder> {

    protected Context context;
    private CompanyShopListener mListener;

    public HomeCompanyAdapter(Context context, @Nullable List<SelectCompanyModel> data) {
        super(R.layout.listview_home_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectCompanyModel model) {
        String logo = model.getLogo();
        if (!TextUtils.isEmpty(logo)) {
            Glide.with(mContext).load(ApiHelper.BASE_URL + logo).into((ImageView) helper.getView(R.id.tv_home_item_icon));
        }
        TextView name = helper.getView(R.id.tv_home_item_name);
        name.setText(model.getCompanyName());
        SelectCompanyModel company = CompanyRepository.getInstance().getCurCompany();
        helper.setVisible(R.id.iv_home_item_dh, company != null && !TextUtils.isEmpty(company.getCompanyID())
                && company.getCompanyID().equals(model.getCompanyID()));
        helper.getView(R.id.company_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.companyShopSelected(model, model.getMainShop());
                }
            }
        });
        int imageResId = 0;
        int companyLevel = model.getCompanyLevel();
        if (companyLevel == 1) {
            imageResId = R.mipmap.vip1;
        } else if (companyLevel == 2) {
            imageResId = R.mipmap.vip2;
        } else if (companyLevel == 3) {
            imageResId = R.mipmap.vip3;
        } else if (companyLevel == 4) {
            imageResId = R.mipmap.vip4;
        } else if (companyLevel == 5) {
            imageResId = R.mipmap.vip5;
        } else if (companyLevel == 6) {
            imageResId = R.mipmap.vip6;
        }
        if (imageResId != 0) {
            Drawable drawable = mContext.getResources().getDrawable(imageResId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            name.setCompoundDrawables(null, null, drawable, null);
        }
        RecyclerView recyclerView = helper.getView(R.id.recycler_view);
        recyclerView.setVisibility(ListUtil.isEmpty(model.getShops()) ? View.GONE : View.VISIBLE);
        if (!ListUtil.isEmpty(model.getShops())) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            HomeComShopAdapter mAdapter = new HomeComShopAdapter(model.getShops());
            recyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (mListener != null) {
                        mListener.companyShopSelected(model, model.getShops().get(position));
                    }
                }
            });
        }
    }

    public CompanyShopListener getListener() {
        return mListener;
    }

    public void setListener(CompanyShopListener listener) {
        mListener = listener;
    }


    public interface CompanyShopListener {
        void companyShopSelected(SelectCompanyModel companyModel, SelectCompanyModel.ShopResponse shop);
    }
}
