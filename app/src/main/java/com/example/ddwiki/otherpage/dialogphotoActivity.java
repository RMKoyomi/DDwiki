package com.example.ddwiki.otherpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ddwiki.R;

public class dialogphotoActivity extends AppCompatActivity {

    private ImageView imageView;

    public static final String IMAGE_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_photo_entry);
        Intent intent = getIntent();
        int id = intent.getIntExtra(IMAGE_ID,0);
        imageView = (ImageView)findViewById(R.id.large_image);
        imageView.setImageResource(id);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
