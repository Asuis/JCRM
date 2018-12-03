package com.jc.crm.form.userRecommend;

import java.util.Date;

/**
 * 客户/商机推荐 （暂未使用）
 * Created by Administrator on 2018/12/3 0003.
 */
public class RecommendForm {
    private Integer userRecommendId;
    private Integer recommendType;
    private Integer recommendId;
    private Integer isActived;
    private Integer weight;
    private Integer userId;

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



}
