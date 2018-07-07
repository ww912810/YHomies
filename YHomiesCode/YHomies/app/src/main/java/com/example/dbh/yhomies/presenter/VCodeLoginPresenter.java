package com.example.dbh.yhomies.presenter;

import android.content.Context;

import com.example.dawn.dawnsutils.NetWorkUtil;
import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.mode.VCodeLoginMode;
import com.example.dbh.yhomies.mode.m_interface.IVCodeLoginMode;
import com.example.dbh.yhomies.view.v_interface.IVCodeLoginView;

public class VCodeLoginPresenter {

    private IVCodeLoginView ivCodeLoginView;

    public VCodeLoginPresenter(IVCodeLoginView ivCodeLoginView) {
        this.ivCodeLoginView = ivCodeLoginView;
    }

    public void VCodeLogin(String userPhone, Context mContext) {
        if (NetWorkUtil.getNetWorkType(mContext) == NetWorkUtil.NETWORKTYPE_INVALID) {
            ivCodeLoginView.vCodeLoginOnError();
        } else {
            VCodeLoginMode.vCodeLogin(new IVCodeLoginMode() {
                @Override
                public void onSuccess(UserBean bean) {
                    ivCodeLoginView.vCodeLoginOnSuccess(bean);
                }

                @Override
                public void onFailure(String msg) {
                    ivCodeLoginView.vCodeLoginOnFailure(msg);
                }

                @Override
                public void onError() {
                    ivCodeLoginView.vCodeLoginOnError();
                }
            }, userPhone);
        }

    }

}
