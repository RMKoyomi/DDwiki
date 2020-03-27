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
import com.example.ddwiki.Vtbclass.Enterprise;
import com.example.ddwiki.activities.EnterpriseActivity;

import java.util.List;

public class EnterAdapter extends RecyclerView.Adapter<EnterAdapter.ViewHolder> {
    private Context mContext;
    private List<Enterprise> mEnterpriseList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView enterImage;
        TextView enterName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            enterImage = (ImageView)view.findViewById(R.id.enter_image);
            enterName = (TextView)view.findViewById(R.id.enter_name);
        }
    }

    public EnterAdapter(List<Enterprise> enterpriseList){
        mEnterpriseList = enterpriseList;
    }

    //打开详细百科页面
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.enter_item,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Enterprise enterprise = mEnterpriseList.get(position);
                Intent intent = new Intent(mContext, EnterpriseActivity.class);
                intent.putExtra(EnterpriseActivity.ENTER_NAME, enterprise.getName());
                intent.putExtra(EnterpriseActivity.ENTER_IMAGE_ID, enterprise.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Enterprise enterprise = mEnterpriseList.get(position);
        holder.enterName.setText(enterprise.getName());
        Glide.with(mContext).load(enterprise.getImageId()).into(holder.enterImage);
    }
    @Override
    public int getItemCount(){
        return mEnterpriseList.size();
    }

}
