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
import com.example.ddwiki.Vtbclass.NJSJ;
import com.example.ddwiki.activities.NJSJActivity;

import java.util.List;

public class NJSJAdapter extends RecyclerView.Adapter<NJSJAdapter.ViewHolder> {
    private Context mContext;
    private List<NJSJ> mnjsjList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView NJSJImage;
        TextView NJSJName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            NJSJImage = (ImageView)view.findViewById(R.id.njsj_image);
            NJSJName = (TextView)view.findViewById(R.id.njsj_name);
        }
    }

    public NJSJAdapter(List<NJSJ> njsjList){
        mnjsjList = njsjList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.njsj_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                NJSJ njsj = mnjsjList.get(position);
                Intent intent = new Intent(mContext, NJSJActivity.class);
                intent.putExtra(NJSJActivity.NJSJ_NAME,njsj.getName());
                intent.putExtra(NJSJActivity.NJSJ_IMAGE_ID,njsj.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        NJSJ njsj = mnjsjList.get(position);
        holder.NJSJName.setText(njsj.getName());
        Glide.with(mContext).load(njsj.getImageId()).into(holder.NJSJImage);
    }
    @Override
    public int getItemCount(){
        return mnjsjList.size();
    }

}
