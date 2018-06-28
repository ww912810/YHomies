package com.example.dbh.yhomies.mode;


import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.FieldBean;
import com.example.dbh.yhomies.mode.m_interface.ISquareFieldMode;

import java.util.ArrayList;

/**
 * 填充数据
 */
public class FieldMode {

    public static void getSquareField(ISquareFieldMode iSquareFieldMode) {
        ArrayList data = new ArrayList();
        int fieldImage[] = new int[]{R.mipmap.ic_hiphop, R.mipmap.ic_rap, R.mipmap.ic_dj, R.mipmap.ic_graffiti};
        String fieldName[] = new String[]{"街舞", "说唱", "DJ", "涂鸦"};
        for (int i = 0; i < fieldImage.length; i++) {
            FieldBean bean = new FieldBean();
            bean.fieldImageResource = fieldImage[i];
            bean.fieldName = fieldName[i];
            data.add(bean);
        }
        iSquareFieldMode.onSuccess(data);
    }
}
