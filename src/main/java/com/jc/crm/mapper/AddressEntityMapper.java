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
    @Insert("INSERT INTO address(country, province, city, street, zip_code, ex_1) " +
            "VALUES" +
            "(#{country}, #{province}, #{city}, #{street}, #{zipCode}, #{ex1})")
    @Options(useGeneratedKeys = true, keyColumn = "address_id", keyProperty = "addressId")
    int insert(AddressEntity addressEntity);

    /**
     * 更新地址
     * @param addressEntity 地址实体
     * @return code > 0 更新成功
     * */
    @Insert("UPDATE address SET " +
            "country=#{country}, province=#{province}, city=#{city}, street=#{street}, zip_code=#{zipCode}, ex_1=#{ex1}" +
            "WHERE " +
            "address_id=#{addressId}" )
    @Options(useGeneratedKeys = true, keyColumn = "address_id", keyProperty = "addressId")
    int update(AddressEntity addressEntity);
}
