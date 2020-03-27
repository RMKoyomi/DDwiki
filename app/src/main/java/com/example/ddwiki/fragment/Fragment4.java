package com.example.ddwiki.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ddwiki.R;
import com.example.ddwiki.Vtbclass.Sort;
import com.example.ddwiki.adapters.sortAdapter;
import com.loopeer.cardstack.CardStackView;

import java.util.ArrayList;
import java.util.List;

public class Fragment4 extends Fragment {
    private TextView textView;
    private Button button;
    private CardStackView mStackView;

    private List<Sort> sortList = new ArrayList<>();

    private sortAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment4,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initSort();
        RecyclerView recyclerView = (RecyclerView)getActivity().findViewById(R.id.recyclerView2);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new sortAdapter(sortList);
        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout)getActivity().findViewById(R.id.swipe_refresh2);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshSort();
            }
        });

    }

    private void initSort(){
        Sort[] sorts = {new Sort("Hololive",R.drawable.ic_holoholo),new Sort("彩虹社",R.drawable.ic_njsjmenu),
                new Sort("个人势",R.drawable.mea_pic),new Sort("四大天王",R.drawable.ai_pic),
                new Sort("其他运营",R.drawable.ic_u8menu)};
        sortList.clear();
        for (int i = 0;i<5;i++){
            sortList.add(sorts[i]);
        }
    }

    private void refreshSort(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initSort();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
