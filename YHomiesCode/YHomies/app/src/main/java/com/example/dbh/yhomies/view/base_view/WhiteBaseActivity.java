package com.example.dbh.yhomies.view.base_view;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.utils.StatusBarUtils;
import com.example.dbh.yhomies.utils.manager.AppManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public abstract class WhiteBaseActivity extends AppCompatActivity {

    /**
     * Activity基类
     * 状态栏白色
     * @param layoutResID
     * @author 段博涵
     */

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        //让状态栏透明
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        super.setContentView(layoutResID);
        StatusBarUtils.setStatusBarColor(this, R.color.translucent);
        StatusBarUtils.StatusBarLightMode(this);
        //applyKitKatTranslucency();
        initView();
        initListener();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
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
            mTintManager.setStatusBarTintResource(R.color.white);//通知栏所需颜色
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }
}
