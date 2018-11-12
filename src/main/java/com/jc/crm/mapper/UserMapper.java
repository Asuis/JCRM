package com.jc.crm.mapper;

import com.jc.crm.model.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<UserEntity> getUserList();
    @Insert("INSERT INTO user(username, pass, email, phone, salt, contact_id, eid) VALUES(#{username},#{pass},#{email},#{phone},#{salt},#{contactId},#{eid})")
    int insert(UserEntity userEntity);
    @Update("UPDATE user(username, pass, email, phone, salt, contact_id, eid, weight) VALUES(#{username},#{pass},#{email},#{phone},#{salt},#{contactId},#{eid},#{weight}")
    int update(UserEntity userEntity);
    @Delete("DELETE FROM user where id = #{id}")
    int delete(int id);
}
