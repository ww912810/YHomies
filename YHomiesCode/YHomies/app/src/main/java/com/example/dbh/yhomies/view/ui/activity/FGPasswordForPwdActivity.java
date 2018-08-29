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
import com.example.dbh.yhomies.presenter.UpdatePwdPresenter;
import com.example.dbh.yhomies.utils.manager.AppManager;
import com.example.dbh.yhomies.view.base_view.BlackBaseActivity;
import com.example.dbh.yhomies.view.v_interface.IPasswordLoginView;
import com.example.dbh.yhomies.view.v_interface.IUpDatePwdView;

public class FGPasswordForPwdActivity extends BlackBaseActivity implements IPasswordLoginView, IUpDatePwdView {

    private Context mContext;
    public SharedPreferences spf; //存储文件对象
    public SharedPreferences.Editor editor; //文件编辑对象

    private ImageView ivLeftImage;

    private String userPhone, oldPassword, newPassword;
    private EditText etInputPassword, etAgainInputPassWord;
    private Button btnGoUpDate;
    private PasswordLoginPresenter passwordLoginPresenter;
    private UpdatePwdPresenter updatePwdPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fg_password_for_pwd);
        mContext = this;
        spf = mContext.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        editor = spf.edit();
        userPhone = getIntent().getStringExtra("userPhone");
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
        tvTitle.setText("忘记密码");
        TextView tvRightText = findViewById(R.id.tvRightText);
        tvRightText.setVisibility(View.GONE);

        etInputPassword = findViewById(R.id.etInputPassword);
        etAgainInputPassWord = findViewById(R.id.etAgainInputPassWord);
        btnGoUpDate = findViewById(R.id.btnGoUpDate);
        passwordLoginPresenter = new PasswordLoginPresenter(this);
        updatePwdPresenter = new UpdatePwdPresenter(this);
    }

    @Override
    protected void initListener() {
        ivLeftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnGoUpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(etInputPassword.getText().toString()) || StringUtils.isEmpty(etAgainInputPassWord.getText().toString())) {
                    ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.passwordNotNull));
                } else {
                    if (etInputPassword.length() < 6 || etAgainInputPassWord.length() < 6) {
                        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.passwordNotLessThanSixLength));
                    } else {
                        oldPassword = etInputPassword.getText().toString();
                        newPassword = etAgainInputPassWord.getText().toString();
                        if (oldPassword.equals(newPassword)) {
                            //两次密码相等
                            updatePwdPresenter.updatePwdLogin(userPhone, newPassword, mContext);
                        } else {
                            ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.oldPwdAndNewPwdNotEquals));
                        }
                    }
                }
            }
        });
    }

    ///////////////////////////////////////分割线///////////////////////////////////////////////////////
    /////////////////////////////////以下为密码登陆返回结果//////////////////////////////////////////////
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
        editor.putString("userPwd", newPassword);
        editor.commit();
        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.loginOnSuccess));
        AppManager.getAppManager().finishActivity(new FGPasswordForPhoneActivity());
        AppManager.getAppManager().finishActivity(new LoginForPasswordActivity());
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void passwordLoginOnFailure(String msg) {
        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.loginOnError));
    }

    @Override
    public void passwordLoginOnError() {
        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.loginOnError));
    }

    ///////////////////////////////////////分割线///////////////////////////////////////////////////////
    /////////////////////////////////以下为修改密码返回结果//////////////////////////////////////////////
    @Override
    public void updatePwdOnSuccess(String userPhone, String pwd) {
        this.userPhone = userPhone;
        newPassword = pwd;
        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.updateOnSuccess));
        passwordLoginPresenter.passwordLogin(userPhone, newPassword, mContext);
    }

    @Override
    public void updatePwdOnFailure(String msg) {
        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.updateOnFailure));
    }

    @Override
    public void updatePwdOnError() {
        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.updateOnError));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (passwordLoginPresenter != null || updatePwdPresenter != null) {
            passwordLoginPresenter.destroy();
            updatePwdPresenter.destroy();
            passwordLoginPresenter = null;
            updatePwdPresenter = null;
        }
    }
}
