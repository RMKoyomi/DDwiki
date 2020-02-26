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
import com.example.ddwiki.Vtbclass.Others;
import com.example.ddwiki.activities.OtherActivity;

import java.util.List;

public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.ViewHolder> {
    private Context mContext;
    private List<Others> mOthersList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView OtherImage;
        TextView OtherName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            OtherImage = (ImageView)view.findViewById(R.id.other_image);
            OtherName = (TextView)view.findViewById(R.id.other_name);
        }
    }

    public OtherAdapter(List<Others> otherList){
        mOthersList = otherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.other_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Others others = mOthersList.get(position);
                Intent intent = new Intent(mContext, OtherActivity.class);
                intent.putExtra(OtherActivity.OTHER_NAME,others.getName());
                intent.putExtra(OtherActivity.OTHER_IMAGE_ID,others.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Others others = mOthersList.get(position);
        holder.OtherName.setText(others.getName());
        Glide.with(mContext).load(others.getImageId()).into(holder.OtherImage);
    }
    @Override
    public int getItemCount(){
        return mOthersList.size();
    }

}
