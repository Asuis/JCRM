package com.jc.crm.form.competitors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompetitorsInsertForm {

    @NotBlank(message = "竞争对手名字不能为空")
    @Size(min = 2, max = 18 , message = "填写内容范围2-18")
    public String competitorName;

    @NotBlank(message = "竞争对手描述不能为空")
    @Size(min = 10, max = 100 , message = "填写内容范围10-100")
    public String description;

    public Integer types;

    @Size(max = 200 , message = "填写内容长度不能超过200")
    public String ex1;

    public String getCompetitorName() {
        return competitorName;
    }

    public void setCompetitorName(String competitorName) {
        this.competitorName = competitorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getEx1() {
        return ex1;
    }

    public void setEx1(String ex1) {
        this.ex1 = ex1;
    }
}
