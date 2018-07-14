package com.example.dbh.yhomies.view.ui.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dawn.dawnsutils.ToastUtils;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.view.adapter.MainFragmentsAdapter;
import com.example.dbh.yhomies.view.base_view.WhiteBaseActivity;
import com.example.dbh.yhomies.view.customize_view.MoreWindow;
import com.example.dbh.yhomies.view.customize_view.NoScrollViewPager;
import com.example.dbh.yhomies.view.ui.fragment.FindFragment;
import com.example.dbh.yhomies.view.ui.fragment.HomeFragment;
import com.example.dbh.yhomies.view.ui.fragment.MyFragment;
import com.example.dbh.yhomies.view.ui.fragment.SquareFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 首页Fragment
 * 包含三个子Fragment：热门 关注 附近
 *
 * @author 段博涵
 */
public class MainActivity extends WhiteBaseActivity {

    private Context mContext;

    private ImageView ivHomies, ivSquare, ivAdd, ivFriend, ivMy;
    private int[] toolsImageCheck = new int[]{R.mipmap.ic_home_check, R.mipmap.ic_square_check, R.mipmap.ic_friend_check, R.mipmap.ic_my_check};
    private int[] toolsImageUnCheck = new int[]{R.mipmap.ic_home_uncheck, R.mipmap.ic_square_uncheck, R.mipmap.ic_friend_uncheck, R.mipmap.ic_my_uncheck};
    private NoScrollViewPager myViewPage;

    private MoreWindow mMoreWindow;
    private RelativeLayout idContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initView() {
        myViewPage = findViewById(R.id.myViewPage);
        myViewPage.setNoScroll(true);
        ivHomies = findViewById(R.id.ivHomies);
        ivSquare = findViewById(R.id.ivSquare);
        ivAdd = findViewById(R.id.ivAdd);
        ivFriend = findViewById(R.id.ivFriend);
        ivMy = findViewById(R.id.ivMy);
        idContainer = findViewById(R.id.id_container);
        mMoreWindow = new MoreWindow(this);
        mMoreWindow.init(idContainer);
    }

    @Override
    protected void initListener() {
        //底部工具栏点击事件
        //首页
        ivHomies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPage.setCurrentItem(0);
                setImage(0);
            }
        });
        //广场
        ivSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPage.setCurrentItem(1);
                setImage(1);
            }
        });
        //发现
        ivFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPage.setCurrentItem(2);
                setImage(2);
            }
        });
        //我的
        ivMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPage.setCurrentItem(3);
                setImage(3);
            }
        });
        //发布
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreWindow();
            }
        });
    }

    /**
     * 初始化碎片
     */
    private void initFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        SquareFragment squareFragment = new SquareFragment();
        FindFragment findFragment = new FindFragment();
        MyFragment myFragment = new MyFragment();
        fragments.add(homeFragment);
        fragments.add(squareFragment);
        fragments.add(findFragment);
        fragments.add(myFragment);
        MainFragmentsAdapter mainFragmentsAdapter = new MainFragmentsAdapter(getSupportFragmentManager(), fragments);
        myViewPage.setAdapter(mainFragmentsAdapter);
        myViewPage.setCurrentItem(0);
    }

    /**
     * 设置底部菜单栏图标
     *
     * @param id
     */
    public void setImage(int id) {
        ivHomies.setImageResource(toolsImageUnCheck[0]);
        ivSquare.setImageResource(toolsImageUnCheck[1]);
        ivFriend.setImageResource(toolsImageUnCheck[2]);
        ivMy.setImageResource(toolsImageUnCheck[3]);

        switch (id) {
            case 0:
                ivHomies.setImageResource(toolsImageCheck[0]);
                break;
            case 1:
                ivSquare.setImageResource(toolsImageCheck[1]);
                break;
            case 2:
                ivFriend.setImageResource(toolsImageCheck[2]);
                break;
            case 3:
                ivMy.setImageResource(toolsImageCheck[3]);
                break;
            default:
                break;
        }
    }

    /**
     * 显示发帖选择框
     */
    private void showMoreWindow() {
        if (null == mMoreWindow) {
            mMoreWindow = new MoreWindow(this);
            mMoreWindow.init(idContainer);
        }

        mMoreWindow.showMoreWindow(idContainer );
    }

    /**
     * 双击退出应用
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        if (!isExit) {

            isExit = true; // 准备退出
            ToastUtils.showShortToast(mContext, "再按一次退出Homies");

            Timer tExit = new Timer();

            tExit.schedule(new TimerTask() {

                @Override

                public void run() {

                    isExit = false; // 取消退出
                }

            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();  //退出应用的操作
        }
        return false;
    }

}
