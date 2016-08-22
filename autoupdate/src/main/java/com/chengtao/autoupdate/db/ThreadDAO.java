package com.chengtao.autoupdate.db;

import com.chengtao.autoupdate.entity.ThreadInfo;

import java.util.List;

/**
 * 数据访问接口
 * Created by ChengTao on 2016-08-22.
 */
public interface ThreadDAO {
    /**
     *
     * @param info
     */
    public void insertTread(ThreadInfo info);

    /**
     *
     * @param url
     */
    public void deleteTread(String url,int thread_id);

    /**
     *
     * @param url
     * @param threadId
     * @param finishProgress
     */
    public void updateTread(String url,int threadId,int finishProgress);

    /**
     *
     * @param url
     * @return
     */
    public List<ThreadInfo> getThreads(String url);

    /**
     *
     * @param url
     * @param threadId
     * @return
     */
    public boolean isExists(String url,int threadId);
}
