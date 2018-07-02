package com.example.dbh.yhomies.view.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.UserBean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 广场更多人气界面人气列表适配器
 */

public class SquareMorePopularityAdapter extends BaseQuickAdapter<UserBean, BaseViewHolder> {

    public SquareMorePopularityAdapter() {
        super(R.layout.item_more_popularity_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBean item) {
        helper.setText(R.id.tvUserName,item.userName);
        helper.setText(R.id.tvUserPostNumber,item.userPostNumber);
        helper.setText(R.id.tvUserFansNumber,item.userFansNumber);
        if (item.userSex.equals("0")){
            helper.setImageResource(R.id.ivUserSex,R.mipmap.ic_girl);
        }else {
            helper.setImageResource(R.id.ivUserSex,R.mipmap.ic_boy);
        }
        Glide.with(mContext).load(item.userLogo).crossFade().into((CircleImageView) helper.getView(R.id.civUserLogo));
    }
}
