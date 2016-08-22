# 安卓应用自动更新

一个操作简单、安全性能较高的安卓应用更新库

## 特点

1. 操作简单，几行代码就搞定
```java
//数据准备
String json = "{VersionCode:10036, "+
"VersionName:1.3.6, UpdateMessage:'1.修复BUG<br>2.修复BUG', "+
"ApkUrl:'http://apk.hiapk.com/appdown/com.ss.android.article.news'}";
//初始化自动更新
AutoUpdate autoUpdate = new AutoUpdate(MainActivity.this, json);
//实现接口
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
```
2. 自定义实现接口，给操作者更多空间
3. 断点续传，断网后，当启动网络后继续下载
4. 安全，代码操作进行大量捕获异常，降低报错几率
5. UI比较美观

## 效果展示

<img src="./images/1.jpg" width="280">
<img src="./images/2.jpg" width="280">
<img src="./images/3.jpg" width="280">
<img src="./images/4.jpg" width="280">
<img src="./images/5.jpg" width="280">

## 感谢
- [慕课网](http://www.imooc.com/)
- [@feicien](https://github.com/feicien/android-auto-update)