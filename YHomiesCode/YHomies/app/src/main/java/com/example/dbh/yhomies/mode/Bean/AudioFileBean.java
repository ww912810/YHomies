package com.example.dbh.yhomies.mode.Bean;

public class AudioFileBean {

    public int fileId;
    public String fileTitle;
    public String fileDisplayName;
    public String fileSinger;
    public boolean fileStatus;
    public String fileTime;
    public String fileSize;
    public String filePath;

    @Override
    public String toString() {
        return "AudioFileBean{" +
                "fileId=" + fileId +
                ", fileTitle='" + fileTitle + '\'' +
                ", fileDisplayName='" + fileDisplayName + '\'' +
                ", fileSinger='" + fileSinger + '\'' +
                ", fileStatus=" + fileStatus +
                ", fileTime='" + fileTime + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
