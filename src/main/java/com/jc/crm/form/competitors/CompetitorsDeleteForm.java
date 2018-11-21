package com.jc.crm.form.competitors;

/**
 * 为修改竞争对手状态创建的实体类
 * @author currysss 2018-11-16
 * */
public class CompetitorsDeleteForm {

    private int competitorId;

    private Integer status;

    public int getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(int competitorId) {
        this.competitorId = competitorId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
