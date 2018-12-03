package com.jc.crm.service.user.vo;

/**
 * @author asuis
 * @version: GeographicVO.java 18-12-2:下午11:54
 */
public class GeographicVO {
    ProvinceVO province;
    CityVO city;

    public GeographicVO(String province, String city) {
        this.province = new ProvinceVO(province);
        this.city = new CityVO(city);
    }

    public ProvinceVO getProvince() {
        return province;
    }

    public void setProvince(ProvinceVO province) {
        this.province = province;
    }

    public CityVO getCity() {
        return city;
    }

    public void setCity(CityVO city) {
        this.city = city;
    }
}
