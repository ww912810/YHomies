package com.example.dbh.yhomies.mode.Bean;

/**
 * 用户实体类
 *
 * @author 段博涵
 */
public class UserBean {

    public String userId;
    public String userName;
    public String userLogo;
    public String userSex;
    public String userPhone;
    public String userCity;
    public String userPwd;
    public String userBackgroundUrl;
    public String userSignature;
    public String userPostNumber;
    public String userFansNumber;
    public String userLastTime;
    public String userLastDistance;
    public String isAttention;
    public boolean isLoggedIn = false;

    @Override
    public String toString() {
        return "UserBean{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userLogo='" + userLogo + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userCity='" + userCity + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userBackgroundUrl='" + userBackgroundUrl + '\'' +
                ", userSignature='" + userSignature + '\'' +
                ", userPostNumber='" + userPostNumber + '\'' +
                ", userFansNumber='" + userFansNumber + '\'' +
                ", userLastTime='" + userLastTime + '\'' +
                ", userLastDistance='" + userLastDistance + '\'' +
                ", isAttention='" + isAttention + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }
}
