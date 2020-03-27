package com.example.ddwiki.otherpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ddwiki.R;
import com.loopeer.cardstack.CardStackView;

public class companyActivity extends AppCompatActivity {

    private CardStackView mStackView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        mStackView = (CardStackView)findViewById(R.id.cardstack);

    }
}
