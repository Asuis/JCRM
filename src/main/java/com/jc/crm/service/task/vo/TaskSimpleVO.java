package com.jc.crm.service.task.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.jc.crm.service.user.vo.UserSimpleVO;

import java.util.Date;
import java.util.List;

/**
 * @author asuis
 * @version: TaskVO.java 18-12-9:下午4:09
 */
public class TaskSimpleVO {
    private UserSimpleVO userSimpleVO;
    private String avatar;
    private String theme;
    private Integer taskId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date startTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date deadline;
    private Integer oppId;
    private Integer consumerId;
    private String description;
    /**
     * 0 未完成
     * 1 已完成
     * 2 等待中
     * */
    private Integer state;
    private Integer priority;
    private RepeatSettingVO repeatSetting;
    private List<UserSimpleVO> holders;

    public List<UserSimpleVO> getHolders() {
        return holders;
    }

    public void setHolders(List<UserSimpleVO> holders) {
        this.holders = holders;
    }

    public Integer getOppId() {
        return oppId;
    }

    public void setOppId(Integer oppId) {
        this.oppId = oppId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public UserSimpleVO getUserSimpleVO() {
        return userSimpleVO;
    }

    public void setUserSimpleVO(UserSimpleVO userSimpleVO) {
        this.userSimpleVO = userSimpleVO;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public RepeatSettingVO getRepeatSetting() {
        return repeatSetting;
    }

    public void setRepeatSetting(RepeatSettingVO repeatSetting) {
        this.repeatSetting = repeatSetting;
    }
}
