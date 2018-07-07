package com.example.dbh.yhomies.mode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.dbh.yhomies.mode.m_interface.IGetVCodeMode;
import com.example.dbh.yhomies.utils.HttpUrlUtils;
import com.example.dbh.yhomies.utils.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

public class GetVCodeMode {

    public static void getVCode(final IGetVCodeMode iGetVCodeMode, String phone) {
        RequestParams params = new RequestParams(HttpUrlUtils.GET_PHONE_CODE);
        params.addBodyParameter("phone", phone);
        x.http().get(params, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                getVCodeValue(iGetVCodeMode, result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                iGetVCodeMode.onError();
            }
        });
    }

    private static void getVCodeValue(IGetVCodeMode iGetVCodeMode, String result) {
        try {
            JSONObject obj = JSON.parseObject(result);
            String status = obj.getString("status");
            if (status.equals("ok")) {
                JSONObject attribute = obj.getJSONObject("attribute");
                String codeStr = attribute.getString("code");
                iGetVCodeMode.onSuccess(codeStr);
            } else {
                iGetVCodeMode.onFailure("发送频繁，请稍后再试！");
            }
        } catch (JSONException e) {
            iGetVCodeMode.onError();
        }

    }
}