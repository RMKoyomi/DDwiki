package com.example.ddwiki.fragment;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.ddwiki.R;
import com.example.ddwiki.Vtbclass.pictures;
import com.example.ddwiki.adapters.PicAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragment3 extends Fragment{
    private TextView textView;
    private Button button;

    private PicAdapter adapter;

    private RecyclerView rvone;

    private ImageView iview;
    private static final String TAG = "Fragment3";
    public boolean isAmplification = false;

    public Matrix matrix = new Matrix();
    public Bitmap bitmap;
    public float scaleWidth;
    public float scaleHeight;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment3,container,false);
        iview = (ImageView)view.findViewById(R.id.iv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        List<pictures> picturesList = new ArrayList<>();
        picturesList.add(new pictures("猫爱丽丝",R.drawable.pic_1));
        picturesList.add(new pictures("homolive",R.drawable.pic_12));
        picturesList.add(new pictures("himehina",R.drawable.pic_3));
        picturesList.add(new pictures("夏日夸",R.drawable.pic_10));
        picturesList.add(new pictures("peko",R.drawable.pic_6));
        picturesList.add(new pictures("ark船长",R.drawable.pic_19));
        picturesList.add(new pictures("冬装傻紫",R.drawable.pic_15));
        picturesList.add(new pictures("ark狐狸",R.drawable.pic_20));
        picturesList.add(new pictures("星姐",R.drawable.pic_9));
        picturesList.add(new pictures("赛车手mea",R.drawable.pic_11));
        picturesList.add(new pictures("ark亚琦",R.drawable.pic_22));
        picturesList.add(new pictures("meaqua",R.drawable.pic_14));
        /**/

        adapter = new PicAdapter(picturesList);

        rvone = getActivity().findViewById(R.id.rv_one);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rvone.setLayoutManager(layoutManager);
        rvone.setAdapter(adapter);

        /*
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;

        //initView();
        bitmap = ((BitmapDrawable)iview.getDrawable()).getBitmap();
        scaleWidth = width/bitmap.getWidth();
        scaleHeight = height/bitmap.getHeight();*/

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
