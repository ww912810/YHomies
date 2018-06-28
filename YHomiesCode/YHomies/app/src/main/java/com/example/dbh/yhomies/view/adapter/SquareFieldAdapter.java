package com.example.dbh.yhomies.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.FieldBean;

/**
 * 广场-领域筛选适配器
 *
 * @author 段博涵
 */

public class SquareFieldAdapter extends BaseQuickAdapter<FieldBean, BaseViewHolder> {

    public SquareFieldAdapter() {
        super(R.layout.item_square_field_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, FieldBean item) {
        helper.setText(R.id.tvFieldName,item.fieldName);
        helper.setImageResource(R.id.ivFieldImage, item.fieldImageResource);
    }
}
