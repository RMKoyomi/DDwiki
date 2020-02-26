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

public class NJSJActivity extends AppCompatActivity {
    public static final String NJSJ_NAME = "njsj_name";

    public static final String NJSJ_IMAGE_ID = "njsj_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_njsj);
        Intent intent = getIntent();
        String NJSJName = intent.getStringExtra(NJSJ_NAME);
        int njsjimageId = intent.getIntExtra(NJSJ_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar2);
        ImageView njsjImageView = (ImageView)findViewById(R.id.njsj_image_view);
        TextView njsjContentText = (TextView)findViewById(R.id.njsj_content_text);
        WebView webView = (WebView)findViewById(R.id.web_view2);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(NJSJName);
        Glide.with(this).load(njsjimageId).into(njsjImageView);
        String njsjContent = generateNJSJContent(NJSJName);
        njsjContentText.setText(njsjContent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.loadUrl("https://mzh.moegirl.org/"+NJSJName);
    }

    private String generateNJSJContent(String njsjName){
        StringBuilder njsjContent = new StringBuilder();


        njsjContent.append("正在加载"+njsjName+"的百科...");

        return njsjContent.toString();
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
