package com.chenliuliu.ListViewRefreshToLoadMore.beans;

/**
 * Created by liuliuchen on 16/1/12.
 */
public class RowsEntity {
    private String title;
    private String content;
    private String createTime;

    public RowsEntity(String title, String createTime, String content) {
        this.title = title;
        this.createTime = createTime;
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreateTime() {
        return createTime;
    }
}
