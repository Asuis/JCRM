package com.jc.crm.mapper;

import com.jc.crm.model.DepartmentEntity;
import com.jc.crm.model.UserEntity;
import com.jc.crm.service.department.vo.UserDepartmentVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author asuis
 * @version: DepartmentMapper.java 18-11-23:上午9:59
 */
@Repository
public interface DepartmentMapper {

    @Insert("INSERT INTO department VALUES()")
    int insert();

    @Select("SELECT * FROM department WHERE eid = #{eid}")
    List<DepartmentEntity> queryByEid(int eid);

    @Delete("DELETE department WHERE")
    int delete();
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
            "AND (department.struct like CONCAT(#{departmentId},'%') OR (department.department_id = 1 AND department_user_link.weight <= #{weight}))" +
            "AND eid = #{eid}\n" +
            "GROUP BY uid")
    List<UserDepartmentVO> getDepartmentUserByDepartmentIdAndWeight(@Param("weight") int weight,@Param("departmentId") Integer departmentId, @Param("eid") Integer eid);
    @Select("SELECT COUNT(`user`.uid) FROM \n" +
            "`user`,\n" +
            "department,\n" +
            "department_user_link\n" +
            "WHERE `user`.uid = department_user_link.user_id\n" +
            "AND department.department_id =  department_user_link.department_id\n" +
            "AND (department.struct like CONCAT(#{departmentId},'%') OR (department.department_id = #{departmentId} AND department_user_link.weight <= #{weight})) AND eid = #{eid} AND `user`.uid = #{uid}")
    int isHaveAuth(@Param("weight") int weight,@Param("departmentId") Integer departmentId, @Param("eid") Integer eid, @Param("uid")Integer uid);


}
