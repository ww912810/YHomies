package com.example.dbh.yhomies.view.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dawn.dawnsutils.StringUtils;
import com.example.dawn.dawnsutils.ToastUtils;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.utils.RegularFormatVerificationUtil;
import com.example.dbh.yhomies.view.base_view.BlackBaseActivity;

public class RegisterInputPhoneActivity extends BlackBaseActivity {

    private Context mContext;

    private ImageView ivLeftImage;

    private EditText etPhone;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_input_phone);
        mContext = this;
    }

    @Override
    protected void initView() {
        ivLeftImage = findViewById(R.id.ivLeftImage);
        ivLeftImage.setImageResource(R.mipmap.ic_return_white);
        RelativeLayout rlAppTitle = findViewById(R.id.rlAppTitle);
        rlAppTitle.setBackgroundColor(getResources().getColor(R.color.black_43));
        View vTitleLine = findViewById(R.id.vTitleLine);
        vTitleLine.setVisibility(View.GONE);
        ImageView ivRightImage = findViewById(R.id.ivRightImage);
        ivRightImage.setVisibility(View.GONE);
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setTextColor(getResources().getColor(R.color.white));
        tvTitle.setText("注册");

        etPhone = findViewById(R.id.etPhone);
        btnNext = findViewById(R.id.btnNext);
    }

    @Override
    protected void initListener() {
        ivLeftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(etPhone.getText().toString())) {
                    ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.phoneNotNull));
                } else {
                    if (RegularFormatVerificationUtil.isMobileExact(etPhone.getText())){
                        Intent intent = new Intent(mContext,RegisterInputVCodeActivity.class);
                        intent.putExtra("userPhone", etPhone.getText().toString());
                        startActivity(intent);
                        finish();
                    }else {
                        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.phoneFormatError));
                    }

                }
            }
        });
    }
}
