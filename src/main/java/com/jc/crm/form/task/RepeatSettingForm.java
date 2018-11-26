package com.jc.crm.form.task;

import java.util.Date;

/**
 * @author asuis
 * @version: RepeatSettingForm.java 18-11-24:下午4:53
 */
public class RepeatSettingForm {
    private Integer repeatId;
    private Integer taskId;
    private boolean isRepeat;
    private boolean isRecommend;
    private Date remindTime;
    private int interval;
    private int repeatType;
    private Date startTime;
    private Date endTime;

    public Integer getRepeatId() {
        return repeatId;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
    }

    public boolean isRecommend() {
        return isRecommend;
    }

    public void setRecommend(boolean recommend) {
        isRecommend = recommend;
    }

    public Date getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(int repeatType) {
        this.repeatType = repeatType;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
