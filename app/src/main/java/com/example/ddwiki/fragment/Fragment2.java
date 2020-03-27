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
import com.example.ddwiki.Vtbclass.Enterprise;
import com.example.ddwiki.adapters.EnterAdapter;
import com.example.ddwiki.db.Enterprises;
import com.loopeer.cardstack.CardStackView;

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {
    private TextView textView;
    private Button button;
    private CardStackView mStackView;

    private List<Enterprise> enterpriseList = new ArrayList<>();

    private EnterAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment2,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initEnter();
        RecyclerView recyclerView = (RecyclerView)getActivity().findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EnterAdapter(enterpriseList);
        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout)getActivity().findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshEnter();
            }
        });

    }

    private void initEnter(){
        /**
         * 数据库操作
         */
        Connector.getDatabase();
        String[] namestr = new String[5];
        int[] imagestr = new int[5];
        Enterprises enterprise1 = new Enterprises();
        enterprise1.setName("hololive");
        enterprise1.setImageId(R.drawable.ic_holoholo);
        enterprise1.save();
        namestr[0] = enterprise1.getName();
        imagestr[0] = enterprise1.getImageId();
        Enterprises enterprise2 = new Enterprises();
        enterprise2.setName("彩虹社");
        enterprise2.setImageId(R.drawable.ic_njsjmenu);
        enterprise2.save();
        namestr[1] = enterprise2.getName();
        imagestr[1] = enterprise2.getImageId();
        Enterprises enterprise3= new Enterprises();
        enterprise3.setName("Upd8");
        enterprise3.setImageId(R.drawable.ic_u8menu);
        enterprise3.save();
        namestr[2] = enterprise3.getName();
        imagestr[2] = enterprise3.getImageId();
        Enterprises enterprise4= new Enterprises();
        enterprise4.setName(".LIVE");
        enterprise4.setImageId(R.drawable.ic_pointlivemenu);
        enterprise4.save();
        namestr[3] = enterprise4.getName();
        imagestr[3] = enterprise4.getImageId();
        Enterprises enterprise5= new Enterprises();
        enterprise5.setName("Overidea");
        enterprise5.setImageId(R.drawable.ic_oideamenu);
        enterprise5.save();
        namestr[4] = enterprise5.getName();
        imagestr[4] = enterprise5.getImageId();


        Enterprise[] enterprises = {new Enterprise(namestr[0],imagestr[0]),new Enterprise(namestr[1],imagestr[1]),
                new Enterprise(namestr[2],imagestr[2]),new Enterprise(namestr[3],imagestr[3]),
                new Enterprise(namestr[4],imagestr[4])};
        enterpriseList.clear();
        for (int i = 0;i<5;i++){
            enterpriseList.add(enterprises[i]);
        }
    }

    private void refreshEnter(){
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
                        initEnter();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
