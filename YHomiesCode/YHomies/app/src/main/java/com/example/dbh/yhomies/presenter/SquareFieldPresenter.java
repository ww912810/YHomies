package com.example.dbh.yhomies.presenter;

import com.example.dbh.yhomies.mode.Bean.FieldBean;
import com.example.dbh.yhomies.mode.SquareFieldMode;
import com.example.dbh.yhomies.mode.m_interface.ISquareFieldMode;
import com.example.dbh.yhomies.view.v_interface.ISquareFieldView;

import java.util.ArrayList;

public class SquareFieldPresenter {

    private ISquareFieldView iSquareFieldView;

    public SquareFieldPresenter(ISquareFieldView iSquareFieldView){
        this.iSquareFieldView = iSquareFieldView;
    }

    public void getFieldData(){
        SquareFieldMode.getSquareField(new ISquareFieldMode() {
            @Override
            public void onSuccess(ArrayList<FieldBean> data) {
                iSquareFieldView.getSquareFieldData(data);
            }
        });
    }

    /**
     * 释放引用，防止内存泄露
     */
    public void destroy() {
        iSquareFieldView = null;
    }

}
