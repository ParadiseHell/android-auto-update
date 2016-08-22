package com.chengtao.sample;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.chengtao.autoupdate.AutoUpdate;
import com.chengtao.autoupdate.AutoUpdateDialog;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String json = "{VersionCode:10036, VersionName:1.3.6, UpdateMessage:'1.修复BUG<br>2.修复BUG', ApkUrl:'http://apk.hiapk.com/appdown/com.ss.android.article.news'}";
        AutoUpdate autoUpdate = new AutoUpdate(MainActivity.this, json);
        autoUpdate.setAutoDialogListener(new AutoUpdateDialog.AutoUpdateDialogListener() {
            @Override
            public void onClick(int status) {
                switch (status) {
                    case AutoUpdateDialog.UPDATE:
                        Toast.makeText(MainActivity.this, "UPDATE", Toast.LENGTH_SHORT).show();
                        break;
                    case AutoUpdateDialog.CANCEL:
                        Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
