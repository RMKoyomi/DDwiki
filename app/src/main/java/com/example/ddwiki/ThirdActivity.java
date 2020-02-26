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

import com.example.ddwiki.adapters.IndAdapter;
import com.example.ddwiki.otherpage.MainpageActivity;
import com.example.ddwiki.R;
import com.example.ddwiki.Vtbclass.Independent;
import com.example.ddwiki.db.Vtubers;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends MainActivity {

    private DrawerLayout mDrawerLayout;

    /*
    private Independent[] ind = {new Independent("Paryi",R.drawable.paryi_pic),new Independent("Yumeno_Shiori",
            R.drawable.shiori_pic),
            new Independent("神乐Mea",R.drawable.mea_pic),new Independent("千草Hana",
            R.drawable.hana_pic),
            new Independent("森永Miu",R.drawable.miu_pic),new Independent("高槻律",
            R.drawable.ritu_pic),
            new Independent("花园Serena",R.drawable.serena_pic),new Independent("名取纱那",
            R.drawable.sana_pic),
            new Independent("时雨羽衣",R.drawable.shigure_pic),new Independent("神乐七奈",
            R.drawable.nana_pic),};*/

    private List<Independent> indList = new ArrayList<>();

    private IndAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        //点击打开滑动菜单
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout3);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view3);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_menu);
        }
        //滑动菜单的点击事件
        navView.setCheckedItem(R.id.nav_independent);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intent5 = new Intent(ThirdActivity.this, MainpageActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.nav_holo:
                        Intent intent = new Intent(ThirdActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_nijisanji:
                        Intent intent2 = new Intent(ThirdActivity.this, SecondActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_independent:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_four:
                        Intent intent3 = new Intent(ThirdActivity.this, ForthActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_other:
                        Intent intent4 = new Intent(ThirdActivity.this, FifthActivity.class);
                        startActivity(intent4);
                        break;
                    default:
                        break;

                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"你点开了一个查找功能",Snackbar.LENGTH_SHORT)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(ThirdActivity.this,"取消查找",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });


        initIND();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView3);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new IndAdapter(indList);
        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh3);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshIND();
            }
        });

    }

    private void refreshIND(){
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
                        initIND();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initIND(){
        /**
         * 数据库操作
         */
        Connector.getDatabase();
        String[] namestr = new String[10];
        int[] imagestr = new int[10];
        Vtubers vtubers45 = new Vtubers();
        vtubers45.setName("Paryi");
        vtubers45.setImageId(R.drawable.paryi_pic);
        vtubers45.save();
        namestr[0] = vtubers45.getName();
        imagestr[0] = vtubers45.getImageId();
        Vtubers vtubers46 = new Vtubers();
        vtubers46.setName("Yumeno_Shiori");
        vtubers46.setImageId(R.drawable.shiori_pic);
        vtubers46.save();
        namestr[1] = vtubers46.getName();
        imagestr[1] = vtubers46.getImageId();
        Vtubers vtubers47 = new Vtubers();
        vtubers47.setName("神乐Mea");
        vtubers47.setImageId(R.drawable.mea_pic);
        vtubers47.save();
        namestr[2] = vtubers47.getName();
        imagestr[2] = vtubers47.getImageId();
        Vtubers vtubers48 = new Vtubers();
        vtubers48.setName("千草Hana");
        vtubers48.setImageId(R.drawable.hana_pic);
        vtubers48.save();
        namestr[3] = vtubers48.getName();
        imagestr[3] = vtubers48.getImageId();
        Vtubers vtubers49 = new Vtubers();
        vtubers49.setName("森永Miu");
        vtubers49.setImageId(R.drawable.miu_pic);
        vtubers49.save();
        namestr[4] = vtubers49.getName();
        imagestr[4] = vtubers49.getImageId();
        Vtubers vtubers50 = new Vtubers();
        vtubers50.setName("高槻律");
        vtubers50.setImageId(R.drawable.ritu_pic);
        vtubers50.save();
        namestr[5] = vtubers50.getName();
        imagestr[5] = vtubers50.getImageId();
        Vtubers vtubers51 = new Vtubers();
        vtubers51.setName("花园Serena");
        vtubers51.setImageId(R.drawable.serena_pic);
        vtubers51.save();
        namestr[6] = vtubers51.getName();
        imagestr[6] = vtubers51.getImageId();
        Vtubers vtubers52 = new Vtubers();
        vtubers52.setName("名取纱那");
        vtubers52.setImageId(R.drawable.sana_pic);
        vtubers52.save();
        namestr[7] = vtubers52.getName();
        imagestr[7] = vtubers52.getImageId();
        Vtubers vtubers53 = new Vtubers();
        vtubers53.setName("时雨羽衣");
        vtubers53.setImageId(R.drawable.shigure_pic);
        vtubers53.save();
        namestr[8] = vtubers53.getName();
        imagestr[8] = vtubers53.getImageId();
        Vtubers vtubers54 = new Vtubers();
        vtubers54.setName("神乐七奈");
        vtubers54.setImageId(R.drawable.nana_pic);
        vtubers54.save();
        namestr[9] = vtubers54.getName();
        imagestr[9] = vtubers54.getImageId();

        Independent[] ind = {new Independent(namestr[0],imagestr[0]),new Independent(namestr[1],imagestr[1]),
                new Independent(namestr[2],imagestr[2]),new Independent(namestr[3],imagestr[3]),
                new Independent(namestr[4],imagestr[4]),new Independent(namestr[5],imagestr[5]),
                new Independent(namestr[6],imagestr[6]),new Independent(namestr[7],imagestr[7]),
                new Independent(namestr[8],imagestr[8]),new Independent(namestr[9],imagestr[9])};
        indList.clear();
        for (int i = 0;i<10;i++){
            indList.add(ind[i]);
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
            /*
            case R.id.delete:
                Toast.makeText(this,"You clicked Delete",
                        Toast.LENGTH_SHORT).show();
                break;*/
            case R.id.settings:
                Toast.makeText(this,"You clicked Settings",
                        Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ThirdActivity.this, msg, Toast.LENGTH_SHORT).show();
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

