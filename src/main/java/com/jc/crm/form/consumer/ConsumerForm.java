package com.jc.crm.form.consumer;

import com.jc.crm.form.AddressForm;
import com.jc.crm.form.contacts.ContactForm;
import com.jc.crm.model.Consumer;
import com.jc.crm.utils.TimeUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author asuis
 * @version: ConsumerForm.java 18-11-24:下午5:52
 */
public class ConsumerForm {
    /***
     * 客户来源
     * */
    private Integer cid;

    private Integer pid;

    @NotBlank(message = "客户名字不能为空")
    @Size(min = 2, max = 18 , message = "填写内容范围2-18")
    private String consumerName;

    private Integer holderId;
    private String website;

    private String description;
    @NotNull(message = "客户地址不能为空")
    //@Valid
    private AddressForm address;
    private int state;
    private int staffNum;
    private ContactForm contact;
    /* 潜在0 / 正式客户1  */
    private Integer isOfficial;

    public Integer getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(Integer isOfficial) {
        this.isOfficial = isOfficial;
    }

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

    public Integer getHolderId() {
        return holderId;
    }

    public void setHolderId(Integer holderId) {
        this.holderId = holderId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public AddressForm getAddress() {
        return address;
    }

    public void setAddress(AddressForm address) {
        this.address = address;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(int staffNum) {
        this.staffNum = staffNum;
    }

    public ContactForm getContact() {
        return contact;
    }

    public void setContact(ContactForm contact) {
        this.contact = contact;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public Consumer toConsumer(Integer addressId) {
        Consumer consumer = new Consumer();
        consumer.setPid(this.getPid());
        consumer.setConsumerName(this.getConsumerName());
        consumer.setWebsite(this.getWebsite());
        consumer.setAddressId(addressId);
        consumer.setState(0);
        consumer.setDescription(this.getDescription());
        consumer.setStaffNum(this.getStaffNum());
        Date now = TimeUtils.getNowTime();
        consumer.setCtime(now);
        consumer.setUtime(now);
        consumer.setIsOfficial(this.getIsOfficial());
        return consumer;
    }
    public Consumer toConsumerUpdata(Integer cid) {
        Consumer consumer = new Consumer();
        consumer.setCid(cid);
        consumer.setPid(this.getPid());
        consumer.setConsumerName(this.getConsumerName());
        consumer.setWebsite(this.getWebsite());
        consumer.setState(0);
        consumer.setDescription(this.getDescription());
        consumer.setStaffNum(this.getStaffNum());
        Date now = TimeUtils.getNowTime();
        consumer.setUtime(now);
        consumer.setIsOfficial(this.getIsOfficial());
        return consumer;
    }
}
