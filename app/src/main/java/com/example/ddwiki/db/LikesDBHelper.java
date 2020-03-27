package com.example.ddwiki.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ddwiki.Vtbclass.Like;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

public class LikesDBHelper extends SQLiteOpenHelper {
    //创建数据库，建表
    private static final String DBNAME="likes.db";
    private static final int VERSION=3; //设置版本号
    private static final String TBL_DETAILLIKE="Likes"; //创建表名为news的表
    private static final String TBL_DETAILLIKE_COLUMN_NAME="name";
    private static final String TBL_DETAILNLIKE_COLUMN_IMAGEID="imageid";
    public LikesDBHelper(Context context){
        super(context,DBNAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub16
        StringBuffer sb=new StringBuffer();
        sb.append("create table if not exists ");
        sb.append(TBL_DETAILLIKE+"(");
        sb.append(TBL_DETAILLIKE_COLUMN_NAME +" varchar(100) primary key ,"); //设置主键
        sb.append(TBL_DETAILNLIKE_COLUMN_IMAGEID+ " integer ");
        sb.append(")");
        db.execSQL(sb.toString());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql2="drop table if exists "+TBL_DETAILLIKE;
        db.execSQL(sql2); //创建
        onCreate(db);
    }
}
