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
import com.example.dbh.yhomies.presenter.GetVCodePresenter;
import com.example.dbh.yhomies.presenter.VCodeLoginPresenter;
import com.example.dbh.yhomies.view.base_view.BlackBaseActivity;
import com.example.dbh.yhomies.view.customize_view.MyCountDownTimer;
import com.example.dbh.yhomies.view.v_interface.IGetVCodeView;

public class FGPasswordForPhoneActivity extends BlackBaseActivity implements IGetVCodeView {

    private Context mContext;
    private ImageView ivLeftImage;

    private EditText etPhone, etVCode;
    private TextView tvGetVCode;
    private Button btnNext;
    private String phone, editVCode, vCodeValue;
    private MyCountDownTimer myCountDownTimer;
    private GetVCodePresenter getVCodePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fg_password_for_phone);

        mContext = this;

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

        etPhone = findViewById(R.id.etPhone);
        etVCode = findViewById(R.id.etVCode);
        tvGetVCode = findViewById(R.id.tvGetVCode);
        btnNext = findViewById(R.id.btnNext);
        getVCodePresenter = new GetVCodePresenter(this);
        myCountDownTimer = new MyCountDownTimer(60000, 1000, tvGetVCode);
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
                if (StringUtils.isEmpty(etPhone.getText().toString()) || StringUtils.isEmpty(etVCode.getText().toString())) {
                    ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.phoneOrVCodeNotNull));
                } else {
                    phone = etPhone.getText().toString();
                    editVCode = etVCode.getText().toString();
                    if (editVCode.equals(vCodeValue)) {
                        //
                        Intent intent = new Intent(mContext, FGPasswordForPwdActivity.class);
                        intent.putExtra("userPhone", phone);
                        startActivity(intent);
                    } else {
                        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.inputVCodeError));
                    }
                }
            }
        });

        //获取验证码
        tvGetVCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(etPhone.getText().toString())) {
                    ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.phoneNotNull));
                } else {
                    phone = etPhone.getText().toString();
                    getVCodePresenter.getVCode(phone, mContext);
                    myCountDownTimer.start();
                }
            }
        });
    }

    @Override
    public void getVCodeOnSuccess(String vCodeValue) {
        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.getVCodeOnSuccess));
        this.vCodeValue = vCodeValue;
    }

    @Override
    public void getVCodeOnFailure(String msg) {
        ToastUtils.showSafeShortToast(mContext, msg);
    }

    @Override
    public void getVCodeOnError() {
        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.getVCodeOnError));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getVCodePresenter != null) {
            getVCodePresenter.destroy();
            getVCodePresenter = null;
        }
    }
}
