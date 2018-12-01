package com.jc.crm.form.opportunity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 为拒绝商业机会修改申请创建的实体类
 * @author currysss 2018-11-27
 * */
public class BusinessOpportunityRejectApplicationForm extends BusinessOpportunityAgreeApplicationForm {

    @NotBlank(message = "拒绝原因不能为空")
    @Size(min = 5, max = 200 , message = "填写内容范围为5-200")
    private String rejectionReason;

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}
