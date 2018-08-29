package com.example.dbh.yhomies.presenter;

import android.content.Context;

import com.example.dawn.dawnsutils.NetWorkUtil;
import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.mode.PasswordLoginMode;
import com.example.dbh.yhomies.mode.UpdatePwdMode;
import com.example.dbh.yhomies.mode.m_interface.IPasswordLoginMode;
import com.example.dbh.yhomies.mode.m_interface.IUpdatePwdMode;
import com.example.dbh.yhomies.view.v_interface.IPasswordLoginView;
import com.example.dbh.yhomies.view.v_interface.IUpDatePwdView;

public class UpdatePwdPresenter {

    private IUpDatePwdView iUpDatePwdView;

    public UpdatePwdPresenter(IUpDatePwdView iUpDatePwdView) {
        this.iUpDatePwdView = iUpDatePwdView;
    }

    public void updatePwdLogin(String userPhone, String password, Context mContext) {
        if (NetWorkUtil.getNetWorkType(mContext) == NetWorkUtil.NETWORKTYPE_INVALID) {
            iUpDatePwdView.updatePwdOnError();
        } else {
            UpdatePwdMode.updatePwd(new IUpdatePwdMode() {
                @Override
                public void onSuccess(String userPhone, String pwd) {
                    iUpDatePwdView.updatePwdOnSuccess(userPhone, pwd);
                }

                @Override
                public void onFailure(String msg) {
                    iUpDatePwdView.updatePwdOnFailure(msg);
                }

                @Override
                public void onError() {
                    iUpDatePwdView.updatePwdOnError();
                }
            }, userPhone, password);
        }

    }

    /**
     * 释放引用，防止内存泄露
     */
    public void destroy() {
        iUpDatePwdView = null;
    }

}
