package com.jc.crm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CompetitorsEntity {

    public int competitor_id;

    public String description;

    public int types;

    public int holder;

    public int status;

    public String ex_1;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date ctime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date utime;

    public String competitor_name;

    public int getCompetitor_id() {
        return competitor_id;
    }

    public void setCompetitor_id(int competitor_id) {
        this.competitor_id = competitor_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }

    public int getHolder() {
        return holder;
    }

    public void setHolder(int holder) {
        this.holder = holder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEx_1() {
        return ex_1;
    }

    public void setEx_1(String ex_1) {
        this.ex_1 = ex_1;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public String getCompetitor_name() { return competitor_name; }

    public void setCompetitor_name(String competitor_name) { this.competitor_name = competitor_name; }

}
