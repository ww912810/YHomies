package com.example.dbh.yhomies.view.ui.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dawn.dawnsutils.ToastUtils;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.AudioFileBean;
import com.example.dbh.yhomies.presenter.GetAudioPresenter;
import com.example.dbh.yhomies.view.adapter.AudioFileAdapter;
import com.example.dbh.yhomies.view.base_view.WhiteBaseActivity;
import com.example.dbh.yhomies.view.v_interface.IGetAudioView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChooseVideoFileActivity extends WhiteBaseActivity implements IGetAudioView {

    private Context mContext;

    private ImageView ivBack;
    private RecyclerView rvAudioFile;

    private GetAudioPresenter getAudioPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_video_file);
    }

    @Override
    protected void initView() {
        mContext = this;
        ivBack = findViewById(R.id.ivBack);

        rvAudioFile = findViewById(R.id.rvAudioFile);

        getAudioPresenter = new GetAudioPresenter(this);
        getAudioPresenter.getAudioFile(mContext, this);
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void getAudioOnSuccess(final ArrayList<AudioFileBean> beans) {
        AudioFileAdapter audioFileAdapter = new AudioFileAdapter(mContext);
        audioFileAdapter.setNewData(beans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rvAudioFile.setLayoutManager(layoutManager);
        rvAudioFile.setAdapter(audioFileAdapter);

        audioFileAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()==R.id.tvChoose){
                    ToastUtils.showSafeShortToast(mContext,beans.get(position).fileDisplayName+"选择");
                }
            }
        });
        audioFileAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showSafeShortToast(mContext,beans.get(position).fileDisplayName);
            }
        });
    }

    @Override
    public void getAudioOnError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getAudioPresenter != null) {
            getAudioPresenter = null;
        }
    }
}
