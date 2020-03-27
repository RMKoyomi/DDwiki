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
import com.example.ddwiki.Vtbclass.Like;
import com.example.ddwiki.activities.HoloActivity;
import com.example.ddwiki.activities.LikeActivity;

import java.util.List;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.ViewHolder> {
    private Context mContext;
    private List<Like> mLikeList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView likeImage;
        TextView likeName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            likeImage = (ImageView)view.findViewById(R.id.like_image);
            likeName = (TextView)view.findViewById(R.id.like_name);
        }
    }

    public LikeAdapter(List<Like> likeList){
        mLikeList = likeList;
    }

    //打开详细百科页面
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.like_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Like like = mLikeList.get(position);
                Intent intent = new Intent(mContext, LikeActivity.class);
                intent.putExtra(LikeActivity.LIKE_NAME, like.getName());
                intent.putExtra(LikeActivity.LIKE_IMAGE_ID, like.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Like like = mLikeList.get(position);
        holder.likeName.setText(like.getName());
        Glide.with(mContext).load(like.getImageId()).into(holder.likeImage);
    }
    @Override
    public int getItemCount(){
        return mLikeList.size();
    }

}
