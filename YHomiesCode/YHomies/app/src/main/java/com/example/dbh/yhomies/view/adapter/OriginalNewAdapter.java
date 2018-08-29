package com.example.dbh.yhomies.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.PostBean;

public class OriginalNewAdapter extends BaseQuickAdapter<PostBean,BaseViewHolder> {

    public OriginalNewAdapter() {
        super(R.layout.item_new_original_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, PostBean item) {

    }
}
