package com.jc.crm.query;

import java.util.Date;

/**
 * @author asuis
 * @version: UserQuery.java 18-12-9:下午2:12
 */
public class UserQuery {
    private String keyword;
    private Date ctime;
    private boolean isLocked;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
