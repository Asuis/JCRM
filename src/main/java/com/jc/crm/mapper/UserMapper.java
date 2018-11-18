package com.jc.crm.mapper;

import com.jc.crm.model.RoleEntity;
import com.jc.crm.model.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<UserEntity> getUserList();
    @Insert("INSERT INTO user(username, pass, email, phone, salt, contact_id, eid) VALUES(#{username},#{pass},#{email},#{phone},#{salt},#{contactId},#{eid})")
    @Options(useGeneratedKeys = true, keyProperty = "uid", keyColumn = "uid")
    int insert(UserEntity userEntity);
    @Update("UPDATE user set username = #{username}, pass = #{pass}, email = #{email}, phone = #{phone}, salt = #{salt}, contact_id = #{contactId}, eid = #{eid}, weight = #{weight}")
    int update(UserEntity userEntity);
    @Update("UPDATE user SET last_login = #{loginTime} WHERE uid = #{uid}")
    int updateLoginTime(@Param("loginTime") Date loginTime, @Param("uid")int uid);
    @Delete("DELETE FROM user where id = #{id}")
    int delete(int id);
    @Select("SELECT * FROM user WHERE email = #{email} LIMIT 1")
    UserEntity getByEmail(String email);
    @Select("SELECT role_name FROM auth_role, auth_role_user_link, user WHERE user.uid=auth_role_user_link.uid AND auth_role.role_id=auth_role_user_link.id")
    List<String> getRoles(int uid);
    @Update("UPDATE user set is_lock = #{isLock} WHERE uid = #{uid}")
    int setLock(int isLock, int uid);
    @Update("UPDATE user SET salt = #{salt} WHERE uid = #{uid}")
    int updateSalt(String salt, int uid);
    @Update("UPDATE user set eid = #{eid} WHERE uid = #{uid}")
    int bindEnterprise(int eid, int uid);
}
