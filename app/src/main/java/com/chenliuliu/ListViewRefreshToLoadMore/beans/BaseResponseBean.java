package com.chenliuliu.ListViewRefreshToLoadMore.beans;

/**
 * Created by liuliuchen on 15/11/8.
 */

public class BaseResponseBean {
    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

