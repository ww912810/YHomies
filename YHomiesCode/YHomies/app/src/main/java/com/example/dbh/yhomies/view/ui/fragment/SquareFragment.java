package com.example.dbh.yhomies.view.ui.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.FieldBean;
import com.example.dbh.yhomies.presenter.SquareFieldPresenter;
import com.example.dbh.yhomies.view.adapter.SquareFieldAdapter;
import com.example.dbh.yhomies.view.v_interface.ISquareFieldView;

import java.util.List;

/**
 * 广场
 *
 * @author 段博涵
 */
public class SquareFragment extends Fragment implements ISquareFieldView {

    private Context mContext;
    private View view;

    private RecyclerView rvFieldFilter;
    private SquareFieldAdapter squareFieldAdapter;
    private SquareFieldPresenter squareFieldPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_square, null);
        mContext = getActivity();

        initView();
        squareFieldPresenter.getFieldData();

        return view;
    }

    private void initView() {
        ImageView ivLeftImage = view.findViewById(R.id.ivLeftImage);
        ivLeftImage.setVisibility(View.GONE);
        ImageView ivRightImage = view.findViewById(R.id.ivRightImage);
        ivRightImage.setVisibility(View.GONE);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText("广场");

        rvFieldFilter = view.findViewById(R.id.rvFieldFilter);
        squareFieldPresenter = new SquareFieldPresenter(this);
    }

    @Override
    public void getSquareFieldData(List<FieldBean> list) {
        GridLayoutManager manager = new GridLayoutManager(mContext,4);
        rvFieldFilter.setLayoutManager(manager);
        squareFieldAdapter = new SquareFieldAdapter();
        squareFieldAdapter.setNewData(list);
        rvFieldFilter.setAdapter(squareFieldAdapter);
    }
}
