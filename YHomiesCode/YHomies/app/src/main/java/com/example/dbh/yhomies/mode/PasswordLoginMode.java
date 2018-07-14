package com.example.dbh.yhomies.mode;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.mode.m_interface.IPasswordLoginMode;
import com.example.dbh.yhomies.utils.HttpUrlUtils;
import com.example.dbh.yhomies.utils.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

public class PasswordLoginMode {

    public static void passwordLogin(final IPasswordLoginMode iPasswordLoginMode, String userPhone, String password) {

        RequestParams params = new RequestParams(HttpUrlUtils.PASSWORD_LOGIN);
        params.addBodyParameter("pwd", password);
        params.addBodyParameter("phone", userPhone);
        params.addBodyParameter("lon", "0");
        params.addBodyParameter("lat", "0");
        x.http().get(params, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                getLoginOnSuccessData(iPasswordLoginMode, result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                iPasswordLoginMode.onError();
            }
        });
    }

    private static void getLoginOnSuccessData(final IPasswordLoginMode iPasswordLoginMode, String result) {
        try {
            JSONObject jsonObject = JSON.parseObject(result);
            String status = jsonObject.getString("status");
            if (status.equals("ok")) {
                JSONObject object1 = jsonObject.getJSONObject("attribute");
                JSONObject user = object1.getJSONObject("user");
                UserBean userBean = new UserBean();
                userBean.userId = user.getString("id");
                userBean.userPhone = user.getString("phone");
                userBean.userSignature = user.getString("introduction");
                userBean.userCity = user.getString("city");
                userBean.userName = user.getString("nickName");
                userBean.userLogo = user.getString("headUrl");
                userBean.userBackgroundUrl = user.getString("backgroundUrl");
                userBean.userPwd = user.getString("pwd");
                userBean.userSex = user.getString("sex");
                iPasswordLoginMode.onSuccess(userBean);
            } else {
                iPasswordLoginMode.onFailure("登陆失败！");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
