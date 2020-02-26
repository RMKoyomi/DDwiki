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

import java.util.List;

public class HoloAdapter extends RecyclerView.Adapter<HoloAdapter.ViewHolder> {
    private Context mContext;
    private List<Hololive> mHololiveList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView holoImage;
        TextView holoName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            holoImage = (ImageView)view.findViewById(R.id.holo_image);
            holoName = (TextView)view.findViewById(R.id.holo_name);
        }
    }

    public HoloAdapter(List<Hololive> hololiveList){
        mHololiveList = hololiveList;
    }

    //详细百科页面
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.holo_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Hololive hololive = mHololiveList.get(position);
                Intent intent = new Intent(mContext, HoloActivity.class);
                intent.putExtra(HoloActivity.HOLO_NAME, hololive.getName());
                intent.putExtra(HoloActivity.HOLO_IMAGE_ID, hololive.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Hololive hololive = mHololiveList.get(position);
        holder.holoName.setText(hololive.getName());
        Glide.with(mContext).load(hololive.getImageId()).into(holder.holoImage);
    }
    @Override
    public int getItemCount(){
        return mHololiveList.size();
    }

}
