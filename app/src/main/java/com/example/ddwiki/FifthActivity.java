package com.example.ddwiki;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ddwiki.adapters.OtherAdapter;
import com.example.ddwiki.otherpage.AboutActivity;
import com.example.ddwiki.otherpage.MainpageActivity;
import com.example.ddwiki.R;
import com.example.ddwiki.Vtbclass.Others;
import com.example.ddwiki.db.Vtubers;
import com.example.ddwiki.search.SearchActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class FifthActivity extends MainActivity {

    private DrawerLayout mDrawerLayout;

    /*
    private Others[] others = {new Others("猫宫日向",R.drawable.hinata_pic),new Others("犬山玉姬",
            R.drawable.tamaki_pic),
            new Others("YuNi",R.drawable.yuni_pic),new Others("周防帕托拉",
            R.drawable.patora_pic),
            new Others("因幡Haneru",R.drawable.haneru_pic),new Others("宇森雏子",
            R.drawable.hinako_pic),
            new Others("织田信姬",R.drawable.hime_pic)};*/

    private List<Others> othersList = new ArrayList<>();

    private OtherAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        //点击打开滑动菜单
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout5);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view5);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_menu);
        }
        //滑动菜单的点击事件
        navView.setCheckedItem(R.id.nav_other);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intent5 = new Intent(FifthActivity.this, MainpageActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.nav_holo:
                        Intent intent = new Intent(FifthActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_nijisanji:
                        Intent intent2 = new Intent(FifthActivity.this,SecondActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_independent:
                        Intent intent3 = new Intent(FifthActivity.this,ThirdActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_four:
                        Intent intent4 = new Intent(FifthActivity.this,ForthActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.nav_other:
                        break;
                    default:
                        break;

                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab5);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FifthActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


        initOTHER();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView5);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OtherAdapter(othersList);
        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh5);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshOTHER();
            }
        });

    }

    private void refreshOTHER(){
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
                        initOTHER();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initOTHER(){
        /**
         * 数据库操作
         */
        Connector.getDatabase();
        String[] namestr = new String[9];
        int[] imagestr = new int[9];
        Vtubers vtubers60 = new Vtubers();
        vtubers60.setName("猫宫日向");
        vtubers60.setImageId(R.drawable.hinata_pic);
        vtubers60.save();
        namestr[0] = vtubers60.getName();
        imagestr[0] = vtubers60.getImageId();
        Vtubers vtubers61 = new Vtubers();
        vtubers61.setName("犬山玉姬");
        vtubers61.setImageId(R.drawable.tamaki_pic);
        vtubers61.save();
        namestr[1] = vtubers61.getName();
        imagestr[1] = vtubers61.getImageId();
        Vtubers vtubers62 = new Vtubers();
        vtubers62.setName("YuNi");
        vtubers62.setImageId(R.drawable.yuni_pic);
        vtubers62.save();
        namestr[2] = vtubers62.getName();
        imagestr[2] = vtubers62.getImageId();
        Vtubers vtubers63 = new Vtubers();
        vtubers63.setName("周防帕托拉");
        vtubers63.setImageId(R.drawable.patora_pic);
        vtubers63.save();
        namestr[3] = vtubers63.getName();
        imagestr[3] = vtubers63.getImageId();
        Vtubers vtubers64 = new Vtubers();
        vtubers64.setName("因幡Haneru");
        vtubers64.setImageId(R.drawable.haneru_pic);
        vtubers64.save();
        namestr[4] = vtubers64.getName();
        imagestr[4] = vtubers64.getImageId();
        Vtubers vtubers65 = new Vtubers();
        vtubers65.setName("宇森雏子");
        vtubers65.setImageId(R.drawable.hinako_pic);
        vtubers65.save();
        namestr[5] = vtubers65.getName();
        imagestr[5] = vtubers65.getImageId();
        Vtubers vtubers66 = new Vtubers();
        vtubers66.setName("织田信姬");
        vtubers66.setImageId(R.drawable.hime_pic);
        vtubers66.save();
        namestr[6] = vtubers66.getName();
        imagestr[6] = vtubers66.getImageId();
        Vtubers vtubers67 = new Vtubers();
        vtubers67.setName("鹿乃(虚拟UP主)");
        vtubers67.setImageId(R.drawable.kano_pic);
        vtubers67.save();
        namestr[7] = vtubers67.getName();
        imagestr[7] = vtubers67.getImageId();
        Vtubers vtubers68 = new Vtubers();
        vtubers68.setName("花丸晴琉");
        vtubers68.setImageId(R.drawable.seiryu_pic);
        vtubers68.save();
        namestr[8] = vtubers68.getName();
        imagestr[8] = vtubers68.getImageId();

        Others[] others = {new Others(namestr[0],imagestr[0]),new Others(namestr[1],imagestr[1]),
                new Others(namestr[2],imagestr[2]),new Others(namestr[3],imagestr[3]),
                new Others(namestr[4],imagestr[4]),new Others(namestr[5],imagestr[5]),
                new Others(namestr[6],imagestr[6]),new Others(namestr[7],imagestr[7]),
                new Others(namestr[8],imagestr[8])};
        othersList.clear();
        for (int i = 0;i<9;i++){
            othersList.add(others[i]);
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
                Intent intent2 = new Intent(FifthActivity.this, AboutActivity.class);
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
                Toast.makeText(FifthActivity.this, msg, Toast.LENGTH_SHORT).show();
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

