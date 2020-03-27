package com.example.ddwiki.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.ddwiki.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class EnterpriseActivity extends AppCompatActivity {
    public static final String ENTER_NAME = "enter_name";

    public static final String ENTER_IMAGE_ID = "enter_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise);
        Intent intent = getIntent();
        String enterName = intent.getStringExtra(ENTER_NAME);
        int enterimageId = intent.getIntExtra(ENTER_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        ImageView enterImageView = (ImageView)findViewById(R.id.enter_image_view);
        TextView enterContentText = (TextView)findViewById(R.id.enter_content_text);
        WebView webView = (WebView)findViewById(R.id.web_view);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(enterName);
        Glide.with(this).load(enterimageId).into(enterImageView);
        String holoContent = generateHoloContent(enterName);
        enterContentText.setText(holoContent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.loadUrl("https://mzh.moegirl.org/"+enterName);

    }

    private String generateHoloContent(String enterName){
        StringBuilder enterContent = new StringBuilder();


        enterContent.append("正在加载"+enterName+"的介绍...");

        return enterContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
