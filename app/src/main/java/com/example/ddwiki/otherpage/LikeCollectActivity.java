package com.example.ddwiki.otherpage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ddwiki.ActivityCollector;
import com.example.ddwiki.MainActivity;
import com.example.ddwiki.SecondActivity;
import com.example.ddwiki.Vtbclass.Like;
import com.example.ddwiki.adapters.LikeAdapter;
import com.example.ddwiki.db.LikesDBHelper;
import com.example.ddwiki.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class LikeCollectActivity extends MainActivity {

    private DrawerLayout mDrawerLayout;

    /*
    private Four[] four = {new Four("绊爱",R.drawable.ai_pic),new Four("辉夜月",
            R.drawable.kaguya_pic),
            new Four("未来明",R.drawable.akari_pic),new Four("电脑少女Siro",
            R.drawable.siro_pic),
            new Four("Nekomasu",R.drawable.nekomasu_pic)};*/

    private List<Like> likeList = new ArrayList<>();

    private LikeAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    private LikesDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_collect);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);
        //点击打开滑动菜单
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout7);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view7);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //滑动菜单的点击事件
        navView.setCheckedItem(R.id.nav_like);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intent5 = new Intent(LikeCollectActivity.this, MainpageActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.nav_like:
                        break;
                    case R.id.nav_his:
                        Intent intent = new Intent(LikeCollectActivity.this, HistoryCollectActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_logout:
                        ActivityCollector.finishAll();
                        break;
                    default:
                        break;

                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });


        initLIKE();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView7);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LikeAdapter(likeList);
        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh7);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLIKE();
            }
        });



    }

    private void refreshLIKE(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initLIKE();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initLIKE(){
        /**
         * 数据库操作
         */
        dbHelper = new LikesDBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c= db.rawQuery("select * from Likes",null);
        while (c.moveToNext()){
            Like like = new Like();
            like.setName(c.getString(c.getColumnIndex("name")));
            like.setImageId(c.getInt(c.getColumnIndex("imageid")));

            likeList.add(like);

        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    /**
     * 图标的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                break;
            case R.id.settings:
                Intent intent2 = new Intent(LikeCollectActivity.this, AboutActivity.class);
                startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            default:
                break;
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
                Toast.makeText(LikeCollectActivity.this, msg, Toast.LENGTH_SHORT).show();
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

