package com.example.ddwiki.otherpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ddwiki.R;

public class AboutActivity extends AppCompatActivity {
    private String[] data ={"版本：Version1.0.2","作者：Koyomi","版权信息：2020.03"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                AboutActivity.this,android.R.layout.simple_list_item_1,data
        );
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
