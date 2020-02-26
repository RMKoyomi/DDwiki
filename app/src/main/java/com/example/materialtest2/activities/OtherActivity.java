package com.example.materialtest2.activities;

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
import com.example.materialtest2.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class OtherActivity extends AppCompatActivity {
    public static final String OTHER_NAME = "other_name";

    public static final String OTHER_IMAGE_ID = "other_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Intent intent = getIntent();
        String OtherName = intent.getStringExtra(OTHER_NAME);
        int otherimageId = intent.getIntExtra(OTHER_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar5);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar5);
        ImageView otherImageView = (ImageView)findViewById(R.id.other_image_view);
        TextView otherContentText = (TextView)findViewById(R.id.other_content_text);
        WebView webView = (WebView)findViewById(R.id.web_view5);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(OtherName);
        Glide.with(this).load(otherimageId).into(otherImageView);
        String otherContent = generateOtherContent(OtherName);
        otherContentText.setText(otherContent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.loadUrl("https://mzh.moegirl.org/"+OtherName);
    }

    private String generateOtherContent(String OtherName){
        StringBuilder otherContent = new StringBuilder();


        otherContent.append("正在加载"+OtherName+"的百科...");

        return otherContent.toString();
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
