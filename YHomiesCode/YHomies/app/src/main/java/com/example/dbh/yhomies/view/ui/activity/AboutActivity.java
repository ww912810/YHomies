package com.example.dbh.yhomies.view.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.utils.HttpUrlUtils;
import com.example.dbh.yhomies.view.ui.base_view.WhiteBaseActivity;

public class AboutActivity extends WhiteBaseActivity {

    private Context mContext;

    private ImageView ivLeftImage;

    private WebView wvAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mContext = this;
        setWebView();
    }

    @Override
    protected void initView() {
        ivLeftImage = findViewById(R.id.ivLeftImage);
        ImageView ivRightImage = findViewById(R.id.ivRightImage);
        ivRightImage.setVisibility(View.GONE);
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("关于");

        wvAbout = findViewById(R.id.wvAbout);
    }

    @Override
    protected void initListener() {
        ivLeftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 设置webView
     */
    private void setWebView() {
        //WebView加载web资源
        wvAbout.loadUrl(HttpUrlUtils.AS_REGARDS);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wvAbout.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

}
