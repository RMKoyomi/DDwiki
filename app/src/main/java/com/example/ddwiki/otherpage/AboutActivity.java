package com.example.ddwiki.otherpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ddwiki.R;

public class AboutActivity extends AppCompatActivity {
    private String[] data ={"当前版本：Version1.0.5","作者：Koyomi","版权信息：2020.03"};

    private Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                AboutActivity.this,android.R.layout.simple_list_item_1,data
        );
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        backbutton = (Button)findViewById(R.id.back_button);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
