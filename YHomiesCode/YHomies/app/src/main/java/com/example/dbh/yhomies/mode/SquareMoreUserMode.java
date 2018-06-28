package com.example.dbh.yhomies.mode;


import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.mode.m_interface.ISquarePopularityMode;
import com.example.dbh.yhomies.utils.HttpUrlUtils;
import com.example.dbh.yhomies.utils.MyCallBack;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 填充数据
 * 广场用户相关
 */
public class SquareMoreUserMode {

    /**
     * 网络请求人气Homies
     *
     * @param iSquarePopularityMode
     */
    public static void getSquarePopularity(final ISquarePopularityMode iSquarePopularityMode) {
        RequestParams params = new RequestParams(HttpUrlUtils.MY_SQUARE);
        params.addBodyParameter("lon", "0");
        params.addBodyParameter("lat", "0");
        x.http().get(params, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                //人气数据
                ArrayList<UserBean> data = getDatPopularityList(result, "popularty");
                iSquarePopularityMode.onSuccess(data);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                iSquarePopularityMode.onFailure("请求失败~");
            }
        });
    }

    /**
     * 网络请求更多附近Homies
     *
     * @param iSquarePopularityMode
     */
    public static void getSquareNearby(final ISquarePopularityMode iSquarePopularityMode, String pageNo, String pageCount) {
        RequestParams params = new RequestParams(HttpUrlUtils.MORE_NEARBY);
        params.addBodyParameter("lon", "0");
        params.addBodyParameter("lat", "0");
        params.addBodyParameter("pageNo", pageNo);
        params.addBodyParameter("pageCount", pageCount);
        x.http().get(params, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                ArrayList<UserBean> data = getDataNearbyList(result);
                iSquarePopularityMode.onSuccess(data);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                iSquarePopularityMode.onFailure("请求失败~");
            }
        });
    }

    /**
     * 解析填充更多人气数据
     *
     * @param result
     * @param type
     * @return
     */
    private static ArrayList<UserBean> getDatPopularityList(String result, String type) {
        ArrayList<UserBean> PopularityList = new ArrayList<>();
        try {
            JSONObject jsonObject = JSON.parseObject(result);
            String status = jsonObject.getString("status");
            if (status.equals("ok")) {
                JSONObject attribute = jsonObject.getJSONObject("attribute");
                com.alibaba.fastjson.JSONArray arr = attribute.getJSONArray(type);
                if (arr.size() > 0) {
                    for (int i = 0; i < arr.size(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        UserBean bean = new UserBean();
                        bean.userId = obj.getString("id");
                        bean.userLogo = obj.getString("headUrl");
                        bean.userName = obj.getString("nickName");
                        bean.userSex = obj.getString("sex");
                        PopularityList.add(bean);
                    }
                    return PopularityList;
                } else {
                    return PopularityList;
                }
            } else {
                return PopularityList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return PopularityList;
    }

    /**
     * 解析填充更多附近数据
     *
     * @param result
     * @return
     */
    private static ArrayList<UserBean> getDataNearbyList(String result) {
        ArrayList<UserBean> nearbyList = new ArrayList<>();
        try {
            JSONObject jsonObject = JSON.parseObject(result);
            String status = jsonObject.getString("status");
            if (status.equals("ok")) {
                JSONObject attribute = jsonObject.getJSONObject("attribute");
                com.alibaba.fastjson.JSONArray arr = attribute.getJSONArray("list");
                if (arr.size() > 0) {
                    for (int i = 0; i < arr.size(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        UserBean bean = new UserBean();
                        bean.userId = obj.getString("id");
                        bean.userLogo = obj.getString("headUrl");
                        bean.userName = obj.getString("nickName");
                        bean.userSex = obj.getString("sex");
                        bean.userPostNumber = obj.getString("postNum");
                        bean.userFansNumber = obj.getString("attentionNum");
                        bean.userLastTime = obj.getString("times");
                        bean.userLastDistance = obj.getString("distance");
                        nearbyList.add(bean);
                    }
                    return nearbyList;
                } else {
                    return nearbyList;
                }
            } else {
                return nearbyList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nearbyList;
    }

}
