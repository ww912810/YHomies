package com.example.dbh.yhomies.view.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.NoticeBean;
import com.example.dbh.yhomies.view.adapter.NoticeAdapter;
import com.example.dbh.yhomies.view.customize_view.MyDialog;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {

    private Context mContext;
    private View view;

    private LinearLayoutCompat llComment, llPraise, llFans;
    private TextView tvCommentNumber, tvPraiseNumber, tvFansNumber;

    private RecyclerView rvNotice;
    private NoticeAdapter noticeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notice, null);
        mContext = getActivity();

        initView();
        initListener();

        return view;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        llComment = view.findViewById(R.id.llComment);
        llPraise = view.findViewById(R.id.llPraise);
        llFans = view.findViewById(R.id.llFans);
        tvCommentNumber = view.findViewById(R.id.tvCommentNumber);
        tvPraiseNumber = view.findViewById(R.id.tvPraiseNumber);
        tvFansNumber = view.findViewById(R.id.tvFansNumber);

        rvNotice = view.findViewById(R.id.rvNotice);
        noticeAdapter = new NoticeAdapter(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rvNotice.setLayoutManager(layoutManager);
        ArrayList<NoticeBean> noticeBeans = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            NoticeBean bean = new NoticeBean();
            noticeBeans.add(bean);
        }
        noticeAdapter.setNewData(noticeBeans);
        rvNotice.setAdapter(noticeAdapter);
    }

    private void initListener() {
        noticeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        showDialog("用户xx", "你的原创作品《123》付费12.4元", "留言：加油哦，期待更多好的作品！", "查看作品帖子");
                        break;
                    case 1:
                        showDialog("您的账户收入24.3元", "当前余额为235元", "收入来源：用户付费原创作品", "查看余额");
                        break;
                    case 2:
                        showDialog("恭喜您获得官方奖励的123元奖励金", " 请继续坚持原创！", "收入来源：官方奖励金", "查看余额");
                        break;
                    case 3:
                        showDialog("恭喜您，您的原创作品《xxx》", " 被评为每周精选！", "感谢您的分享！", "查看作品帖子");
                        break;
                }
            }
        });
    }

    private void showDialog(String title, String subTitle, String content, String leftString) {
        new MyDialog(mContext, R.style.dialog, content, new MyDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm, String leftString) {
                if (confirm) {
                    dialog.dismiss();
                } else {
                    Toast.makeText(mContext, leftString, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        })
                .setTitle(title)
                .setSubTitle(subTitle)
                .setLeftButton(leftString)
                .setRightButton("确定")
                .show();
    }

}
