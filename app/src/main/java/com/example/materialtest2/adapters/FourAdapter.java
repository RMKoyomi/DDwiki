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
import com.example.materialtest2.activities.FourActivity;
import com.example.materialtest2.Vtbclass.Four;

import java.util.List;

public class FourAdapter extends RecyclerView.Adapter<FourAdapter.ViewHolder> {
    private Context mContext;
    private List<Four> mFourList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fourImage;
        TextView fourName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            fourImage = (ImageView)view.findViewById(R.id.four_image);
            fourName = (TextView)view.findViewById(R.id.four_name);
        }
    }

    public FourAdapter(List<Four> fourList){
        mFourList = fourList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.four_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Four four = mFourList.get(position);
                Intent intent = new Intent(mContext, FourActivity.class);
                intent.putExtra(FourActivity.FOUR_NAME, four.getName());
                intent.putExtra(FourActivity.FOUR_IMAGE_ID, four.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Four four = mFourList.get(position);
        holder.fourName.setText(four.getName());
        Glide.with(mContext).load(four.getImageId()).into(holder.fourImage);
    }
    @Override
    public int getItemCount(){
        return mFourList.size();
    }

}
