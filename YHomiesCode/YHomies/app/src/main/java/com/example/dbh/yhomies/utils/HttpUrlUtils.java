package com.example.dbh.yhomies.utils;

/**
 * 接口调试类
 * Created by dh on 2018/1/9.
 */

public class HttpUrlUtils {

    //public static String IP = "http://dawn55.55555.io:10353";

   public static String IP = "http://47.52.142.235:8081"; //服务器IP


    /**
     * 图片服务器接口
     */
    public static String PICTURE_SERVICER = IP + "/FreeStyle/uploadFile/uploadPicture";


    /**
     * 视频服务器接口
     */
    public static String VIDEO_SERVICER = IP + "/FreeStyle/uploadFile/uploadVideo";


    /**
     * 音频服务器接口
     */
    public static String AUDIO_SERVICER = IP + "/FreeStyle/uploadFile/uploadPicture";


    /***
     * 验证码服务器接口
     * **/
    public static String GET_PHONE_CODE = IP + "/FreeStyle/user/getCode";


    /**
     * 注册接口【参数：密码，手机号】
     */
    public static String REGISTER = IP + "/FreeStyle/user/addUser";


    /**
     * 微信登录接口
     */
    public static String WEIXIN_LOGIN = IP + "/FreeStyle/user/wxLogin";


    /**
     * QQ登录接口
     */
    public static String QQ_LOGIN = IP + "/FreeStyle/user/qqLogin";


    /**
     * 密码&账号登录接口
     */
    public static String PASSWORD_LOGIN = IP + "/FreeStyle/user/login";


    /**
     * 手机号码登录接口
     */
    public static String PHONE_NUMBER_LOGIN = IP + "/FreeStyle/user/phoneLogin";


    /**
     * 退出登录接口
     */
    public static String LOG_OFF = IP + "/FreeStyle/user/loginOut";


    /**
     * 重新设置密码接口
     */
    public static String SET_NEW_PASSWORD = IP + "/FreeStyle/user/updateUserPassWord";


    /**
     * 更改用户信息接口
     */
    public static String EDIT_USER_INFO = IP + "/FreeStyle/user/updateUser";


    /**
     * 查询用户信息接口
     */
    public static String GET_USER_INFO = IP + "/FreeStyle/user/users/";


    /**
     * 发帖子接口
     */
    public static String RELEASE_POST = IP + "/FreeStyle/post/addPost";


    /**
     * 意见反馈接口
     */
    public static String FEED_BACK = IP + "/FreeStyle/user/addAdvise";


    /**
     * 首页热门帖子列表接口
     */
    public static String HOME_PAGE_RECOMMEND = IP + "/FreeStyle/post/getMainPush";


    /**
     * 首页关注帖子列表接口
     */
    public static String HOME_PAGE_ATTENTION = IP + "/FreeStyle/post/getAttentionByPost";


    /**
     * 首页附近帖子列表接口
     */
    public static String HOME_PAGE_NEARBY = IP + "/FreeStyle/post/getNearByPost";


    /**
     * 首页列表详情接口
     */
    public static String HOME_PAGE_DETAILS = IP + "/FreeStyle/post/getDetailById";


    /**
     * 发现-推荐列表接口
     */
    public static String DISCOVER_RECOMMEND = IP + "/FreeStyle/user/getPushList";


    /**
     * 发现-好友列表接口
     */
    public static String DISCOVER_FRIEND = IP + "/FreeStyle/user/getFriendsList";


    /**
     * 发现-微博列表接口
     */
    public static String DISCOVER_WEIBO = IP + "/FreeStyle/user/getWbFriends";


    /**
     * 一级评论列表接口
     */
    public static String STAIR_RECOMMEND = IP + "/FreeStyle/comment/getComlist";


    /**
     * 发布评论接口
     */
    public static String POST_RECOMMEND = IP + "/FreeStyle/comment/addComment";


