package com.chengtao.autoupdate.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chengtao.autoupdate.entity.ThreadInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChengTao on 2016-08-22.
 */
public class ThreadDAOImpl implements ThreadDAO {
    private DBHelper helper = null;

    public ThreadDAOImpl(Context context) {
        this.helper = DBHelper.getInstance(context);
    }

    @Override
    public synchronized void insertTread(ThreadInfo info) {
        SQLiteDatabase db = null;
        try{
            db = helper.getWritableDatabase();
            db.execSQL("insert into thread_info(thread_id,url,start,end,finish_progress) " +
                    "values(?,?,?,?,?)",new Object[]{info.getId(),
                    info.getUrl(),
                    info.getStart(),
                    info.getEnd(),
                    info.getFinishProgress()});
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db!=null){
                db.close();
            }
        }

    }

    @Override
    public synchronized void deleteTread(String url,int thread_id) {
        SQLiteDatabase db = null;
        try{
            db = helper.getWritableDatabase();
            db.execSQL("delete from thread_info where url = ? and thread_id = ?",
                    new Object[]{url,thread_id});
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db!=null){
                db.close();
            }
        }
    }

    @Override
    public synchronized void updateTread(String url, int threadId, int finishProgress) {
        SQLiteDatabase db = null;
        try{
            db = helper.getWritableDatabase();
            db.execSQL("update thread_info set finish_progress = ? where url = ? and thread_id = ?",
                    new Object[]{finishProgress,url,threadId});
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db!=null){
                db.close();
            }
        }
    }

    @Override
    public List<ThreadInfo> getThreads(String url) {
        List<ThreadInfo> infos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try{
            db = helper.getReadableDatabase();
            cursor = db.rawQuery("select * from thread_info where url = ?",
                    new String[]{url});
            while (cursor.moveToNext()){
                ThreadInfo info = new ThreadInfo();
                info.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
                info.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                info.setStart(cursor.getInt(cursor.getColumnIndex("start")));
                info.setEnd(cursor.getInt(cursor.getColumnIndex("end")));
                info.setFinishProgress(cursor.getInt(cursor.getColumnIndex("finish_progress")));
                infos.add(info);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!= null){
                cursor.close();
            }
            if (db!=null){
                db.close();
            }
        }
        return infos;
    }

    @Override
    public boolean isExists(String url, int threadId) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        boolean exists = false;
        try{
            db = helper.getReadableDatabase();
            cursor = db.rawQuery("select * from thread_info where url = ? and thread_id = ?",
                    new String[]{url,threadId+""});
             exists = cursor.moveToNext();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!= null){
                cursor.close();
            }
            if (db!=null){
                db.close();
            }
        }
        return exists;
    }
}
