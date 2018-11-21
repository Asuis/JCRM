package com.jc.crm.form.tag;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 为自定义标签信息创建的实体类
 * @author currysss 2018-11-16
 * */
public class TagInsertForm {

    @NotBlank(message = "标签名不能为空")
    @Size(min = 2, max = 5 , message = "填写内容范围2-5")
    private String tagName;

    @NotBlank(message = "标签描述不能为空")
    @Size(min = 5, max = 20 , message = "填写内容范围5-20")
    private String description;

    private Integer pid;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

}
