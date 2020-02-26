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
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.ddwiki.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FourActivity extends AppCompatActivity {
    public static final String FOUR_NAME = "four_name";

    public static final String FOUR_IMAGE_ID = "four_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        Intent intent = getIntent();
        String fourName = intent.getStringExtra(FOUR_NAME);
        int fourimageId = intent.getIntExtra(FOUR_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar4);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar4);
        ImageView fourImageView = (ImageView)findViewById(R.id.four_image_view);
        TextView fourContentText = (TextView)findViewById(R.id.four_content_text);
        WebView webView = (WebView)findViewById(R.id.web_view4);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(fourName);
        Glide.with(this).load(fourimageId).into(fourImageView);
        String fourContent = generateFourContent(fourName);
        fourContentText.setText(fourContent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.loadUrl("https://mzh.moegirl.org/"+fourName);
    }

    private String generateFourContent(String fourName){
        StringBuilder fourContent = new StringBuilder();
        fourContent.append("正在加载"+fourName+"的百科...");

        return fourContent.toString();
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

