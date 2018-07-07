package com.example.dbh.yhomies.view.customize_view;

import android.os.CountDownTimer;
import android.widget.TextView;

public class MyCountDownTimer extends CountDownTimer {

    private TextView tvGetVCode;

    /**
     * @param millisInFuture    预计倒计时参数，也就是倒计时多少秒
     * @param countDownInterval 倒计时间隔
     * @param tvGetVCode        展示变化的控件，类型不固定
     */
    public MyCountDownTimer(long millisInFuture, long countDownInterval, TextView tvGetVCode) {
        super(millisInFuture, countDownInterval);
        this.tvGetVCode = tvGetVCode;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        tvGetVCode.setClickable(false);
        tvGetVCode.setText("重新发送" + millisUntilFinished / 1000 + "s");
    }

    @Override
    public void onFinish() {
        tvGetVCode.setText("重新发送");
        tvGetVCode.setClickable(true);
    }
}
