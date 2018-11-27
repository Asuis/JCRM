package com.jc.crm.model;

import com.jc.crm.form.AddressForm;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户实体类 consumer
 * @author 
 */
public class Consumer implements Serializable {
    private Integer cid;

    /**
     * 母公司客户id
     */
    private Integer pid;

    private String consumerName;

    /**
     * 公司网站
     */
    private String website;

    private String description;

    /**
     * 行业id
     */
    private Integer industry;

    /**
     * address_id
     */
    private Integer address;

    /**
     * 联系人id
     */
    private Integer contactId;

    private Date ctime;

    private Date utime;

    private Integer state;

    private Integer staffNum;

    private static final long serialVersionUID = 1L;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public Integer getAddressId() {
        return address;
    }

    public void setAddressId(Integer addressId) {
        this.address = address;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(Integer staffNum) {
        this.staffNum = staffNum;
    }

}