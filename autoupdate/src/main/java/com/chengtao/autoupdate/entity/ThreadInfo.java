package com.chengtao.autoupdate.entity;

/**
 * 线程信息
 * Created by ChengTao on 2016-08-22.
 */
public class ThreadInfo {
    private int id;
    private String url;
    private int start;
    private int end;
    private int finishProgress;

    public ThreadInfo() {
    }

    public ThreadInfo(int id, String url, int start, int end, int finishProgress) {
        this.id = id;
        this.url = url;
        this.start = start;
        this.end = end;
        this.finishProgress = finishProgress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getFinishProgress() {
        return finishProgress;
    }

    public void setFinishProgress(int finishProgress) {
        this.finishProgress = finishProgress;
    }
}
