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

public class IndActivity extends AppCompatActivity {
    public static final String IND_NAME = "ind_name";

    public static final String IND_IMAGE_ID = "ind_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ind);
        Intent intent = getIntent();
        String indName = intent.getStringExtra(IND_NAME);
        int indimageId = intent.getIntExtra(IND_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar3);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar3);
        ImageView indImageView = (ImageView)findViewById(R.id.ind_image_view);
        TextView indContentText = (TextView)findViewById(R.id.ind_content_text);
        WebView webView = (WebView)findViewById(R.id.web_view3);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(indName);
        Glide.with(this).load(indimageId).into(indImageView);
        String indContent = generateIndContent(indName);
        indContentText.setText(indContent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.loadUrl("https://mzh.moegirl.org/"+indName);
    }

    private String generateIndContent(String indName){
        StringBuilder indContent = new StringBuilder();


        indContent.append("正在加载"+indName+"的百科...");

        return indContent.toString();
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
