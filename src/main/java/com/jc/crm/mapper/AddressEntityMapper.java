package com.jc.crm.mapper;

import com.jc.crm.model.AddressEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * @author asuis
 * @version: AddressEntityMapper.java 18-11-21:下午3:40
 */
@Repository
public interface AddressEntityMapper {

    /**
     * 插入地址
     * @param addressEntity 地址实体
     * @return code > 0 插入成功
     * */
    @Insert("INSERT INTO address(country, province, city, street, zip_code, ex_1) VALUES(country, province, city, street, zip_code, ex_1)")
    @Options(useGeneratedKeys = true, keyColumn = "address_id", keyProperty = "addressId")
    int insert(AddressEntity addressEntity);
}
