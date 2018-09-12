package com.example.dbh.yhomies.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.example.dbh.yhomies.mode.Bean.AudioFileBean;
import com.example.dbh.yhomies.mode.GetAudioMode;
import com.example.dbh.yhomies.mode.m_interface.IGetAudioMode;
import com.example.dbh.yhomies.view.v_interface.IGetAudioView;

import java.util.ArrayList;

public class GetAudioPresenter {

    private IGetAudioView iGetAudioView;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public GetAudioPresenter(IGetAudioView iGetAudioView) {
        this.iGetAudioView = iGetAudioView;
    }

    public void getAudioFile(Context mContext, Activity mActivity) {
        myPermission(mContext, mActivity);

        GetAudioMode.getAudioFile(new IGetAudioMode() {
            @Override
            public void onSuccess(ArrayList<AudioFileBean> beans) {
                iGetAudioView.getAudioOnSuccess(beans);
            }
        }, mContext);

    }

    private void myPermission(Context mContext, Activity mActivity) {
        int permission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            iGetAudioView.getAudioOnError();
            ActivityCompat.requestPermissions(
                    mActivity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}
