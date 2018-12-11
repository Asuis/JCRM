package com.jc.crm.service.task.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author asuis
 * @version: RepeatSettingVO.java 18-12-9:下午4:14
 */
public class RepeatSettingVO {
    private Integer repeatId;
    private Integer taskId;
    private Boolean isRepeat;
    private Boolean isRecommend;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date remindTime;
    private Integer interval;
    private Integer repeatType;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date startTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date endTime;

    public Integer getRepeatId() {
        return repeatId;
    }

    @Override
    public String toString() {
        return "RepeatSettingVO{" +
                "repeatId=" + repeatId +
                ", taskId=" + taskId +
                ", isRepeat=" + isRepeat +
                ", isRecommend=" + isRecommend +
                ", remindTime=" + remindTime +
                ", interval=" + interval +
                ", repeatType=" + repeatType +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public void setRepeatId(Integer repeatId) {
        this.repeatId = repeatId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Boolean getRepeat() {
        return isRepeat;
    }

    public void setRepeat(Boolean repeat) {
        isRepeat = repeat;
    }

    public Boolean getRecommend() {
        return isRecommend;
    }

    public void setRecommend(Boolean recommend) {
        isRecommend = recommend;
    }

    public Date getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(Integer repeatType) {
        this.repeatType = repeatType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}