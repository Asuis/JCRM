package com.jc.crm.mapper;

import com.jc.crm.model.EnterpriseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author asuis
 */
@Repository
public interface EnterpriseMapper {

    /**
     * 插入企业信息
     * */
    @Insert("INSERT INTO enterprise(enterprise_name, phone, description, avatar, address_id, admin) VALUES(#{enterpriseName},#{phone},#{description},#{avatar},#{address_id},#{admin})")
    @Options(useGeneratedKeys = true, keyProperty = "eid", keyColumn = "eid")
    int insert(EnterpriseEntity enterpriseEntity);
    /**
     * 通过id查询企业信息
     * */
    @Select("SELECT * FROM enterprise WHERE eid = #{eid}")
    EnterpriseEntity findById(int eid);

    /**
     * 判断企业是否存在
     * */
    @Select("SELECT COUNT() FROM enterprise WHERE eid = #{eid} LIMIT 1")
    int isExist(int eid);

    /**
     * 更新企业信息
     * */
    @Update("UPDATE enterprise SET enterprise_name = #{enterpriseName}, phone = #{phone}, description=#{description},address_id=#{addressId},admin=#{admin},utime=#{utime}")
    int update(EnterpriseEntity enterpriseEntity);
}
