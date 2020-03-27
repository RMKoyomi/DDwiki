package com.example.ddwiki.adapters;


import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ddwiki.R;
import com.example.ddwiki.Vtbclass.pictures;

import java.util.List;
import java.util.Random;

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder> {

    private List<pictures> picList;

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

        //initView();
        bitmap = ((BitmapDrawable)iview.getDrawable()).getBitmap();
        scaleWidth = width/bitmap.getWidth();
        scaleHeight = height/bitmap.getHeight();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        pictures pics = picList.get(i);
        viewHolder.picImage.setImageResource(pics.getImageId());

        ViewGroup.LayoutParams params = viewHolder.picImage.getLayoutParams();
        params.height = params.height + new Random().nextInt(300);
        viewHolder.picImage.setLayoutParams(params);

        viewHolder.picTitle.setText(pics.getName());
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

    private void initView(){
        //iview = (ImageView)getActivity().findViewById(R.id.iv);
        iview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.iv:
                        if(!isAmplification){
                            matrix.set(iview.getImageMatrix());
                            matrix.postScale(scaleWidth,scaleHeight);
                            Bitmap newbitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),
                                    bitmap.getHeight(),matrix,true);
                            iview.setImageBitmap(newbitmap);
                            isAmplification = true;
                        }
                        else {
                            matrix.set(iview.getImageMatrix());
                            matrix.postScale(1.0f,1.0f);
                            Bitmap newbitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),
                                    bitmap.getHeight(),matrix,true);
                            iview.setImageBitmap(newbitmap);
                            isAmplification = false;
                        }
                        break;
                    default:break;
                }

            }
        });
    }
}

