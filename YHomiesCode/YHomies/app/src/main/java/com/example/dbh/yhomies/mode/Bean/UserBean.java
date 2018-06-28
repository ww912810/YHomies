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
    public String userPostNumber;
    public String userFansNumber;
    public String userLastTime;
    public String userLastDistance;

    @Override
    public String toString() {
        return "UserBean{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userLogo='" + userLogo + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userPostNumber='" + userPostNumber + '\'' +
                ", userFansNumber='" + userFansNumber + '\'' +
                ", userLastTime='" + userLastTime + '\'' +
                ", userLastDistance='" + userLastDistance + '\'' +
                '}';
    }
}
