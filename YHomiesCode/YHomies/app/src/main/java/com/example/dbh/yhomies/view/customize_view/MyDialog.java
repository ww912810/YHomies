package com.example.dbh.yhomies.view.customize_view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dbh.yhomies.R;

public class MyDialog extends Dialog implements View.OnClickListener {
    private TextView contentTxt;
    private TextView titleTxt;
    private TextView subTitleTxt;
    private TextView rightTxt;
    private TextView leftTxt;
    private ImageView cancelImg;

    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String rightString;
    private String leftString;
    private String title;
    private String subTitle;

    public MyDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public MyDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public MyDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public MyDialog setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public MyDialog setRightButton(String name) {
        this.rightString = name;
        return this;
    }

    public MyDialog setLeftButton(String name) {
        this.leftString = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog_layout);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        contentTxt = (TextView) findViewById(R.id.content);
        titleTxt = (TextView) findViewById(R.id.title);
        subTitleTxt = findViewById(R.id.subTitle);
        rightTxt = (TextView) findViewById(R.id.submit);
        rightTxt.setOnClickListener(this);
        leftTxt = (TextView) findViewById(R.id.cancel);
        leftTxt.setOnClickListener(this);
        cancelImg = findViewById(R.id.ivClose);
        cancelImg.setOnClickListener(this);

        contentTxt.setText(content);
        if (!TextUtils.isEmpty(rightString)) {
            rightTxt.setText(rightString);
        }

        if (!TextUtils.isEmpty(leftString)) {
            leftTxt.setText(leftString);
        }

        if (!TextUtils.isEmpty(title)) {
            titleTxt.setText(title);
        }

        if (!TextUtils.isEmpty(subTitle)) {
            subTitleTxt.setText(subTitle);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                if (listener != null) {
                    listener.onClick(this, false,leftString);
                }
                break;
            case R.id.submit:
                if (listener != null) {
                    listener.onClick(this, true,leftString);
                }
                break;
            case R.id.ivClose:
                if (listener != null) {
                    listener.onClick(this, true,leftString);
                }
                break;
        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm,String leftString);
    }
}
