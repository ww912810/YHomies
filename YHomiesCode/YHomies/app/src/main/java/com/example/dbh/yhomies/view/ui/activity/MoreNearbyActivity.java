package com.example.dbh.yhomies.view.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dawn.dawnsutils.ToastUtils;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.presenter.SquarePresenter;
import com.example.dbh.yhomies.view.adapter.SquareNearbyAdapter;
import com.example.dbh.yhomies.view.base_view.WhiteBaseActivity;
import com.example.dbh.yhomies.view.v_interface.ISquareView;

import java.util.List;

public class MoreNearbyActivity extends WhiteBaseActivity implements ISquareView {

    private Context mContext;
    private ImageView ivLeftImage;

    private int pageNo = 1;
    private int pageCount = 20;
    private RecyclerView rvNearby;
    private SquarePresenter squarePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_nearby);

        mContext = this;
        squarePresenter.getMoreNearbyData(pageNo + "", pageCount + "");
    }

    @Override
    protected void initView() {
        ivLeftImage = findViewById(R.id.ivLeftImage);
        ImageView ivRightImage = findViewById(R.id.ivRightImage);
        ivRightImage.setVisibility(View.GONE);
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("附近Homies");

        rvNearby = findViewById(R.id.rvNearby);
        squarePresenter = new SquarePresenter(this);
    }

    @Override
    protected void initListener() {
        ivLeftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getSquarePopularityData(List<UserBean> list) {

    }

    @Override
    public void getSquareNearbyData(final List<UserBean> data) {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        rvNearby.setLayoutManager(manager);
        SquareNearbyAdapter squareNearbyAdapter = new SquareNearbyAdapter();
        squareNearbyAdapter.setNewData(data);
        rvNearby.setAdapter(squareNearbyAdapter);
        squareNearbyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showSafeShortToast(mContext, data.get(position).userSex);
            }
        });
    }
}
