package com.example.dbh.yhomies.mode;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.dbh.yhomies.mode.m_interface.IRegisterMode;
import com.example.dbh.yhomies.utils.HttpUrlUtils;
import com.example.dbh.yhomies.utils.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;


public class RegisterMode {

    /**
     * 网络请求注册
     *
     * @param iRegisterMode
     * @param userPhone
     * @param userPwd
     */
    public static void toRegister(final IRegisterMode iRegisterMode, String userPhone, String userPwd) {
        iRegisterMode.onLoading();
        RequestParams params = new RequestParams(HttpUrlUtils.REGISTER);
        params.addBodyParameter("phone", userPhone);
        params.addBodyParameter("pwd", userPwd);
        x.http().get(params, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                toRegisterAnalysis(iRegisterMode, result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                iRegisterMode.onError();
            }
        });
    }

    /**
     * 解析注册返回数据
     *
     * @param iRegisterMode
     * @param result
     */
    private static void toRegisterAnalysis(IRegisterMode iRegisterMode, String result) {
        String messageValue = "";
        try {
            JSONObject jsonObject = JSON.parseObject(result);
            String status = jsonObject.getString("status");
            String message = jsonObject.getString("message");
            messageValue = message;
            if (status.equals("ok")) {
                JSONObject Attribute = jsonObject.getJSONObject("attribute");
                JSONObject tUser = Attribute.getJSONObject("tUser");
                String userPhone = tUser.getString("phone");
                String userPassword = tUser.getString("pwd");
                iRegisterMode.onSuccess(userPhone, userPassword);
            } else {
                iRegisterMode.onFailure(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            iRegisterMode.onFailure(messageValue);
        }
    }

}
