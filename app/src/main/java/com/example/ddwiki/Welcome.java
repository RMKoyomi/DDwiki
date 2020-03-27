package com.example.ddwiki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.example.ddwiki.otherpage.MainpageActivity;
import com.example.ddwiki.R;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏顶部标题栏
        //getSupportActionBar().hide();
        handler.sendEmptyMessageDelayed(0,3000);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            //实现页面跳转
            Intent intent = new Intent(Welcome.this, MainpageActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Welcome.this).toBundle());
            finish();
            super.handleMessage(msg);
        }
    };
}
