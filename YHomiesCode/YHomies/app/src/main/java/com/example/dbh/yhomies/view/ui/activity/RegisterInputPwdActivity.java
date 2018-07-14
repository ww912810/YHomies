package com.example.dbh.yhomies.view.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dawn.dawnsutils.StringUtils;
import com.example.dawn.dawnsutils.ToastUtils;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.view.base_view.BlackBaseActivity;

public class RegisterInputPwdActivity extends BlackBaseActivity {

    private Context mContext;

    private ImageView ivLeftImage;

    private String userPhone;
    private EditText etPassWord, etAgainInputPassWord;
    private Button btnNext;
    private ImageView ivPwdVisible;
    private boolean isVisiblePwd = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_input_pwd);
        mContext = this;
        userPhone = getIntent().getStringExtra("userPhone");
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

        etPassWord = findViewById(R.id.etPassWord);
        etAgainInputPassWord = findViewById(R.id.etAgainInputPassWord);
        btnNext = findViewById(R.id.btnNext);
        ivPwdVisible = findViewById(R.id.ivPwdVisible);
    }

    @Override
    protected void initListener() {
        ivLeftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivPwdVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisiblePwd) {
                    etPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPassWord.setSelection(etPassWord.getText().toString().length());
                    isVisiblePwd = !isVisiblePwd;
                } else {
                    etPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPassWord.setSelection(etPassWord.getText().toString().length());
                    isVisiblePwd = !isVisiblePwd;
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(etPassWord.getText().toString()) || StringUtils.isEmpty(etAgainInputPassWord.getText().toString())) {
                    ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.passwordNotNull));
                } else {
                    if (etPassWord.length() < 6 || etAgainInputPassWord.length() < 6) {
                        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.passwordNotLessThanSixLength));
                    } else {
                        if (etPassWord.getText().toString().equals(etAgainInputPassWord.getText().toString())) {
                            //两次密码相等

                        } else {
                            ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.oldPwdAndNewPwdNotEquals));
                        }
                    }
                }
            }
        });
    }
}
