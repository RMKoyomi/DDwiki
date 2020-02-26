package com.example.materialtest2.adapters;

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
import com.example.materialtest2.R;
import com.example.materialtest2.activities.IndActivity;
import com.example.materialtest2.Vtbclass.Independent;

import java.util.List;

public class IndAdapter extends RecyclerView.Adapter<IndAdapter.ViewHolder> {
    private Context mContext;
    private List<Independent> mIndList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView indImage;
        TextView indName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            indImage = (ImageView)view.findViewById(R.id.ind_image);
            indName = (TextView)view.findViewById(R.id.ind_name);
        }
    }

    public IndAdapter(List<Independent> indList){
        mIndList = indList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.ind_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Independent independent = mIndList.get(position);
                Intent intent = new Intent(mContext, IndActivity.class);
                intent.putExtra(IndActivity.IND_NAME, independent.getName());
                intent.putExtra(IndActivity.IND_IMAGE_ID, independent.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Independent independent = mIndList.get(position);
        holder.indName.setText(independent.getName());
        Glide.with(mContext).load(independent.getImageId()).into(holder.indImage);
    }
    @Override
    public int getItemCount(){
        return mIndList.size();
    }

}
