package com.example.ddwiki.adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddwiki.R;
import com.example.ddwiki.Vtbclass.pictures;
import com.example.ddwiki.otherpage.dialogphotoActivity;

import java.util.List;
import java.util.Random;

import static org.litepal.LitePalApplication.getContext;

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder> {

    private Context mContext;

    private List<pictures> picList;

    private LayoutInflater bsman = null;

    public PicAdapter(List<pictures> picList) {
        this.picList = picList;

    }

    private ImageView iview;
    private static final String TAG = "Fragment3";
    public boolean isAmplification = false;

    public Matrix matrix = new Matrix();
    public Bitmap bitmap;
    public float scaleWidth;
    public float scaleHeight;
    private int id;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pic_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);

        iview = view.findViewById(R.id.iv);
        DisplayMetrics dm = view.getResources().getDisplayMetrics();
        view.getResources().getDisplayMetrics();
        int height = dm.heightPixels;
        int width = dm.widthPixels;

        bitmap = ((BitmapDrawable)iview.getDrawable()).getBitmap();
        scaleWidth = width/bitmap.getWidth();
        scaleHeight = height/bitmap.getHeight();

        if(mContext == null){
            mContext = viewGroup.getContext();
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final pictures pics = picList.get(i);
        viewHolder.picImage.setImageResource(pics.getImageId());
        viewHolder.picImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id =pics.getImageId();
                Intent intent = new Intent(mContext, dialogphotoActivity.class);
                intent.putExtra(dialogphotoActivity.IMAGE_ID, id);
                mContext.startActivity(intent);
            }
        });

        ViewGroup.LayoutParams params = viewHolder.picImage.getLayoutParams();
        params.height = params.height + new Random().nextInt(300);
        viewHolder.picImage.setLayoutParams(params);

        viewHolder.picTitle.setText(pics.getName());
        //initView(pics);
    }

    @Override
    public int getItemCount() {
        return picList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView picImage;
        TextView picTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            picImage = itemView.findViewById(R.id.iv);
            picTitle = itemView.findViewById(R.id.picTitle);
        }
    }

    private void initView(final pictures picture){

        // TODO Auto-generated method stub
        iview.setOnClickListener(new View.OnClickListener() { // 点击放大
            public void onClick(View paramView) {

            }
        });
    }
}

