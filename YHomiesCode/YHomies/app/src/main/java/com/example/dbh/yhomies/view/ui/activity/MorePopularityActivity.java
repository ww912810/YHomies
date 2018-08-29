package com.example.dbh.yhomies.view.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dawn.dawnsutils.ToastUtils;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.presenter.SquareMorePresenter;
import com.example.dbh.yhomies.presenter.SquarePresenter;
import com.example.dbh.yhomies.view.adapter.SquareMorePopularityAdapter;
import com.example.dbh.yhomies.view.adapter.SquarePopularityAdapter;
import com.example.dbh.yhomies.view.base_view.WhiteBaseActivity;
import com.example.dbh.yhomies.view.v_interface.ISquareMoreView;
import com.example.dbh.yhomies.view.v_interface.ISquareView;

import java.util.List;

public class MorePopularityActivity extends WhiteBaseActivity implements ISquareView, ISquareMoreView {

    private Context mContext;
    private ImageView ivLeftImage;

    private int pageNo = 1;
    private int pageCount = 20;
    private RecyclerView rvSettleIn;
    private SquarePresenter squarePresenter;
    private SquareMorePresenter getMorePopularity;
    private RecyclerView rvPopularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_popularity);

        mContext = this;

        squarePresenter.getMoreSettleIn();
        getMorePopularity.getMorePopularity(pageNo, pageCount, "0", "城市", "人气最多");
    }

    @Override
    protected void initView() {
        ivLeftImage = findViewById(R.id.ivLeftImage);
        ImageView ivRightImage = findViewById(R.id.ivRightImage);
        ivRightImage.setVisibility(View.GONE);
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("人气Homies");

        rvSettleIn = findViewById(R.id.rvSettleIn);
        squarePresenter = new SquarePresenter(this);
        getMorePopularity = new SquareMorePresenter(this);
        rvPopularity = findViewById(R.id.rvPopularity);
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
    public void getSquarePopularityData(final List<UserBean> list) {
        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        rvSettleIn.setLayoutManager(manager);
        SquarePopularityAdapter squarePopularityAdapter = new SquarePopularityAdapter();
        squarePopularityAdapter.setNewData(list);
        rvSettleIn.setAdapter(squarePopularityAdapter);
        squarePopularityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showSafeShortToast(mContext, list.get(position).userSex);
            }
        });
    }

    @Override
    public void getSquareNearbyData(List<UserBean> data) {

    }

    @Override
    public void getSquareMorePopularityData(final List<UserBean> list) {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        rvPopularity.setLayoutManager(manager);
        SquareMorePopularityAdapter squareMorePopularityAdapter = new SquareMorePopularityAdapter();
        squareMorePopularityAdapter.setNewData(list);
        rvPopularity.setAdapter(squareMorePopularityAdapter);
        squareMorePopularityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showSafeShortToast(mContext, list.get(position).isAttention);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getMorePopularity != null || squarePresenter != null) {
            getMorePopularity.destroy();
            getMorePopularity.destroy();
            squarePresenter = null;
            squarePresenter = null;
        }
    }
}
