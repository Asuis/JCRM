package com.jc.crm.form.tag;

/**
 * 为含有标签的竞争对手信息类的变量创建的实体类
 * @author currysss 2018-11-21
 * */
public class TagVo {

    private Integer competitorsTagLinkId;

    private String tagName;

    public Integer getCompetitorsTagLinkId() {
        return competitorsTagLinkId;
    }

    public void setCompetitorsTagLinkId(Integer competitorsTagLinkId) {
        this.competitorsTagLinkId = competitorsTagLinkId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

}
