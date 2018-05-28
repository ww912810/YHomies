package com.example.dbh.yhomies.view.ui.base_view;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.dbh.yhomies.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public abstract class BlackBaseActivity extends AppCompatActivity {

    /**
     * Activity基类
     * 状态栏白色
     * @param layoutResID
     * @author 段博涵
     */

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        applyKitKatTranslucency();
        initView();
        initListener();
    }

    /**
     * Apply KitKat specific translucency.
     */
    private void applyKitKatTranslucency() {
        // KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            //在内容视图设置后创建我们的管理器实例
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setStatusBarTintResource(R.color.black);//通知栏所需颜色
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected abstract void initView();

    protected abstract void initListener();

}
