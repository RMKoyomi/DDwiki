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

import org.litepal.tablemanager.Connector;

import java.util.Calendar;

public class NJSJActivity extends AppCompatActivity {
    public static final String NJSJ_NAME = "njsj_name";

    public static final String NJSJ_IMAGE_ID = "njsj_image_id";

    private LikesDBHelper dbHelper;

    private HisDBHelper db2;

    Like like;

    History history;

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
        viewcollect(history);
        webView.loadUrl("https://mzh.moegirl.org/"+NJSJName);
        Connector.getDatabase();
    }

    private String generateNJSJContent(String njsjName){
        StringBuilder njsjContent = new StringBuilder();


        njsjContent.append("正在加载"+njsjName+"的百科...");

        return njsjContent.toString();
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
        String Name2 = intent2.getStringExtra(NJSJ_NAME);
        int imageId2 = intent2.getIntExtra(NJSJ_IMAGE_ID,0);
        String Datetime = getTime();

        db2 = new HisDBHelper(this);
        SQLiteDatabase db= db2.getWritableDatabase();
        db.delete("his","hisname = ?",new String[]{Name2});
        ContentValues values2 = new ContentValues();
        values2.put("hisname",Name2);
        values2.put("hisimageid",imageId2);
        values2.put("histime",Datetime);

        db.insert("his",null,values2);
        //Toast.makeText(this,"加入历史记录",Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void collect(Like like){
        /**
         * 数据库操作
         */
        Intent intent = getIntent();
        String Name = intent.getStringExtra(NJSJ_NAME);
        int imageId = intent.getIntExtra(NJSJ_IMAGE_ID,0);

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
        String Name = intent.getStringExtra(NJSJ_NAME);
        dbHelper = new LikesDBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Likes","name = ?",new String[]{Name});
        db.close();
    }

    public String getTime(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String datetime1 = year+"."+month+"."+day+" "+hour+":"+minute;
        return datetime1;
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
