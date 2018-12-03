package com.jc.crm.model;

import java.util.Date;

/**
 * 客户/商机推荐实体类（暂未使用）
 * Created by Administrator on 2018/12/3 0003.
 */
public class RecommendEntity {
    private Integer userRecommendId;
    private Integer recommendType;
    private Integer recommendId;
    private Integer isActived;
    private Integer weight;
    private Integer userId;
    private Date ctime;
    private Date utime;

    public Integer getUserRecommendId() {
        return userRecommendId;
    }

    public void setUserRecommendId(Integer userRecommendId) {
        this.userRecommendId = userRecommendId;
    }

    public Integer getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public Integer getIsActived() {
        return isActived;
    }

    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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


}
