package com.jc.crm.form.contacts;

import com.jc.crm.form.AddressForm;
import com.jc.crm.model.ContactsEntity;
import com.jc.crm.utils.TimeUtils;

import java.util.Date;

/**
 * @author asuis
 */
public class ContactForm {
    private Integer contactId;
    private String contactName;
    private String phone;
    private String email;
    private String familyName;
    private String firstName;
    private String title;
    private Integer pid;
    /**座机*/
    private String telephone;
    private String post;
    private AddressForm address;
    private String department;
    private int consumerId;

    public Integer getContactId() {return contactId;}

    public void setContactId(Integer contactId) {this.contactId = contactId;}

    public int getConsumerId() {return consumerId;}

    public void setConsumerId(int consumerId) {this.consumerId = consumerId;}

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public AddressForm getAddress() {
        return address;
    }

    public void setAddress(AddressForm address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public ContactsEntity toContact(Integer addressId) {
        ContactsEntity contactsEntity = new ContactsEntity();
        contactsEntity.setContactName(this.contactName);
        contactsEntity.setPhoneNumber(this.phone);
        contactsEntity.setEmail(this.email);
        contactsEntity.setFirstName(this.firstName);
        contactsEntity.setTitle(this.title);
        contactsEntity.setPid(this.pid);
        contactsEntity.setPost(this.post);
        contactsEntity.setDepartment(this.department);
        contactsEntity.setConsumerId(this.consumerId);
        contactsEntity.setAddressId(addressId);

        return contactsEntity;
    }

    public ContactsEntity toContactUpdata(Integer cid) {
        ContactsEntity contactsEntity = new ContactsEntity();
        contactsEntity.setConsumerId(cid);
        contactsEntity.setContactName(this.getContactName());
        contactsEntity.setPhoneNumber(this.getPhone());
        contactsEntity.setEmail(this.getEmail());
        contactsEntity.setFirstName(this.getFirstName());
        contactsEntity.setTitle(this.getTitle());
        contactsEntity.setPid(this.getPid());
        contactsEntity.setPost(this.getPost());
        contactsEntity.setDepartment(this.getDepartment());
        contactsEntity.setConsumerId(this.getConsumerId());

        return contactsEntity;
    }
}
