package com.example.dbh.yhomies.view.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.dbh.yhomies.presenter.GetVCodePresenter;
import com.example.dbh.yhomies.presenter.VCodeLoginPresenter;
import com.example.dbh.yhomies.view.base_view.BlackBaseActivity;
import com.example.dbh.yhomies.view.customize_view.MyCountDownTimer;
import com.example.dbh.yhomies.view.v_interface.IGetVCodeView;
import com.example.dbh.yhomies.view.v_interface.IVCodeLoginView;

public class LoginActivity extends BlackBaseActivity implements IVCodeLoginView, IGetVCodeView {

    private Context mContext;
    public SharedPreferences spf; //存储文件对象
    public SharedPreferences.Editor editor; //文件编辑对象

    private ImageView ivLeftImage;

    private EditText etPhone, etVCode;
    private TextView tvGetVCode, tvPasswordLogin;
    private Button btnLogin;
    private String phone, editVCode, vCodeValue;
    private VCodeLoginPresenter vCodeLoginPresenter;
    private GetVCodePresenter getVCodePresenter;
    private MyCountDownTimer myCountDownTimer;
    private final int MY_INFO_UPDATE_CODE = 82101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        TextView tvRightText = findViewById(R.id.tvRightText);
        tvRightText.setVisibility(View.VISIBLE);
        tvRightText.setText("忘记密码");
        tvRightText.setTextColor(getResources().getColor(R.color.white));

        etPhone = findViewById(R.id.etPhone);
        etVCode = findViewById(R.id.etVCode);
        tvGetVCode = findViewById(R.id.tvGetVCode);
        tvPasswordLogin = findViewById(R.id.tvPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        vCodeLoginPresenter = new VCodeLoginPresenter(this);
        getVCodePresenter = new GetVCodePresenter(this);
        myCountDownTimer = new MyCountDownTimer(60000, 1000, tvGetVCode);
    }

    @Override
    protected void initListener() {
        ivLeftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent();
                intent.putExtra("isCancelLogin", true);
                setResult(MY_INFO_UPDATE_CODE, intent);
            }
        });

        //获取验证码
        tvGetVCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(etPhone.getText().toString())) {
                    ToastUtils.showSafeShortToast(mContext, "手机号不能为空！");
                } else {
                    phone = etPhone.getText().toString();
                    getVCodePresenter.getVCode(phone, mContext);
                    myCountDownTimer.start();
                }
            }
        });
        //切换密码登陆
        tvPasswordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //登陆
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(etPhone.getText().toString()) || StringUtils.isEmpty(etVCode.getText().toString())) {
                    ToastUtils.showSafeShortToast(mContext, "手机号或验证码不能为空！");
                } else {
                    phone = etPhone.getText().toString();
                    editVCode = etVCode.getText().toString();
                    if (editVCode.equals(vCodeValue)) {
                        vCodeLoginPresenter.VCodeLogin(phone, mContext);
                    } else {
                        ToastUtils.showSafeShortToast(mContext, "验证码输入错误！");
                    }
                }

            }
        });
    }

    /**
     * 验证码登陆成功
     *
     * @param userBean 用户信息
     */
    @Override
    public void vCodeLoginOnSuccess(UserBean userBean) {
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
        ToastUtils.showSafeShortToast(mContext, "登陆成功！");
    }

    /**
     * 验证码登陆失败
     *
     * @param msg
     */
    @Override
    public void vCodeLoginOnFailure(String msg) {
        ToastUtils.showSafeShortToast(mContext, msg);
    }

    /**
     * 网络错误
     */
    @Override
    public void vCodeLoginOnError() {
        ToastUtils.showSafeShortToast(mContext, "登陆失败，请检查网络！");
    }

    /**
     * 获取验证码成功
     *
     * @param vCodeValue 验证码
     */
    @Override
    public void getVCodeOnSuccess(String vCodeValue) {
        ToastUtils.showSafeShortToast(mContext, "验证码已发送，Bro ！");
        this.vCodeValue = vCodeValue;
    }

    /**
     * 获取验证码失败
     *
     * @param msg
     */
    @Override
    public void getVCodeOnFailure(String msg) {
        ToastUtils.showSafeShortToast(mContext, msg);
    }

    /**
     * 网络错误
     */
    @Override
    public void getVCodeOnError() {
        ToastUtils.showSafeShortToast(mContext, "获取验证码失败，请检查网络！");
    }
}
