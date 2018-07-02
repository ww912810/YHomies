package com.example.dbh.yhomies.view.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dawn.dawnsutils.ToastUtils;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.FieldBean;
import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.presenter.SquareFieldPresenter;
import com.example.dbh.yhomies.presenter.SquarePresenter;
import com.example.dbh.yhomies.view.adapter.SquareFieldAdapter;
import com.example.dbh.yhomies.view.adapter.SquareNearbyAdapter;
import com.example.dbh.yhomies.view.adapter.SquarePopularityAdapter;
import com.example.dbh.yhomies.view.ui.activity.MoreNearbyActivity;
import com.example.dbh.yhomies.view.ui.activity.MorePopularityActivity;
import com.example.dbh.yhomies.view.v_interface.ISquareFieldView;
import com.example.dbh.yhomies.view.v_interface.ISquareView;

import java.util.List;

/**
 * 广场
 *
 * @author 段博涵
 */
public class SquareFragment extends Fragment implements ISquareFieldView, ISquareView {

    private Context mContext;
    private View view;

    //广场领域筛选相关
    private RecyclerView rvFieldFilter;
    private SquareFieldPresenter squareFieldPresenter;

    //广场人气Homies相关
    private RelativeLayout rlToMorePopularity;
    private RecyclerView rvPopularity;
    private SquarePresenter squarePresenter;

    //广场附近Homies相关
    private RelativeLayout rlToMoreNearby;
    private RecyclerView rvNearby;

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_square, null);
        mContext = getActivity();

        initView();
        squareFieldPresenter.getFieldData();
        squarePresenter.getPopularityData();
        squarePresenter.getNearbyData();
        initListener();

        return view;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        ImageView ivLeftImage = view.findViewById(R.id.ivLeftImage);
        ivLeftImage.setVisibility(View.GONE);
        ImageView ivRightImage = view.findViewById(R.id.ivRightImage);
        ivRightImage.setVisibility(View.GONE);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText("广场");

        rvFieldFilter = view.findViewById(R.id.rvFieldFilter);
        squareFieldPresenter = new SquareFieldPresenter(this);

        rlToMorePopularity = view.findViewById(R.id.rlToMorePopularity);
        rvPopularity = view.findViewById(R.id.rvPopularity);
        squarePresenter = new SquarePresenter(this);

        rlToMoreNearby = view.findViewById(R.id.rlToMoreNearby);
        rvNearby = view.findViewById(R.id.rvNearby);

    }

    /**
     * 领域数据
     *
     * @param list
     */
    @Override
    public void getSquareFieldData(List<FieldBean> list) {
        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        rvFieldFilter.setLayoutManager(manager);
        SquareFieldAdapter squareFieldAdapter = new SquareFieldAdapter();
        squareFieldAdapter.setNewData(list);
        rvFieldFilter.setAdapter(squareFieldAdapter);
    }

    /**
     * 人气用户数据
     *
     * @param list
     */
    @Override
    public void getSquarePopularityData(final List<UserBean> list) {
        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        rvPopularity.setLayoutManager(manager);
        SquarePopularityAdapter squarePopularityAdapter = new SquarePopularityAdapter();
        squarePopularityAdapter.setNewData(list);
        rvPopularity.setAdapter(squarePopularityAdapter);
        squarePopularityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showSafeShortToast(mContext, list.get(position).userSex);
            }
        });
        squarePopularityAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showSafeShortToast(mContext, "添加" + list.get(position).userName);
            }
        });
    }

    /**
     * 附近用户数据
     * @param data
     */
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

    /**
     * 加载监听事件
     */
    private void initListener() {
        rlToMorePopularity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MorePopularityActivity.class));
            }
        });

        rlToMoreNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MoreNearbyActivity.class));
            }
        });
    }

}
