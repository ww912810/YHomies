package com.example.dbh.yhomies.view.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.FieldBean;
import com.example.dbh.yhomies.mode.Bean.UserBean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 广场-人气Homies列表
 *
 * @author 段博涵
 */

public class SquarePopularityAdapter extends BaseQuickAdapter<UserBean, BaseViewHolder> {

    public SquarePopularityAdapter() {
        super(R.layout.item_square_popularity_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBean item) {
        helper.setText(R.id.tvUserName,item.userName);
        Glide.with(mContext).load(item.userLogo).crossFade().into((CircleImageView) helper.getView(R.id.civUserLogo));
        helper.addOnClickListener(R.id.ivAddUser);
    }
}