    /**
     * 获取别的用户信息的接口
     */
    public static String GET_OTHER_USERINFO = IP + "/FreeStyle/user/getOtherUser";


    /**
     * 添加关注接口
     */
    public static String ADD_ATTENTION = IP + "/FreeStyle/post/addAttentionByUid";


    /**
     * 取消关注接口
     */
    public static String CANCLE_ATTENTION = IP + "/FreeStyle/post/delAttentionByUid";


    /**
     * 添加点赞接口
     */
    public static String ADD_LIKE = IP + "/FreeStyle/post/addLikeByUid";


    /**
     * 取消点赞接口
     */
    public static String CANCLE_LIKE = IP + "/FreeStyle/post/delLikeByUid";


    /**
     * 添加收藏接口
     */
    public static String ADD_COLLECT = IP + "/FreeStyle/post/addCollectByUid";


    /**
     * 取消收藏接口
     */
    public static String CANCLE_COLLECT = IP + "/FreeStyle/post/delCollectByUid";


    /**
     * 广场主页面数据接口
     */
    public static String MY_SQUARE = IP + "/FreeStyle/user/getSquareByFriends";


    /**
     * 首页搜索接口
     */
    public static String HOME_PAGE_SEARCH = IP + "/FreeStyle/post/getListByTitle";


    /**
     * 关于接口
     */
    public static String AS_REGARDS = IP + "/FreeStyle/about.html";


    /**
     * 广场-通过标签查询数据
     */
    public static String SQUARE_BY_LABLE = IP + "/FreeStyle/post/getListByTitle";


    /**
     * 广场更多人气接口
     */
    public static String MORE_POPULARITY = IP + "/FreeStyle/user/getPopularByFriends";


    /**
     * 广场更多附近的人接口
     */
    public static String MORE_NEARBY = IP + "/FreeStyle/user/getNearByFriends";


    /**
     * 查询用户发送的帖子的接口
     */
    public static String MY_RELEASE = IP + "/FreeStyle/post/getPostByUserId";


    /**
     * 查询用户收藏的帖子的接口
     */
    public static String MY_ATTENTION_POST = IP + "/FreeStyle/user/AllCollection";


    /**
     * 查询用户聊天列表的接口
     */
    public static String MESSAGR_LIST = IP + "/FreeStyle/cloud/getCharlist";


    /**
     * 获取融云Token接口
     */
    public static String GET_RONG_TOKEN = IP + "/FreeStyle/cloud/getToken";


    /***
     * 我的-赞-数据列表接口
     * */
    public static String MY_LIKE_LIST = IP + "/FreeStyle/user/getLikelistById";

    /***
     * 我的-粉丝-数据列表接口
     * */
    public static String MY_FANS_LIST = IP + "/FreeStyle/user/getMyFansList";


    /***
     * 我的-关注-数据列表接口
     * */
    public static String MY_ATTENTION_LIST = IP + "/FreeStyle/user/getMyAttentionList";


    /***
     *通知-通知列表接口
     * */
    public static String TONG_ZHI_LIEBIAO = IP + "/FreeStyle/remind/getTRemidList";


    /***
     * 通知-评论列表接口
     * */
    public static String TONGZHI_ZHI_PINGLUN = IP + "/FreeStyle/user/getCommendlistById";


    /***
     * 广场-入驻用户列表接口
     * */
    public static String RU_ZHU_YONG_HU = IP + "/FreeStyle/user/getSettledUser";


    /***
     * 举报接口
     * */
    public static String JU_BAO = IP + "/FreeStyle/user/addReport";


    /***
     * 回复评论接口
     * */
    public static String HUI_FU_PINGKUN = IP + "/FreeStyle/comment/addComRep";


    /***
     * 回复评论列表接口
     * */
    public static String HUIFU_PINGLUN = IP + "/FreeStyle/comment/getReplist";


    /***
     * 热门搜索提示接口
     * */
    public static String RE_MEN_TISHI = IP + "/FreeStyle/user/searchName";


}
