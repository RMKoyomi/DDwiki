package com.example.ddwiki.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ddwiki.R;
import com.example.ddwiki.activities.SearchResultActivity;
import com.example.ddwiki.db.Vtubers;
import com.example.ddwiki.search.OnSearchListener;

import org.litepal.crud.DataSupport;

import java.util.List;


public class SearchActivity extends AppCompatActivity implements OnSearchListener {

    private SearchView searchView;
    EditText mEditSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = (SearchView)findViewById(R.id.sv_default);
        searchView.setOnSearchListener(this);
    }

    @Override
    public void search(String content){
        List<Vtubers> vtubers = DataSupport.where("name = ?",content).find(Vtubers.class);
        if(vtubers.size()!=0) {
            //Toast.makeText(this, "搜索内容： " + vtubers.get(0), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
            intent.putExtra("name",content);
            startActivity(intent);
        }else {
            Toast.makeText(this,"在数据库没有查找到该词条的百科",Toast.LENGTH_SHORT).show();
        }
       // Toast.makeText(this,"搜索内容： "+content,Toast.LENGTH_SHORT).show();
    }
}
