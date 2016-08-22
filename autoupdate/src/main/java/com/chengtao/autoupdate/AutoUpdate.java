package com.chengtao.autoupdate;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.chengtao.autoupdate.entity.AutoUpdateEntity;
import com.chengtao.autoupdate.entity.FileInfo;
import com.chengtao.autoupdate.service.DownloadService;

/**
 * Created by ChengTao on 2016-08-19.
 */
@SuppressWarnings("ALL")
public class AutoUpdate implements AutoUpdateInterface {
    private static String TAG = "AutoUpdate";
    private static Context context;
    private static AutoUpdateEntity entity;
    private AutoUpdateDialog dialog;
    public static final int NOTIFICATION_ID = 0;
    private static NotificationManager notificationManager;
    private static Notification notify;
    private static Notification.Builder builder;

    public AutoUpdate(Context context, String json) {
        this.context = context;
        entity = new AutoUpdateEntity(json);
    }


    @Override
    public void autoUpdateComfirm(String apkUrl) {
        Log.e(TAG, "autoUpdateComfirm");
        createNotification();
        startDownloadService();
    }

    /**
     * 启动下载服务
     */
    public static void startDownloadService() {
        FileInfo fileInfo = new FileInfo(0, entity.getApkUrl());
        fileInfo.setFileName(entity.getApkUrl()
                .substring(entity
                                .getApkUrl()
                                .lastIndexOf("/") + 1,
                        fileInfo.getFileUrl().length())+".apk");
        Log.e(TAG,"-------getFileName--------"+fileInfo.getFileName());
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(DownloadService.ACTION_START);
        intent.putExtra(DownloadService.FILE_INFO, fileInfo);
        context.startService(intent);
    }

    private void createNotification() {
        int icon = Utils.getApplicationIcon(context);
        String applicationName = Utils.getApplicationName(context);
        builder = new Notification.Builder(context)
                .setSmallIcon(icon)
                .setContentTitle(applicationName)
                .setContentText("正在下载:0%");
        notify = builder.build();
        notify.flags = android.app.Notification.FLAG_AUTO_CANCEL;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notify);
    }

    public static void updateProgress(int progress) {
        builder.setContentText("正在下载:" + progress + "%").setProgress(100, progress, false);
        notify = builder.build();
        notificationManager.notify(NOTIFICATION_ID, notify);
        if (progress == 100) {
            notificationManager.cancel(NOTIFICATION_ID);
        }
    }

    @Override
    public void autoUpdateCancle() {

    }

    public void setAutoDialogListener(AutoUpdateDialog.AutoUpdateDialogListener listener) {
        Log.e(TAG, entity.getVersionCode() + "------" + Utils.getVersionCode(context));
        if (entity.getVersionCode() > Utils.getVersionCode(context)) {
            dialog = new AutoUpdateDialog(context, entity, listener, this);
            dialog.showAutoUpdateDialog();
        }
    }

    /**
     * 弹出吐司
     *
     * @param s
     */
    public static void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
