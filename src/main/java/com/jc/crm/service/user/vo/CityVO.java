package com.jc.crm.service.user.vo;

/**
 * @author asuis
 * @version: CityVO.java 18-12-2:下午11:55
 */
public class CityVO {
    private String label;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public CityVO(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
