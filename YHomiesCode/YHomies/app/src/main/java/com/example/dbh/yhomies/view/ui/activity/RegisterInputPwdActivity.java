package com.example.dbh.yhomies.view.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.dbh.yhomies.mode.Bean.UserBean;
import com.example.dbh.yhomies.presenter.PasswordLoginPresenter;
import com.example.dbh.yhomies.presenter.RegisterPresenter;
import com.example.dbh.yhomies.view.base_view.BlackBaseActivity;
import com.example.dbh.yhomies.view.v_interface.IPasswordLoginView;
import com.example.dbh.yhomies.view.v_interface.IRegisterView;

public class RegisterInputPwdActivity extends BlackBaseActivity implements IRegisterView, IPasswordLoginView {

    private Context mContext;
    public SharedPreferences spf; //存储文件对象
    public SharedPreferences.Editor editor; //文件编辑对象

    private ImageView ivLeftImage;

    private String userPhone;
    private EditText etPassWord, etAgainInputPassWord;
    private Button btnNext;
    private ImageView ivPwdVisible;
    private boolean isVisiblePwd = true;

    private RegisterPresenter registerPresenter;
    private PasswordLoginPresenter passwordLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_input_pwd);
        mContext = this;
        userPhone = getIntent().getStringExtra("userPhone");
        spf = mContext.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        editor = spf.edit();
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

        registerPresenter = new RegisterPresenter(this);
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
                            registerPresenter.toRegister(userPhone, etPassWord.getText().toString(), mContext);
                        } else {
                            ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.oldPwdAndNewPwdNotEquals));
                        }
                    }
                }
            }
        });
    }

    /**
     * 注册中
     */
    @Override
    public void registerOnLoading() {

    }

    /**
     * 注册成功
     * @param userPhone 用户账号
     * @param pwd       用户密码
     */
    @Override
    public void registerOnSuccess(String userPhone, String pwd) {
        passwordLoginPresenter.passwordLogin(userPhone, pwd, mContext);
        ToastUtils.showSafeShortToast(mContext,getResources().getString(R.string.registerOnSuccess));
    }

    /**
     * 注册失败
     * @param msg
     */
    @Override
    public void registerOnFailure(String msg) {
        ToastUtils.showSafeShortToast(mContext,getResources().getString(R.string.registerOnFailure));
    }

    /**
     * 注册失败，网络原因
     */
    @Override
    public void registerOnError() {
        ToastUtils.showSafeShortToast(mContext,getResources().getString(R.string.registerOnError));
    }

    /**
     * 登陆成功
     * @param userBean 用户信息
     */
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
        finish();
    }

    /**
     * 登陆失败
     * @param msg
     */
    @Override
    public void passwordLoginOnFailure(String msg) {
        ToastUtils.showSafeShortToast(mContext,getResources().getString(R.string.loginOnError));
    }

    /**
     * 登陆失败，网络原因
     */
    @Override
    public void passwordLoginOnError() {
        ToastUtils.showSafeShortToast(mContext,getResources().getString(R.string.loginOnError));
    }
}
