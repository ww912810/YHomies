package com.example.dbh.yhomies.view.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.presenter.PasswordLoginPresenter;
import com.example.dbh.yhomies.view.base_view.BlackBaseActivity;
import com.example.dbh.yhomies.view.v_interface.IPasswordLoginView;

public class LoginForPasswordActivity extends BlackBaseActivity implements IPasswordLoginView {

    private Context mContext;
    public SharedPreferences spf; //存储文件对象
    public SharedPreferences.Editor editor; //文件编辑对象

    private ImageView ivLeftImage;

    private EditText etPhone, etPassWord;
    private Button btnLogin;
    private TextView tvVCodeLogin, tvRightText;
    private PasswordLoginPresenter passwordLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_for_password);

        mContext = this;

        spf = mContext.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        editor = spf.edit();
    }

    @Override
    protected void initView() {
        //标题栏相关配置
        ivLeftImage = findViewById(R.id.ivLeftImage);
        ivLeftImage.setImageResource(R.mipmap.ic_return_white);
        RelativeLayout rlAppTitle = findViewById(R.id.rlAppTitle);
        rlAppTitle.setBackgroundColor(getResources().getColor(R.color.black_43));
        View vTitleLine = findViewById(R.id.vTitleLine);
        vTitleLine.setVisibility(View.GONE);
        ImageView ivRightImage = findViewById(R.id.ivRightImage);
        ivRightImage.setVisibility(View.GONE);
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("");
        tvRightText = findViewById(R.id.tvRightText);
        tvRightText.setVisibility(View.VISIBLE);
        tvRightText.setText("忘记密码");
        tvRightText.setTextColor(getResources().getColor(R.color.white));

        etPhone = findViewById(R.id.etPhone);
        etPassWord = findViewById(R.id.etPassWord);
        btnLogin = findViewById(R.id.btnLogin);
        tvVCodeLogin = findViewById(R.id.tvVCodeLogin);
        passwordLoginPresenter = new PasswordLoginPresenter(this);
    }

    @Override
    protected void initListener() {
        ivLeftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //忘记密码
        tvRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,FGPasswordForPhoneActivity.class));
            }
        });
        //登陆
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(etPhone.getText().toString()) || StringUtils.isEmpty(etPassWord.getText().toString())) {
                    ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.phoneOrPasswordNotNull));
                } else {
                    if (etPassWord.length() >= 6) {
                        String phone = etPhone.getText().toString();
                        String password = etPassWord.getText().toString();
                        passwordLoginPresenter.passwordLogin(phone, password, mContext);
                    } else {
                        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.passwordNotLessThanSixLength));
                    }
                }
            }
        });
        //跳转到验证码登陆
        tvVCodeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void passwordLoginOnSuccess(UserBean userBean) {
        editor.putString("userId", userBean.userId);
        editor.putString("userName", userBean.userName);
        editor.putString("userCity", userBean.userCity);
        editor.putString("userSex", userBean.userSex);
        editor.putString("userSignature", userBean.userSignature);
        editor.putString("userLogo", userBean.userLogo);
        editor.putString("userBackgroundUrl", userBean.userBackgroundUrl);
        editor.putString("userPwd", userBean.userPwd);
        editor.putString("userPhone", userBean.userPhone);
        editor.putBoolean("isLoggedIn", true);
        editor.commit();
        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.loginOnSuccess));
        finish();
    }

    @Override
    public void passwordLoginOnFailure(String msg) {
        ToastUtils.showSafeShortToast(mContext, msg);
    }

    @Override
    public void passwordLoginOnError() {
        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.loginOnError));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (passwordLoginPresenter != null) {
            passwordLoginPresenter.destroy();
            passwordLoginPresenter = null;
        }
    }
}
