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
import com.example.ddwiki.R;
import com.example.ddwiki.Vtbclass.Hololive;
import com.example.ddwiki.activities.HoloActivity;
import com.example.ddwiki.activities.SearchResultActivity;
import com.example.ddwiki.db.Vtubers;

import java.util.List;

public class SearResAdapter extends RecyclerView.Adapter<SearResAdapter.ViewHolder> {
    private Context mContext;
    private List<Vtubers> mVtbList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView Image;
        TextView Name;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            Image = (ImageView)view.findViewById(R.id.vtb_image);
            Name = (TextView)view.findViewById(R.id.vtb_name);
        }
    }

    public SearResAdapter(List<Vtubers> vtbList){
        mVtbList = vtbList;
    }

    //打开详细百科页面
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.result_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Vtubers vtubers = mVtbList.get(position);
                Intent intent = new Intent(mContext, HoloActivity.class);
                intent.putExtra(SearchResultActivity.NAME, vtubers.getName());
                intent.putExtra(SearchResultActivity.IMAGE_ID, vtubers.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Vtubers vtubers = mVtbList.get(position);
        holder.Name.setText(vtubers.getName());
        Glide.with(mContext).load(vtubers.getImageId()).into(holder.Image);
    }
    @Override
    public int getItemCount(){
        return mVtbList.size();
    }

}
