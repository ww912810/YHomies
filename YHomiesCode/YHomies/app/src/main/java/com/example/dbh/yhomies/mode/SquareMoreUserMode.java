package com.example.dbh.yhomies.mode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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

    /**
     * 网络请求更多人气页面入驻用户
     *
     * @param iSquarePopularityMode
     */
    public static void getSquareMoreSettleIn(final ISquarePopularityMode iSquarePopularityMode) {
        RequestParams params = new RequestParams(HttpUrlUtils.RU_ZHU_YONG_HU);
        params.addBodyParameter("lon", "0");
        params.addBodyParameter("lat", "0");
        x.http().get(params, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                ArrayList<UserBean> data = getDataPopularitySettleIn(result);
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
     * 解析填充更多人气页面入驻用户数据
     *
     * @param result
     * @return
     */
    private static ArrayList<UserBean> getDataPopularitySettleIn(String result) {
        ArrayList<UserBean> popularitySettleInList = new ArrayList<>();

        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.getString("status");
        if (status.equals("ok")) {
            JSONObject attribute = jsonObject.getJSONObject("attribute");
            JSONArray arr = attribute.getJSONArray("list");
            if (arr.size() > 0) {
                for (int i = 0; i < arr.size(); i++) {
                    try {
                        JSONObject obj = arr.getJSONObject(i);
                        UserBean bean = new UserBean();
                        bean.userId = obj.getString("id");
                        bean.userName = obj.getString("nickName");
                        bean.userLogo = obj.getString("headUrl");
                        bean.userSex = obj.getString("sex");
                        bean.userPostNumber = obj.getString("postNum");
                        bean.userFansNumber = obj.getString("attentionNum");
                        bean.userLastTime = obj.getString("times");
                        bean.userLastDistance = obj.getString("distance");
                        bean.isAttention = obj.getString("isAttention");
                        popularitySettleInList.add(bean);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return popularitySettleInList;
            }
        }
        return popularitySettleInList;
    }

    /**
     * 网络请求更多人气页面人气列表数据
     * @param iSquarePopularityMode
     * @param pageNo
     * @param countPage
     * @param uid
     * @param city
     * @param filer
     */
    public static void getSquareMorePopularity(final ISquarePopularityMode iSquarePopularityMode, int pageNo, int countPage, String uid, String city, String filer) {
        RequestParams params = new RequestParams(HttpUrlUtils.MORE_POPULARITY);
        params.addBodyParameter("pageNo", pageNo + "");
        params.addBodyParameter("pageCount", countPage + "");
        params.addBodyParameter("lon", "0");
        params.addBodyParameter("lat", "0");
        params.addBodyParameter("uid", uid);
        if (filer.equals("人气最多")) {
            params.addBodyParameter("isPopulaty", "1");
        } else if (city.equals("城市")) {
            params.addBodyParameter("city", city);
        }
        x.http().get(params, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                ArrayList<UserBean> data = getDataMorePopularity(result);
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
     * 解析广场更多人气界面人气列表数据
     * @param result
     * @return
     */
    private static ArrayList<UserBean> getDataMorePopularity(String result) {
        ArrayList<UserBean> morePopularityList = new ArrayList<>();
        try {
            JSONObject jsonObject = JSON.parseObject(result);
            String status = jsonObject.getString("status");
            if (status.equals("ok")) {
                JSONObject attribute = jsonObject.getJSONObject("attribute");
                JSONArray list = attribute.getJSONArray("list");
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        JSONObject obj = list.getJSONObject(i);
                        UserBean bean = new UserBean();
                        bean.userId = obj.getString("id");
                        bean.userName = obj.getString("nickName");
                        bean.userLogo = obj.getString("headUrl");
                        bean.userSex = obj.getString("sex");
                        bean.userPostNumber = obj.getString("postNum");
                        bean.userFansNumber = obj.getString("attentionNum");
                        bean.userLastTime = obj.getString("times");
                        bean.userLastDistance = obj.getString("distance");
                        bean.isAttention = obj.getString("isAttention");
                        morePopularityList.add(bean);
                    }
                    return morePopularityList;
                } else {
                    return morePopularityList;
                }
            } else {
                return morePopularityList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return morePopularityList;
    }

}