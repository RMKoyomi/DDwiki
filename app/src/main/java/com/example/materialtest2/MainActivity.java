package com.example.materialtest2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
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

import com.example.materialtest2.adapters.HoloAdapter;
import com.example.materialtest2.Vtbclass.Hololive;
import com.example.materialtest2.db.Vtubers;
import com.example.materialtest2.otherpage.MainpageActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    //private Hololive[] hololives = new Hololive[];

    /*
    private Hololive[] hololives = {new Hololive("时乃空",R.drawable.sora_pic),new Hololive("樱巫女",
            R.drawable.miko_pic),
            new Hololive("萝卜子",R.drawable.roboko_pic),new Hololive("星街彗星",
            R.drawable.suisei_pic),
            new Hololive("夜空梅露",R.drawable.mel_pic),new Hololive("夏色祭",
            R.drawable.maturi_pic),
            new Hololive("赤井心",R.drawable.haato_pic),new Hololive("亚绮·罗森塔尔",
            R.drawable.aki_pic),
            new Hololive("白上吹雪",R.drawable.fbk_pic),new Hololive("湊阿库娅",
            R.drawable.aqua_pic),
            new Hololive("紫咲诗音",R.drawable.shion_pic),new Hololive("百鬼绫目",
            R.drawable.ayame_pic),
            new Hololive("癒月巧可",R.drawable.choco_pic),new Hololive("大空昴",
            R.drawable.subaru_pic),
            new Hololive("兔田佩克拉",R.drawable.peko_pic),new Hololive("润羽露西亚",
            R.drawable.rushia_pic),
            new Hololive("不知火芙蕾雅",R.drawable.furea_pic),new Hololive("白银诺艾尔",
            R.drawable.noeru_pic),
            new Hololive("宝钟玛琳",R.drawable.marine_pic),new Hololive("天音彼方",
            R.drawable.kanata_pic),
            new Hololive("桐生可可",R.drawable.coco_pic),new Hololive("角卷绵芽",
            R.drawable.watame_pic),
            new Hololive("常暗永远",R.drawable.towa_pic),new Hololive("姬森璐娜",
            R.drawable.luna_pic),
            new Hololive("大神澪",R.drawable.mio_pic),new Hololive("猫又小粥",
            R.drawable.okayu_pic),
            new Hololive("戌神沁音",R.drawable.korone_pic)};*/

    private List<Hololive> hololiveList = new ArrayList<>();

    private HoloAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //点击打开滑动菜单
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_menu);
        }
        //滑动菜单的点击事件
        navView.setCheckedItem(R.id.nav_holo);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intent5 = new Intent(MainActivity.this, MainpageActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.nav_holo:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_nijisanji:
                        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_independent:
                        Intent intent2 = new Intent(MainActivity.this,ThirdActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.nav_four:
                        Intent intent3 = new Intent(MainActivity.this,ForthActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_other:
                        Intent intent4 = new Intent(MainActivity.this,FifthActivity.class);
                        startActivity(intent4);
                        break;
                        default:
                            break;

                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"你点开了一个查找功能",Snackbar.LENGTH_SHORT)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,"取消查找",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        initFruits();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HoloAdapter(hololiveList);
        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
        ActivityCollector.addActivity(this);
    }

    private void refreshFruits(){
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
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits(){
        /**
         * 数据库操作
         */
        Connector.getDatabase();
        String[] namestr = new String[27];
        int[] imagestr = new int[27];
        Vtubers vtubers1 = new Vtubers();
        vtubers1.setName("时乃空");
        vtubers1.setImageId(R.drawable.sora_pic);
        vtubers1.save();
        namestr[0] = vtubers1.getName();
        imagestr[0] = vtubers1.getImageId();
        Vtubers vtubers2 = new Vtubers();
        vtubers2.setName("樱巫女");
        vtubers2.setImageId(R.drawable.miko_pic);
        vtubers2.save();
        namestr[1] = vtubers2.getName();
        imagestr[1] = vtubers2.getImageId();
        Vtubers vtubers3 = new Vtubers();
        vtubers3.setName("萝卜子");
        vtubers3.setImageId(R.drawable.roboko_pic);
        vtubers3.save();
        namestr[2] = vtubers3.getName();
        imagestr[2] = vtubers3.getImageId();
        Vtubers vtubers4 = new Vtubers();
        vtubers4.setName("星街彗星");
        vtubers4.setImageId(R.drawable.suisei_pic);
        vtubers4.save();
        namestr[3] = vtubers4.getName();
        imagestr[3] = vtubers4.getImageId();
        Vtubers vtubers5 = new Vtubers();
        vtubers5.setName("夜空梅露");
        vtubers5.setImageId(R.drawable.mel_pic);
        vtubers5.save();
        namestr[4] = vtubers5.getName();
        imagestr[4] = vtubers5.getImageId();
        Vtubers vtubers6 = new Vtubers();
        vtubers6.setName("夏色祭");
        vtubers6.setImageId(R.drawable.maturi_pic);
        vtubers6.save();
        namestr[5] = vtubers6.getName();
        imagestr[5] = vtubers6.getImageId();
        Vtubers vtubers7 = new Vtubers();
        vtubers7.setName("赤井心");
        vtubers7.setImageId(R.drawable.haato_pic);
        vtubers7.save();
        namestr[6] = vtubers7.getName();
        imagestr[6] = vtubers7.getImageId();
        Vtubers vtubers8 = new Vtubers();
        vtubers8.setName("亚绮·罗森塔尔");
        vtubers8.setImageId(R.drawable.aki_pic);
        vtubers8.save();
        namestr[7] = vtubers8.getName();
        imagestr[7] = vtubers8.getImageId();
        Vtubers vtubers9 = new Vtubers();
        vtubers9.setName("白上吹雪");
        vtubers9.setImageId(R.drawable.fbk_pic);
        vtubers9.save();
        namestr[8] = vtubers9.getName();
        imagestr[8] = vtubers9.getImageId();
        Vtubers vtubers10 = new Vtubers();
        vtubers10.setName("湊阿库娅");
        vtubers10.setImageId(R.drawable.aqua_pic);
        vtubers10.save();
        namestr[9] = vtubers10.getName();
        imagestr[9] = vtubers10.getImageId();
        Vtubers vtubers11 = new Vtubers();
        vtubers11.setName("紫咲诗音");
        vtubers11.setImageId(R.drawable.shion_pic);
        vtubers11.save();
        namestr[10] = vtubers11.getName();
        imagestr[10] = vtubers11.getImageId();
        Vtubers vtubers12 = new Vtubers();
        vtubers12.setName("百鬼绫目");
        vtubers12.setImageId(R.drawable.ayame_pic);
        vtubers12.save();
        namestr[11] = vtubers12.getName();
        imagestr[11] = vtubers12.getImageId();
        Vtubers vtubers13 = new Vtubers();
        vtubers13.setName("癒月巧可");
        vtubers13.setImageId(R.drawable.choco_pic);
        vtubers13.save();
        namestr[12] = vtubers13.getName();
        imagestr[12] = vtubers13.getImageId();
        Vtubers vtubers14 = new Vtubers();
        vtubers14.setName("大空昴");
        vtubers14.setImageId(R.drawable.subaru_pic);
        vtubers14.save();
        namestr[13] = vtubers13.getName();
        imagestr[13] = vtubers13.getImageId();
        Vtubers vtubers15 = new Vtubers();
        vtubers15.setName("兔田佩克拉");
        vtubers15.setImageId(R.drawable.peko_pic);
        vtubers15.save();
        namestr[14] = vtubers15.getName();
        imagestr[14] = vtubers15.getImageId();
        Vtubers vtubers16 = new Vtubers();
        vtubers16.setName("润羽露西亚");
        vtubers16.setImageId(R.drawable.rushia_pic);
        vtubers16.save();
        namestr[15] = vtubers16.getName();
        imagestr[15] = vtubers16.getImageId();
        Vtubers vtubers17 = new Vtubers();
        vtubers17.setName("不知火芙蕾雅");
        vtubers17.setImageId(R.drawable.furea_pic);
        vtubers17.save();
        namestr[16] = vtubers17.getName();
        imagestr[16] = vtubers17.getImageId();
        Vtubers vtubers18 = new Vtubers();
        vtubers18.setName("白银诺艾尔");
        vtubers18.setImageId(R.drawable.noeru_pic);
        vtubers18.save();
        namestr[17] = vtubers18.getName();
        imagestr[17] = vtubers18.getImageId();
        Vtubers vtubers19 = new Vtubers();
        vtubers19.setName("宝钟玛琳");
        vtubers19.setImageId(R.drawable.marine_pic);
        vtubers19.save();
        namestr[18] = vtubers19.getName();
        imagestr[18] = vtubers19.getImageId();
        Vtubers vtubers20 = new Vtubers();
        vtubers20.setName("天音彼方");
        vtubers20.setImageId(R.drawable.kanata_pic);
        vtubers20.save();
        namestr[19] = vtubers20.getName();
        imagestr[19] = vtubers20.getImageId();
        Vtubers vtubers21 = new Vtubers();
        vtubers21.setName("桐生可可");
        vtubers21.setImageId(R.drawable.coco_pic);
        vtubers21.save();
        namestr[20] = vtubers21.getName();
        imagestr[20] = vtubers21.getImageId();
        Vtubers vtubers22 = new Vtubers();
        vtubers22.setName("角卷绵芽");
        vtubers22.setImageId(R.drawable.watame_pic);
        vtubers22.save();
        namestr[21] = vtubers22.getName();
        imagestr[21] = vtubers22.getImageId();
        Vtubers vtubers23 = new Vtubers();
        vtubers23.setName("常暗永远");
        vtubers23.setImageId(R.drawable.towa_pic);
        vtubers23.save();
        namestr[22] = vtubers23.getName();
        imagestr[22] = vtubers23.getImageId();
        Vtubers vtubers24 = new Vtubers();
        vtubers24.setName("姬森璐娜");
        vtubers24.setImageId(R.drawable.luna_pic);
        vtubers24.save();
        namestr[23] = vtubers24.getName();
        imagestr[23] = vtubers24.getImageId();
        Vtubers vtubers25 = new Vtubers();
        vtubers25.setName("大神澪");
        vtubers25.setImageId(R.drawable.mio_pic);
        vtubers25.save();
        namestr[24] = vtubers25.getName();
        imagestr[24] = vtubers25.getImageId();
        Vtubers vtubers26 = new Vtubers();
        vtubers26.setName("猫又小粥");
        vtubers26.setImageId(R.drawable.okayu_pic);
        vtubers26.save();
        namestr[25] = vtubers26.getName();
        imagestr[25] = vtubers26.getImageId();
        Vtubers vtubers27 = new Vtubers();
        vtubers27.setName("戌神沁音");
        vtubers27.setImageId(R.drawable.korone_pic);
        vtubers27.save();
        namestr[26] = vtubers27.getName();
        imagestr[26] = vtubers27.getImageId();

        Hololive[] hololives = {new Hololive(namestr[0],imagestr[0]),new Hololive(namestr[1],imagestr[1]),
                new Hololive(namestr[2],imagestr[2]),new Hololive(namestr[3],imagestr[3]),
                new Hololive(namestr[4],imagestr[4]),new Hololive(namestr[5],imagestr[5]),
                new Hololive(namestr[6],imagestr[6]),new Hololive(namestr[7],imagestr[7]),
                new Hololive(namestr[8],imagestr[8]),new Hololive(namestr[9],imagestr[9]),
                new Hololive(namestr[10],imagestr[10]),new Hololive(namestr[11],imagestr[11]),
                new Hololive(namestr[12],imagestr[12]),new Hololive(namestr[13],imagestr[13]),
                new Hololive(namestr[14],imagestr[14]),new Hololive(namestr[15],imagestr[15]),
                new Hololive(namestr[16],imagestr[16]),new Hololive(namestr[17],imagestr[17]),
                new Hololive(namestr[18],imagestr[18]),new Hololive(namestr[19],imagestr[19]),
                new Hololive(namestr[20],imagestr[20]),new Hololive(namestr[21],imagestr[21]),
                new Hololive(namestr[22],imagestr[22]),new Hololive(namestr[23],imagestr[23]),
                new Hololive(namestr[24],imagestr[24]),new Hololive(namestr[25],imagestr[25]),
                new Hololive(namestr[26],imagestr[26])};
        hololiveList.clear();
        for (int i = 0;i<27;i++){
            hololiveList.add(hololives[i]);
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

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


    //应用退出
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
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
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
