package com.jc.crm.form.consumer;

import com.jc.crm.form.AddressForm;
import com.jc.crm.form.contacts.ContactForm;

/**
 * @author asuis
 * @version: ConsumerForm.java 18-11-24:下午5:52
 */
public class ConsumerForm {
    /***
     * 潜在客户来源】】】】】】】
     * */
    private Integer pid;
    private String consumerName;
    private Integer holderId;
    private String website;
    private AddressForm address;
    private int state;
    private int staffNum;
    private ContactForm contact;

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
}
