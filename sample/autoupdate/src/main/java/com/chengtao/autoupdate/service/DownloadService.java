package com.chengtao.autoupdate.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chengtao.autoupdate.StorageUtils;
import com.chengtao.autoupdate.entity.FileInfo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ChengTao on 2016-08-22.
 */
@SuppressWarnings("ALL")
public class DownloadService extends Service{
    private static final String TAG ="DownloadService";
    public static final String ACTION_START = "ACTION_START";
    public static final String ACTION_UPDATE = "com.chengtao.autoupdate.service.ACTION_UPDATE";
    public static final String FILE_INFO = "FILE_INFO";
    private static final int MESSAGE_INIT = 0;
    private int threadCount = 3;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_INIT:
                    FileInfo fileInfo = (FileInfo) msg.obj;
                    Log.e("TAG","TEST:-----"+fileInfo.toString());
                    DownloadTask downloadTask = new DownloadTask(DownloadService.this,fileInfo);
                    downloadTask.download();
                    break;
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (ACTION_START.equals(intent.getAction())){//开始
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra(FILE_INFO);
            Log.e(TAG,"start:------"+fileInfo.toString());
            //启动线程
            InitThread thread = new InitThread(fileInfo);
            thread.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class InitThread extends Thread{
        private FileInfo fileInfo = null;
        public InitThread(FileInfo fileInfo){
            this.fileInfo = fileInfo;
        }
        @Override
        public void run() {
            HttpURLConnection connection = null;
            RandomAccessFile raf = null;
            try {
                //连接网络
                URL url = new URL(fileInfo.getFileUrl());
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(10 * 1000);
                connection.setReadTimeout(10 * 1000);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Charset", "UTF-8");
                int length = -1;
                if (connection.getResponseCode() == 200){
                    //获取文件长度
                    length = connection.getContentLength();
                    Log.e(TAG,"---------length---------------"+length);
                }
                if (length <= 0){
                    return;
                }
                //在本地创建文件
                File dir = StorageUtils.getCacheDirectory(DownloadService.this);
                File file = new File(dir,fileInfo.getFileName());
                Log.e(TAG,"------file-------"+file.getAbsolutePath());
                raf = new RandomAccessFile(file,"rwd");
                //设置文件长度
                raf.setLength(length);
                fileInfo.setFileLength(length);
                handler.obtainMessage(MESSAGE_INIT,fileInfo).sendToTarget();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    if (raf!=null){
                        raf.close();
                    }
                    if (connection != null){
                        connection.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
