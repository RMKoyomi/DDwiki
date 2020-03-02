package com.example.ddwiki.search;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public interface SearchExtendImpl {
    int getLayoutId();

    ImageView getImageView();

    EditText getEditText();

    ViewGroup getSearchFrame();
}
