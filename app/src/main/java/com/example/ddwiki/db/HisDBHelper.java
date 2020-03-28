package com.example.ddwiki.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ddwiki.Vtbclass.Like;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

public class HisDBHelper extends SQLiteOpenHelper {
    //创建数据库，建表
    private static final String DBNAME="his.db";
    private static final int VERSION=3; //设置版本号
    private static final String TBL_DETAILHIS="his"; //创建表名为his的表
    private static final String TBL_DETAILHIS_COLUMN_NAME="hisname";
    private static final String TBL_DETAILNHIS_COLUMN_IMAGEID="hisimageid";
    private static final String TBL_DETAILNHIS_COLUMN_DATETIME="histime";
    public HisDBHelper(Context context){
        super(context,DBNAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub16
        StringBuffer sb=new StringBuffer();
        sb.append("create table if not exists ");
        sb.append(TBL_DETAILHIS+"(");
        sb.append(TBL_DETAILHIS_COLUMN_NAME +" varchar(100) primary key ,"); //设置主键
        sb.append(TBL_DETAILNHIS_COLUMN_IMAGEID+ " integer ,");
        sb.append(TBL_DETAILNHIS_COLUMN_DATETIME+ " varchar(100) ");
        sb.append(")");
        db.execSQL(sb.toString());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql2="drop table if exists "+TBL_DETAILHIS;
        db.execSQL(sql2); //创建
        onCreate(db);
    }
}
