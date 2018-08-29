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
import com.example.dbh.yhomies.view.base_view.BlackBaseActivity;
import com.example.dbh.yhomies.view.customize_view.MyCountDownTimer;
import com.example.dbh.yhomies.view.v_interface.IGetVCodeView;

public class RegisterInputVCodeActivity extends BlackBaseActivity implements IGetVCodeView{

    private Context mContext;

    private ImageView ivLeftImage;

    private String userPhone;
    private EditText etVCode;
    private String vCodeValue;
    private TextView tvGetVCode;
    private Button btnNext;
    private GetVCodePresenter getVCodePresenter;
    private MyCountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_input_vcode);
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

        etVCode = findViewById(R.id.etVCode);
        tvGetVCode = findViewById(R.id.tvGetVCode);
        btnNext = findViewById(R.id.btnNext);
        getVCodePresenter = new GetVCodePresenter(this);
//        myCountDownTimer = new MyCountDownTimer(60000, 1000, tvGetVCode);
//        getVCodePresenter.getVCode(userPhone, mContext);
//        myCountDownTimer.start();
    }

    @Override
    protected void initListener() {
        ivLeftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvGetVCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVCodePresenter.getVCode(userPhone, mContext);
                myCountDownTimer.start();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (StringUtils.isEmpty(etVCode.getText().toString())) {
//                    ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.vCodeNotNull));
//                } else {
//                    if (etVCode.getText().toString().equals(vCodeValue)) {
                        Intent intent = new Intent(mContext,RegisterInputPwdActivity.class);
                        intent.putExtra("userPhone",userPhone);
                        startActivity(intent);
                        finish();
//                    } else {
////                        ToastUtils.showSafeShortToast(mContext, getResources().getString(R.string.inputVCodeError));
////                    }
////                }
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
