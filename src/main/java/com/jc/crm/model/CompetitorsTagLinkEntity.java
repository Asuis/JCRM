package com.jc.crm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 竞争对手与标签链接表(competitors_tag_link)实体类
 * @author currysss 2018-11-16
 * */
public class CompetitorsTagLinkEntity {

    private Integer competitorsTagLinkId;

    private Integer tagId;

    private Integer competitorId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ctime;

    public Integer getCompetitorsTagLinkId() {
        return competitorsTagLinkId;
    }

    public void setCompetitorsTagLinkId(Integer competitorsTagLinkId) {
        this.competitorsTagLinkId = competitorsTagLinkId;
    }

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

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }


}
