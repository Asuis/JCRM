package com.jc.crm.service.user.vo;

/**
 * @author asuis
 * @version: ProvinceVO.java 18-12-2:下午11:55
 */
public class ProvinceVO {
    private String label;
    private String key;

    @Override
    public String toString() {
        return "ProvinceVO{" +
                "label='" + label + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public ProvinceVO(String label) {
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
