package com.example.dbh.yhomies.presenter;

import android.content.Context;

import com.example.dawn.dawnsutils.NetWorkUtil;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.GetVCodeMode;
import com.example.dbh.yhomies.mode.m_interface.IGetVCodeMode;
import com.example.dbh.yhomies.view.v_interface.IGetVCodeView;

public class GetVCodePresenter {

    private IGetVCodeView iGetVCodeView;

    public GetVCodePresenter(IGetVCodeView iGetVCodeView) {
        this.iGetVCodeView = iGetVCodeView;
    }

    public void getVCode(String phone, final Context mContext) {
        if (NetWorkUtil.getNetWorkType(mContext) == NetWorkUtil.NETWORKTYPE_INVALID) {
            iGetVCodeView.getVCodeOnError();
        } else {
            GetVCodeMode.getVCode(new IGetVCodeMode() {
                @Override
                public void onSuccess(String vCodeValue) {
                    iGetVCodeView.getVCodeOnSuccess(vCodeValue);
                }

                @Override
                public void onFailure(String msg) {
                    iGetVCodeView.getVCodeOnFailure(mContext.getResources().getString(R.string.getVCodeOnError));
                }

                @Override
                public void onError() {
                    iGetVCodeView.getVCodeOnError();
                }
            }, phone);
        }
    }

    /**
     * 释放引用，防止内存泄露
     */
    public void destroy() {
        iGetVCodeView = null;
    }

}
