package com.example.materialtest2;

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

import com.example.materialtest2.adapters.FourAdapter;
import com.example.materialtest2.Vtbclass.Four;
import com.example.materialtest2.db.Vtubers;
import com.example.materialtest2.otherpage.MainpageActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class ForthActivity extends MainActivity {

    private DrawerLayout mDrawerLayout;

    /*
    private Four[] four = {new Four("绊爱",R.drawable.ai_pic),new Four("辉夜月",
            R.drawable.kaguya_pic),
            new Four("未来明",R.drawable.akari_pic),new Four("电脑少女Siro",
            R.drawable.siro_pic),
            new Four("Nekomasu",R.drawable.nekomasu_pic)};*/

    private List<Four> fourList = new ArrayList<>();

    private FourAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        //点击打开滑动菜单
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout4);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view4);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_menu);
        }
        //滑动菜单的点击事件
        navView.setCheckedItem(R.id.nav_four);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intent5 = new Intent(ForthActivity.this, MainpageActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.nav_holo:
                        Intent intent = new Intent(ForthActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_nijisanji:
                        Intent intent2 = new Intent(ForthActivity.this,SecondActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_independent:
                        Intent intent3 = new Intent(ForthActivity.this,ThirdActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_four:
                        break;
                    case R.id.nav_other:
                        Intent intent4 = new Intent(ForthActivity.this,FifthActivity.class);
                        startActivity(intent4);
                        break;
                    default:
                        break;

                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab4);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"你点开了一个查找功能",Snackbar.LENGTH_SHORT)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(ForthActivity.this,"取消查找",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });


        initFOUR();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView4);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FourAdapter(fourList);
        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh4);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFOUR();
            }
        });

    }

    private void refreshFOUR(){
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
                        initFOUR();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFOUR(){
        /**
         * 数据库操作
         */
        Connector.getDatabase();
        String[] namestr = new String[5];
        int[] imagestr = new int[5];
        Vtubers vtubers55 = new Vtubers();
        vtubers55.setName("绊爱");
        vtubers55.setImageId(R.drawable.ai_pic);
        vtubers55.save();
        namestr[0] = vtubers55.getName();
        imagestr[0] = vtubers55.getImageId();
        Vtubers vtubers56 = new Vtubers();
        vtubers56.setName("辉夜月");
        vtubers56.setImageId(R.drawable.kaguya_pic);
        vtubers56.save();
        namestr[1] = vtubers56.getName();
        imagestr[1] = vtubers56.getImageId();
        Vtubers vtubers57 = new Vtubers();
        vtubers57.setName("未来明");
        vtubers57.setImageId(R.drawable.akari_pic);
        vtubers57.save();
        namestr[2] = vtubers57.getName();
        imagestr[2] = vtubers57.getImageId();
        Vtubers vtubers58 = new Vtubers();
        vtubers58.setName("电脑少女Siro");
        vtubers58.setImageId(R.drawable.siro_pic);
        vtubers58.save();
        namestr[3] = vtubers58.getName();
        imagestr[3] = vtubers58.getImageId();
        Vtubers vtubers59 = new Vtubers();
        vtubers59.setName("Nekomasu");
        vtubers59.setImageId(R.drawable.nekomasu_pic);
        vtubers59.save();
        namestr[4] = vtubers59.getName();
        imagestr[4] = vtubers59.getImageId();

         Four[] four = {new Four(namestr[0],imagestr[0]),new Four(namestr[1],imagestr[1]),
                 new Four(namestr[2],imagestr[2]),new Four(namestr[3],imagestr[3]),
                 new Four(namestr[4],imagestr[4])};
        fourList.clear();
        for (int i = 0;i<5;i++){
            fourList.add(four[i]);
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
                Toast.makeText(ForthActivity.this, msg, Toast.LENGTH_SHORT).show();
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

