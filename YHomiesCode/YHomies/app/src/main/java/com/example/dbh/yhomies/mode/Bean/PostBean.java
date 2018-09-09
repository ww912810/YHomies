package com.example.dbh.yhomies.mode.Bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class PostBean implements MultiItemEntity {

    public static final int TEXT = 0;
    public static final int IMG = 1;
    public static final int VIDEO = 2;
    public static final int VOICE = 3;
    public int itemType;

    @Override
    public int getItemType() {
        return itemType;
    }
}
