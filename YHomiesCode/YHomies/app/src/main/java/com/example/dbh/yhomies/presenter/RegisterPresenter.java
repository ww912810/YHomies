package com.example.dbh.yhomies.presenter;

import android.content.Context;

import com.example.dawn.dawnsutils.NetWorkUtil;
import com.example.dbh.yhomies.mode.RegisterMode;
import com.example.dbh.yhomies.mode.m_interface.IRegisterMode;
import com.example.dbh.yhomies.view.v_interface.IRegisterView;

public class RegisterPresenter {

    private IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }

    public void toRegister(String userPhone, String userPwd, Context mContext) {
        if (NetWorkUtil.getNetWorkType(mContext) == NetWorkUtil.NETWORKTYPE_INVALID) {
            iRegisterView.registerOnError();
        } else {
            RegisterMode.toRegister(new IRegisterMode() {
                @Override
                public void onLoading() {
                    iRegisterView.registerOnLoading();
                }

                @Override
                public void onSuccess(String userPhone, String userPwd) {
                    iRegisterView.registerOnSuccess(userPhone, userPwd);
                }

                @Override
                public void onFailure(String msg) {
                    iRegisterView.registerOnFailure(msg);
                }

                @Override
                public void onError() {
                    iRegisterView.registerOnError();
                }
            }, userPhone, userPwd);
        }
    }

    /**
     * 释放引用，防止内存泄露
     */
    public void destroy() {
        iRegisterView = null;
    }

}
