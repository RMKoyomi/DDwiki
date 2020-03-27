package com.example.ddwiki;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ddwiki.Vtbclass.NJSJ;
import com.example.ddwiki.adapters.NJSJAdapter;
import com.example.ddwiki.otherpage.AboutActivity;
import com.example.ddwiki.otherpage.IntroduceActivity;
import com.example.ddwiki.otherpage.MainpageActivity;
import com.example.ddwiki.R;
import com.example.ddwiki.db.Vtubers;
import com.example.ddwiki.search.SearchActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends MainActivity {

    private DrawerLayout mDrawerLayout;

    /*
    private NJSJ[] njsjs = {new NJSJ("月之美兔",R.drawable.mito_pic),new NJSJ("樋口枫",
            R.drawable.kaede_pic),
            new NJSJ("静凛",R.drawable.rin_pic),new NJSJ("物述有栖",
            R.drawable.alice_pic),
            new NJSJ("宇志海莓",R.drawable.ichigo_pic),new NJSJ("森中花咲",
            R.drawable.kazaki_pic),
            new NJSJ("叶",R.drawable.kanae_pic),new NJSJ("笹木咲",
            R.drawable.saki_pic),
            new NJSJ("本间向日葵",R.drawable.himawari_pic),new NJSJ("椎名唯华",
            R.drawable.shina_pic),
            new NJSJ("修女克蕾雅",R.drawable.cleaire_pic),new NJSJ("德比德比·德比鲁",
            R.drawable.debiru_pic),
            new NJSJ("童田明治",R.drawable.meiji_pic),new NJSJ("郡道美玲",
            R.drawable.miren_pic),
            new NJSJ("御伽原江良",R.drawable.gibara_pic),new NJSJ("贝尔蒙德·班德拉斯",
            R.drawable.belmond_pic),
            new NJSJ("绿仙",R.drawable.rikusen_pic)};*/

    private List<NJSJ> njsjList = new ArrayList<>();

    private NJSJAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        //点击打开滑动菜单
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout2);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view2);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //滑动菜单的点击事件
        navView.setCheckedItem(R.id.nav_nijisanji);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intent5 = new Intent(SecondActivity.this, MainpageActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.nav_holo:
                        Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_nijisanji:
                        break;
                    case R.id.nav_independent:
                        Intent intent2 = new Intent(SecondActivity.this,ThirdActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_four:
                        Intent intent3 = new Intent(SecondActivity.this, ForthActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_other:
                        Intent intent4 = new Intent(SecondActivity.this, FifthActivity.class);
                        startActivity(intent4);
                        break;
                    default:
                        break;

                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


        initNJSJ();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView2);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NJSJAdapter(njsjList);
        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh2);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshNJSJ();
            }
        });

    }

    private void refreshNJSJ(){
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
                        initNJSJ();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initNJSJ(){
        /**
         * 数据库操作
         */
        Connector.getDatabase();
        String[] namestr = new String[17];
        int[] imagestr = new int[17];
        Vtubers vtubers28 = new Vtubers();
        vtubers28.setName("月之美兔");
        vtubers28.setImageId(R.drawable.mito_pic);
        vtubers28.save();
        namestr[0] = vtubers28.getName();
        imagestr[0] = vtubers28.getImageId();
        Vtubers vtubers29 = new Vtubers();
        vtubers29.setName("樋口枫");
        vtubers29.setImageId(R.drawable.kaede_pic);
        vtubers29.save();
        namestr[1] = vtubers29.getName();
        imagestr[1] = vtubers29.getImageId();
        Vtubers vtubers30 = new Vtubers();
        vtubers30.setName("静凛");
        vtubers30.setImageId(R.drawable.rin_pic);
        vtubers30.save();
        namestr[2] = vtubers30.getName();
        imagestr[2] = vtubers30.getImageId();
        Vtubers vtubers31 = new Vtubers();
        vtubers31.setName("物述有栖");
        vtubers31.setImageId(R.drawable.alice_pic);
        vtubers31.save();
        namestr[3] = vtubers31.getName();
        imagestr[3] = vtubers31.getImageId();
        Vtubers vtubers32 = new Vtubers();
        vtubers32.setName("宇志海莓");
        vtubers32.setImageId(R.drawable.ichigo_pic);
        vtubers32.save();
        namestr[4] = vtubers32.getName();
        imagestr[4] = vtubers32.getImageId();
        Vtubers vtubers33 = new Vtubers();
        vtubers33.setName("森中花咲");
        vtubers33.setImageId(R.drawable.kazaki_pic);
        vtubers33.save();
        namestr[5] = vtubers33.getName();
        imagestr[5] = vtubers33.getImageId();
        Vtubers vtubers34 = new Vtubers();
        vtubers34.setName("叶");
        vtubers34.setImageId(R.drawable.kanae_pic);
        vtubers34.save();
        namestr[6] = vtubers34.getName();
        imagestr[6] = vtubers34.getImageId();
        Vtubers vtubers35 = new Vtubers();
        vtubers35.setName("笹木咲");
        vtubers35.setImageId(R.drawable.saki_pic);
        vtubers35.save();
        namestr[7] = vtubers35.getName();
        imagestr[7] = vtubers35.getImageId();
        Vtubers vtubers36 = new Vtubers();
        vtubers36.setName("本间向日葵");
        vtubers36.setImageId(R.drawable.himawari_pic);
        vtubers36.save();
        namestr[8] = vtubers36.getName();
        imagestr[8] = vtubers36.getImageId();
        Vtubers vtubers37 = new Vtubers();
        vtubers37.setName("椎名唯华");
        vtubers37.setImageId(R.drawable.shina_pic);
        vtubers37.save();
        namestr[9] = vtubers37.getName();
        imagestr[9] = vtubers37.getImageId();
        Vtubers vtubers38 = new Vtubers();
        vtubers38.setName("修女克蕾雅");
        vtubers38.setImageId(R.drawable.cleaire_pic);
        vtubers38.save();
        namestr[10] = vtubers38.getName();
        imagestr[10] = vtubers38.getImageId();
        Vtubers vtubers39 = new Vtubers();
        vtubers39.setName("德比德比·德比鲁");
        vtubers39.setImageId(R.drawable.debiru_pic);
        vtubers39.save();
        namestr[11] = vtubers39.getName();
        imagestr[11] = vtubers39.getImageId();
        Vtubers vtubers40 = new Vtubers();
        vtubers40.setName("童田明治");
        vtubers40.setImageId(R.drawable.meiji_pic);
        vtubers40.save();
        namestr[12] = vtubers40.getName();
        imagestr[12] = vtubers40.getImageId();
        Vtubers vtubers41 = new Vtubers();
        vtubers41.setName("郡道美玲");
        vtubers41.setImageId(R.drawable.miren_pic);
        vtubers41.save();
        namestr[13] = vtubers41.getName();
        imagestr[13] = vtubers41.getImageId();
        Vtubers vtubers42 = new Vtubers();
        vtubers42.setName("御伽原江良");
        vtubers42.setImageId(R.drawable.gibara_pic);
        vtubers42.save();
        namestr[14] = vtubers42.getName();
        imagestr[14] = vtubers42.getImageId();
        Vtubers vtubers43 = new Vtubers();
        vtubers43.setName("贝尔蒙德·班德拉斯");
        vtubers43.setImageId(R.drawable.belmond_pic);
        vtubers43.save();
        namestr[15] = vtubers43.getName();
        imagestr[15] = vtubers43.getImageId();
        Vtubers vtubers44 = new Vtubers();
        vtubers44.setName("绿仙");
        vtubers44.setImageId(R.drawable.rikusen_pic);
        vtubers44.save();
        namestr[16] = vtubers44.getName();
        imagestr[16] = vtubers44.getImageId();

        NJSJ[] njsjs = {new NJSJ(namestr[0],imagestr[0]),new NJSJ(namestr[1],imagestr[1]),
                new NJSJ(namestr[2],imagestr[2]),new NJSJ(namestr[3],imagestr[3]),
                new NJSJ(namestr[4],imagestr[4]),new NJSJ(namestr[5],imagestr[5]),
                new NJSJ(namestr[6],imagestr[6]),new NJSJ(namestr[7],imagestr[7]),
                new NJSJ(namestr[8],imagestr[8]),new NJSJ(namestr[9],imagestr[9]),
                new NJSJ(namestr[10],imagestr[10]),new NJSJ(namestr[11],imagestr[11]),
                new NJSJ(namestr[12],imagestr[12]),new NJSJ(namestr[13],imagestr[13]),
                new NJSJ(namestr[14],imagestr[14]),new NJSJ(namestr[15],imagestr[15]),
                new NJSJ(namestr[16],imagestr[16])};
        njsjList.clear();
        for (int i = 0;i<17;i++){
            njsjList.add(njsjs[i]);
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
                Intent intent2 = new Intent(SecondActivity.this, AboutActivity.class);
                startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.settings2:
                Intent intent3 = new Intent(SecondActivity.this, IntroduceActivity.class);
                startActivity(intent3);
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
                Toast.makeText(SecondActivity.this, msg, Toast.LENGTH_SHORT).show();
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

