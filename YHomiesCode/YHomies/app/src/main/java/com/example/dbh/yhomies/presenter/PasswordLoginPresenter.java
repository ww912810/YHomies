package com.example.dbh.yhomies.presenter;

import android.content.Context;

import com.example.dawn.dawnsutils.NetWorkUtil;
import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.mode.PasswordLoginMode;
import com.example.dbh.yhomies.mode.m_interface.IPasswordLoginMode;
import com.example.dbh.yhomies.view.v_interface.IPasswordLoginView;

public class PasswordLoginPresenter {

    private IPasswordLoginView iPasswordLoginView;

    public PasswordLoginPresenter(IPasswordLoginView iPasswordLoginView) {
        this.iPasswordLoginView = iPasswordLoginView;
    }

    public void passwordLogin(String userPhone, String password, Context mContext) {
        if (NetWorkUtil.getNetWorkType(mContext) == NetWorkUtil.NETWORKTYPE_INVALID) {
            iPasswordLoginView.passwordLoginOnError();
        } else {
            PasswordLoginMode.passwordLogin(new IPasswordLoginMode() {
                @Override
                public void onSuccess(UserBean bean) {
                    iPasswordLoginView.passwordLoginOnSuccess(bean);
                }

                @Override
                public void onFailure(String msg) {
                    iPasswordLoginView.passwordLoginOnFailure(msg);
                }

                @Override
                public void onError() {
                    iPasswordLoginView.passwordLoginOnError();
                }
            }, userPhone, password);
        }

    }

}
