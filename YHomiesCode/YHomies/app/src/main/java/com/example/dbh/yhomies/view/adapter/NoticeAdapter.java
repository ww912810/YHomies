package com.example.dbh.yhomies.view.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.NoticeBean;

public class NoticeAdapter extends BaseQuickAdapter<NoticeBean, BaseViewHolder> {

    private Context mContext;

    public NoticeAdapter(Context mContext) {
        super(R.layout.item_notice);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeBean item) {

    }
}
