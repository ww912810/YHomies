package com.example.dbh.yhomies.mode;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.mode.m_interface.IVCodeLoginMode;
import com.example.dbh.yhomies.utils.HttpUrlUtils;
import com.example.dbh.yhomies.utils.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

public class VCodeLoginMode {

    public static void vCodeLogin(final IVCodeLoginMode ivCodeLoginMode, String userPhone) {

        RequestParams params = new RequestParams(HttpUrlUtils.PHONE_NUMBER_LOGIN);
        params.addBodyParameter("phone", userPhone);
        params.addBodyParameter("lon", "0");
        params.addBodyParameter("lat", "0");
        x.http().get(params, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                getLoginOnSuccessData(ivCodeLoginMode, result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ivCodeLoginMode.onError();
            }
        });
    }

    private static void getLoginOnSuccessData(final IVCodeLoginMode ivCodeLoginMode, String result) {
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
                ivCodeLoginMode.onSuccess(userBean);
            } else {
                ivCodeLoginMode.onFailure("登陆失败！");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
