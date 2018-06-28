package com.example.dbh.yhomies.view.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.UserBean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 广场-附近Homies列表
 *
 * @author 段博涵
 */

public class SquareNearbyAdapter extends BaseQuickAdapter<UserBean, BaseViewHolder> {

    public SquareNearbyAdapter() {
        super(R.layout.item_square_nearby_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBean item) {
        helper.setText(R.id.tvUserName,item.userName);
        helper.setText(R.id.tvOriginalNumber,item.userPostNumber);
        helper.setText(R.id.tvFansNumber,item.userFansNumber);
        helper.setText(R.id.tvUserLastTime,item.userLastTime);
        helper.setText(R.id.tvUserLastDistance,item.userLastDistance);
        if (item.userSex.equals("0")){
            helper.setImageResource(R.id.ivUserSex,R.mipmap.ic_girl);
        }else {
            helper.setImageResource(R.id.ivUserSex,R.mipmap.ic_boy);
        }
        Glide.with(mContext).load(item.userLogo).crossFade().into((CircleImageView) helper.getView(R.id.civUserLogo));
    }
}
