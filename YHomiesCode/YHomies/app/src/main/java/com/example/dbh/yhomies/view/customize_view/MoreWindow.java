package com.example.dbh.yhomies.view.customize_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dawn.dawnsutils.ToastUtils;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.view.ui.activity.ChooseVideoFileActivity;
import com.example.dbh.yhomies.view.ui.activity.EditOriginalPost;
import com.ms_square.etsyblur.BlurringView;

public class MoreWindow extends PopupWindow implements OnClickListener {

    private Activity mContext;
    private RelativeLayout layout;
    private ImageView close;
    private View bgView;
    private View rootView;
    private BlurringView blurringView, dialogBlurringView;
    private int mWidth;
    private int mHeight;
    private int statusBarHeight;
    private Handler mHandler = new Handler();
    private Dialog dialog;

    public MoreWindow(Activity context) {
        mContext = context;
    }

    /**
     * 初始化
     *
     * @param view 要显示的模糊背景View,一般选择跟布局layout
     */
    public void init(View view) {
        rootView = view;
        Rect frame = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusBarHeight = frame.top;
        DisplayMetrics metrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;

        setWidth(mWidth);
        setHeight(mHeight);

        layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.more_window, null);

        setContentView(layout);

        close = (ImageView) layout.findViewById(R.id.iv_close);
        close.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isShowing()) {
                    closeAnimation();
                }
            }

        });

        blurringView = layout.findViewById(R.id.blurring_view);
        blurringView.blurredView(view);//模糊背景

        bgView = layout.findViewById(R.id.rel);
        setOutsideTouchable(true);
        setFocusable(true);
        setClippingEnabled(false);//使popupwindow全屏显示
    }

    public int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 显示window动画
     *
     * @param anchor
     */
    public void showMoreWindow(View anchor) {

        showAtLocation(anchor, Gravity.TOP | Gravity.START, 0, 0);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //圆形扩展的动画
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        int x = mWidth / 2;
                        int y = (int) (mHeight - fromDpToPx(25));
                        Animator animator = ViewAnimationUtils.createCircularReveal(bgView, x,
                                y, 0, bgView.getHeight());
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
//                                bgView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                //							layout.setVisibility(View.VISIBLE);
                            }
                        });
                        animator.setDuration(300);
                        animator.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        showAnimation(layout);

    }

    private void showAnimation(ViewGroup layout) {
        try {
            RelativeLayout relativeLayout = layout.findViewById(R.id.rlPostType);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    //＋ 旋转动画
                    close.animate().rotation(90).setDuration(300);
                }
            });
            //菜单项弹出动画
            for (int i = 0; i < relativeLayout.getChildCount(); i++) {
                final View child = relativeLayout.getChildAt(i);
                child.setOnClickListener(this);
                //child.setVisibility(View.INVISIBLE);
//                mHandler.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        child.setVisibility(View.VISIBLE);
//                        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
//                        fadeAnim.setDuration(200);
//                        KickBackAnimator kickAnimator = new KickBackAnimator();
//                        kickAnimator.setDuration(150);
//                        fadeAnim.setEvaluator(kickAnimator);
//                        fadeAnim.start();
//                    }
//                }, i * 50 + 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭window动画
     */
    private void closeAnimation() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                close.animate().rotation(-90).setDuration(400);
            }
        });

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                int x = mWidth / 2;
                int y = (int) (mHeight - fromDpToPx(25));
                Animator animator = ViewAnimationUtils.createCircularReveal(bgView, x,
                        y, bgView.getHeight(), 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //							layout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
//                        bgView.setVisibility(View.GONE);
                        dismiss();
                    }
                });
                animator.setDuration(300);
                animator.start();
            }
        } catch (Exception e) {
        }
    }

    /**
     * 点击事件处理
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        if (isShowing()) {
            closeAnimation();
        }

        switch (v.getId()) {
            case R.id.ivReleasePic:
                ToastUtils.showSafeShortToast(mContext, "图视频");
                break;
            case R.id.ivReleaseVoice:
                ToastUtils.showSafeShortToast(mContext, "语音");
                break;
            case R.id.ivReleaseText:
                ToastUtils.showSafeShortToast(mContext, "文字");
                break;
            case R.id.ivOriginal:
                showBottomDialog();
                break;
            //👇👇👇👇👇点击原创类型帖子后，弹出框的点击事件👇👇👇👇👇
            case R.id.tvText:
                //文字
                startEditPostActivity(0);
                dialog.dismiss();
                break;
            case R.id.tvImgAndVideo:
                //图视频
                startEditPostActivity(1);
                dialog.dismiss();
                break;
            case R.id.tvRecording:
                //录音
                startEditPostActivity(2);
                dialog.dismiss();
                break;
            case R.id.tvVoiceFile:
                //音频文件
                startEditPostActivity(3);
                dialog.dismiss();
                break;
            case R.id.tvCancel:
                dialog.dismiss();
                break;
        }

    }

    private void startEditPostActivity(int postType) {
        Intent intent = null;
        if (postType != 3) {
            intent = new Intent(mContext, EditOriginalPost.class);
            intent.putExtra("postType", postType);
        } else {
            intent = new Intent(mContext, ChooseVideoFileActivity.class);
        }
        mContext.startActivity(intent);
    }

    private void showBottomDialog() {
        //设置Dialog的风格
        dialog = new Dialog(mContext, R.style.bottomDialog);
        //加载布局
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.my_bottom_dialog_layout, null);
        //将布局设置给Dialog
        dialog.setContentView(dialogView);

        //设置对话框显示的位置
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //与底部距离为10
        lp.y = 10;
        dialogWindow.setAttributes(lp);
        //显示对话框
        dialog.show();

        TextView tvImgAndVideo = dialogView.findViewById(R.id.tvImgAndVideo);
        TextView tvText = dialogView.findViewById(R.id.tvText);
        TextView tvRecording = dialogView.findViewById(R.id.tvRecording);
        TextView tvVoiceFile = dialogView.findViewById(R.id.tvVoiceFile);
        TextView tvCancel = dialogView.findViewById(R.id.tvCancel);
        tvImgAndVideo.setOnClickListener(this);
        tvText.setOnClickListener(this);
        tvRecording.setOnClickListener(this);
        tvVoiceFile.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    float fromDpToPx(float dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }
}
