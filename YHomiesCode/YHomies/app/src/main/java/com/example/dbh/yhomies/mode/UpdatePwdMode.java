package com.example.dbh.yhomies.mode;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.dbh.yhomies.mode.m_interface.IUpdatePwdMode;
import com.example.dbh.yhomies.utils.HttpUrlUtils;
import com.example.dbh.yhomies.utils.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

public class UpdatePwdMode {

    public static void updatePwd(final IUpdatePwdMode iUpdatePwdMode, final String userPhone, final String password) {

        RequestParams params = new RequestParams(HttpUrlUtils.SET_NEW_PASSWORD);
        params.addBodyParameter("pwd", password);
        params.addBodyParameter("phone", userPhone);
        params.addBodyParameter("confirmPassWord", password);
        x.http().get(params, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                getUpdatePwdOnSuccessData(iUpdatePwdMode, result,userPhone,password);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                iUpdatePwdMode.onError();
            }
        });
    }

    private static void getUpdatePwdOnSuccessData(final IUpdatePwdMode iUpdatePwdMode, String result, String userPhone, String password) {
        try {
            JSONObject jsonObject = JSON.parseObject(result);
            String status = jsonObject.getString("status");
            if (status.equals("ok")) {
                iUpdatePwdMode.onSuccess(userPhone, password);
            } else {
                iUpdatePwdMode.onFailure("修改失败！");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
