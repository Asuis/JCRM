package com.jc.crm.mapper;

import com.jc.crm.form.account.UserUpdateForm;
import com.jc.crm.model.RoleEntity;
import com.jc.crm.model.TagEntity;
import com.jc.crm.model.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
/**
 * @author asuis
 */
@Repository
public interface UserMapper {
    @Select("SELECT * FROM user WHERE eid = #{eid} AND uid != #{uid}")
    List<UserEntity> getUserList(@Param("eid") Integer eid, @Param("uid") Integer uid);
    @Insert("INSERT INTO user(username, pass, email, phone, salt, contact_id, eid, avatar) VALUES(#{username},#{pass},#{email},#{phone},#{salt},#{contactId},#{eid},#{avatar})")
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
    @Update("UPDATE user set is_lock = #{isLock} WHERE uid = #{uid}")
    int setLock(int isLock, int uid);
    @Update("UPDATE user SET salt = #{salt} WHERE uid = #{uid}")
    int updateSalt(String salt, int uid);
    @Update("UPDATE user set eid = #{eid} WHERE uid = #{uid}")
    int bindEnterprise(int eid, int uid);
    @Select("SELECT x_auth_role.role_name FROM x_auth_role, auth_role_user_link " +
            "WHERE x_auth_role.role_id = auth_role_user_link.role_id AND " +
            "uid = #{uid}")
    List<String> getRoles(Integer uid);
    @Select("SELECT tag.tag_name, tag.tag_id FROM tag, tag_user_link WHERE tag.tag_id = tag_user_link.tag_id AND tag_user_link.uid = #{uid}")
    List<TagEntity> queryUserTags(Integer uid);
    @Insert("INSERT INTO auth_role_user_link(role_id, uid) VALUES((SELECT role_id FROM x_auth_role WHERE role_name = #{roleName}),#{uid})")
    int insertRoleForUser(@Param("uid") Integer uid, @Param("roleName") String roleName);
    @Update("UPDATE user SET avatar = #{fileName} WHERE uid = #{uid}")
    int updateUserAvatar(@Param("uid") Integer uid, @Param("fileName") String fileName);

    @Update("UPDATE user set username = #{username}, signature = #{signature}, phone = #{phone} WHERE uid = #{uid}")
    int updateSimpleUser(UserUpdateForm form);
}
