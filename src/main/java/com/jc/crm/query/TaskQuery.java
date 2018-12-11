package com.jc.crm.query;

import java.util.List;

/**
 * @author asuis
 * @version: TaskQuery.java 18-12-9:下午5:41
 */
public class TaskQuery {
    private String keyword;
    private List<Integer> uids;
    private int state;
    private boolean isMe;
    private Integer pageSize;
    private Integer pageNum;
    private String orderBy;
    public String getKeyword() {
        return keyword;
    }

    public List<Integer> getUids() {
        return uids;
    }

    public void setUids(List<Integer> uids) {
        this.uids = uids;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
