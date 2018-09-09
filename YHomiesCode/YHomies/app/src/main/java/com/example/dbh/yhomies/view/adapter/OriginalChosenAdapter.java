package com.example.dbh.yhomies.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.PostBean;
import com.example.dbh.yhomies.utils.xUtilsImageUtils;

import java.net.URI;

public class OriginalChosenAdapter extends BaseQuickAdapter<PostBean, BaseViewHolder> {

    private Context mContext;

    public OriginalChosenAdapter(Context mContext) {
        super(R.layout.item_original_layout);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, PostBean item) {
        String url1 = "https://mmbiz.qpic.cn/mmbiz_png/cLqs59pCHQ4R027591rewnibzX1dFyZCj2Q1azpybZyNic5dAvu5JzNNYeibdhMzMxNic01Nre6Pabw567ibBXcH6qA/0?wx_fmt=png";
        xUtilsImageUtils.display((ImageView) helper.getView(R.id.ivPostCover), url1, 10);
        String url2 = "https://mmbiz.qpic.cn/mmbiz_png/cLqs59pCHQ4R027591rewnibzX1dFyZCjt0kspEDDXc7fCvxtpecibtWE91lbNYwdvK3Glib096HvUu3vqDczWibuA/0?wx_fmt=png";
        Glide.with(mContext).load(url2).into((ImageView) helper.getView(R.id.civUserLogo));
    }
}
