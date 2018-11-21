package com.jc.crm.form;

import com.jc.crm.model.AddressEntity;

/**
 * @author asuis
 */
public class AddressForm {
    private String country;
    private String province;
    private String city;
    private String street;
    private String zipCode;
    private String ex1;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEx1() {
        return ex1;
    }

    public void setEx1(String ex1) {
        this.ex1 = ex1;
    }
    public AddressEntity toAddress() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(this.getCity());
        addressEntity.setCountry(this.getCountry());
        addressEntity.setEx1(this.getEx1());
        addressEntity.setProvince(this.getProvince());
        addressEntity.setStreet(this.getStreet());
        addressEntity.setZipCode(this.getZipCode());
        return addressEntity;
    }
}
