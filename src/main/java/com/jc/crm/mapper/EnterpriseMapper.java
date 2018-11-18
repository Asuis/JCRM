package com.jc.crm.mapper;

import com.jc.crm.model.EnterpriseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseMapper {
    @Insert("INSERT INTO enterprise(enterprise_name, phone, description, avatar, address_id, admin) VALUES(#{enterpriseName},#{phone},#{description},#{avatar},#{address_id},#{admin})")
    @Options(useGeneratedKeys = true, keyProperty = "eid", keyColumn = "eid")
    int insert(EnterpriseEntity enterpriseEntity);
    @Select("SELECT * FROM enterprise WHERE eid = #{eid}")
    EnterpriseEntity findById(int eid);
    @Select("SELECT COUNT() FROM enterprise WHERE eid = #{eid}")
    int isExist(int eid);
    @Update("UPDATE enterprise SET enterprise_name = #{enterpriseName}, phone = #{phone}, description=#{description},address_id=#{addressId},admin=#{admin},utime=#{utime}")
    int update(EnterpriseEntity enterpriseEntity);
}
