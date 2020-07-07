package com.mfzn.deepuses.purchasesellsave.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfzn.deepuses.R;
import com.mfzn.deepuses.purchasesellsave.setting.model.ModuleItem;

import java.util.List;

/**
 * @author syz @date 2020-03-24
 */
public class SettingModuleAdapter extends BaseQuickAdapter<ModuleItem, BaseViewHolder> {
    public SettingModuleAdapter(@Nullable List<ModuleItem> data) {
        super(R.layout.base_archives_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ModuleItem item) {
        helper.setText(R.id.title, item.getTitle())
                .setText(R.id.content, item.getContent())
                .setImageResource(R.id.icon, item.getIconRedId());
    }
}
