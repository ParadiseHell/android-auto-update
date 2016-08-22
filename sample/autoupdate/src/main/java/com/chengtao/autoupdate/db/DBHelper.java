package com.chengtao.autoupdate.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ChengTao on 2016-08-22.
 */
@SuppressWarnings("ALL")
public class DBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "autoupdate.db";
    private static DBHelper instance;
    private static final int VERSION = 1;
    private static final String SQL_CREATE = "create table thread_info(" +
            "_id integer primary key autoincrement," +
            "thread_id integer," +
            "url text," +
            "start integer," +
            "end integer," +
            "finish_progress integer)";
    private static final String SQL_DROP = "drop table if exists thread_info";


    private DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public static DBHelper getInstance(Context context){
        if (instance == null){
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP);
        sqLiteDatabase.execSQL(SQL_CREATE);
    }
}
