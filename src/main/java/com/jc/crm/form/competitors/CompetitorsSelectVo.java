package com.jc.crm.form.competitors;

import com.jc.crm.form.tag.TagVo;
import com.jc.crm.model.CompetitorsEntity;

import java.util.List;

/**
 * 为返回含有标签的竞争对手信息创建的实体类
 * @author currysss 2018-11-21
 * */
public class CompetitorsSelectVo extends CompetitorsEntity {

    private String username;

    private List<TagVo> tagVoList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<TagVo> getTagVoList() {
        return tagVoList;
    }

    public void setTagVoList(List<TagVo> tagVoList) {
        this.tagVoList = tagVoList;
    }

}
