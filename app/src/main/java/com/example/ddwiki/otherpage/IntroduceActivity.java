package com.example.ddwiki.otherpage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.example.ddwiki.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class IntroduceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        //getWindow().setEnterTransition(explode);
        setContentView(R.layout.activity_introduce);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        ActionBar actionBar = getSupportActionBar();
        collapsingToolbar.setTitle("什么是虚拟YouTuber？");
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
