package com.jc.crm.form.tag;


/**
 * 为竞争对手添加标签信息创建的实体类
 * @author currysss 2018-11-16
 * */
public class CompetitorsTagLinkInsertForm {

    private Integer tagId;

    private Integer competitorId;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }


}
