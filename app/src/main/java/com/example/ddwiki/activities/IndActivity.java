package com.example.ddwiki.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.ddwiki.R;
import com.example.ddwiki.Vtbclass.History;
import com.example.ddwiki.Vtbclass.Like;
import com.example.ddwiki.db.HisDBHelper;
import com.example.ddwiki.db.LikesDBHelper;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class IndActivity extends AppCompatActivity {
    public static final String IND_NAME = "ind_name";

    public static final String IND_IMAGE_ID = "ind_image_id";

    private LikesDBHelper dbHelper;

    private HisDBHelper db2;

    Like like;

    History history;

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
        viewcollect(history);
        webView.loadUrl("https://mzh.moegirl.org/"+indName);
    }

    private String generateIndContent(String indName){
        StringBuilder indContent = new StringBuilder();


        indContent.append("正在加载"+indName+"的百科...");

        return indContent.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar3,menu);
        return true;
    }

    public void viewcollect(History history){
        /**
         * 数据库操作
         */
        Intent intent2 = getIntent();
        String Name2 = intent2.getStringExtra(IND_NAME);
        int imageId2 = intent2.getIntExtra(IND_IMAGE_ID,0);

        db2 = new HisDBHelper(this);
        SQLiteDatabase db= db2.getWritableDatabase();
        ContentValues values2 = new ContentValues();
        values2.put("hisname",Name2);
        values2.put("hisimageid",imageId2);

        db.insert("his",null,values2);
        //Toast.makeText(this,"加入历史记录",Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void collect(Like like){
        /**
         * 数据库操作
         */
        Intent intent = getIntent();
        String Name = intent.getStringExtra(IND_NAME);
        int imageId = intent.getIntExtra(IND_IMAGE_ID,0);

        dbHelper = new LikesDBHelper(this);
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",Name);
        values.put("imageid",imageId);

        db.insert("Likes",null,values);
        db.close();
    }

    public void del(){
        Intent intent = getIntent();
        String Name = intent.getStringExtra(IND_NAME);
        dbHelper = new LikesDBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Likes","name = ?",new String[]{Name});
        db.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.like:
                if(item.getTitle().equals("收藏")) {
                    item.setIcon( R.drawable.ic_like);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                    collect(like);
                    item.setTitle("取消收藏");
                }else {
                    del();
                    Toast.makeText(this,"取消收藏",Toast.LENGTH_SHORT).show();
                    item.setIcon(R.drawable.ic_like_before);
                    item.setTitle("收藏");
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
