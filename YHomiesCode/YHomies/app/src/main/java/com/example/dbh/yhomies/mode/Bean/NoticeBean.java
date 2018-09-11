package com.example.dbh.yhomies.mode.Bean;

public class NoticeBean {

    public int noticeId;
    public int noticeType;
    public String noticeName;
    public String noticeTitle;
    public String noticeTime;
    public String noticeNameLogo;

    @Override
    public String toString() {
        return "NoticeBean{" +
                "noticeId=" + noticeId +
                ", noticeType=" + noticeType +
                ", noticeName='" + noticeName + '\'' +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeTime='" + noticeTime + '\'' +
                ", noticeNameLogo='" + noticeNameLogo + '\'' +
                '}';
    }
}
