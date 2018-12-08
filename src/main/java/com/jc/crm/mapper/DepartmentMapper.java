package com.jc.crm.mapper;

import com.jc.crm.form.department.DepartmentUpdateForm;
import com.jc.crm.mapper.provider.DepartmentSqlProvider;
import com.jc.crm.model.DepartmentEntity;
import com.jc.crm.model.DepartmentUserLinkEntity;
import com.jc.crm.service.department.vo.DepartmentMemberVO;
import com.jc.crm.service.department.vo.UserDepartmentVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author asuis
 * @version: DepartmentMapper.java 18-11-23:上午9:59
 */
@Repository
public interface DepartmentMapper {

    @Insert("INSERT INTO department(pid, description, department_name, struct) VALUES(#{pid}, #{description}, #{departmentName}, #{struct})")
    int insert(DepartmentEntity department);

    @Select("SELECT * FROM department WHERE eid = #{eid}")
    List<DepartmentEntity> queryByEid(int eid);

    @Delete("DELETE FROM department WHERE department_id = #{departmentId}")
    int delete(Integer departmentId);
    /**
     * 获得用户组（同组、部门）
     * */
    @Select("SELECT `user`.uid, `user`.username, `user`.email, `user`.phone, `user`.eid, department_user_link.post, department_user_link.weight,department.department_name, department.department_id FROM `user`, department, department_user_link WHERE `user`.uid = department_user_link.user_id AND department.department_id =  department_user_link.department_id AND `user`.uid = #{uid} ORDER BY department.department_id ASC")
    UserDepartmentVO getDepartmentDetailByUser(Integer uid);
    @Select("SELECT\n" +
            " `user`.uid,\n" +
            "`user`.username,\n" +
            "`user`.email,\n" +
            "`user`.phone,\n" +
            "`user`.eid,\n" +
            "department_user_link.post,\n" +
            "department.department_name,\n" +
            "struct\n" +
            "FROM\n" +
            "`user`,\n" +
            "department,\n" +
            "department_user_link\n" +
            "WHERE `user`.uid = department_user_link.user_id\n" +
            "AND department.department_id =  department_user_link.department_id\n" +
            "AND (department.struct like CONCAT('%',#{departmentId},'%') OR (department.department_id = 1 AND department_user_link.weight <= #{weight}))" +
            "AND department.eid = #{eid} AND department_user_link.is_actived = 1" +
            "GROUP BY uid")
    List<UserDepartmentVO> getDepartmentUserByDepartmentIdAndWeight(@Param("weight") int weight,@Param("departmentId") Integer departmentId, @Param("eid") Integer eid);

    @Select("SELECT\n" +
            " `user`.uid,`user`.username" +
            "FROM\n" +
            "`user`,\n" +
            "department,\n" +
            "department_user_link\n" +
            "WHERE `user`.uid = department_user_link.user_id \n" +
            "AND department.department_id =  department_user_link.department_id\n" +
            "AND (department.struct like CONCAT('%',#{departmentId},'%') OR (department.department_id = #{departmentId}))" +
            "AND department.eid = #{eid} AND department_user_link.is_actived = 1 " +
            "ORDER BY uid ASC")
    List<DepartmentMemberVO> getDepartmentUserByDepartmentId(@Param("departmentId") Integer departmentId, @Param("eid") Integer eid);
    @Select("SELECT COUNT(`user`.uid) FROM \n" +
            "`user`,\n" +
            "department,\n" +
            "department_user_link\n" +
            "WHERE `user`.uid = department_user_link.user_id\n" +
            "AND department.department_id =  department_user_link.department_id\n" +
            "AND (department.struct like CONCAT('%',#{departmentId},'%') OR (department.department_id = #{departmentId} AND department_user_link.weight <= #{weight})) AND department.eid = #{eid} AND `user`.uid = #{uid} AND department_user_link.is_actived = 1")
    int isHaveAuth(@Param("weight") int weight,@Param("departmentId") Integer departmentId, @Param("eid") Integer eid, @Param("uid")Integer uid);

    @Select("SELECT * FROM department_user_link WHERE user_id = #{userId} AND is_actived = 1")
    DepartmentUserLinkEntity selectByUid(int userId);

    @Select("SELECT * FROM department WHERE eid = #{eid} AND struct IS NULL")
    List<DepartmentEntity> getDepartments(Integer eid);
    
    @Select("SELECT * FROM department WHERE department.eid = #{eid} AND department.struct LIKE CONCAT('%',#{departmentId},'%')")
    List<DepartmentEntity> getDepartmentByStruct(@Param("eid") Integer eid, @Param("departmentId") Integer departmentId);

    @Delete("DELETE FROM department WHERE department_id = #{departmentId} OR department.struct LIKE CONCAT('%',#{departmentId},'%')")
    int deleteAllByDepartmentId(Integer departmentId);
    @Select("SELECT * FROM department WHERE department_id = #{departmentId}")
    DepartmentEntity getDepartmentEntityById(Integer departmentId);

    @Insert("INSERT INTO department_user_link(user_id, department_id, post, weight) VALUES (#{uid}, #{departmentId}, #{post}, #{weight})")
    int insertDepartmentUser(@Param("uid") Integer uid, @Param("departmentId") Integer departmentId, @Param("post") String post, @Param("weight") Integer weight);

    @Delete("UPDATE department_user_link SET is_actived = 0 WHERE user_id = #{uid} AND department_id = #{departmentId}")
    int deleteUserDepartmnet(@Param("uid") Integer uid, @Param("departmentId") Integer departmentId);
    @Select("SELECT COUNT(department_user_link.uid) FROM department_user_link WHERE is_actived = 1 AND user_id = #{uid} AND department_id = #{departmentId}")
    int isExistDepartmentUser(Integer uid, Integer departmentId);
    @Select("SELECT * FROM department WHERE department.pid = #{pid} AND department.eid = #{eid}")
    List<DepartmentEntity> getByPid(Integer pid, Integer eid);
    @UpdateProvider(type = DepartmentSqlProvider.class, method = "updateDepartment")
    int updateDepartment(DepartmentUpdateForm departmentUpdateForm);
    @Select("SELECT department.department_name, department.department_id, department_user_link.post FROM department, department_user_link WHERE department_user_link.department_id = department.department_id AND department_user_link.user_id = #{uid} AND department.eid = #{eid} AND department_user_link.is_actived = 1")
    List<UserDepartmentVO> queryUserDepartmentVOByUid(@Param("uid") Integer uid, @Param("eid")Integer eid);
}
