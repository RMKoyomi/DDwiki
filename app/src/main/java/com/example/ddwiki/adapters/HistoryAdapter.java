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
import com.example.ddwiki.Vtbclass.History;
import com.example.ddwiki.Vtbclass.Hololive;
import com.example.ddwiki.Vtbclass.Like;
import com.example.ddwiki.activities.HistoryActivity;
import com.example.ddwiki.activities.HoloActivity;
import com.example.ddwiki.activities.LikeActivity;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context mContext;
    private List<History> mHisList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView hisImage;
        TextView hisName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            hisImage = (ImageView)view.findViewById(R.id.history_image);
            hisName = (TextView)view.findViewById(R.id.history_name);
        }
    }

    public HistoryAdapter(List<History> historyList){
        mHisList = historyList;
    }

    //打开详细百科页面
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.history_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                History history = mHisList.get(position);
                Intent intent = new Intent(mContext, HistoryActivity.class);
                intent.putExtra(HistoryActivity.HIS_NAME, history.getName());
                intent.putExtra(HistoryActivity.HIS_IMAGE_ID, history.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        History history = mHisList.get(position);
        holder.hisName.setText(history.getName());
        Glide.with(mContext).load(history.getImageId()).into(holder.hisImage);
    }
    @Override
    public int getItemCount(){
        return mHisList.size();
    }

}
