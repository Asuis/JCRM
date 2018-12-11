package com.jc.crm.mapper;

import com.jc.crm.bean.Address;
import com.jc.crm.form.AddressForm;
import com.jc.crm.model.AddressEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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
            "country=#{country}, province=#{province}, city=#{city}, street=#{street}" +
            "WHERE " +
            "address_id=#{addressId}" )
    @Options(useGeneratedKeys = true, keyColumn = "address_id", keyProperty = "addressId")
    int update(AddressEntity addressEntity);

    /**
     * 查询地址
     * @param aid 地址id
     * @return code > 0 查询成功
     * */
    @Select("SELECT * FROM address WHERE address_id = #{aid}")
    AddressEntity selectAddress(@Param(value = "aid")Integer aid);
}
