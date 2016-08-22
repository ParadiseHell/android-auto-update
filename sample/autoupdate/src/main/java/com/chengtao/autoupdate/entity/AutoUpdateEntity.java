package com.chengtao.autoupdate.entity;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ChengTao on 2016-08-19.
 */
@SuppressWarnings("ALL")
public class AutoUpdateEntity {
    private static String TAG ="AutoUpdateEntity";
    private int versionCode;
    private String versionName;
    private String updateMessage;
    private String apkUrl;

    /**
     *
     * @param jsonInfo 包含本类实体属性的json字符串
     */
    public AutoUpdateEntity(String jsonInfo){
        String info = jsonInfo.toLowerCase();
        try {
            JSONObject object = new JSONObject(info);
            setVersionCode(object.getInt("versioncode"));
            setVersionName(object.getString("versionname"));
            setUpdateMessage(object.getString("updatemessage"));
            setApkUrl(object.getString("apkurl"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.v(TAG,e.toString());
        }
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }
}
