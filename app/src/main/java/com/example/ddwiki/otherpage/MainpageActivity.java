package com.example.ddwiki.otherpage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ddwiki.ActivityCollector;
import com.example.ddwiki.FifthActivity;
import com.example.ddwiki.ForthActivity;
import com.example.ddwiki.MainActivity;
import com.example.ddwiki.R;
import com.example.ddwiki.SecondActivity;
import com.example.ddwiki.ThirdActivity;
import com.example.ddwiki.search.SearchActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainpageActivity extends MainActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage_activty);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        //点击打开滑动菜单
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout6);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view6);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_menu);
        }
        //滑动菜单的点击事件
        navView.setCheckedItem(R.id.nav_home);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        break;
                    case R.id.nav_holo:
                        Intent intent5 = new Intent(MainpageActivity.this, MainActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.nav_nijisanji:
                        Intent intent = new Intent(MainpageActivity.this, SecondActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_independent:
                        Intent intent2 = new Intent(MainpageActivity.this, ThirdActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_four:
                        Intent intent3 = new Intent(MainpageActivity.this, ForthActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_other:
                        Intent intent4 = new Intent(MainpageActivity.this, FifthActivity.class);
                        startActivity(intent4);
                        break;
                    default:
                        break;

                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        /*
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab6);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"你点开了一个查找功能",Snackbar.LENGTH_SHORT)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainpageActivity.this,"取消查找",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });*/
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.search:
                Intent intent = new Intent(MainpageActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.settings:
                Intent intent2 = new Intent(MainpageActivity.this, AboutActivity.class);
                startActivity(intent2);
                break;
            default:
        }
        return true;
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 按两次返回键退出应用程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 判断间隔时间 大于2秒就退出应用
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                // 应用名
                String applicationName = getResources().getString(
                        R.string.app_name);
                String msg = "再按一次返回键退出" + applicationName;
                //String msg1 = "再按一次返回键回到桌面";
                Toast.makeText(MainpageActivity.this, msg, Toast.LENGTH_SHORT).show();
                // 计算两次返回键按下的时间差
                exitTime = System.currentTimeMillis();
            } else {
                // 关闭应用程序
                ActivityCollector.finishAll();
                // 返回桌面操作
                // Intent home = new Intent(Intent.ACTION_MAIN);
                // home.addCategory(Intent.CATEGORY_HOME);
                // startActivity(home);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
