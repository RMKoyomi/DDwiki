package com.example.ddwiki.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ddwiki.R;
import com.example.ddwiki.activities.SearchResultActivity;
import com.example.ddwiki.db.Vtubers;
import com.example.ddwiki.search.OnSearchListener;

import org.litepal.crud.DataSupport;

import java.util.List;


public class SearchActivity extends AppCompatActivity implements OnSearchListener {

    private SearchView searchView;
    private Button backbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = (SearchView)findViewById(R.id.sv_default);
        searchView.setOnSearchListener(this);
        backbutton = (Button)findViewById(R.id.back_button);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void search(final String content){
        String[] str = {"搜索："+ content};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                SearchActivity.this,android.R.layout.simple_list_item_1,str
        );
        ListView listView = (ListView) findViewById(R.id.search_target);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        search(content);
                        break;
                }
            }
        });
        List<Vtubers> vtubers = DataSupport.where("name = ?",content).find(Vtubers.class);
        if(vtubers.size()!=0) {
            //Toast.makeText(this, "搜索内容： " + vtubers.get(0), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
            intent.putExtra("name",content);
            intent.putExtra("image_id",R.drawable.searchbackground_pic);
            startActivity(intent);
        }else {
            Toast.makeText(this,"在数据库没有查找到该词条的百科，现在跳转到互联网查找...",Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(SearchActivity.this, SearchResultActivity.class);
            intent2.putExtra("name",content);
            intent2.putExtra("image_id",R.drawable.searchbackground_pic);
            startActivity(intent2);
        }
       // Toast.makeText(this,"搜索内容： "+content,Toast.LENGTH_SHORT).show();
    }
}
