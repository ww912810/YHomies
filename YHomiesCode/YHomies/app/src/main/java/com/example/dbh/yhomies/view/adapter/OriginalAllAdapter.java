package com.example.dbh.yhomies.view.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.PostBean;

import java.util.ArrayList;

public class OriginalAllAdapter extends BaseMultiItemQuickAdapter<PostBean, BaseViewHolder> {

    public OriginalAllAdapter(ArrayList data) {
        super(data);
        addItemType(PostBean.TEXT, R.layout.item_post_text);
        addItemType(PostBean.IMG, R.layout.item_post_image);
        addItemType(PostBean.VIDEO, R.layout.item_post_video);
        addItemType(PostBean.VOICE, R.layout.item_post_voice);
    }

    @Override
    protected void convert(BaseViewHolder helper, PostBean item) {
        switch (helper.getItemViewType()) {
            case PostBean.TEXT:

                break;
            case PostBean.IMG:

                break;
            case PostBean.VIDEO:

                break;
            case PostBean.VOICE:

                break;
        }
    }
}
