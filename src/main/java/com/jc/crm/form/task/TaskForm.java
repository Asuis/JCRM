package com.jc.crm.form.task;

import com.jc.crm.model.TaskEntity;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author asuis
 */
public class TaskForm {
    private Integer taskId;
    @Size(min = 1,max = 64)
    private String theme;
    private Integer businessOppId;
    private Integer consumberId;
    private List<Integer> holders;
    @Future
    private Date deadline;
    private Date startTime;
    private String description;

    private int priority;
    private int state;
    @Valid
    private RepeatSettingForm repeatSetting;
    public TaskEntity toTask() {
        TaskEntity entity = new TaskEntity();
        entity.setTheme(theme);
        entity.setDeadline(deadline);
        entity.setPriority(priority);
        entity.setState(state);
        entity.setTaskId(taskId);
        entity.setHolderId(holders.get(0));
        entity.setRemind(true);
        entity.setRepeat(repeatSetting!=null);
        entity.setDescription(description);
        entity.setStartTime(startTime);
        return entity;
    }

    public Integer getConsumberId() {
        return consumberId;
    }

    public void setConsumberId(Integer consumberId) {
        this.consumberId = consumberId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

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

    public Integer getBusinessOppId() {
        return businessOppId;
    }

    public void setBusinessOppId(Integer businessOppId) {
        this.businessOppId = businessOppId;
    }

    public List<Integer> getHolders() {
        return holders;
    }

    public void setHolders(List<Integer> holders) {
        this.holders = holders;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    public RepeatSettingForm getRepeatSetting() {
        return repeatSetting;
    }

    public void setRepeatSetting(RepeatSettingForm repeatSetting) {
        this.repeatSetting = repeatSetting;
    }
}
