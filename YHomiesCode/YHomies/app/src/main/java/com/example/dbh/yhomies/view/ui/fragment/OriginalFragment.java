package com.example.dbh.yhomies.view.ui.fragment;

import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.PostBean;
import com.example.dbh.yhomies.utils.xUtilsImageUtils;
import com.example.dbh.yhomies.view.adapter.OriginalAllAdapter;
import com.example.dbh.yhomies.view.adapter.OriginalChosenAdapter;
import com.example.dbh.yhomies.view.adapter.OriginalNewAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OriginalFragment extends Fragment {

    private Context mContext;
    private View view;

    private RecyclerView rvChosenOriginal, rvNewOriginal, rvAllOriginal;
    private OriginalChosenAdapter originalChosenAdapter;
    private OriginalNewAdapter originalNewAdapter;
    private OriginalAllAdapter originalAllAdapter;

    private ImageView ivNewPost;
    private CircleImageView civUserLogo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_original, null);
        mContext = getActivity();

        initView();
        initFragment();

        return view;
    }

    private void initView() {
        ivNewPost = view.findViewById(R.id.ivNewPost);
        String url1 = "https://mmbiz.qpic.cn/mmbiz_png/cLqs59pCHQ4R027591rewnibzX1dFyZCj2Q1azpybZyNic5dAvu5JzNNYeibdhMzMxNic01Nre6Pabw567ibBXcH6qA/0?wx_fmt=png";
        xUtilsImageUtils.display(ivNewPost, url1, 10);
        civUserLogo = view.findViewById(R.id.civUserLogo);
        String url2 = "https://mmbiz.qpic.cn/mmbiz_png/cLqs59pCHQ4R027591rewnibzX1dFyZCjt0kspEDDXc7fCvxtpecibtWE91lbNYwdvK3Glib096HvUu3vqDczWibuA/0?wx_fmt=png";
        Glide.with(mContext).load(url2).into(civUserLogo);

        rvChosenOriginal = view.findViewById(R.id.rvChosenOriginal);
        originalChosenAdapter = new OriginalChosenAdapter(mContext);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvChosenOriginal.setLayoutManager(manager);
        ArrayList<PostBean> postBeans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PostBean bean = new PostBean();
            postBeans.add(bean);
        }
        originalChosenAdapter.setNewData(postBeans);
        rvChosenOriginal.setAdapter(originalChosenAdapter);

////////////////////////////////////////////////////////////////////////

        rvNewOriginal = view.findViewById(R.id.rvNewOriginal);
        originalNewAdapter = new OriginalNewAdapter(mContext);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        rvNewOriginal.setLayoutManager(gridLayoutManager);
        ArrayList<PostBean> postBeans1 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            PostBean bean = new PostBean();
            postBeans1.add(bean);
        }
        originalNewAdapter.setNewData(postBeans1);
        rvNewOriginal.setAdapter(originalNewAdapter);


////////////////////////////////////////////////////////////////////////

        rvAllOriginal = view.findViewById(R.id.rvAllOriginal);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rvAllOriginal.setLayoutManager(layoutManager);
        ArrayList<PostBean> postBeans2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            PostBean bean = new PostBean();
            switch (i) {
                case 0:
                    bean.itemType = 0;
                    break;
                case 1:
                    bean.itemType = 1;
                    break;
                case 2:
                    bean.itemType = 2;
                    break;
                case 3:
                    bean.itemType = 3;
                    break;
            }
            postBeans2.add(bean);
        }
        originalAllAdapter = new OriginalAllAdapter(postBeans2);
        rvAllOriginal.setAdapter(originalAllAdapter);
    }

    private void initFragment() {

    }

}
