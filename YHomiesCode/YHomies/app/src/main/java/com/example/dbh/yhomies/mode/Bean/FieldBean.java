package com.example.dbh.yhomies.mode.Bean;

/**
 * 广场-领域-实体类
 */

public class FieldBean {

    public String fieldImageUrl;
    public int fieldImageResource;
    public String fieldName;

    @Override
    public String toString() {
        return "FieldBean{" +
                "fieldImageUrl='" + fieldImageUrl + '\'' +
                ", fieldImageResource=" + fieldImageResource +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
}
