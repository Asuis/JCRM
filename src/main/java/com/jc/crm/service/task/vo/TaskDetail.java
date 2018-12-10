package com.jc.crm.service.task.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.jc.crm.form.task.RepeatSettingForm;
import com.jc.crm.service.user.vo.UserSimpleVO;

import java.util.Date;
import java.util.List;

/**
 * @author asuis
 * @version: TaskDetail.java 18-11-26:上午11:29
 */
public class TaskDetail {
    private Integer taskId;
    private String theme;
    private Integer OppId;
    private Integer consumerId;
    private List<UserSimpleVO> holders;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date deadline;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date startTime;
    private String description;

    private int priority;
    private int state;
    private RepeatSettingVO repeatSettingDetails;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getOppId() {
        return OppId;
    }

    public void setOppId(Integer oppId) {
        OppId = oppId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public List<UserSimpleVO> getHolders() {
        return holders;
    }

    public void setHolders(List<UserSimpleVO> holders) {
        this.holders = holders;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public RepeatSettingVO getRepeatSettingDetails() {
        return repeatSettingDetails;
    }

    public void setRepeatSettingDetails(RepeatSettingVO repeatSettingDetails) {
        this.repeatSettingDetails = repeatSettingDetails;
    }
}
