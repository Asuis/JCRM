package com.jc.crm.form.opportunity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 为返回阶段为赢得的商业机会信息创建的实体类
 * @author currysss 2018-11-25
 * */
public class BusinessRecordSelectVo {

    private Integer oppRecordId;

    private String description;

    private Integer Holder;

    private Integer executor;

    private String oppName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date deadline;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ctime;

    public Integer getOppRecordId() {
        return oppRecordId;
    }

    public void setOppRecordId(Integer oppRecordId) {
        this.oppRecordId = oppRecordId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHolder() {
        return Holder;
    }

    public void setHolder(Integer holder) {
        Holder = holder;
    }

    public Integer getExecutor() {
        return executor;
    }

    public void setExecutor(Integer executor) {
        this.executor = executor;
    }

    public String getOppName() {
        return oppName;
    }

    public void setOppName(String oppName) {
        this.oppName = oppName;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
