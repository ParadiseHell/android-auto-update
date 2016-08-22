package com.chengtao.autoupdate.entity;

import java.io.Serializable;

/**
 * 文件信息
 * Created by ChengTao on 2016-08-22.
 */
public class FileInfo implements Serializable{
    private int id;
    private String fileUrl;
    private String fileName;
    private int fileLength;
    private int finishProgress;

    public FileInfo() {

    }

    public FileInfo(int id, String fileUrl) {
        this.id = id;
        this.fileUrl = fileUrl;
    }

    public FileInfo(int id, String fileUrl, String fileName, int fileLength, int finishProgress) {
        this.id = id;
        this.fileUrl = fileUrl;
        this.fileName = fileName;
        this.fileLength = fileLength;
        this.finishProgress = finishProgress;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "id=" + id +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileLength=" + fileLength +
                ", finishProgress=" + finishProgress +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileLength() {
        return fileLength;
    }

    public void setFileLength(int fileLength) {
        this.fileLength = fileLength;
    }

    public int getFinishProgress() {
        return finishProgress;
    }

    public void setFinishProgress(int finishProgress) {
        this.finishProgress = finishProgress;
    }
}
