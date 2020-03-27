package com.example.ddwiki.Implements;

import android.content.Context;

import com.example.ddwiki.Vtbclass.History;
import com.example.ddwiki.Vtbclass.Like;

public interface LikeCollector {
    void collect(Like like);

    void viewcollect(History history);
}
