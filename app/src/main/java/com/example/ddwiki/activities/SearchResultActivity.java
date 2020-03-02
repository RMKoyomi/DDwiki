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
import com.example.ddwiki.db.Vtubers;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.litepal.crud.DataSupport;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
//    List<Vtubers> vtubers = DataSupport.select("name = ?","").find(Vtubers.class);

    public static final String NAME = "name";

    public static final String IMAGE_ID = "image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Intent intent = getIntent();
        String Name = intent.getStringExtra(NAME);
        int imageId = intent.getIntExtra(IMAGE_ID,0);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        ImageView ImageView = (ImageView)findViewById(R.id.vtb_image_view);
        TextView ContentText = (TextView)findViewById(R.id.vtb_content_text);
        WebView webView = (WebView)findViewById(R.id.web_view);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(Name);
        Glide.with(this).load(imageId).into(ImageView);
        String holoContent = generateContent(Name);
        ContentText.setText(holoContent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.loadUrl("https://mzh.moegirl.org/"+Name);
    }


    private String generateContent(String Name){
        StringBuilder Content = new StringBuilder();


        Content.append("正在加载"+Name+"的百科...");

        return Content.toString();
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
