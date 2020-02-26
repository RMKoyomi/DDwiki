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

public class HoloActivity extends AppCompatActivity {
    public static final String HOLO_NAME = "holo_name";

    public static final String HOLO_IMAGE_ID = "holo_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holo);
        Intent intent = getIntent();
        String holoName = intent.getStringExtra(HOLO_NAME);
        int holoimageId = intent.getIntExtra(HOLO_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        ImageView holoImageView = (ImageView)findViewById(R.id.holo_image_view);
        TextView holoContentText = (TextView)findViewById(R.id.holo_content_text);
        WebView webView = (WebView)findViewById(R.id.web_view);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(holoName);
        Glide.with(this).load(holoimageId).into(holoImageView);
        String holoContent = generateHoloContent(holoName);
        holoContentText.setText(holoContent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.loadUrl("https://mzh.moegirl.org/"+holoName);
    }

    private String generateHoloContent(String holoName){
        StringBuilder holoContent = new StringBuilder();


        holoContent.append("正在加载"+holoName+"的百科...");

        return holoContent.toString();
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
