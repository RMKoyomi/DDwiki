package com.example.ddwiki.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ddwiki.FifthActivity;
import com.example.ddwiki.ForthActivity;
import com.example.ddwiki.MainActivity;
import com.example.ddwiki.R;
import com.example.ddwiki.SecondActivity;
import com.example.ddwiki.ThirdActivity;
import com.example.ddwiki.Vtbclass.Sort;

import java.util.List;

public class sortAdapter extends RecyclerView.Adapter<sortAdapter.ViewHolder> {
    private Context mContext;
    private List<Sort> mSortList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView sortImage;
        TextView sortName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            sortImage = (ImageView)view.findViewById(R.id.sort_image);
            sortName = (TextView)view.findViewById(R.id.sort_name);
        }
    }

    public sortAdapter(List<Sort> sortList){
        mSortList = sortList;
    }

    //打开详细百科页面
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.sort_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = holder.getAdapterPosition();
                Sort sort = mSortList.get(position);
                Intent intent1 = new Intent(mContext, MainActivity.class);
                Intent intent2 = new Intent(mContext, SecondActivity.class);
                Intent intent3 = new Intent(mContext, ThirdActivity.class);
                Intent intent4 = new Intent(mContext, ForthActivity.class);
                Intent intent5 = new Intent(mContext, FifthActivity.class);
                /*intent.putExtra(EnterpriseActivity.ENTER_NAME, sort.getName());
                intent.putExtra(EnterpriseActivity.ENTER_IMAGE_ID, sort.getImageId());
                mContext.startActivity(intent);
                */
                if(sort.getName()=="Hololive"){
                    mContext.startActivity(intent1);
                }
                else if(sort.getName()=="彩虹社"){
                    mContext.startActivity(intent2);
                }
                else if(sort.getName()=="个人势"){
                    mContext.startActivity(intent3);
                }
                else if(sort.getName()=="四大天王"){
                    mContext.startActivity(intent4);
                }
                else if(sort.getName()=="其他运营"){
                    mContext.startActivity(intent5);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Sort sort = mSortList.get(position);
        holder.sortName.setText(sort.getName());
        Glide.with(mContext).load(sort.getImageId()).into(holder.sortImage);
    }
    @Override
    public int getItemCount(){
        return mSortList.size();
    }

}
